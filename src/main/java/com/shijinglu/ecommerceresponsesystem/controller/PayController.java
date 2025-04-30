/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-8 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.controller;

import com.shijinglu.ecommerceresponsesystem.common.Result;
import com.shijinglu.ecommerceresponsesystem.common.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

// ProductController.java
@Slf4j
@RestController
@RequestMapping("/pay")
@CrossOrigin(origins = "*")
public class PayController {

    @PostMapping("/MSpay")
    public Result MSpay(@RequestBody Map<String, Object> data) {
        // TODO: 实现支付逻辑
        return Result.success(ResultCodeEnum.SUCCESS, data);
    }

}
