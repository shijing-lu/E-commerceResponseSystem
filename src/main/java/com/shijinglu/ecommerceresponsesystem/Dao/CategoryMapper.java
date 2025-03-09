/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-8 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shijinglu.ecommerceresponsesystem.entity.Category;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// ProductMapper.java
public interface CategoryMapper extends BaseMapper<Category> {
    @Select("SELECT * FROM category")
    List<Category> selectCategories();

    @Select("SELECT * FROM category WHERE category_name = #{categoryName}")
    Category selectCategoryByName(String categoryName);

}
