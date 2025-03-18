/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-15 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.common.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    @Value("myStrongSecretKeyWithAtLeast64BytesLength1234567890ThisIsASufficientlyLongKeytLeast64BytesLength1234567890ThisIsASufficientlyLong")
    private String secret;

    //    @Value("${jwt.expire}")
    private Long expire = 86400000L;

    public String generateToken(Integer userId, Integer rid) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userId.toString())
                .claim("rid", rid)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}