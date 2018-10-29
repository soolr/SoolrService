package com.soolr.api.service;


import com.soolr.api.base.ApiBaseService;
import com.soolr.api.entity.Role;

import java.util.List;

/**
 * 角色接口
 * @author Soolr
 */
public interface RoleService extends ApiBaseService<Role,String> {

    /**
     * 获取默认角色
     * @param defaultRole
     * @return
     */
    List<Role> findByDefaultRole(Boolean defaultRole);
}
