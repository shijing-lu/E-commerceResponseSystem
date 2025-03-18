/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-10 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.service.impl;

import com.shijinglu.ecommerceresponsesystem.Dao.OrderMapper;
import com.shijinglu.ecommerceresponsesystem.Dao.ProductMapper;
import com.shijinglu.ecommerceresponsesystem.Dao.ShoppingCartMapper;
import com.shijinglu.ecommerceresponsesystem.Dao.UserMapper;
import com.shijinglu.ecommerceresponsesystem.common.Result;
import com.shijinglu.ecommerceresponsesystem.common.ResultCodeEnum;
import com.shijinglu.ecommerceresponsesystem.dto.OrderResponsed;
import com.shijinglu.ecommerceresponsesystem.dto.ProductDTO;
import com.shijinglu.ecommerceresponsesystem.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductMapper productMapper;

    public Result getOrders(String userId) {
        // 2. 获取订单分组
        List<String> orderGroups = orderMapper.getOrderGroups(userId);
        if (orderGroups.isEmpty()) {
            return Result.success(ResultCodeEnum.ORDER_NOT_EXIST, "该用户没有订单信息");
        }
        // 3. 获取订单详细信息（已包含商品信息）
        List<OrderResponsed> orders = orderMapper.getOrdersWithProducts(userId);
        // 4. 按order_id分组
        Map<String, List<OrderResponsed>> groupedOrders = orders.stream()
                .collect(Collectors.groupingBy(OrderResponsed::getOrderId));
        // 5. 转换为前端需要的结构
        List<List<OrderResponsed>> result = new ArrayList<>();
        orderGroups.forEach(orderId -> {
            if (groupedOrders.containsKey(orderId)) {
                result.add(groupedOrders.get(orderId));
            }
        });

        return Result.success(ResultCodeEnum.SUCCESS, result);
    }


    @Transactional
    public Result<Object> createOrder(Integer userId, List<ProductDTO> products) {
        for (ProductDTO p : products) {
            int stockUpdated = productMapper.deductStock(p.getProductId(), p.getNum());
            if (stockUpdated == 0) {
                return Result.error(ResultCodeEnum.GOODS_STOCK_INSUFFICIENT);
            }
        }
        BigDecimal totalAmount = products.stream()
                .map(p -> p.getPrice().multiply(BigDecimal.valueOf(p.getNum())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
//用户余额扣减
        int balanceUpdated = userMapper.deductBalance(userId, totalAmount);
        if (balanceUpdated == 0) {
            return Result.error(ResultCodeEnum.USER_BALANCE_INSUFFICIENT);
        }
//产品库存扣减
        System.out.println(totalAmount);
        Long timestamp = System.currentTimeMillis();
        String orderId = userId.toString() + timestamp;
        // 构造订单列表
        List<Order> orders = products.stream()
                .map(p -> new Order(null, orderId, userId,
                        p.getProductId(), p.getNum(), p.getPrice(), timestamp))
                .collect(Collectors.toList());
        // 批量插入订单
        int insertedRows = orderMapper.batchInsert(orders);
        if (insertedRows != products.size()) {
            throw new RuntimeException("订单插入数量不匹配");
        }

        // 删除购物车商品
        int deletedTotal = products.stream()
                .mapToInt(p -> shoppingCartMapper.deleteByUserAndProduct(userId, p.getProductId()))
                .sum();

        if (deletedTotal != products.size()) {
            throw new RuntimeException("购物车删除数量不匹配");
        }
        return Result.success(ResultCodeEnum.ORDER_CREATE_SUCCESS);
    }
}
