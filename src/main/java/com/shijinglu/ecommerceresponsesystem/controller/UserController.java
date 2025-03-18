/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-7 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.controller;

import com.shijinglu.ecommerceresponsesystem.Dao.UserMapper;
import com.shijinglu.ecommerceresponsesystem.common.Result;
import com.shijinglu.ecommerceresponsesystem.common.ResultCodeEnum;
import com.shijinglu.ecommerceresponsesystem.common.utils.JwtUtils;
import com.shijinglu.ecommerceresponsesystem.entity.User;
import com.shijinglu.ecommerceresponsesystem.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/AdminLogin")
    public Result AdminLogin(@RequestParam String userName, @RequestParam String password,
                             HttpSession session) {

//        String userName = userDTO.getUsername();
//        String password = userDTO.getPassword();

        System.out.print(userName + " ");
        System.out.print(password + " ");

        List<User> user = userMapper.findByUserName(userName);
        if (user.size() == 0) {
            return Result.error(ResultCodeEnum.BAD_REQUEST, "用户不存在");
        }

        if (!Objects.equals(password, user.get(0).getPassword())) {
            return Result.error(ResultCodeEnum.BAD_REQUEST, "密码错误");
        }

        String token = jwtUtils.generateToken(user.get(0).getUserId(), user.get(0).getUserEmailCode());
        user.get(0).setToken("Bearer " + token);
        return Result.success(ResultCodeEnum.SUCCESS, user.get(0));

    }

    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> params,
                        HttpSession session) {

        String userName = params.get("userName");
        String password = params.get("password");
        List<User> users = userServiceImpl.login(userName, password);

        if (users.size() != 1) {
            return Result.error(ResultCodeEnum.USER_LOGIN_FAILED, ResultCodeEnum.USER_LOGIN_FAILED.getMessage());
        }

        User user = users.get(0);
        session.setAttribute("user", user);

        return Result.success(user);
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
        List<User> users = userServiceImpl.findByUserName(params.get("username"));

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
    public Result register(@RequestBody Map<String, String> params) {
        List<User> users = userServiceImpl.findByUserName(params.get("userName"));
        if (!users.isEmpty()) {
            return Result.error(ResultCodeEnum.USER_IS_EXIST);
        }

        int result = userServiceImpl.register(params.get("userName"), params.get("password"));
        return Result.success(result);
    }
}

