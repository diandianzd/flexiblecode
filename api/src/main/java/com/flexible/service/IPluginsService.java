package com.flexible.service;

import com.flexible.entity.Plugins;
import com.flexible.entity.Users;
import com.flexible.entity.request.plugins.PluginListReq;
import com.flexible.utils.response.ResultModel;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xlizz
 * @since 2025-04-08
 */
public interface IPluginsService extends IService<Plugins> {

    // 用户列表
    ResultModel fetchList(PluginListReq data, Users user);
}
