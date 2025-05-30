package com.flexible.service.impl;

import com.flexible.entity.Departments;
import com.flexible.entity.RelDepartmentUser;
import com.flexible.entity.request.role.DepartmentUserListReq;
import com.flexible.entity.request.role.EditDepartmentUsersReq;
import com.flexible.entity.response.role.DepartmentUserRes;
import com.flexible.mapper.DepartmentsMapper;
import com.flexible.service.IDepartmentsService;
import com.flexible.service.IRelDepartmentUserService;
import com.flexible.utils.page.PageList;
import com.flexible.utils.page.PageUtils;
import com.flexible.utils.response.ResultModel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xlizz
 * @since 2025-03-18
 */
@Service
public class DepartmentsServiceImpl extends ServiceImpl<DepartmentsMapper, Departments> implements IDepartmentsService {

  @Autowired
  DepartmentsMapper departmentsMapper;


  @Autowired
  private IRelDepartmentUserService relDepartmentUserService;


  @Override
  public String addUsers(EditDepartmentUsersReq data) {

    List<RelDepartmentUser> list = new ArrayList<RelDepartmentUser>();

    for (Integer userId : data.getUserIds()) {
      RelDepartmentUser relDepartmentUser = new RelDepartmentUser();
      relDepartmentUser.setDepartmentId(data.getDepartmentId());
      relDepartmentUser.setUserId(userId);
      list.add(relDepartmentUser);
    }

    relDepartmentUserService.saveBatch(list);

    return "ok";

  }

  @Override
  public String removeUsers(EditDepartmentUsersReq data) {

    QueryWrapper<RelDepartmentUser> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("department_id", data.getDepartmentId());
    queryWrapper.in("user_id", data.getUserIds());

    relDepartmentUserService.remove(queryWrapper);

    return "ok";
  }

  @Override
  public ResultModel getUsersByDepartmentId(DepartmentUserListReq data) {
    // 响应对象
    PageList<DepartmentUserRes> resData = new PageList<>();
    // 设置分页
    // import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
    Page<DepartmentUserRes> pager = new Page<>(data.getPage(), data.getPageSize());

    List<DepartmentUserRes> userList = departmentsMapper.getUserList(data.getPage(), data.getPageSize(),
        data.getDepartmentId());

    Integer userListCount = departmentsMapper.getUserListCount(data.getDepartmentId());

    System.out.println(userListCount);

    pager.setTotal(userListCount);


    PageList<DepartmentUserRes> pageList = PageUtils.toCustomPage(pager);

    resData.setList(userList);
    resData.setPagination(pageList.getPagination());

    return ResultModel.ok(resData);

  }

}
