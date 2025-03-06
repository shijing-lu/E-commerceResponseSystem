/*
 *
 *  * Copyright (C) ${YEAR}-${MONTH}-${DAY} 姚宇航
 *  * 江苏大学
 *
 */

package com.shijinglu.ecommerceresponsesystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shijinglu.ecommerceresponsesystem.mapper")
public class ECommerceResponseSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ECommerceResponseSystemApplication.class, args);
    }

}
