package com.flexible.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flexible.core.annotation.CurrentUser;
import com.flexible.entity.Departments;
import com.flexible.entity.Permissions;
import com.flexible.entity.Users;
import com.flexible.entity.request.role.DepartmentUserListReq;
import com.flexible.entity.request.role.EditDepartmentUsersReq;
import com.flexible.entity.response.role.DepartmentTreeRes;
import com.flexible.mapper.DepartmentsMapper;
import com.flexible.mapper.PermissionsMapper;
import com.flexible.service.IDepartmentsService;
import com.flexible.service.IPermissionsService;
import com.flexible.service.IRelDepartmentUserService;
import com.flexible.utils.response.ResultModel;

import io.swagger.annotations.ApiOperation;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xlizz
 * @since 2025-03-18
 */
@RestController
@RequestMapping("/departments")
public class DepartmentsController {

  @Autowired
  IDepartmentsService departmentsService;


  @ApiOperation(value = "列表")
  @RequestMapping(value = "/list", method = { RequestMethod.POST })
  public ResultModel list(@Valid @RequestBody Departments data, @CurrentUser Users user) {
    return ResultModel.ok(departmentsService.list());
  }

  @ApiOperation(value = "保存")
  @RequestMapping(value = "/save", method = { RequestMethod.POST })
  public ResultModel save(@Valid @RequestBody Departments data, @CurrentUser Users user) {
    return ResultModel.ok(departmentsService.saveOrUpdate(data));
  }

  @ApiOperation(value = "删除")
  @RequestMapping(value = "/delete/{id}", method = { RequestMethod.DELETE })
  public ResultModel delete(@Valid @PathVariable Integer id, @CurrentUser Users user) {
    return ResultModel.ok(departmentsService.removeById(id));
  }

  @ApiOperation(value = "树结构组织")
  @RequestMapping(value = "/tree", method = { RequestMethod.POST })
  public ResultModel tree(@Valid @RequestBody Departments data, @CurrentUser Users user) {

    List<Departments> list = departmentsService.list().stream().sorted((a, b) -> a.getId() - b.getId())
        .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

    List<DepartmentTreeRes> tree = new ArrayList<>();
    

    Map<Integer, DepartmentTreeRes> map = new HashMap<>();
    for (Departments item : list) {
      DepartmentTreeRes node = new DepartmentTreeRes();
      node.setId(item.getId());
      node.setPid(item.getPid());
      node.setLabel(item.getName());
      map.put(item.getId(), node);
    }

    for (Departments item : list) {
      DepartmentTreeRes node = map.get(item.getId());
      if (item.getPid() == null || item.getPid() == 0) {
        tree.add(node);
      } else {
        DepartmentTreeRes parent = map.get(item.getPid());
        if (parent != null) {
          if (parent.getChildren() == null) {
            parent.setChildren(new ArrayList<>());
          }
          parent.getChildren().add(node);
        }
      }
    }

    return ResultModel.ok(tree);

  }

  @ApiOperation(value = "获取部门下的用户")
  @RequestMapping(value = "/users", method = { RequestMethod.POST })
  public ResultModel users(@Valid @RequestBody DepartmentUserListReq data, @CurrentUser Users user) {
    return departmentsService.getUsersByDepartmentId(data);
  }


  @ApiOperation(value = "添加部门用户")
  @RequestMapping(value = "/addUsers", method = { RequestMethod.POST })
  public ResultModel addUsers(@Valid @RequestBody EditDepartmentUsersReq data, @CurrentUser Users user) {
    return ResultModel.ok(departmentsService.addUsers(data));
  }


  @ApiOperation(value = "删除部门用户")
  @RequestMapping(value = "/removeUsers", method = { RequestMethod.POST })
  public ResultModel removeUsers(@Valid @RequestBody EditDepartmentUsersReq data, @CurrentUser Users user) {
    return ResultModel.ok(departmentsService.removeUsers(data));
  }

}

