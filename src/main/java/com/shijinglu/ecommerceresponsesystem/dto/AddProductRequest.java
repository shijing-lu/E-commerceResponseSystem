/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-9 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddProductRequest {
    private Integer goods_cat;
    private String goods_introduce;
    private String goods_name;
    private Integer goods_number;
    private BigDecimal goods_price;
    private String pics;

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public Integer getGoods_cat() {
        return goods_cat;
    }

    public void setGoods_cat(Integer goods_cat) {
        this.goods_cat = goods_cat;
    }

    public String getGoods_introduce() {
        return goods_introduce;
    }

    public void setGoods_introduce(String goods_introduce) {
        this.goods_introduce = goods_introduce;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public Integer getGoods_number() {
        return goods_number;
    }

    public void setGoods_number(Integer goods_number) {
        this.goods_number = goods_number;
    }

    public BigDecimal getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(BigDecimal goods_price) {
        this.goods_price = goods_price;
    }

    @Override
    public String toString() {
        return "AddProductRequest{" +
                "goods_cat=" + goods_cat +
                ", goods_introduce='" + goods_introduce + '\'' +
                ", goods_name='" + goods_name + '\'' +
                ", goods_number=" + goods_number +
                ", goods_price=" + goods_price +
                '}';
    }
}