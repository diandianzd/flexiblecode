package com.flexible.controller;


import java.io.Serializable;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flexible.core.annotation.CurrentUser;
import com.flexible.entity.Plugins;
import com.flexible.entity.Users;
import com.flexible.entity.request.BaseListRequest;
import com.flexible.entity.request.plugins.PluginListReq;
import com.flexible.entity.request.user.UsersListRequest;
import com.flexible.service.IPluginsService;
import com.flexible.utils.response.ResultModel;

import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xlizz
 * @since 2025-04-08
 */
@RestController
@RequestMapping("/plugins")
public class PluginsController {

    @Autowired
    IPluginsService pluginsService;


    @ApiOperation(value = "列表")
    @RequestMapping(value = "/list", method = {RequestMethod.POST})
    public ResultModel list(@Valid @RequestBody PluginListReq data, @CurrentUser Users user) {
        return pluginsService.fetchList(data, user);
    }


    @ApiOperation(value = "保存")
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ResultModel save(@Valid @RequestBody Plugins data, @CurrentUser Users user) {
        return ResultModel.ok(pluginsService.saveOrUpdate(data));
    }


    @ApiOperation(value = "删除")
    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE})
    public ResultModel delete(@Valid @PathVariable Integer id, @CurrentUser Users user) {

        Plugins exitUser = pluginsService.getById(id);
        if (exitUser == null) {
            return ResultModel.error("Plugin not found");
        }

        return ResultModel.ok(pluginsService.removeById(id));
    }


}

