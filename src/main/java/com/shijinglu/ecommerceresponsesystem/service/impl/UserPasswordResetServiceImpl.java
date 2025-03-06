package com.shijinglu.ecommerceresponsesystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shijinglu.ecommerceresponsesystem.entity.UserPasswordReset;
import com.shijinglu.ecommerceresponsesystem.service.UserPasswordResetService;
import com.shijinglu.ecommerceresponsesystem.mapper.UserPasswordResetMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【user_password_reset(密码重置记录表)】的数据库操作Service实现
* @createDate 2025-03-06 13:23:45
*/
@Service
public class UserPasswordResetServiceImpl extends ServiceImpl<UserPasswordResetMapper, UserPasswordReset>
    implements UserPasswordResetService{

}




