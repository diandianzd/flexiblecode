package com.flexible.entity.response.role;

import lombok.Data;

@Data
public class RoleHasPermissionRes {
  Long id;
  String permissionName;
  Long permissionId;
  Long roleId;
}
