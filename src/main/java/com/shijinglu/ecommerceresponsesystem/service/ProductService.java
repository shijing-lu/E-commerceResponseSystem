/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-8 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shijinglu.ecommerceresponsesystem.entity.Category;
import com.shijinglu.ecommerceresponsesystem.entity.Product;

import java.util.List;

public interface ProductService {

    public List<Category> getCategories();
    public Page<Product> getAllProducts(int currentPage, int pageSize);
    public List<Product> getPromoProducts(String categoryName);
}
