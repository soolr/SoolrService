package com.soolr.api.dao;

import com.soolr.api.base.ApiBaseDao;
import com.soolr.api.entity.Role;
import com.soolr.api.base.ApiBaseDao;
import com.soolr.api.entity.Role;

import java.util.List;

/**
 * 角色数据处理层
 * @author Soolr
 */
public interface RoleDao extends ApiBaseDao<Role,String> {

    /**
     * 获取默认角色
     * @param defaultRole
     * @return
     */
    List<Role> findByDefaultRole(Boolean defaultRole);
}
