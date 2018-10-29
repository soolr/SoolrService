package com.soolr.api.service;

import com.soolr.api.base.ApiBaseService;
import com.soolr.api.entity.RolePermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 角色权限接口
 * @author Soolr
 */
public interface RolePermissionService extends ApiBaseService<RolePermission,String> {

    /**
     * 通过permissionId获取
     * @param permissionId
     * @return
     */
    List<RolePermission> findByPermissionId(String permissionId);

    /**
     * 通过roleId删除
     * @param roleId
     */
    void deleteByRoleId(String roleId);
}