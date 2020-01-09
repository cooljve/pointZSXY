package com.system.user.service.impl;

import com.system.user.dao.SysRoleDao;
import com.system.user.entity.SysRole;
import com.system.user.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public SysRole getSysRoleById(Integer id) {
        return sysRoleDao.findOne(id);
    }

    @Override
    public List<SysRole> getSysRolesByUserId(Integer userId) {
        return sysRoleDao.findBySysUserId(userId);
    }

    @Override
    public List<SysRole> findSysRole() {
        return sysRoleDao.findSysRole();
    }
}
