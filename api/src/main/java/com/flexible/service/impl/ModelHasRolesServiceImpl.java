package com.flexible.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.flexible.entity.ModelHasRoles;
import com.flexible.entity.RelDepartmentUser;
import com.flexible.entity.request.role.EditRoleRelationReq;
import com.flexible.entity.request.role.RoleRelationListReq;
import com.flexible.entity.response.role.DepartmentUserRes;
import com.flexible.entity.response.role.ModelHasRoleRes;
import com.flexible.mapper.ModelHasRolesMapper;
import com.flexible.service.IModelHasRolesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flexible.utils.page.PageList;
import com.flexible.utils.page.PageUtils;
import com.flexible.utils.response.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xlizz
 * @since 2025-03-18
 */
@Service
public class ModelHasRolesServiceImpl extends ServiceImpl<ModelHasRolesMapper, ModelHasRoles> implements IModelHasRolesService {

    @Autowired
    ModelHasRolesMapper modelHasRolesMapper;

    @Override
    public String addModels(EditRoleRelationReq data,String modelType) {



        List<ModelHasRoles> list = new ArrayList<ModelHasRoles>();
        for (Long relationId : data.getRelationIds()){
            ModelHasRoles modelHasRoles = new ModelHasRoles();
            modelHasRoles.setModelId(relationId);
            modelHasRoles.setRoleId(data.getRoleId());
            modelHasRoles.setModelType(modelType);
            list.add(modelHasRoles);
        }

        // 这里需要添加用户和角色的关系
        this.saveBatch(list);

        return "ok";

    }

    @Override
    public String removeModels(EditRoleRelationReq data,String modelType) {
        QueryWrapper<ModelHasRoles> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", data.getRoleId());
        queryWrapper.eq("model_type", modelType);
        queryWrapper.in("model_id", data.getRelationIds());

        this.remove(queryWrapper);

        return "ok";
    }

    @Override
    public ResultModel getModelsByRoleId(RoleRelationListReq data,String modelType) {
        // 响应对象
        PageList<ModelHasRoleRes> resData = new PageList<>();
        // 设置分页
        // import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
        Page<ModelHasRoleRes> pager = new Page<>(data.getPage(), data.getPageSize());

        List<ModelHasRoleRes> userList = modelHasRolesMapper.getModelList(data.getPage(), data.getPageSize(),
                data.getRoleId(),modelType);

        Integer userListCount = modelHasRolesMapper.getModelListCount(data.getRoleId(),modelType);

        System.out.println(userListCount);

        pager.setTotal(userListCount);


        PageList<ModelHasRoleRes> pageList = PageUtils.toCustomPage(pager);

        resData.setList(userList);
        resData.setPagination(pageList.getPagination());

        return ResultModel.ok(resData);
    }

}
