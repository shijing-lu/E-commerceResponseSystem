/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-9 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.service.impl;

import com.shijinglu.ecommerceresponsesystem.Dao.ProductMapper;
import com.shijinglu.ecommerceresponsesystem.Dao.ShoppingCartMapper;
import com.shijinglu.ecommerceresponsesystem.common.Result;
import com.shijinglu.ecommerceresponsesystem.common.ResultCodeEnum;
import com.shijinglu.ecommerceresponsesystem.dto.ShoppingCartDTO;
import com.shijinglu.ecommerceresponsesystem.dto.UpdateCartRequest;
import com.shijinglu.ecommerceresponsesystem.entity.Product;
import com.shijinglu.ecommerceresponsesystem.entity.ShoppingCart;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// ShoppingCartService.java
@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl {
    @Autowired
    private ShoppingCartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;


    public Result<List<ShoppingCartDTO>> getCart(Long userId) {
        List<ShoppingCart> carts = cartMapper.findByUserId(userId);
        List<ShoppingCartDTO> dtos = carts.stream()
                .map(cart -> {
                    Product product = productMapper.selectById(cart.getProductId());
                    return new ShoppingCartDTO(
                            cart.getId(),
                            cart.getProductId(),
                            product.getProductName(),
                            product.getProductPicture(),
                            product.getProductPrice(),
                            cart.getNum(),
                            product.getProductNum(),
                            false
                    );
                }).collect(Collectors.toList());
        return Result.success(dtos);
    }

    @Transactional
    public Result addCart(Integer userId, Integer productId) {
        ShoppingCart existing = cartMapper.findByUserIdAndProductId(userId, productId);
        ShoppingCart newCart = new ShoppingCart();
        newCart.setUserId(userId);
        newCart.setProductId(productId);
        if (existing != null) {
            Product product = productMapper.selectById(productId);
            int max = product.getProductNum() / 2;

            if (existing.getNum() + 1 > max) {
                return Result.error(ResultCodeEnum.INVALID_REQUEST);
            }
            cartMapper.updateNum(existing.getNum() + 1, userId, productId);
            return Result.success(ResultCodeEnum.GOODS_IS_EXIST, getCartData(newCart));
        } else {
            cartMapper.insert(newCart);
        }
        return Result.success(ResultCodeEnum.SUCCESS, getCartData(newCart));

    }

    private ShoppingCartDTO getCartData(ShoppingCart cart) {
        Product product = productMapper.selectById(cart.getProductId());
        ShoppingCart shoppingCart = cartMapper.findByUserIdAndProductId(cart.getUserId(), cart.getProductId());
        return new ShoppingCartDTO(shoppingCart.getId(), shoppingCart.getProductId(), product.getProductName(),
                product.getProductPicture(), product.getProductPrice(), shoppingCart.getNum(),
                product.getProductNum() / 2, false);
    }

    public Result updateCartItem(UpdateCartRequest request) {
        // 参数校验
        if (request.getNum() < 1) {
            return Result.error(ResultCodeEnum.ORDER_CREATE_FAIL, "数量不合法");
        }

        // 查询购物车项
        ShoppingCart carts = cartMapper.findByUserIdAndProductId(
                request.getUser_id(),
                request.getProduct_id()
        );
        // 检查数量变化
        if (carts.getNum().equals(request.getNum())) {
            return Result.error(ResultCodeEnum.ORDER_CREATE_FAIL, "数量没有变化");
        }

        // 获取商品信息
        Product product = productMapper.selectById(request.getProduct_id());
        int maxNum = product.getProductNum() / 2;

        // 检查限购数量
        if (request.getNum() > maxNum) {
            return Result.error(ResultCodeEnum.ORDER_CREATE_FAIL,
                    "数量超过限购 " + maxNum);
        }


        // 执行更新
        int affected = cartMapper.updateNum(
                request.getNum(),
                request.getUser_id(),
                request.getProduct_id()
        );

        if (affected != 1) {
            try {
                throw new RuntimeException("更新购物车失败");
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            } finally {
                return Result.error(ResultCodeEnum.ORDER_CREATE_FAIL, "更新购物车失败");
            }
        }
        return Result.success("购物车数量更新成功");
    }

    public Result<?> deleteCartItem(UpdateCartRequest request, HttpSession session) {
        // 登录校验

        // 查询购物车项
        ShoppingCart carts = cartMapper.findByUserIdAndProductId(
                request.getUser_id(),
                request.getProduct_id()
        );
        if (carts == null) {
            return Result.error(ResultCodeEnum.NOT_FOUND, "该商品不在购物车");
        }
        int affected = cartMapper.deleteByUserAndProduct(
                request.getUser_id(),
                request.getProduct_id()
        );
        if (affected == 1) {
            return Result.success("删除购物车成功");
        }
        return Result.error(ResultCodeEnum.BAD_REQUEST, "删除操作未生效");

    }
}
