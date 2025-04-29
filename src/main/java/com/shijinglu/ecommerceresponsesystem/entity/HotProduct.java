/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-4-6 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.entity;

import java.math.BigDecimal;

public class HotProduct {
    private Integer id;
    private Integer productId;
    private Integer nums;
    private BigDecimal sellingPrice;
    private Integer salesCount;
    private BigDecimal discountRate;

    public HotProduct(Integer productId, Integer nums, BigDecimal sellingPrice, Integer saleCount) {
        this.productId = productId;
        this.nums = nums;
        this.sellingPrice = sellingPrice;
        this.salesCount = saleCount;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getSaleCount() {
        return salesCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.salesCount = saleCount;
    }


}
