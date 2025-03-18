/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-7 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.Dao;

import com.shijinglu.ecommerceresponsesystem.entity.User;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE user_name = #{userName} AND password = #{password}")
    List<User> login(@Param("userName") String userName, @Param("password") String password);

    @Select("SELECT * FROM users WHERE user_name = #{userName}")
    List<User> findByUserName(String userName);

    @Insert("INSERT INTO users(user_name, password) VALUES(#{userName}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int register(User user);

    int deductBalance(@Param("userId") Integer userId, @Param("amount") BigDecimal amount);


}
