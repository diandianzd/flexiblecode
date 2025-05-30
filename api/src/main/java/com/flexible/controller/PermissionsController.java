package com.flexible.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flexible.core.annotation.CurrentUser;
import com.flexible.entity.Permissions;
import com.flexible.entity.Users;
import com.flexible.entity.request.user.UsersListRequest;
import com.flexible.entity.response.role.PermissionsTreeRes;
import com.flexible.service.IPermissionsService;
import com.flexible.utils.response.ResultModel;

import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xlizz
 * @since 2025-03-18
 */
@RestController
@RequestMapping("/permissions")
public class PermissionsController {

  @Autowired
  IPermissionsService permissionsService;

  @ApiOperation(value = "列表")
  @RequestMapping(value = "/tree", method = { RequestMethod.POST })
  public ResultModel tree(@Valid @RequestBody Permissions data, @CurrentUser Users user) {

    List<Permissions> permissionsList = permissionsService.list().stream().sorted((a, b) -> a.getId() - b.getId())
        .collect(Collectors.toList());

    List<PermissionsTreeRes> treeList = new ArrayList<>();
    // 根据groupe_name分组
    Map<String, List<Permissions>> groupMap = permissionsList.stream()
        .collect(Collectors.groupingBy(Permissions::getGroupName));

    groupMap.forEach((k, v) -> {
      PermissionsTreeRes tree = new PermissionsTreeRes();
      tree.setGroupName(k);
      List<PermissionsTreeRes> children = new ArrayList<>();
      v.forEach(p -> {
        PermissionsTreeRes child = new PermissionsTreeRes();
        child.setId(p.getId());
        child.setName(p.getName());
        child.setGroupName(p.getGroupName());
        child.setType("permission");
        children.add(child);
      });
      tree.setType("group");
      tree.setChildren(children);
      treeList.add(tree);
    });
    

    return ResultModel.ok(treeList);

  }

  @ApiOperation(value = "保存")
  @RequestMapping(value = "/save", method = { RequestMethod.POST })
  public ResultModel save(@Valid @RequestBody Permissions data, @CurrentUser Users user) {

    data.setGuardName("web");
    return ResultModel.ok(permissionsService.saveOrUpdate(data));
  }

  @ApiOperation(value = "删除")
  @RequestMapping(value = "/delete/{id}", method = { RequestMethod.DELETE })
  public ResultModel delete(@Valid @PathVariable Integer id, @CurrentUser Users user) {
    return ResultModel.ok(permissionsService.removeById(id));
  }

}
