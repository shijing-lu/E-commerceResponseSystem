/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-6 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.controller;

import com.shijinglu.ecommerceresponsesystem.entity.UserInfo;
import com.shijinglu.ecommerceresponsesystem.service.UserInfoService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
public class UserController {
    @Resource
    private UserInfoService userInfoService;
@GetMapping("info")
    public UserInfo getUserinfo(){
        return userInfoService.getById(1L);
    }
    // 用户控制器类
}
