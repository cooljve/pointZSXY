package com.system.user.service.impl;

import com.system.core.util.ParamUtil;
import com.system.user.dao.SysRoleMenuDao;
import com.system.user.entity.SysRoleMenu;
import com.system.user.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.system.core.util.Constant.COMMA;

@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    /**
     * 插入角色菜单中间表
     *
     * @param roleId
     * @param menuIdStr
     */
    @Transactional
    @Override
    public void insertSysRoleMenu(Integer roleId, String menuIdStr) {
        sysRoleMenuDao.delByRoleId(roleId);
        if (!StringUtils.isEmpty(menuIdStr)) {
            Integer[] menuIdInt = ParamUtil.toIntegers(menuIdStr.split(COMMA));
            for (Integer id : menuIdInt) {
                SysRoleMenu sysRoleMenu = generateSysRoleMenu(roleId, id);
                sysRoleMenuDao.save(sysRoleMenu);
            }
        }
    }

    private SysRoleMenu generateSysRoleMenu(Integer roleId, Integer id) {
        SysRoleMenu sysRoleMenu = new SysRoleMenu();
        sysRoleMenu.setSysRoleId(roleId);
        sysRoleMenu.setSysMenuId(id);
        return sysRoleMenu;
    }

    /**
     * 根据角色查询菜单
     *
     * @param roleId
     * @return
     */
    @Override
    public List<SysRoleMenu> listMenuByRoleId(Integer roleId) {
        return sysRoleMenuDao.findByRoleId(roleId);
    }

}
