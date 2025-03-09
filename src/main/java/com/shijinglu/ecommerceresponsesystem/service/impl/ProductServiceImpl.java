/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-8 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shijinglu.ecommerceresponsesystem.Dao.CategoryMapper;
import com.shijinglu.ecommerceresponsesystem.Dao.ProductMapper;
import com.shijinglu.ecommerceresponsesystem.Dao.ProductPictureMapper;
import com.shijinglu.ecommerceresponsesystem.entity.Category;
import com.shijinglu.ecommerceresponsesystem.entity.Product;
import com.shijinglu.ecommerceresponsesystem.entity.ProductPicture;
import com.shijinglu.ecommerceresponsesystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// ProductService.java
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductPictureMapper productPictureMapper;

    public List<Category> getCategories() {
        return productMapper.selectCategories();
    }

    public Page<Product> getAllProducts(List<Integer> category, int currentPage, int pageSize) {
        // 创建 QueryWrapper
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();

        // 添加条件：分类 ID 在 category 数组中
        if (category != null && category.size() > 0) {
            queryWrapper.in("category_id", category);
        }
        Page<Product> page = new Page<>(currentPage, pageSize);
        return productMapper.selectPage(page, queryWrapper);
    }

    public List<Product> getPromoProducts(String categoryName) {
        Long categoryId = categoryMapper.getCategoryIdByName(categoryName);
        return productMapper.getPromoProductsByCategoryId(categoryId);
    }

    @Override
    public Product getProductById(int id) {
        return productMapper.selectById(id);
    }

    @Override
    public List<Product> getPromoProductsByName(String categoryName) {
        Category category = categoryMapper.selectCategoryByName(categoryName);
        return productMapper.selectPromoProducts(category.getCategoryId());
    }

    public List<ProductPicture> getDetailsPicture(Long productId) {
        return productPictureMapper.findByProductId(productId);
    }

}