package com.flexible.entity.request.role;


import com.flexible.entity.request.BaseListRequest;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CompaniesParameters对象", description = "")
@Accessors(chain = true)
public class RoleRelationListReq extends BaseListRequest implements Serializable {
    private Long roleId;

}