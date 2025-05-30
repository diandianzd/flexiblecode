package com.flexible.mapper;

import com.flexible.entity.ModelHasRoles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flexible.entity.response.role.DepartmentUserRes;
import com.flexible.entity.response.role.ModelHasRoleRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xlizz
 * @since 2025-03-18
 */
public interface ModelHasRolesMapper extends BaseMapper<ModelHasRoles> {
    List<ModelHasRoleRes> getModelList(@Param("page") Integer page, @Param("pageSize") Integer pageSize,
                                       @Param("roleId") Long roleId, @Param("modelType") String modelType);

    Integer getModelListCount(@Param("roleId") Long roleId, @Param("modelType") String modelType);

}
