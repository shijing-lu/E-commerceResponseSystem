/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-8 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;

public class Product {
    @TableId(type = IdType.AUTO)
    private Integer productId;
    private Integer categoryId;
    private String productName;
    private String productTitle;
    private String productIntro;
    private BigDecimal productPrice;
    private BigDecimal productSellingPrice;
    private Integer productSales;
    private String productPicture;
    private Integer productNum;

    public Product() {
    }

    public Product(Integer productId, Integer categoryId, String productName, String productTitle, String productIntro, BigDecimal productPrice, BigDecimal productSellingPrice, Integer productSales, String productPicture, Integer productNum) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.productName = productName;
        this.productTitle = productTitle;
        this.productIntro = productIntro;
        this.productPrice = productPrice;
        this.productSellingPrice = productSellingPrice;
        this.productSales = productSales;
        this.productPicture = productPicture;
        this.productNum = productNum;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductIntro() {
        return productIntro;
    }

    public void setProductIntro(String productIntro) {
        this.productIntro = productIntro;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getProductSellingPrice() {
        return productSellingPrice;
    }

    public void setProductSellingPrice(BigDecimal productSellingPrice) {
        this.productSellingPrice = productSellingPrice;
    }

    public Integer getProductSales() {
        return productSales;
    }

    public void setProductSales(Integer productSales) {
        this.productSales = productSales;
    }

    public String getProductPicture() {
        return productPicture;
    }

    public void setProductPicture(String productPicture) {
        this.productPicture = productPicture;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }
}