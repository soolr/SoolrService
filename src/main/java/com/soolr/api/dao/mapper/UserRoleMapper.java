package com.soolr.api.dao.mapper;

import com.soolr.api.entity.Role;
import com.soolr.api.entity.UserRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.soolr.api.entity.Role;
import com.soolr.api.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Soolr
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 通过用户id获取
     * @param userId
     * @return
     */
    List<Role> findByUserId(@Param("userId") String userId);
}
