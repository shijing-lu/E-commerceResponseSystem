/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-9 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.Dao;

import com.shijinglu.ecommerceresponsesystem.entity.ShoppingCart;
import org.apache.ibatis.annotations.*;

import java.util.List;

// ShoppingCartMapper.java
@Mapper
public interface ShoppingCartMapper {
    @Select("SELECT * FROM shoppingCart WHERE user_id = #{userId}")
    List<ShoppingCart> findByUserId(Long userId);

    @Select("SELECT * FROM shoppingCart WHERE user_id = #{userId} AND product_id = #{productId}")
    ShoppingCart findByUserIdAndProductId(@Param("userId") Integer userId,
                                          @Param("productId") Integer productId);

    @Insert("INSERT INTO shoppingCart VALUES (null, #{userId}, #{productId}, 1)")
    int insert(ShoppingCart cart);

    @Update("UPDATE shoppingCart SET num = #{num} WHERE user_id = #{userId} AND product_id = #{productId}")
    int updateNum(@Param("num") Integer num,
                  @Param("userId") Integer userId,
                  @Param("productId") Integer productId);

    @Delete("DELETE FROM shoppingCart WHERE user_id = #{userId} AND product_id = #{productId}")
    int deleteByUserAndProduct(@Param("userId") Integer userId,
                               @Param("productId") Integer productId);
}

// ProductMapper.java

