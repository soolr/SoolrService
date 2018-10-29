package com.soolr.api.dao;

import com.soolr.api.base.ApiBaseDao;
import com.soolr.api.entity.User;
import com.soolr.api.base.ApiBaseDao;
import com.soolr.api.entity.User;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * 用户数据处理层
 * @author Soolr
 */
public interface UserDao extends ApiBaseDao<User,String> {

    /**
     * 通过用户名和状态获取用户
     * @param username
     * @param status
     * @return
     */
    List<User> findByUsernameAndStatus(String username, Integer status);

    /**
     * 通过手机和状态获取用户
     * @param mobile
     * @param status
     * @return
     */
    List<User> findByMobileAndStatus(String mobile, Integer status);

    /**
     * 通过状态和类型获取用户
     * @param status
     * @param type
     * @return
     */
    List<User> findByStatusAndType(Integer status, Integer type);

    /**
     * 通过部门id获取
     * @param departmentId
     * @return
     */
    List<User> findByDepartmentId(String departmentId);
}
