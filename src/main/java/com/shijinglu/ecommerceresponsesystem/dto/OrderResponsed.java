/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-10 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.dto;

import java.math.BigDecimal;

public class OrderResponsed {
    private Integer id;         // 自增主键
    private String orderId; // 订单编号（用户ID+时间戳）
    private Integer userId;
    private Integer productId;
    private Integer num;
    private BigDecimal price;
    private Long createTime;
    private String productName;
    private String productPicture;

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

    public OrderResponsed(Integer id, String orderId, Integer userId, Integer productId, Integer num, BigDecimal price, Long timestamp) {

        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.productId = productId;
        this.num = num;
        this.price = price;
        this.createTime = timestamp;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
