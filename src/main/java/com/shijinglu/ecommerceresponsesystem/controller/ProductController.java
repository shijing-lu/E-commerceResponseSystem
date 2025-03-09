/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-8 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shijinglu.ecommerceresponsesystem.common.Result;
import com.shijinglu.ecommerceresponsesystem.common.ResultCodeEnum;
import com.shijinglu.ecommerceresponsesystem.dto.CategoryProductDTO;
import com.shijinglu.ecommerceresponsesystem.dto.HotProductRequest;
import com.shijinglu.ecommerceresponsesystem.dto.PromoProductRequest;
import com.shijinglu.ecommerceresponsesystem.entity.Category;
import com.shijinglu.ecommerceresponsesystem.entity.Product;
import com.shijinglu.ecommerceresponsesystem.entity.ProductPicture;
import com.shijinglu.ecommerceresponsesystem.service.impl.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// ProductController.java
@Slf4j
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
    public Result getPromoProduct(@RequestBody PromoProductRequest promoProductRequest) {
        List<Product> products = productServiceImpl.getPromoProducts(promoProductRequest.getCategoryName());
        return Result.success(ResultCodeEnum.SUCCESS, products);
    }

    //?
    @PostMapping("/getHotProduct")
    public Result getHotProduct(@RequestBody HotProductRequest request) {
        List<Product> products = productServiceImpl.getHotProducts(request.getCategoryName());
        return Result.success(ResultCodeEnum.SUCCESS, products);
    }

    //?
    @PostMapping("/getProductByCategory")
    public Result getProductByCategory(@RequestBody CategoryProductDTO categoryProductDTO) {
        System.out.println(categoryProductDTO.getCategoryID().toString());
        Page<Product> page = productServiceImpl.getAllProducts(
                categoryProductDTO.getCategoryID(), categoryProductDTO.getCurrentPage(), categoryProductDTO.getPageSize()
        );
        return Result.success(ResultCodeEnum.SUCCESS, page);
    }

    //?
    @PostMapping("/getProductBySearch")
    public Result getProductBySearch() {
        List<Category> categories = productServiceImpl.getCategories();
        return Result.success(ResultCodeEnum.SUCCESS, categories);
    }

    //？
    @GetMapping("/getDetails")
    public Result getDetails(@RequestParam("productID") Integer productID) {
        return Result.success(ResultCodeEnum.SUCCESS, productServiceImpl.getProductById(productID));
    }

    //？
    @GetMapping("/getDetailsPicture")
    public Result getDetailsPicture(@RequestParam("productID") Long productID) {
        List<ProductPicture> pictures = productServiceImpl.getDetailsPicture(productID);
        return Result.success(ResultCodeEnum.SUCCESS, pictures);
    }


    @PostMapping("/getAllProduct")
    public Result getAllProducts(@RequestBody CategoryProductDTO categoryProductDTO) {

        Page<Product> page = productServiceImpl.getAllProducts(
                categoryProductDTO.getCategoryID(), categoryProductDTO.getCurrentPage(), categoryProductDTO.getPageSize()
        );
        return Result.success(ResultCodeEnum.SUCCESS, page);
    }


}
