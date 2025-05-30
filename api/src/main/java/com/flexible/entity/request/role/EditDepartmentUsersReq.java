package com.flexible.entity.request.role;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EditDepartmentUsersReq {
    
    @NotNull(message = "部门ID不能为空")
    private Integer departmentId;

    @NotEmpty(message = "用户ID不能为空")
    private List<Integer> userIds;

}
