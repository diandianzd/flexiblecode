package com.flexible.service;

import com.flexible.entity.Roles;
import com.flexible.entity.Users;
import com.flexible.entity.request.role.*;
import com.flexible.utils.page.PageList;

import javax.validation.Valid;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flexible.utils.response.ResultModel;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xlizz
 * @since 2025-02-06
 */
public interface IRolesService extends IService<Roles> {

    PageList<Roles> fetchList(RolesListRequest data, Users user);

    Roles getDetail(Integer id);

    Roles saveData(Roles data);




    String addPermissions(EditRoleRelationReq data);

    String removePermissions(EditRoleRelationReq data);

    ResultModel getPermissionsByRoleId(RoleRelationListReq data);


}
