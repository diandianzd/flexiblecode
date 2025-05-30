package com.flexible.entity.response.role;

import java.util.List;

@lombok.Data
public class PermissionsTreeRes {

  private Integer id;
  private String name;
  private String groupName;
  private String type;
  private List<PermissionsTreeRes> children;

}
