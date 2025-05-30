package com.flexible.service.impl;

import com.flexible.entity.ModelHasRoles;
import com.flexible.entity.Roles;
import com.flexible.entity.Users;
import com.flexible.entity.request.role.EditRoleRelationReq;
import com.flexible.entity.request.role.RoleRelationListReq;
import com.flexible.entity.request.role.RolesListRequest;
import com.flexible.mapper.RolesMapper;
import com.flexible.service.IRolesService;
import com.flexible.utils.page.PageList;
import com.flexible.utils.page.PageUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import com.flexible.utils.response.ResultModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xlizz
 * @since 2025-02-06
 */
@Service
public class RolesServiceImpl extends ServiceImpl<RolesMapper, Roles> implements IRolesService {

    @Autowired
    RolesMapper rolesMapper;

    @Override
    public PageList<Roles> fetchList(RolesListRequest data, Users user) {

        Page<Roles> pager = new Page<>(data.getPage(), data.getPageSize());
        // 查询数据
        QueryWrapper<Roles> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(data.getName()), "name", data.getName())
                .orderByDesc("id");

        IPage<Roles> iPage = rolesMapper.selectPage(pager, queryWrapper);

        PageList<Roles> pageList = PageUtils.toCustomPage(iPage);

        PageList<Roles> resData = new PageList<>();
        List<Roles> resList = new ArrayList<>();

        for (Roles model : pageList.getList()) {
            // BeanUtils.copyProperties(resData, resList);
            resList.add(model);
        }

        // 业务数据转换

        resData.setList(resList);
        resData.setPagination(pageList.getPagination());
        return resData;

    }

    @Override
    public Roles getDetail(Integer id) {
        Roles roles = rolesMapper.selectById(id);
        return roles;
    }

    @Override
    public Roles saveData(Roles data) {

        data.setGuardName("web");
        if (data.getId() != null) {
            rolesMapper.updateById(data);
        } else {
            rolesMapper.insert(data);
        }

        return data;
    }




    @Override
    public String addPermissions(EditRoleRelationReq data) {
        return "";
    }

    @Override
    public String removePermissions(EditRoleRelationReq data) {
        return "";
    }

    @Override
    public ResultModel getPermissionsByRoleId(RoleRelationListReq data) {
        return null;
    }


}
