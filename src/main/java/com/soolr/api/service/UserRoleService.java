package com.soolr.api.service;


import com.soolr.api.base.ApiBaseService;
import com.soolr.api.dao.RoleDao;
import com.soolr.api.entity.Role;
import com.soolr.api.entity.UserRole;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 用户角色接口
 * @author Soolr
 */
public interface UserRoleService extends ApiBaseService<UserRole,String> {

    /**
     * 通过roleId查找
     * @param roleId
     * @return
     */
    List<UserRole> findByRoleId(String roleId);

    /**
     * 删除用户角色
     * @param userId
     */
    void deleteByUserId(String userId);
}
