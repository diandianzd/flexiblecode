package com.flexible.service.impl;

import com.flexible.entity.Plugins;
import com.flexible.entity.Users;
import com.flexible.entity.request.plugins.PluginListReq;
import com.flexible.mapper.PluginsMapper;
import com.flexible.service.IPluginsService;
import com.flexible.utils.page.PageList;
import com.flexible.utils.page.PageUtils;
import com.flexible.utils.response.ResultModel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xlizz
 * @since 2025-04-08
 */
@Service
public class PluginsServiceImpl extends ServiceImpl<PluginsMapper, Plugins> implements IPluginsService {

    @Override
    public ResultModel fetchList(PluginListReq data, Users user) {

        // 设置分页
        Page<Plugins> pager = new Page<>(data.getPage(), data.getPageSize());
        // 查询数据
        QueryWrapper<Plugins> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(data.getName()), "name", data.getName()).orderByDesc("id");

        Page<Plugins> pluginList = this.page(pager, queryWrapper);
        // 原生分页字段转换
        PageList<Plugins> pageList = PageUtils.toCustomPage(pluginList);

        return ResultModel.ok(pageList);

    }

}
