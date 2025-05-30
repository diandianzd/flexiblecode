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
 * @since 2025-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ModelHasRoles对象", description="")
public class ModelHasRoles implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private String modelType;

    private Long modelId;


}
