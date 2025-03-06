package com.shijinglu.ecommerceresponsesystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shijinglu.ecommerceresponsesystem.entity.UserAddress;
import com.shijinglu.ecommerceresponsesystem.service.UserAddressService;
import com.shijinglu.ecommerceresponsesystem.mapper.UserAddressMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【user_address(用户地址簿)】的数据库操作Service实现
* @createDate 2025-03-06 13:23:19
*/
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress>
    implements UserAddressService{

}




