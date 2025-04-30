/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-4-29 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shijinglu.ecommerceresponsesystem.Dao.HotProductMapper;
import com.shijinglu.ecommerceresponsesystem.entity.HotProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
@EnableScheduling
public class RedisSyncService {

    final BigDecimal defaultDiscountRate = new BigDecimal(0.0);
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private HotProductMapper hotProductMapper;

    @Scheduled(cron = "0 * * * * *") // 每分钟执行
    public void syncToRedis() {
        try {
            // 先清空原有数据（安全模式：仅删除product:开头的key）
            ScanOptions options = ScanOptions.scanOptions().match("product:*").count(100).build();
            try (Cursor<String> cursor = redisTemplate.scan(options)) {
                cursor.forEachRemaining(key -> redisTemplate.delete(key));
            }

            // 加载最新数据
            List<HotProduct> products = hotProductMapper.selectList(new QueryWrapper<>());
            final BigDecimal defaultDiscount = new BigDecimal("0.00");

            products.forEach(product -> {
                String key = "product:" + product.getProductId();
                HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();

                // 核心字段（根据新表结构）
                hashOps.put(key, "id", String.valueOf(product.getId()));
                hashOps.put(key, "product_id", String.valueOf(product.getProductId()));
                hashOps.put(key, "product_name",
                        Optional.ofNullable(product.getProductName()).orElse(""));

                // 数值型处理（防止null）
                hashOps.put(key, "nums",
                        Optional.ofNullable(product.getNums()).orElse(0).toString());
                hashOps.put(key, "selling_price",
                        product.getProductSellingPrice().setScale(2, RoundingMode.HALF_UP).toString()); // 强制保留2位小数
                hashOps.put(key, "sales_count",
                        Optional.ofNullable(product.getSalesCount()).orElse(0).toString());
                hashOps.put(key, "discount_rate",
                        Optional.ofNullable(product.getDiscountRate()).orElse(defaultDiscount).toString());

                // 新增price字段（原product_price已弃用）
                hashOps.put(key, "Product_Price",
                        Optional.ofNullable(product.getProductPrice())
                                .orElse(BigDecimal.ZERO)
                                .setScale(2, RoundingMode.HALF_UP)
                                .toString());
                hashOps.put(key, "product_picture", product.getProductPicture());
            });


        } catch (Exception e) {
            // 可添加重试逻辑或报警
        }
    }
}
