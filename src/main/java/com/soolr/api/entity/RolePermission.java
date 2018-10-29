package com.soolr.api.entity;

import com.soolr.api.base.ApiBaseEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Soolr
 */
@Data
@Entity
@Table(name = "t_role_permission")
@TableName("t_role_permission")
public class RolePermission extends ApiBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色id")
    private String roleId;

    @ApiModelProperty(value = "权限id")
    private String permissionId;
}