/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-9 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.dto;

import java.math.BigDecimal;

// ShoppingCartDTO.java

public class ShoppingCartDTO {
    private Integer id;
    private Integer productId;
    private String productName;
    private String productImg;
    private BigDecimal price;
    private Integer num;
    private Integer maxNum;
    private Boolean checked;

    public ShoppingCartDTO(Integer id, Integer productId, String ProductName, String productImg
            , BigDecimal price, Integer num, Integer maxNum, Boolean checked) {
        this.id = id;
        this.productId = productId;
        this.productName = ProductName;
        this.productImg = productImg;
        this.price = price;
        this.num = num;
        this.maxNum = maxNum;
        this.checked = checked;
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

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }


}
