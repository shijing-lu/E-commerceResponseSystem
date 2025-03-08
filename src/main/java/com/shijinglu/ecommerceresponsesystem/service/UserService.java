/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-8 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.service;

import com.shijinglu.ecommerceresponsesystem.common.Result;

public interface UserService {
    public Result login(String username, String password);

    public Result register(String username, String password);
}
