package com.flexible.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flexible.entity.UserInfos;
import com.flexible.mapper.UserInfosMapper;
import com.flexible.service.IUserInfosService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xlizz
 * @since 2021-12-17
 */
@Service
public class UserInfosServiceImpl extends ServiceImpl<UserInfosMapper, UserInfos> implements IUserInfosService {

}
