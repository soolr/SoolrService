package com.soolr.api.dao;

import com.soolr.api.base.ApiBaseDao;
import com.soolr.api.entity.Department;
import com.soolr.api.base.ApiBaseDao;
import com.soolr.api.entity.Department;

import java.util.List;

/**
 * 部门数据处理层
 * @author Soolr
 */
public interface DepartmentDao extends ApiBaseDao<Department,String> {

    /**
     * 通过父id获取 升序
     * @param parentId
     * @return
     */
    List<Department> findByParentIdOrderBySortOrder(String parentId);

    /**
     * 通过父id和状态获取 升序
     * @param parentId
     * @param status
     * @return
     */
    List<Department> findByParentIdAndStatusOrderBySortOrder(String parentId, Integer status);
}