package com.flexible.mapper;

import com.flexible.entity.Departments;
import com.flexible.entity.Users;
import com.flexible.entity.response.role.DepartmentUserRes;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xlizz
 * @since 2025-03-18
 */
public interface DepartmentsMapper extends BaseMapper<Departments> {
  List<DepartmentUserRes> getUserList(@Param("page") Integer page, @Param("pageSize") Integer pageSize,
      @Param("departmentId") Integer departmentId);

  Integer getUserListCount(@Param("departmentId") Integer departmentId);


    // @Update("Update user set name=#{name} WHERE id=#{id}")
    // int setUser(@Param("id") Long id, @Param("name") String name);

    // @Select("SELECT * FROM users WHERE email=#{email}")
    // Users getUser(String email);

}
