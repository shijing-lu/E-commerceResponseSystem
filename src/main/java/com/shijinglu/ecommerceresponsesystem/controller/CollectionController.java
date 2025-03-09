/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-9 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.controller;

import com.shijinglu.ecommerceresponsesystem.common.Result;
import com.shijinglu.ecommerceresponsesystem.dto.AddCollectRequest;
import com.shijinglu.ecommerceresponsesystem.service.impl.CollectServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user/collect")
public class CollectionController {
    @Autowired
    private CollectServiceImpl collectServiceImpl;

    @PostMapping("/addCollect")
    public Result<?> addCollect(@RequestBody AddCollectRequest request,
                                HttpSession session) {
        System.out.println(request.getUserId() + request.getProductId());
        return collectServiceImpl.addCollect(request, session);
    }

    @PostMapping("/getCollect")
    public Result<?> getCollect(@RequestBody Map<String, Long> request) {
        Long userId = request.get("user_id");
        // 这里应添加登录校验逻辑
        return collectServiceImpl.getCollectList(userId);
    }

    @PostMapping("/deleteCollect")
    public Result deleteCollect(@RequestBody AddCollectRequest request) {
        // 此处应添加登录校验逻辑（通过拦截器实现）
        return collectServiceImpl.deleteCollectItem(request);
    }
}