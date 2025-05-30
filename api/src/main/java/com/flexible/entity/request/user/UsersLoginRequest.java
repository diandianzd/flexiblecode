package com.flexible.entity.request.user;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.NotEmpty;

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
public class UsersLoginRequest {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

}