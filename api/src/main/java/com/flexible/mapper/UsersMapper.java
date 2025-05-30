package com.flexible.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flexible.entity.Users;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author abc
 * @since 2021-09-02
 */

public interface UsersMapper extends BaseMapper<Users> {

    int insert(Users record);

    @Update("Update user set name=#{name} WHERE id=#{id}")
    int setUser(@Param("id") Long id, @Param("name") String name);

    @Select("SELECT * FROM users WHERE email=#{email}")
    Users getUser(String email);

}
