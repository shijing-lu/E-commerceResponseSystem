/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-8 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shijinglu.ecommerceresponsesystem.entity.Carousel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ResourcesMapper extends BaseMapper<Carousel> {
    @Select("select * from carousel ")
    List<Carousel> selectCarousels();

}
