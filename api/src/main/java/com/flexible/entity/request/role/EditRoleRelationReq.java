package com.flexible.entity.request.role;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class EditRoleRelationReq {
    
    @NotNull(message = "角色ID不能为空")
    private Long roleId;

    @NotEmpty(message = "关联ID不能为空")
    private List<Long> relationIds;

}
