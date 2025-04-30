/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-8 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shijinglu.ecommerceresponsesystem.Dao.CategoryMapper;
import com.shijinglu.ecommerceresponsesystem.Dao.HotProductMapper;
import com.shijinglu.ecommerceresponsesystem.Dao.ProductMapper;
import com.shijinglu.ecommerceresponsesystem.Dao.ProductPictureMapper;
import com.shijinglu.ecommerceresponsesystem.entity.Category;
import com.shijinglu.ecommerceresponsesystem.entity.HotProduct;
import com.shijinglu.ecommerceresponsesystem.entity.Product;
import com.shijinglu.ecommerceresponsesystem.entity.ProductPicture;
import com.shijinglu.ecommerceresponsesystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

// ProductService.java
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductPictureMapper productPictureMapper;

    @Autowired
    private HotProductMapper hotProductMapper;

    public List<Category> getCategories() {
        return productMapper.selectCategories();
    }

    public Page<Product> getAllProducts(List<Integer> category, int currentPage, int pageSize) {
        // 创建 QueryWrapper
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        // 添加条件：分类 ID 在 category 数组中
        if (category != null && category.size() > 0) {
            queryWrapper.in("category_id", category);
        }
        Page<Product> page = new Page<>(currentPage, pageSize);
        return productMapper.selectPage(page, queryWrapper);
    }

    public List<Product> getPromoProducts(String categoryName) {
        Integer categoryId = categoryMapper.getCategoryIdByName(categoryName);
        return productMapper.getPromoProductsByCategoryId(categoryId);
    }

    @Override
    public Product getProductById(int id) {
        return productMapper.selectById(id);
    }

    @Override
    public List<Product> getPromoProductsByName(String categoryName) {
        Category category = categoryMapper.selectCategoryByName(categoryName);
        return productMapper.selectPromoProducts(category.getCategoryId());
    }

    public List<ProductPicture> getDetailsPicture(Long productId) {
        return productPictureMapper.findByProductId(productId);
    }

    public List<Product> getHotProducts(List<String> categoryNames) {
        // 转换分类名称到ID列表
        List<Integer> categoryIds = categoryNames.stream()
                .map(name -> categoryMapper.getCategoryIdByName(name))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (categoryIds.isEmpty()) {
            return Collections.emptyList();
        }

        return productMapper.findByCategoryIds(categoryIds, 0, 7);
    }

    public void insertProduct(Product product) {
        productMapper.insert(product);
    }

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private static final BigDecimal defaultDiscountRate = new BigDecimal("0");

    public List<HotProduct> getHostList() {
//        List<HotListResponsed> hotListResponseds = new ArrayList<>();
        Set<String> keys = redisTemplate.keys("product:*");
        List<HotProduct> products = new ArrayList<>();

        if (keys != null) {
            for (String key : keys) {
                // 读取哈希数据
                HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
                Map<String, String> entries = hashOps.entries(key);

                // 构建 HotProduct 对象
                HotProduct product = new HotProduct();
                try {
                    product.setId(Integer.parseInt(entries.get("id")));
                    product.setProductId(Integer.parseInt(entries.get("product_id")));
                    product.setNums(Integer.parseInt(entries.get("nums")));
                    product.setProductSellingPrice(new BigDecimal(entries.get("selling_price")));
                    product.setProductPrice(new BigDecimal(entries.get("Product_Price")));
                    product.setProductName(entries.get("product_name"));
                    product.setProductPicture(entries.get("product_picture"));

                    // 处理 sales_count（原逻辑：null → 0）
                    String salesCountStr = entries.get("sales_count");
                    Integer salesCount = salesCountStr != null ? Integer.parseInt(salesCountStr) : 0;
                    product.setSaleCount(salesCount);
                    // 处理 discount_rate（原逻辑：null → defaultDiscountRate）
                    String discountRateStr = entries.get("discount_rate");
                    BigDecimal discountRate = discountRateStr != null
                            ? new BigDecimal(discountRateStr)
                            : defaultDiscountRate;
                    product.setDiscountRate(discountRate);
                    products.add(product);
                } catch (Exception e) {
                    // 处理转换异常（如日志记录或跳过无效数据）
                    System.err.println("Failed to parse data for key: " + key);
                }
            }
        }
//        products.stream().forEach(hotProduct -> {
//            Product pro = productMapper.selectById(hotProduct.getProductId());
//            hotListResponseds.add(new HotListResponsed(hotProduct.getId(), pro.getProductId(), pro.getProductName(), pro.getProductPrice(), hotProduct.getSellingPrice(), hotProduct.getSaleCount(), pro.getProductPicture(), hotProduct.getDiscountRate()));
//        });
//        hotListResponseds.forEach(System.out::println);
        return products;
    }

    //    public List<HotListResponsed> getHostList() {
//        List<HotListResponsed> hotListResponseds = new ArrayList<>();
//        List<HotProduct> hotProducts = hotProductMapper.selectList(new QueryWrapper<>());
//        hotProducts.stream().forEach(hotProduct -> {
//            Product pro = productMapper.selectById(hotProduct.getProductId());
//            hotListResponseds.add(new HotListResponsed(hotProduct.getId(), pro.getProductId(), pro.getProductName(), pro.getProductPrice(), hotProduct.getSellingPrice(), hotProduct.getSaleCount(), pro.getProductPicture(), hotProduct.getDiscountRate()));
//        });
//        hotListResponseds.forEach(System.out::println);
//        return hotListResponseds;
//    }
    /*根据商品名查询商品
     * */
    public Product getProductByName(String name) {
        return productMapper.selectProductByName(name);
    }
}