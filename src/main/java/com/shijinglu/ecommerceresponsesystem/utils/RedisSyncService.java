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
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        QueryWrapper wrapper = new QueryWrapper<HotProduct>();
        List<HotProduct> products = hotProductMapper.selectList(wrapper);

        products.forEach(product -> {
            String key = "product:" + product.getProductId();

            // 处理null值转换为空字符串
            HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
            hashOps.put(key, "id", product.getId().toString());
            hashOps.put(key, "product_id", String.valueOf(product.getProductId()));
            hashOps.put(key, "nums", product.getNums().toString());
            hashOps.put(key, "selling_price", product.getSellingPrice().toString());
            hashOps.put(key, "sales_count",
                    Optional.ofNullable(product.getSaleCount()).orElse(0).toString());
            hashOps.put(key, "discount_rate",
                    Optional.ofNullable(product.getDiscountRate()).orElse(defaultDiscountRate).toString());
        });
    }
}
