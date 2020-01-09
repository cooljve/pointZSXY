package com.system.user.service;

import com.system.user.entity.SysRoleMenu;

import java.util.List;

public interface SysRoleMenuService {

    /**
     * 插入角色菜单中间表
     *
     * @param roleId
     * @param menusId
     */
    public void insertSysRoleMenu(Integer roleId, String menusId);

    /**
     * 根据角色查询菜单
     *
     * @param roleId
     * @return
     */
    public List<SysRoleMenu> listMenuByRoleId(Integer roleId);
}
