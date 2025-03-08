/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-8 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shijinglu.ecommerceresponsesystem.Dao.ProductMapper;
import com.shijinglu.ecommerceresponsesystem.entity.Category;
import com.shijinglu.ecommerceresponsesystem.entity.Product;
import com.shijinglu.ecommerceresponsesystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// ProductService.java
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    public List<Category> getCategories() {
        return productMapper.selectCategories();
    }

    public Page<Product> getAllProducts(int currentPage, int pageSize) {
        Page<Product> page = new Page<>(currentPage, pageSize);
        return productMapper.selectPage(page, null);
    }

    public List<Product> getPromoProducts(String categoryName) {
        Integer categoryId = productMapper.getCategoryIdByName(categoryName);
        return productMapper.selectPromoProducts(categoryId);
    }

}