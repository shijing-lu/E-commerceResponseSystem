/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-9 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.Dao;

import com.shijinglu.ecommerceresponsesystem.entity.Collection;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CollectionMapper {
    @Select("SELECT * FROM collect WHERE user_id = #{userId} AND product_id = #{productId}")
    List<Collection> findByUserAndProduct(@Param("userId") Integer userId,
                                          @Param("productId") Integer productId);

    @Select("SELECT * FROM collect WHERE user_id = #{userId}")
    List<Collection> findByUserId(Long userId);

    @Insert("INSERT INTO collect(user_id, product_id, collect_time) " +
            "VALUES(#{userId}, #{productId}, #{collectTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Collection collect);

    @Delete("DELETE FROM collect WHERE user_id = #{userId} AND product_id = #{productId}")
    int deleteByUserAndProduct(@Param("userId") Integer userId,
                               @Param("productId") Integer productId);
}
