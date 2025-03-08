/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-8 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shijinglu.ecommerceresponsesystem.common.Result;
import com.shijinglu.ecommerceresponsesystem.common.ResultCodeEnum;
import com.shijinglu.ecommerceresponsesystem.entity.Category;
import com.shijinglu.ecommerceresponsesystem.entity.Product;
import com.shijinglu.ecommerceresponsesystem.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

// ProductController.java
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductServiceImpl productServiceImpl;

    @PostMapping("/getCategory")
    public Result getCategories() {
        List<Category> categories = productServiceImpl.getCategories();
        return Result.success(ResultCodeEnum.SUCCESS, categories);
    }
    @PostMapping("/getPromoProduct")
    public Result getPromoProduct() {
        List<Category> categories = productServiceImpl.getCategories();
        return Result.success(ResultCodeEnum.SUCCESS, categories);
    }

//?
    @PostMapping("/getHotProduct")
    public Result getHotProduct() {
        List<Category> categories = productServiceImpl.getCategories();
        return Result.success(ResultCodeEnum.SUCCESS, categories);
    }
//?
    @PostMapping("/getProductByCategory")
    public Result getProductByCategory() {
        List<Category> categories = productServiceImpl.getCategories();
        return Result.success(ResultCodeEnum.SUCCESS, categories);
    }
//?
    @PostMapping("/getProductBySearch")
    public Result getProductBySearch() {
        List<Category> categories = productServiceImpl.getCategories();
        return Result.success(ResultCodeEnum.SUCCESS, categories);
    }
//？
    @PostMapping("/getDetails")
    public Result getDetails() {
        List<Category> categories = productServiceImpl.getCategories();
        return Result.success(ResultCodeEnum.SUCCESS, categories);
    }
//？
    @PostMapping("/getDetailsPicture")
    public Result getDetailsPicture() {
        List<Category> categories = productServiceImpl.getCategories();
        return Result.success(ResultCodeEnum.SUCCESS, categories);
    }


    @PostMapping("/getAllProduct")
    public Result getAllProducts(@RequestBody Map<String, Integer> params) {
        Page<Product> page = productServiceImpl.getAllProducts(
                params.get("currentPage"),
                params.get("pageSize")
        );
        return Result.success(ResultCodeEnum.SUCCESS, page);
    }


}
