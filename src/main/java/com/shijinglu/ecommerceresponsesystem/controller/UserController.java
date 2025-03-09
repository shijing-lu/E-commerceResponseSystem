/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-7 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.controller;

import com.shijinglu.ecommerceresponsesystem.entity.User;
import com.shijinglu.ecommerceresponsesystem.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> params,
                                                     HttpSession session) {

        String userName = params.get("userName");
        String password = params.get("password");
        System.out.println(userName);
        System.out.println(password);
        List<User> users = userServiceImpl.login(userName, password);
        if (users.size() != 1) {
            return ResponseEntity.ok(Map.of(
                    "code", "004",
                    "msg", "用户名或密码错误"
            ));
        }

        User user = users.get(0);
        session.setAttribute("user", user);

        return ResponseEntity.ok(Map.of(
                "code", "001",
                "user", Map.of(
                        "userId", user.getUserId(),
                        "userName", user.getUserName()
                ),
                "msg", "登录成功"
        ));
    }

//    @PostMapping("/miniProgramLogin")
//    public ResponseEntity<Map<String, Object>> miniProgramLogin(@RequestBody Map<String, String> params,
//                                                                HttpSession session) {
//        Map<String, Object> wechatRes = userServiceImpl.miniProgramLogin(params.get("code"));
//
//        List<User> users = userServiceImpl.login((String) wechatRes.get("openid"),
//                (String) wechatRes.get("openid"));
//
//        if (users.isEmpty()) {
//            return ResponseEntity.ok(Map.of(
//                    "code", "004",
//                    "msg", "登录失败"
//            ));
//        }
//
//        User user = users.get(0);
//        session.setAttribute("user", user);
//
//        return ResponseEntity.ok(Map.of(
//                "code", "001",
//                "userId", user.getUserId(),
//                "msg", "登录成功"
//        ));
//    }

    @PostMapping("/findUserName")
    public ResponseEntity<Map<String, Object>> findUserName(@RequestBody Map<String, String> params) {
        List<User> users = userServiceImpl.findByUserName(params.get("userName"));

        if (users.isEmpty()) {
            return ResponseEntity.ok(Map.of(
                    "code", "001",
                    "msg", "用户名不存在，可以注册"
            ));
        }

        return ResponseEntity.ok(Map.of(
                "code", "004",
                "msg", "用户名已经存在，不能注册"
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, String> params) {
        List<User> users = userServiceImpl.findByUserName(params.get("userName"));
        if (!users.isEmpty()) {
            return ResponseEntity.ok(Map.of(
                    "code", "004",
                    "msg", "用户名已经存在，不能注册"
            ));
        }

        int result = userServiceImpl.register(params.get("userName"), params.get("password"));
        return ResponseEntity.ok(Map.of(
                "code", result == 1 ? "001" : "500",
                "msg", result == 1 ? "注册成功" : "注册失败"
        ));
    }
}

