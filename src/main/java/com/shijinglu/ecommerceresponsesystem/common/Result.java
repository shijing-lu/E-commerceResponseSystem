/*
 *  Copyright (c)
 *   Author: 姚宇航
 *   Time: 2025-3-7 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.common;

public class Result<T> {
    private String code;
    private String msg;
    private T data;

    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // 通用成功方法，使用指定的ResultCodeEnum
    public static <T> Result<T> success(ResultCodeEnum resultCode, T data) {
        return new Result<>(
                String.valueOf(resultCode.getHttpStatus()),
                resultCode.getMessage(),
                data
        );
    }

    // 默认成功方法（使用SUCCESS枚举）
    public static <T> Result<T> success(T data) {
        return success(ResultCodeEnum.SUCCESS, data);
    }

    // 带自定义消息的成功方法（可选）
    public static <T> Result<T> success(ResultCodeEnum resultCode, String customMsg, T data) {
        return new Result<>(
                String.valueOf(resultCode.getHttpStatus()),
                customMsg,
                data
        );
    }

    // 错误方法，使用指定的ResultCodeEnum
    public static <T> Result<T> error(ResultCodeEnum resultCode) {
        return new Result<>(
                String.valueOf(resultCode.getHttpStatus()),
                resultCode.getMessage(),
                null
        );
    }

    // 带自定义消息的错误方法（可选）
    public static <T> Result<T> error(ResultCodeEnum resultCode, String customMsg) {
        return new Result<>(
                String.valueOf(resultCode.getHttpStatus()),
                customMsg,
                null
        );
    }

    // Getter方法
    public String getCode() { return code; }
    public String getMsg() { return msg; }
    public T getData() { return data; }
}