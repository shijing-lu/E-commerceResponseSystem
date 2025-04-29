/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-8 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.service.impl;

import com.shijinglu.ecommerceresponsesystem.Dao.HotProductMapper;
import com.shijinglu.ecommerceresponsesystem.entity.HotProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// ProductService.java
@Service
public class HotProductServiceImpl {


    @Autowired
    private HotProductMapper hotProductMapper;


    public void insertHotProduct(HotProduct hotProduct) {
        hotProductMapper.insert(hotProduct);
    }
}