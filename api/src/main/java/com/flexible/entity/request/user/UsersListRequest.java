package com.flexible.entity.request.user;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

import com.flexible.entity.request.BaseListRequest;

/**
 * <p>
 *
 * </p>
 *
 * @author abc
 * @since 2021-09-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CompaniesParameters对象", description = "")
@Accessors(chain = true)
public class UsersListRequest extends BaseListRequest implements Serializable {

    private String name;

    private String email;
}