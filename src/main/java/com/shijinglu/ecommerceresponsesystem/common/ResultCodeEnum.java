/*
 *  Copyright (c)
 *   Author: 姚宇航
 *   Time: 2025-3-7 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.common;


public enum ResultCodeEnum {
    /* ------------------------ 2xx Success ------------------------ */
    SUCCESS(200, "成功"),
    CREATED(201,  "资源创建成功"),
    ACCEPTED(202,  "请求已接受处理"),
    NO_CONTENT(204,  "无返回内容"),

    /* ------------------------ 4xx Client Errors ------------------ */
    // 通用客户端错误
    BAD_REQUEST(400,  "请求参数错误"),
    UNAUTHORIZED(401,  "身份未认证"),
    FORBIDDEN(403, "权限不足"),
    NOT_FOUND(404,  "资源不存在"),
    METHOD_NOT_ALLOWED(405,  "请求方法不允许"),
    CONFLICT(409, "资源状态冲突"),
    TOO_MANY_REQUESTS(429,  "请求过于频繁"),

    // 用户模块 (A1xxx)
    USER_REGISTER_PARAM_INVALID(400,  "参数格式错误"),
    USER_LOGIN_FAILED(401,  "用户名或密码错误"),
    USER_PHONE_EXIST(409,  "手机号已注册"),
    USER_ACCOUNT_LOCKED(403,  "账户已被锁定"),

    // 商品模块 (B2xxx)
    GOODS_NOT_EXIST(404,  "商品不存在"),
    GOODS_SEARCH_INVALID(400,  "搜索关键词非法"),
    GOODS_SKU_INVALID(422,  "商品规格参数错误"),
    GOODS_OFF_SHELF(404,  "商品已下架"),

    // 订单模块 (C3xxx)
    ORDER_CREATE_FAIL(400,  "订单创建失败"),
    ORDER_INVALID_ADDRESS(400,  "收货地址无效"),
    ORDER_COUPON_EXPIRED(400,  "优惠券已过期"),
    ORDER_STOCK_LACK(422,  "库存不足"),
    ORDER_DUPLICATED(409,  "重复订单提交"),
    ORDER_PROCESSING(423,  "订单处理中"),

    // 支付模块 (D4xxx)
    PAYMENT_REQUIRED(402,  "需要完成支付"),
    PAYMENT_TIMEOUT(408,  "支付超时"),
    PAYMENT_CHANNEL_UNAVAILABLE(424,  "支付渠道不可用"),
    PAYMENT_AMOUNT_MISMATCH(400,  "支付金额不符"),

    // 物流模块 (E5xxx)
    LOGISTICS_NOT_FOUND(404,  "物流信息不存在"),
    LOGISTICS_TIMEOUT(504,  "物流查询超时"),

    /* ------------------------ 5xx Server Errors ------------------ */
    INTERNAL_ERROR(500,  "服务器内部错误"),
    SERVICE_UNAVAILABLE(503,  "服务不可用"),
    DB_CONNECTION_FAIL(500,  "数据库连接失败"),
    THIRD_PARTY_ERROR(502,  "第三方服务异常");

    private final int httpStatus;
    private final String message;

    ResultCodeEnum(int httpStatus,  String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    // Getters
    public int getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    // 通过业务码查找枚举

    @Override
    public String toString() {
        return String.format("[%s] %s (HTTP %d)",  message, httpStatus);
    }
}