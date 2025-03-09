/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-9 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.Dao;

import com.shijinglu.ecommerceresponsesystem.entity.ProductPicture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductPictureMapper {
    @Select("SELECT * FROM product_picture WHERE product_id = #{productId}")
    List<ProductPicture> findByProductId(Long productId);
}
