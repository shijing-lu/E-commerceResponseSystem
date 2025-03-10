/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-10 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.dto;

import java.util.List;

public class OrderRequest {
    private Integer userId;
    private List<ProductDTO> products;

    // Getters and Setters


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
