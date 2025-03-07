/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-7 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.Dao;

import com.shijinglu.ecommerceresponsesystem.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users WHERE userName = #{username} AND password = #{password}")
    User findByUsernameAndPassword(@Param("username") String username,
                                   @Param("password") String password);

    @Select("SELECT * FROM users WHERE userName = #{username}")
    User findByUsername(String username);

    @Select("SELECT COUNT(*) FROM users WHERE userName = #{username}")
    boolean existsByUsername(String username);

    @Insert("INSERT INTO users (userName, password, openId) VALUES (#{userName}, #{password}, #{openId})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);

    @Select("SELECT * FROM users WHERE openId = #{openId}")
    User findByOpenId(String openId);
}