package com.flexible.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author xlizz
 * @since 2025-03-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="RelDepartmentUser对象", description="")
public class RelDepartmentUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private Integer departmentId;


}
