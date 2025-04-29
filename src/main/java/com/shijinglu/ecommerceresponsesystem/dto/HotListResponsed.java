/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-4-6 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.dto;

import java.math.BigDecimal;

public class HotListResponsed {
    private Integer id;
    private Integer productId;
    private String productName;
    private BigDecimal productSellingPrice;
    private BigDecimal productPrice;
    private Integer salesVolume;
    private String productPicture;
    private BigDecimal discountRate;

    public HotListResponsed(Integer id, Integer productId, String productName, BigDecimal productSellingPrice, BigDecimal productPrice, Integer salesVolume, String mainImage, BigDecimal discountRate) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.productSellingPrice = productSellingPrice;
        this.productPrice = productPrice;
        this.salesVolume = salesVolume;
        this.productPicture = mainImage;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductSellingPrice() {
        return productSellingPrice;
    }

    public void setProductSellingPrice(BigDecimal productSellingPrice) {
        this.productSellingPrice = productSellingPrice;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public String getProductPicture() {
        return productPicture;
    }

    public void setProductPicture(String productPicture) {
        this.productPicture = productPicture;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }
}
