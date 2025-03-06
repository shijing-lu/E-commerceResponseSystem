package com.shijinglu.ecommerceresponsesystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 用户地址簿
 * @TableName user_address
 */
@TableName(value ="user_address")
@Data
public class UserAddress {
    /**
     * 地址ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID（逻辑关联user_info.id）
     */
    private Long userId;

    /**
     * 收件人姓名
     */
    private String recipient;

    /**
     * 国家地区码
     */
    private String countryCode;

    /**
     * 联系手机号
     */
    private String contactMobile;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区县
     */
    private String district;

    /**
     * 详细地址
     */
    private String detailAddress;

    /**
     * 默认地址 0:否 1:是
     */
    private Integer isDefault;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}