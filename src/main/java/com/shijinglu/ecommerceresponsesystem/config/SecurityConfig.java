/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-15 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 使用 BCrypt 强哈希加密
        return new BCryptPasswordEncoder();
    }
}