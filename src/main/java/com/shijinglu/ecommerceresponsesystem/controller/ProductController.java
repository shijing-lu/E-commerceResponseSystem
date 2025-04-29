/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-8 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shijinglu.ecommerceresponsesystem.Dao.ProductMapper;
import com.shijinglu.ecommerceresponsesystem.common.Result;
import com.shijinglu.ecommerceresponsesystem.common.ResultCodeEnum;
import com.shijinglu.ecommerceresponsesystem.dto.AddProductRequest;
import com.shijinglu.ecommerceresponsesystem.dto.CategoryProductDTO;
import com.shijinglu.ecommerceresponsesystem.dto.HotProductRequest;
import com.shijinglu.ecommerceresponsesystem.dto.PromoProductRequest;
import com.shijinglu.ecommerceresponsesystem.entity.Category;
import com.shijinglu.ecommerceresponsesystem.entity.HotProduct;
import com.shijinglu.ecommerceresponsesystem.entity.Product;
import com.shijinglu.ecommerceresponsesystem.entity.ProductPicture;
import com.shijinglu.ecommerceresponsesystem.service.impl.HotProductServiceImpl;
import com.shijinglu.ecommerceresponsesystem.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

// ProductController.java
@Slf4j
@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    private ProductServiceImpl productServiceImpl;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private HotProductServiceImpl hotProductServiceImpl;

    final String UPLOAD_DIR = "D:/300_归档/毕业设计/E-commerceResponseSystem/src/main/resources/static/public/imgs/phone/";


    @PostMapping("/getCategory")
    public Result getCategories() {
        List<Category> categories = productServiceImpl.getCategories();
        return Result.success(ResultCodeEnum.SUCCESS, categories);
    }

    @PostMapping("/getPromoProduct")
    public Result getPromoProduct(@RequestBody PromoProductRequest promoProductRequest) {
        List<Product> products = productServiceImpl.getPromoProducts(promoProductRequest.getCategoryName());
        return Result.success(ResultCodeEnum.SUCCESS, products);
    }

    //?
    @PostMapping("/getHotProduct")
    public Result getHotProduct(@RequestBody HotProductRequest request) {
        List<Product> products = productServiceImpl.getHotProducts(request.getCategoryName());
        return Result.success(ResultCodeEnum.SUCCESS, products);
    }

    //?
    @PostMapping("/getProductByCategory")
    public Result getProductByCategory(@RequestBody CategoryProductDTO categoryProductDTO) {
        Page<Product> page = productServiceImpl.getAllProducts(
                categoryProductDTO.getCategoryID(), categoryProductDTO.getCurrentPage(), categoryProductDTO.getPageSize()
        );
        return Result.success(ResultCodeEnum.SUCCESS, page);
    }

//    @PostMapping("/getProductByCategoryAdmin")
//    public Result getProductByCategory(@RequestParam(required = false) List<Integer> categoryID, @RequestParam int currentPag, @RequestParam int pageSize) {
//        if (categoryID == null) {
//            categoryID = new ArrayList<>();
//        }
//        Page<Product> page = productServiceImpl.getAllProducts(
//                categoryID, currentPag, pageSize
//        );
//        return Result.success(ResultCodeEnum.SUCCESS, page);
//    }

    //?
    @PostMapping("/getProductBySearch")
    public Result getProductBySearch() {
        List<Category> categories = productServiceImpl.getCategories();
        return Result.success(ResultCodeEnum.SUCCESS, categories);
    }

    /*后台添加商品*/
    @PostMapping("/Products")
    public Result addProduct(@RequestBody AddProductRequest addProductRequest) {

        Product product = new Product();
        product.setCategoryId(addProductRequest.getGoods_cat());
        product.setProductName(addProductRequest.getGoods_name());
        product.setProductPrice(addProductRequest.getGoods_price());
        product.setProductNum(addProductRequest.getGoods_number());
        product.setProductIntro(addProductRequest.getGoods_introduce());
        product.setProductPicture(addProductRequest.getPics());
        productServiceImpl.insertProduct(product);
        if (addProductRequest.getGoods_cat() == 8) {
            Product product1 = productServiceImpl.getProductByName(addProductRequest.getGoods_name());
            HotProduct hotProduct = new HotProduct(product1.getProductId(), product1.getProductNum(), product1.getProductPrice(), product1.getProductSales());
            hotProductServiceImpl.insertHotProduct(hotProduct);
        }
        return Result.success(ResultCodeEnum.SUCCESS);
    }

    //？
    @GetMapping("/getDetails")
    public Result getDetails(@RequestParam("productID") Integer productID) {
        return Result.success(ResultCodeEnum.SUCCESS, productServiceImpl.getProductById(productID));
    }

    @GetMapping("/hotlist")
    public Result hostList() {
        return Result.success(ResultCodeEnum.SUCCESS, productServiceImpl.getHostList());
    }

    //？
    @GetMapping("/getDetailsPicture")
    public Result getDetailsPicture(@RequestParam("productID") Long productID) {
        List<ProductPicture> pictures = productServiceImpl.getDetailsPicture(productID);
        return Result.success(ResultCodeEnum.SUCCESS, pictures);
    }


    @PostMapping("/getAllProduct")
    public Result getAllProducts(@RequestBody CategoryProductDTO categoryProductDTO) {

        Page<Product> page = productServiceImpl.getAllProducts(
                categoryProductDTO.getCategoryID(), categoryProductDTO.getCurrentPage(), categoryProductDTO.getPageSize()
        );
        return Result.success(ResultCodeEnum.SUCCESS, page);
    }

    /*后台管理系统获取数据分页信息*/
    @GetMapping("/getProductList")
    public Result getProductList(@RequestParam String query, @RequestParam int pagenum, @RequestParam int pagesize) {
        System.out.println("ok");
        Page<Product> page = productServiceImpl.getAllProducts(null, pagenum, pagesize);
        return Result.success(ResultCodeEnum.SUCCESS, page);
    }

    /*更新后台商品数据*/
    @PutMapping("/products")
    public Result updateProduct(@RequestBody Product product) {
        productMapper.updateById(product);
        return Result.success(ResultCodeEnum.SUCCESS);
    }

    @DeleteMapping("/{productId}")
    public Result deleteProduct(@PathVariable("productId") Integer productId) {
        productMapper.deleteById(productId);
        return Result.success(ResultCodeEnum.SUCCESS);
    }

    /*接收图片并储存*/
    @PostMapping("/pictureUpload")
    public Result uploadPicture(
            @RequestParam("file") MultipartFile file,
            HttpServletRequest request) {


        // 验证请求头中的Token（示例实现）
        String token = request.getHeader("Authorization");
//    if (token == null || token.isEmpty()) {
//        response.put("code", 401);
//        response.put("message", "未授权的访问");
//        return Result.error(ResultCodeEnum.UNAUTHORIZED, response);
//    }

        try {
            // 验证文件类型
//        String contentType = file.getContentType();
//        if (!"image/jpeg".equals(contentType) && !"image/png".equals(contentType)) {
//            response.put("code", 400);
//            response.put("message", "仅支持JPG/PNG格式");
//            return Result.error(ResultCodeEnum.BAD_REQUEST, response);
//        }

            // 创建目录（如果不存在）
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // 生成唯一文件名
            String originalName = file.getOriginalFilename();
            String fileExtension = originalName.substring(originalName.lastIndexOf("."));
            String uniqueFileName = UUID.randomUUID() + fileExtension;

            // 保存文件
            Path filePath = Paths.get(UPLOAD_DIR + uniqueFileName);
            Files.copy(file.getInputStream(), filePath);

            // 构造响应数据
            Map<String, String> data = new HashMap<>();
            data.put("tmp_path", "public/imgs/phone/" + uniqueFileName); // 临时访问路径
            data.put("url", "public/imgs/phone/" + uniqueFileName);       // 实际访问路径

            return Result.success(ResultCodeEnum.SUCCESS, data);

        } catch (IOException e) {

            return Result.error(ResultCodeEnum.NOT_FOUND);
        }
    }

}
