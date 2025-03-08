/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-8 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.entity;

public class Carousel {
    private int carouselId;
    private String imgPath;
    private  String describes;

    public int getCarouselId() {
        return carouselId;
    }

    public void setCarouselId(int carouselId) {
        this.carouselId = carouselId;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }
}
