package com.system.user.service.impl;

import com.system.core.util.ParamUtil;
import com.system.user.dao.SysMenuDao;
import com.system.user.dao.SysRoleMenuDao;
import com.system.user.entity.SysMenu;
import com.system.user.service.SysMenuService;
import com.system.user.web.model.SysMenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static com.system.core.util.Constant.COMMA;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;//角色菜单中间表

    /**
     * 查询所有的菜单
     *
     * @return
     */
    public List<SysMenu> findMenuList() {
        return sysMenuDao.findAll();
    }

    @Override
    public SysMenu getMenu(Integer id) {
        return sysMenuDao.findOne(id);
    }

    @Override
    public Integer addSysMenu(SysMenuModel model) {
        SysMenu sysMenu = new SysMenu();
        ParamUtil.bindBean(sysMenu, model);
        sysMenu.setParentMenu(new SysMenu(model.getParentId()));
        sysMenuDao.save(sysMenu);
        return sysMenu.getId();
    }

    @Override
    public Integer updateSysMenu(Integer id, SysMenuModel model) {
        SysMenu sysMenu = sysMenuDao.findOne(id);
        ParamUtil.bindBean(sysMenu, model);
        sysMenu.setParentMenu(new SysMenu(model.getParentId()));
        sysMenuDao.save(sysMenu);
        return sysMenu.getId();
    }

    @Override
    @Transactional
    public void delSysMenu(String ids) {
        if (!StringUtils.isEmpty(ids)) {
            Integer[] ides = ParamUtil.toIntegers(ids.split(COMMA));
            Arrays.stream(ides).forEach(menuId -> {
                deleteChildMenu(menuId);
                deleteRelatedMenu(menuId);
            });
        }
    }

    private void deleteChildMenu(Integer menuId) {
        List<SysMenu> childMenus = sysMenuDao.findByParentId(menuId);
        if (childMenus != null && !childMenus.isEmpty()) {
            childMenus.forEach(x -> deleteRelatedMenu(x.getId()));
        }
    }

    private void deleteRelatedMenu(Integer id) {
        sysRoleMenuDao.delByMenuId(id);
        sysMenuDao.delete(id);
    }
}
