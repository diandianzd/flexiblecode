package com.flexible.entity.response.user;

import com.flexible.entity.Departments;
import com.flexible.entity.Permissions;
import com.flexible.entity.Roles;
import lombok.Data;

import java.util.List;

@Data
public class UserPermissions {
    private Integer userId;

    private List<Departments> departments;

    private List<Roles> roles;

    private List<Permissions> permissions;
}
