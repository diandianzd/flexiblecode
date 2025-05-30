package com.flexible.service.impl;

import com.flexible.entity.Permissions;
import com.flexible.entity.Users;
import com.flexible.entity.request.user.UsersListRequest;
import com.flexible.entity.response.user.UsersListResponse;
import com.flexible.mapper.PermissionsMapper;
import com.flexible.mapper.UsersMapper;
import com.flexible.service.IPermissionsService;
import com.flexible.utils.page.PageList;
import com.flexible.utils.page.PageUtils;
import com.flexible.utils.response.ResultModel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

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
 * @since 2025-03-18
 */
@Service
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper, Permissions> implements IPermissionsService {

  @Autowired
  PermissionsMapper permissionsMapper;

  @Override
  public ResultModel fetchList(Users user) {
    return ResultModel.ok(permissionsMapper.selectList(null));
  }

}
