/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-9 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.Dao.provider;

import java.util.List;
import java.util.Map;

public class ProductSqlProvider {
    public String getProductByCategory(Map<String, Object> params) {
        List<Long> categoryIds = (List<Long>) params.get("categoryIds");
        int offset = (int) params.get("offset");
        int rows = (int) params.get("rows");

        StringBuilder sql = new StringBuilder()
                .append("SELECT * FROM product WHERE ");

        // 构建动态OR条件
        for (int i = 0; i < categoryIds.size(); i++) {
            if (i > 0) sql.append(" OR ");
            sql.append("category_id = #{categoryIds[").append(i).append("]}");
        }

        // 添加分页
        if (rows > 0) {
            sql.append(" ORDER BY product_sales DESC LIMIT #{offset}, #{rows}");
        }
        return sql.toString();
    }
}
