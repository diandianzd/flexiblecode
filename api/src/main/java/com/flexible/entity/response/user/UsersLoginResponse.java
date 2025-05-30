package com.flexible.entity.response.user;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author abc
 * @since 2021-09-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UsersLoginResponse {

    public String access_token;

}