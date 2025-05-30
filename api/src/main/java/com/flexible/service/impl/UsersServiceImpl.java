package com.flexible.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flexible.core.exception.FlexErrorException;
import com.flexible.entity.*;
import com.flexible.entity.request.user.UsersListRequest;
import com.flexible.entity.request.user.UsersLoginRequest;
import com.flexible.entity.response.user.UserPermissions;
import com.flexible.entity.response.user.UsersListResponse;
import com.flexible.entity.response.user.UsersLoginResponse;
import com.flexible.mapper.*;
import com.flexible.service.IUsersService;
import com.flexible.utils.JwtTools;
import com.flexible.utils.page.PageList;
import com.flexible.utils.page.PageUtils;
import com.flexible.utils.response.ResultModel;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xlizz
 * @since 2021-12-17
 */
@Slf4j
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    @Autowired
    UsersMapper userMapper;

    @Autowired
    RelDepartmentUserMapper relDepartmentUserMapper;


    @Autowired
    DepartmentsMapper departmentsMapper;

    @Autowired
    RolesMapper rolesMapper;

    @Autowired
    ModelHasRolesMapper modelHasRolesMapper;


    @Autowired
    PermissionsMapper permissionsMapper;

    @Override
    @Transactional(rollbackFor = {Exception.class, FlexErrorException.class, RuntimeException.class})
    public ResultModel userLogin(UsersLoginRequest data) {

        // 查询数据
        // todo 需要对密码进行校验
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(data.getUsername()), "email", data.getUsername()).isNull("deleted_at");

        Users user = this.getOne(queryWrapper);
        if (user == null) {
            throw new FlexErrorException("用户不存在");
        }
        // 校验密码是否正确
        // log.info("encode password", JwtTools.enCodePassword(data.getPassword()));
        System.out.println("Encode password:" + JwtTools.enCodePassword(data.getPassword()));

        if (!JwtTools.enCodePassword(data.getPassword()).equals(user.getPassword())) {
            throw new FlexErrorException("用户名或密码错误");
        }
        // 如果数据库存在token, 允许多用户登录
        String token = user.getRememberToken();
        // 如果token为空或者强制更新token
        if (StringUtils.isEmpty(token)) {
            token = JwtTools.generateToken(user.getId().toString());
        }

        System.out.println("Token:" + token);

        UsersLoginResponse res = new UsersLoginResponse();
        res.setAccess_token(token);

        user.setRememberToken(token);
        userMapper.updateById(user);
        // content.put("token", DigestUtils.md5DigestAsHex(data.getOrDefault("email",
        // "").toString().getBytes()));
        return ResultModel.ok(res);
    }

    @Override
    public ResultModel fetchList(UsersListRequest data, Users user) {

        // 响应对象
        PageList<UsersListResponse> resData = new PageList<>();
        // 设置分页
        // import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
        Page<Users> pager = new Page<>(data.getPage(), data.getPageSize());

        // 查询数据
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("deleted_at").eq(StringUtils.isNotEmpty(data.getName()), "name", data.getName()).orderByDesc("id");

        Page<Users> userList = userMapper.selectPage(pager, queryWrapper);

        // 原生分页字段转换
        PageList<Users> pageList = PageUtils.toCustomPage(userList);
        // 业务数据转换
        List<UsersListResponse> resList = new ArrayList<>();
        for (Users model : pageList.getList()) {
            UsersListResponse usersListResponse = new UsersListResponse();
            BeanUtils.copyProperties(model, usersListResponse);

            resList.add(usersListResponse);
        }
        resData.setList(resList);
        resData.setPagination(pageList.getPagination());
        return ResultModel.ok(resData);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, FlexErrorException.class, RuntimeException.class})
    public Users userAuth(String token) {

        // 查询数据
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("remember_token", token);

        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public UserPermissions getUserPermissions(Users user) {


        UserPermissions userPermissions = new UserPermissions();
        // 获取用户部门
        List<Departments> departments = departmentsMapper.selectList(new QueryWrapper<Departments>().inSql("id", "SELECT department_id FROM rel_department_user WHERE user_id = " + user.getId()));
        userPermissions.setDepartments(departments);

        // 获取用户角色
        List<Roles> roles = rolesMapper.selectList(new QueryWrapper<Roles>()
                .inSql("id", "SELECT role_id FROM model_has_roles WHERE model_id = " + user.getId() + " AND model_type = 'user'"));
        userPermissions.setRoles(roles);

        // 获取用户全部权限
        List<Roles> deptHasRoles = rolesMapper.selectList(new QueryWrapper<Roles>()
                .inSql("id", "SELECT role_id FROM model_has_roles WHERE model_id IN (SELECT department_id FROM rel_department_user WHERE user_id = " + user.getId() + ") AND model_type = 'department'"));


        List<Integer> roleIds = roles.stream().map(Roles::getId).collect(Collectors.toList());
        List<Integer> deptRoleIds = deptHasRoles.stream().map(Roles::getId).collect(Collectors.toList());

        roleIds.addAll(deptRoleIds);
        roleIds = roleIds.stream().distinct().collect(Collectors.toList());

        List<Permissions> permissions = permissionsMapper.selectList(new QueryWrapper<Permissions>()
                .inSql("id", "SELECT permission_id FROM role_has_permissions WHERE role_id IN (" + StringUtils.join(roleIds, ",") + ")"));

        userPermissions.setPermissions(permissions);

        return userPermissions;
    }

}
