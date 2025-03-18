/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-7 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.service.impl;

import com.shijinglu.ecommerceresponsesystem.Dao.UserMapper;
import com.shijinglu.ecommerceresponsesystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {
    @Autowired
    private UserMapper userMapper;

    public List<User> login(String userName, String password) {
        return userMapper.login(userName, password);
    }

    public List<User> findByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }

    public int register(String userName, String password) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        return userMapper.register(user);
    }

}