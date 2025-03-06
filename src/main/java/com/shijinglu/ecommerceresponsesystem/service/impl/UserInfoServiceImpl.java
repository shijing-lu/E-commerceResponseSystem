package com.shijinglu.ecommerceresponsesystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shijinglu.ecommerceresponsesystem.entity.UserInfo;
import com.shijinglu.ecommerceresponsesystem.service.UserInfoService;
import com.shijinglu.ecommerceresponsesystem.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【user_info(用户核心信息表)】的数据库操作Service实现
* @createDate 2025-03-06 13:23:38
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{

}




