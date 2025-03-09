/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-9 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.controller;

import com.shijinglu.ecommerceresponsesystem.common.Result;
import com.shijinglu.ecommerceresponsesystem.dto.ShoppingCartDTO;
import com.shijinglu.ecommerceresponsesystem.dto.UpdateCartRequest;
import com.shijinglu.ecommerceresponsesystem.service.impl.ShoppingCartServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

// ShoppingCartController.java
@RestController
@RequestMapping("/user/shoppingCart")
@RequiredArgsConstructor
public class ShoppingCartController {
    @Autowired
    private ShoppingCartServiceImpl shoppingCartServiceImpl;

    @PostMapping("/getShoppingCart")
    public Result<List<ShoppingCartDTO>> getCart(@RequestBody Map<String, Long> req) {
        Long userId = req.get("user_id");
        // 此处添加登录校验逻辑
        return shoppingCartServiceImpl.getCart(userId);
    }

    @PostMapping("/addShoppingCart")
    public Result<?> addCart(@RequestBody Map<String, Integer> req) {
        Integer userId = req.get("user_id");
        Integer productId = req.get("product_id");
        // 登录校验
        return shoppingCartServiceImpl.addCart(userId, productId);
    }

    @PostMapping("/updateShoppingCart")
    public Result updateCart(@RequestBody UpdateCartRequest request) {
        // 此处添加登录校验逻辑（例如通过拦截器实现）
        return shoppingCartServiceImpl.updateCartItem(request);
    }

    //
    @PostMapping("/deleteShoppingCart")
    public Result<?> deleteCart(@RequestBody UpdateCartRequest request,
                                HttpSession session) {
        return shoppingCartServiceImpl.deleteCartItem(request, session);
    }
}
