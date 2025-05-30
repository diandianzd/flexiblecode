package com.flexible.entity.request;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@ApiModel
@Data
@Accessors(chain = true)
public class BaseListRequest {

    @NotNull(message = "不能为空")
    @ApiModelProperty(value = "每页条数", required = true)
    private Integer pageSize;

    @NotNull(message = "不能为空")
    @ApiModelProperty(value = "页数", name = "page", required = true)
    private Integer page;

}