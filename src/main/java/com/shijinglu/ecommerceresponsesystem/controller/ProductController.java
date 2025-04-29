/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-8 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shijinglu.ecommerceresponsesystem.Dao.ProductMapper;
import com.shijinglu.ecommerceresponsesystem.common.Result;
import com.shijinglu.ecommerceresponsesystem.common.ResultCodeEnum;
import com.shijinglu.ecommerceresponsesystem.dto.AddProductRequest;
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
    @Autowired
    private ProductMapper productMapper;

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

    /*后台添加商品*/
    @PostMapping("/Products")
    public Result addProduct(@RequestBody AddProductRequest addProductRequest) {
        System.out.println(addProductRequest.toString());
        Product product = new Product();
        product.setCategoryId(addProductRequest.getGoods_cat());
        product.setProductName(addProductRequest.getGoods_name());
        product.setProductPrice(addProductRequest.getGoods_price());
        product.setProductNum(addProductRequest.getGoods_number());
        product.setProductIntro(addProductRequest.getGoods_introduce());
        product.setProductPicture(addProductRequest.getPics());
        productServiceImpl.insertProduct(product);
        return Result.success(ResultCodeEnum.SUCCESS);
    }

    //？
    @GetMapping("/getDetails")
    public Result getDetails(@RequestParam("productID") Integer productID) {
        return Result.success(ResultCodeEnum.SUCCESS, productServiceImpl.getProductById(productID));
    }

    @GetMapping("/hotlist")
    public Result hostList() {
        return Result.success(ResultCodeEnum.SUCCESS, productServiceImpl.getHostList());
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

    /*后台管理系统获取数据分页信息*/
    @GetMapping("/getProductList")
    public Result getProductList(@RequestParam String query, @RequestParam int pagenum, @RequestParam int pagesize) {
        System.out.println("ok");
        Page<Product> page = productServiceImpl.getAllProducts(null, pagenum, pagesize);
        return Result.success(ResultCodeEnum.SUCCESS, page);
    }

    /*更新后台商品数据*/
    @PutMapping("/products")
    public Result updateProduct(@RequestBody Product product) {
        productMapper.updateById(product);
        return Result.success(ResultCodeEnum.SUCCESS);
    }

    @DeleteMapping("/{productId}")
    public Result deleteProduct(@PathVariable("productId") Integer productId) {
        productMapper.deleteById(productId);
        return Result.success(ResultCodeEnum.SUCCESS);
    }


}
