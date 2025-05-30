package com.flexible.service;

import com.flexible.entity.Permissions;
import com.flexible.entity.Users;
import com.flexible.utils.response.ResultModel;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xlizz
 * @since 2025-03-18
 */
public interface IPermissionsService extends IService<Permissions> {
  

    // 用户列表
    ResultModel fetchList(Users user);
}
