package com.flexible.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flexible.entity.Users;
import com.flexible.entity.request.user.UsersListRequest;
import com.flexible.entity.request.user.UsersLoginRequest;
import com.flexible.entity.response.user.UserPermissions;
import com.flexible.utils.response.ResultModel;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xlizz
 * @since 2021-12-17
 */
public interface IUsersService extends IService<Users> {

    // 用户登录
    public ResultModel userLogin(UsersLoginRequest data);


    // 校验token
    public Users userAuth(String token);


    // 用户列表
    ResultModel fetchList(UsersListRequest data, Users user);

    // 获取用户部门、角色、权限
    UserPermissions getUserPermissions(Users user);


}
