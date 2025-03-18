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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// ProductController.java
@Slf4j
@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
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
        Page<Product> page = productServiceImpl.getAllProducts(
                categoryProductDTO.getCategoryID(), categoryProductDTO.getCurrentPage(), categoryProductDTO.getPageSize()
        );
        return Result.success(ResultCodeEnum.SUCCESS, page);
    }

    @PostMapping("/getProductByCategoryAdmin")
    public Result getProductByCategory(@RequestParam(required = false) List<Integer> categoryID, @RequestParam int currentPag, @RequestParam int pageSize) {
        if (categoryID == null) {
            categoryID = new ArrayList<>();
        }
        Page<Product> page = productServiceImpl.getAllProducts(
                categoryID, currentPag, pageSize
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

    @PostMapping(
            value = "/insertTbItem"
    )
    // 创建商品
    public Result createProduct(
            @RequestParam("title") String title,
            // 商品名称
            @RequestParam("categoryId") Integer categoryId,
            // 商品分类ID
            @RequestParam("sellPoint") String sellPoint,
            // 商品卖点
            @RequestParam("price") BigDecimal price,
            // 商品价格
            @RequestParam("num") Integer num,
            // 商品数量
            @RequestParam(value = "desc", required = false) String description
    ) {
        // 创建商品对象
        Product product = new Product(null, categoryId, title, title, sellPoint, price, price, 0, "http://localhost:8080/images/1.jpg", num);
        // 调用商品服务层插入商品
        productServiceImpl.insertProduct(product);
        // 返回结果
        return Result.success(ResultCodeEnum.SUCCESS, product);
    }

}
