package com.soolr.api.dao;

import com.soolr.api.base.ApiBaseDao;
import com.soolr.api.entity.RolePermission;
import com.soolr.api.base.ApiBaseDao;
import com.soolr.api.entity.RolePermission;

import java.util.List;

/**
 * 角色权限数据处理层
 * @author Soolr
 */
public interface RolePermissionDao extends ApiBaseDao<RolePermission,String> {

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