package com.flexible.controller;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flexible.core.annotation.CurrentUser;
import com.flexible.entity.Users;
import com.flexible.entity.request.user.UsersListRequest;
import com.flexible.service.IUsersService;
import com.flexible.utils.response.ResultModel;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xlizz
 * @since 2021-12-17
 */
@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    IUsersService usersService;

    @ApiOperation(value = "列表")
    @RequestMapping(value = "/list", method = {RequestMethod.POST})
    public ResultModel list(@Valid @RequestBody UsersListRequest data, @CurrentUser Users user) {
        return usersService.fetchList(data, user);
    }


    @ApiOperation(value = "保存")
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ResultModel save(@Valid @RequestBody Users data, @CurrentUser Users user) {
        return ResultModel.ok(usersService.saveOrUpdate(data));
    }


    @ApiOperation(value = "删除")
    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE})
    public ResultModel delete(@Valid @PathVariable Integer id, @CurrentUser Users user) {

        Users exitUser = usersService.getById(id);
        if (exitUser == null) {
            return ResultModel.error("User not found");
        }
        if ("admin".equals(exitUser.getEmail())) {
            return ResultModel.error("Admin user can not be deleted");
        }

        return ResultModel.ok(usersService.removeById(id));
    }

    @ApiOperation(value = "获取用户全部权限")
    @RequestMapping(value = "/getUserPermissions", method = {RequestMethod.POST})
    public ResultModel list(@Valid @RequestBody Users user, @CurrentUser Users auth) {
        return ResultModel.ok(usersService.getUserPermissions(user));
    }

}

