package com.system.user.service;

import com.system.user.entity.SysRole;

import java.util.List;

public interface SysRoleService {
    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    public SysRole getSysRoleById(Integer id);

    /**
     * 根据当前登录的用户ID查询出角色信息
     *
     * @param userId
     * @return
     */
    public List<SysRole> getSysRolesByUserId(Integer userId);

    /**
     * 查询角色的集合
     *
     * @return
     */
    public List<SysRole> findSysRole();
}

