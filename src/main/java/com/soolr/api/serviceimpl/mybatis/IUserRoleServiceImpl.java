package com.soolr.api.serviceimpl.mybatis;

import com.soolr.api.dao.mapper.UserRoleMapper;
import com.soolr.api.entity.Role;
import com.soolr.api.entity.UserRole;
import com.soolr.api.service.mybatis.IUserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Soolr
 */
@Service
public class IUserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<Role> findByUserId(String userId) {

        return userRoleMapper.findByUserId(userId);
    }
}
