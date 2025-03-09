/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-9 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.service.impl;

import com.shijinglu.ecommerceresponsesystem.Dao.CollectionMapper;
import com.shijinglu.ecommerceresponsesystem.Dao.ProductMapper;
import com.shijinglu.ecommerceresponsesystem.common.Result;
import com.shijinglu.ecommerceresponsesystem.common.ResultCodeEnum;
import com.shijinglu.ecommerceresponsesystem.dto.AddCollectRequest;
import com.shijinglu.ecommerceresponsesystem.entity.Collection;
import com.shijinglu.ecommerceresponsesystem.entity.Product;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CollectServiceImpl {
    @Autowired
    private CollectionMapper collectionMapper;
    @Autowired
    private ProductMapper productMapper;

    //    @Autowired
//    private AuthService authService;
    public Result<List<Product>> getCollectList(Long userId) {
        // 获取收藏记录
        List<Collection> collects = collectionMapper.findByUserId(userId);

        if (collects.isEmpty()) {
            return Result.error(ResultCodeEnum.DATA_NOT_FOUND, "该用户没有收藏的商品");
        }

        // 获取商品详情
        List<Product> products = collects.stream()
                .map(collect -> productMapper.selectById(collect.getProductId()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return Result.success(products);
    }

    @Transactional
    public Result addCollect(AddCollectRequest request, HttpSession session) {
        // 检查是否已收藏
        List<Collection> exists = collectionMapper.findByUserAndProduct(
                request.getUserId(),
                request.getProductId()
        );
        if (!exists.isEmpty()) {
            return Result.error(ResultCodeEnum.DUPLICATE_DATA);
        }

        // 创建收藏记录
        Collection collection = new Collection();
        collection.setId(request.getUserId());
        collection.setUserId(request.getUserId());
        collection.setProductId(request.getProductId());
        collection.setCollectTime(LocalDateTime.now());
        int affected = collectionMapper.insert(collection);
        if (affected == 1) {
            return Result.success(ResultCodeEnum.DATABASE_SUCCESS, "ok");
        }
        return Result.error(ResultCodeEnum.DATABASE_ERROR, "error");

    }

    @Transactional
    public Result deleteCollectItem(AddCollectRequest request) {

        System.out.println(request.getUserId());
        System.out.println(request.getProductId());
        // 检查收藏记录是否存在
        List<Collection> collects = collectionMapper.findByUserAndProduct(
                request.getUserId(),
                request.getProductId()
        );
        System.out.println(collects.toString());
        if (collects.isEmpty()) {
            return Result.error(ResultCodeEnum.DATABASE_NOT_EXIST, "该商品不在收藏列表");
        }


        int affected = collectionMapper.deleteByUserAndProduct(
                request.getUserId(),
                request.getProductId()
        );

        if (affected == 1) {
            return Result.success("删除收藏成功");
        }
        return Result.error(ResultCodeEnum.DATABASE_ERROR, "删除操作未生效");
    }
}
