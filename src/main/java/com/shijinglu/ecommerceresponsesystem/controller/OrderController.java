/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-10 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.controller;

import com.shijinglu.ecommerceresponsesystem.common.Result;
import com.shijinglu.ecommerceresponsesystem.dto.OrderRequest;
import com.shijinglu.ecommerceresponsesystem.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user/order")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @PostMapping("/addOrder")
    public Result addOrder(@RequestBody OrderRequest request) {
        // 登录校验（示例伪代码）
        return orderServiceImpl.createOrder(request.getUserId(), request.getProducts());
    }

    @PostMapping("/getOrder")
    public Result getOrder(@RequestBody Map<String, String> request) {
        String userId = request.get("userId");
        return orderServiceImpl.getOrders(userId);
    }


}
