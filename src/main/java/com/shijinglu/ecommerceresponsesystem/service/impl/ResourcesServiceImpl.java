/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-8 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.service.impl;

import com.shijinglu.ecommerceresponsesystem.Dao.ResourcesMapper;
import com.shijinglu.ecommerceresponsesystem.entity.Carousel;
import com.shijinglu.ecommerceresponsesystem.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResourcesServiceImpl implements ResourceService {

    @Autowired
    private ResourcesMapper carouselMapper;
    @Override
    public List<Carousel> getResource() {
        List<Carousel> carousels = carouselMapper.selectCarousels();
        return carousels;
    }
}
