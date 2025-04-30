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
    private BigDecimal productSellingPrice;
    private Integer salesCount;
    private BigDecimal discountRate;
    private String productName;
    private String productPicture;
    private BigDecimal productPrice;

    public HotProduct(Integer productId, Integer nums, BigDecimal productSellingPrice
            , Integer saleCount, String productName, String productPicture, BigDecimal productPrice) {
        this.productId = productId;
        this.nums = nums;
        this.productSellingPrice = productSellingPrice;
        this.salesCount = saleCount;
        this.productName = productName;
        this.productPicture = productPicture;
        this.productPrice = productPrice;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(Integer salesCount) {
        this.salesCount = salesCount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPicture() {
        return productPicture;
    }

    public void setProductPicture(String productPicture) {
        this.productPicture = productPicture;
    }

    public HotProduct() {
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

    public BigDecimal getProductSellingPrice() {
        return productSellingPrice;
    }

    public void setProductSellingPrice(BigDecimal productSellingPrice) {
        this.productSellingPrice = productSellingPrice;
    }

    public Integer getSaleCount() {
        return salesCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.salesCount = saleCount;
    }


}
