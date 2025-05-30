package com.flexible.entity.response.role;

import lombok.Data;

@Data
public class ModelHasRoleRes {
  Long id;
  String modelName;
  Long modelId;
  Long roleId;
}
