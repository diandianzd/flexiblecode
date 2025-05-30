package com.flexible.entity.request.role;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

import com.flexible.entity.request.BaseListRequest;


@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CompaniesParameters对象", description = "")
@Accessors(chain = true)
public class DepartmentUserListReq extends BaseListRequest implements Serializable {
    private Integer departmentId;

}