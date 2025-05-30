package com.flexible.entity.request.plugins;

import java.io.Serializable;

import com.flexible.entity.request.BaseListRequest;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CompaniesParameters对象", description = "")
@Accessors(chain = true)
public class PluginListReq  extends BaseListRequest implements Serializable  {
    private String name;
}