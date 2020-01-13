package com.system.user.service;

import com.system.user.entity.SysMenu;
import com.system.user.web.model.SysMenuModel;

import java.util.List;

public interface SysMenuService {
    /**
     * 查询所有的菜单
     *
     * @return
     */
    List<SysMenu> findMenuList();

    /**
     * 查询一个
     *
     * @param id
     * @return
     */
    SysMenu getMenu(Integer id);

    /**
     * 添加
     *
     * @param model
     */
    Integer addSysMenu(SysMenuModel model);

    /**
     * 更新
     *
     * @param model
     */
    Integer updateSysMenu(Integer id, SysMenuModel model);

    /**
     * 删除菜单
     *
     * @param ids
     */
    void delSysMenu(String ids);

}

