package com.flexible.controller;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flexible.core.annotation.CurrentUser;
import com.flexible.entity.Users;
import com.flexible.entity.request.user.UsersLoginRequest;
import com.flexible.entity.response.user.UserInfoRes;
import com.flexible.entity.response.user.UsersLoginResponse;
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
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    IUsersService usersService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "名", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true)
    })

    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public ResultModel<UsersLoginResponse> login(@RequestBody UsersLoginRequest data) {
        return usersService.userLogin(data);
    }


    @ApiOperation(value = "用户信息")
    @RequestMapping(value = "/userInfo", method = {RequestMethod.GET})
    public ResultModel<Users> info(@CurrentUser Users user) {

        UserInfoRes userInfoRes = new UserInfoRes();
        BeanUtils.copyProperties(user, userInfoRes);

        return ResultModel.ok(userInfoRes);
    }

}

