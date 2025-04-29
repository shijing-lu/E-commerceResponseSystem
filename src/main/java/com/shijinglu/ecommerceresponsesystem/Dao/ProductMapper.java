/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-8 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shijinglu.ecommerceresponsesystem.Dao.provider.ProductSqlProvider;
import com.shijinglu.ecommerceresponsesystem.entity.Category;
import com.shijinglu.ecommerceresponsesystem.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

// ProductMapper.java
public interface ProductMapper extends BaseMapper<Product> {
    @Select("SELECT * FROM category")
    List<Category> selectCategories();

    @Select("SELECT * FROM product WHERE category_id = #{categoryId} ORDER BY product_sales DESC LIMIT 7")
        // 根据category_id查询product表中的数据，按照product_sales降序排列，限制返回结果为7条
    List<Product> selectPromoProducts(Integer categoryId);

    @Select("SELECT * FROM product WHERE category_id IN (#{categoryIds}) ORDER BY product_sales DESC LIMIT 7")
    List<Product> getPromoProductsByCategoryId(Integer categoryId);

    @Select("SELECT category_id FROM category WHERE category_name = #{categoryName}")
    Integer getCategoryIdByName(String categoryName);

    @SelectProvider(type = ProductSqlProvider.class, method = "getProductByCategory")
    List<Product> findByCategoryIds(@Param("categoryIds") List<Integer> categoryIds,
                                    @Param("offset") int offset,
                                    @Param("rows") int rows);

    int deductStock(@Param("productId") Integer productId, @Param("num") Integer num);

    @Select("SELECT * FROM product WHERE product_name = #{name}")
    Product selectProductByName(String name);
}
