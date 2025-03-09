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

//    @Value("${wechat.appid}")
//    private String appid;
//
//    @Value("${wechat.secret}")
//    private String secret;

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

//    public Map<String, Object> miniProgramLogin(String code) {
//        String apiUrl = String.format("https://api.weixin.qq.com/sns/jscode2session?" +
//                        "appid=%s&secret=%s&js_code=%s&grant_type=authorization_code",
//                appid, secret, code);
//
//        // 使用 RestTemplate 调用微信 API
//        RestTemplate restTemplate = new RestTemplate();
//        Map<String, Object> response = restTemplate.getForObject(apiUrl, Map.class);
//
//        String openid = (String) response.get("openid");
//        String sessionKey = (String) response.get("session_key");
//
//        List<User> users = userMapper.findByUserName(openid);
//        if (users.isEmpty()) {
//            User user = new User();
//            user.setUserName(openid);
//            user.setPassword(openid);
//            user.setOpenId(openid);
//            user.setSessionKey(sessionKey);
//            userMapper.register(user);
//        }
//
//        // 返回处理结果
//        Map<String, Object> result = new HashMap<>();
//        result.put("openid", openid);
//        result.put("sessionKey", sessionKey);
//        return result;
//    }
}