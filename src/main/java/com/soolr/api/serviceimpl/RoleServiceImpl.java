package com.soolr.api.serviceimpl;

import com.soolr.api.dao.RoleDao;
import com.soolr.api.entity.Role;
import com.soolr.api.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色接口实现
 * @author Soolr
 */
@Slf4j
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public RoleDao getRepository() {
        return roleDao;
    }

    @Override
    public List<Role> findByDefaultRole(Boolean defaultRole) {
        return roleDao.findByDefaultRole(defaultRole);
    }
}
