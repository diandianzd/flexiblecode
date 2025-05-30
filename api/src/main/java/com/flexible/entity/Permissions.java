package com.flexible.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author xlizz
 * @since 2025-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Permissions对象", description="")
public class Permissions implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private static final long serialVersionUID = 1L;

    private String name;

    private String guardName;

    private String groupName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
