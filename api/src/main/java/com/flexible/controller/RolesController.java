package com.flexible.controller;

import java.util.stream.Collectors;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flexible.entity.RoleHasPermissions;
import com.flexible.entity.request.role.*;
import com.flexible.service.IModelHasRolesService;
import com.flexible.service.IRoleHasPermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flexible.core.annotation.CurrentUser;
import com.flexible.entity.Roles;
import com.flexible.entity.Users;
import com.flexible.service.IRolesService;
import com.flexible.utils.response.ResultModel;

import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xlizz
 * @since 2025-02-06
 */
@RestController
@RequestMapping("/roles")
public class RolesController {

    @Autowired
    IRolesService rolesService;

    @Autowired
    IRoleHasPermissionsService roleHasPermissionsService;


    @Autowired
    IModelHasRolesService modelHasRolesService;

    @ApiOperation(value = "列表")
    @RequestMapping(value = "/list", method = { RequestMethod.POST })
    public ResultModel list(@Valid @RequestBody Roles data, @CurrentUser Users user) {
        return ResultModel.ok(rolesService.list().stream().sorted(
                (a, b) -> b.getId().compareTo(a.getId())).collect(Collectors.toList()));
    }

    @ApiOperation(value = "详情")
    @RequestMapping(value = "/detail", method = { RequestMethod.GET })
    public ResultModel detail(@Valid @RequestParam Integer id, @CurrentUser Users user) {
        return ResultModel.ok(rolesService.getDetail(id));
    }

    @ApiOperation(value = "保存")
    @RequestMapping(value = "/save", method = { RequestMethod.POST })
    public ResultModel save(@Valid @RequestBody Roles data, @CurrentUser Users user) {
        return ResultModel.ok(rolesService.saveData(data));
    }

    @ApiOperation(value = "删除")
    @RequestMapping(value = "/delete/{id}", method = { RequestMethod.DELETE })
    public ResultModel delete(@Valid @PathVariable Integer id, @CurrentUser Users user) {
        return ResultModel.ok(rolesService.removeById(id));
    }



    @ApiOperation(value = "获取角色下的用户")
    @RequestMapping(value = "/users", method = { RequestMethod.POST })
    public ResultModel users(@Valid @RequestBody RoleRelationListReq data, @CurrentUser Users user) {
        return modelHasRolesService.getModelsByRoleId(data,"user");
    }


    @ApiOperation(value = "添加角色用户")
    @RequestMapping(value = "/addUsers", method = { RequestMethod.POST })
    public ResultModel addUsers(@Valid @RequestBody EditRoleRelationReq data, @CurrentUser Users user) {
        return ResultModel.ok(modelHasRolesService.addModels(data,"user"));
    }


    @ApiOperation(value = "删除角色用户")
    @RequestMapping(value = "/removeUsers", method = { RequestMethod.POST })
    public ResultModel removeUsers(@Valid @RequestBody EditRoleRelationReq data, @CurrentUser Users user) {
        return ResultModel.ok(modelHasRolesService.removeModels(data,"user"));
    }


    @ApiOperation(value = "获取角色下的部门")
    @RequestMapping(value = "/departments", method = { RequestMethod.POST })
    public ResultModel departments(@Valid @RequestBody RoleRelationListReq data, @CurrentUser Users user) {
        return modelHasRolesService.getModelsByRoleId(data,"department");
    }


    @ApiOperation(value = "添加角色部门")
    @RequestMapping(value = "/addDepartments", method = { RequestMethod.POST })
    public ResultModel addDepartments(@Valid @RequestBody EditRoleRelationReq data, @CurrentUser Users user) {
        return ResultModel.ok(modelHasRolesService.addModels(data,"department"));
    }


    @ApiOperation(value = "删除角色部门")
    @RequestMapping(value = "/removeDepartments", method = { RequestMethod.POST })
    public ResultModel removeDepartments(@Valid @RequestBody EditRoleRelationReq data, @CurrentUser Users user) {
        return ResultModel.ok(modelHasRolesService.removeModels(data,"department"));
    }


    @ApiOperation(value = "获取角色下的权限")
    @RequestMapping(value = "/permissions", method = { RequestMethod.POST })
    public ResultModel permissions(@Valid @RequestBody RoleHasPermissions data, @CurrentUser Users user) {

        QueryWrapper<RoleHasPermissions> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", data.getRoleId());

        return ResultModel.ok(roleHasPermissionsService.list(queryWrapper));
    }


    @ApiOperation(value = "Check角色权限")
    @RequestMapping(value = "checkPermissions", method = { RequestMethod.POST })
    public ResultModel checkPermissions(@Valid @RequestBody EditRoleRelationReq data, @CurrentUser Users user) {
        RoleHasPermissions exist = roleHasPermissionsService.getOne(new QueryWrapper<RoleHasPermissions>()
                .eq("role_id", data.getRoleId())
                .in("permission_id", data.getRelationIds()));
        if (exist != null) {
            roleHasPermissionsService.remove(new QueryWrapper<RoleHasPermissions>()
                    .eq("role_id", data.getRoleId())
                    .in("permission_id", data.getRelationIds()));
        } else {
            for (Long permissionId : data.getRelationIds()) {
                RoleHasPermissions roleHasPermissions = new RoleHasPermissions();
                roleHasPermissions.setRoleId(data.getRoleId());
                roleHasPermissions.setPermissionId(permissionId);
                roleHasPermissionsService.save(roleHasPermissions);
            }
        }

        return ResultModel.ok();
    }

}
