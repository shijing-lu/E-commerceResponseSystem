/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-7 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.controller;

import com.shijinglu.ecommerceresponsesystem.common.Result;
import com.shijinglu.ecommerceresponsesystem.dto.UserDTO;
import com.shijinglu.ecommerceresponsesystem.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDto) {
        return userService.login(userDto.getUsername(), userDto.getPassword());
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDto) {
        return userService.register(userDto.getUsername(), userDto.getPassword());
    }
}

