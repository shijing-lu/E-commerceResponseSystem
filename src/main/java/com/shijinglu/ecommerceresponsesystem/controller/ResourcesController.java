/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-8 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.controller;

import com.shijinglu.ecommerceresponsesystem.common.Result;
import com.shijinglu.ecommerceresponsesystem.common.ResultCodeEnum;
import com.shijinglu.ecommerceresponsesystem.entity.Carousel;
import com.shijinglu.ecommerceresponsesystem.service.impl.ResourcesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resources")
public class ResourcesController {
@Autowired
    private ResourcesServiceImpl resourcesServiceimpl;
    @PostMapping("/carousel")
    public Result carousel(){
        List<Carousel> carousels= resourcesServiceimpl.getResource();
        if (carousels.size()>0){
            return Result.success(carousels);
        }else{
            return Result.error(ResultCodeEnum.BAD_REQUEST);
        }
    }
}
