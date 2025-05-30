package com.flexible.service;

import com.flexible.entity.ModelHasRoles;
import com.baomidou.mybatisplus.extension.service.IService;
import com.flexible.entity.request.role.EditRoleRelationReq;
import com.flexible.entity.request.role.RoleRelationListReq;
import com.flexible.utils.response.ResultModel;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xlizz
 * @since 2025-03-18
 */
public interface IModelHasRolesService extends IService<ModelHasRoles> {
    String addModels(EditRoleRelationReq data,String modelType);

    String removeModels(EditRoleRelationReq data,String modelType);

    ResultModel getModelsByRoleId(RoleRelationListReq data,String modelType);


}
