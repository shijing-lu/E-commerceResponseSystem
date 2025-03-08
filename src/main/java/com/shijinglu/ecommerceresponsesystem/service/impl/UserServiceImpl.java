/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-7 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.service.impl;
import com.shijinglu.ecommerceresponsesystem.Dao.UserMapper;
import com.shijinglu.ecommerceresponsesystem.common.Result;
import com.shijinglu.ecommerceresponsesystem.common.ResultCodeEnum;
import com.shijinglu.ecommerceresponsesystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl {

    @Autowired
    private UserMapper userMapper;


    private static final Pattern USERNAME_PATTERN = Pattern.compile("^\\w{4,20}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^\\w{6,20}$");

    public Result login(String username, String password) {
        // 参数校验
        if (!validateUserInfo(username, password)) {
            return Result.error(ResultCodeEnum.BAD_REQUEST, ResultCodeEnum.USER_REGISTER_PARAM_INVALID.getMessage());
        }
        // 根据用户名和密码查询用户
        User user = userMapper.findByUsernameAndPassword(username, password);
        // 如果用户不存在，返回登录失败
        if (user == null) {
            return Result.error(ResultCodeEnum.USER_LOGIN_FAILED, ResultCodeEnum.USER_LOGIN_FAILED.getMessage());
        }
        // 返回登录成功
        return Result.success(ResultCodeEnum.SUCCESS, user);
    }
    public Result register(String username, String password) {
        if (!validateUserInfo(username, password)) {
            return Result.error(ResultCodeEnum.BAD_REQUEST, ResultCodeEnum.USER_REGISTER_PARAM_INVALID.getMessage());
        }
        if (userMapper.existsByUsername(username)) {
            return Result.error(ResultCodeEnum.USER_PHONE_EXIST);
        }
        User newUser = new User();
        newUser.setUserName(username);
        newUser.setPassword(password);
        userMapper.insert(newUser);
        return Result.success(ResultCodeEnum.SUCCESS);

    }

    private boolean validateUserInfo(String username, String password) {
        return validateUsername(username) && validatePassword(password);
    }

    private boolean validateUsername(String username) {
        return true;
//        return USERNAME_PATTERN.matcher(username).matches();
    }
    private boolean validatePassword(String password) {
        return true;

//        return PASSWORD_PATTERN.matcher(password).matches();
    }
}