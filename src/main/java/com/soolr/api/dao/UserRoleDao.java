package com.soolr.api.dao;

import com.soolr.api.base.ApiBaseDao;
import com.soolr.api.entity.Role;
import com.soolr.api.entity.UserRole;
import com.soolr.api.base.ApiBaseDao;
import com.soolr.api.entity.UserRole;
import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 用户角色数据处理层
 * @author Soolr
 */
public interface UserRoleDao extends ApiBaseDao<UserRole,String> {

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
