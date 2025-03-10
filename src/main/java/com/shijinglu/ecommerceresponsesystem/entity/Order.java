/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-10 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.entity;

import java.math.BigDecimal;

public class Order {
    private Integer id;         // 自增主键
    private String orderId; // 订单编号（用户ID+时间戳）
    private Integer userId;
    private Integer productId;
    private Integer num;
    private BigDecimal price;
    private Long createTime;

    public Order(Integer id, String orderId, Integer userId, Integer productId, Integer num, BigDecimal price, Long timestamp) {

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
