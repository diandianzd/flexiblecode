package com.flexible.entity.response.role;

import java.util.List;

@lombok.Data
public class DepartmentTreeRes {

  private Integer id;
  private Integer pid;
  private String label;
  private List<DepartmentTreeRes> children;

}
