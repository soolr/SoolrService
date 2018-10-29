package com.soolr.api.service;

import com.soolr.api.base.ApiBaseService;
import com.soolr.api.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 部门接口
 * @author Soolr
 */
public interface DepartmentService extends ApiBaseService<Department,String> {

    /**
     * 通过父id获取 升序
     * @param parentId
     * @return
     */
    List<Department> findByParentIdOrderBySortOrder(String parentId);

    /**
     * 通过父id和状态获取
     * @param parentId
     * @param status
     * @return
     */
    List<Department> findByParentIdAndStatusOrderBySortOrder(String parentId, Integer status);
}