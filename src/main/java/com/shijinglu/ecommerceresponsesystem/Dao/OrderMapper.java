/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-10 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.Dao;

import com.shijinglu.ecommerceresponsesystem.dto.OrderResponsed;
import com.shijinglu.ecommerceresponsesystem.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper {
    int batchInsert(@Param("orders") List<Order> orders);

    @Select("SELECT order_id FROM orders WHERE user_id = #{userId} GROUP BY order_id ORDER BY order_id DESC")
    List<String> getOrderGroups(@Param("userId") String userId);

    // 获取订单详细信息（优化后的SQL）
    @Select("SELECT o.*, p.product_name, p.product_picture " +
            "FROM orders o " +
            "LEFT JOIN product p ON o.product_id = p.product_id " +
            "WHERE o.user_id = #{userId} " +
            "ORDER BY o.order_time DESC")
    List<OrderResponsed> getOrdersWithProducts(@Param("userId") String userId);
}
