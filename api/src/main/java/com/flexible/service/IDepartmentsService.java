package com.flexible.service;

import com.flexible.entity.Departments;
import com.flexible.entity.request.role.DepartmentUserListReq;
import com.flexible.entity.request.role.EditDepartmentUsersReq;
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
public interface IDepartmentsService extends IService<Departments> {

  String addUsers(EditDepartmentUsersReq data);

  String removeUsers(EditDepartmentUsersReq data);

  ResultModel getUsersByDepartmentId(DepartmentUserListReq data);

}
