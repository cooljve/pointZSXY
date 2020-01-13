package com.system.user.service.impl;

import com.system.user.dao.SysMenuDao;
import com.system.user.dao.SysRoleMenuDao;
import com.system.user.entity.SysMenu;
import com.system.user.entity.SysRoleMenu;
import com.system.user.web.model.SysMenuModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysMenuServiceImplTest {

    public static final String SYSTEM_MANAGE = "系统管理";
    public static final String TEST_MENU = "测试菜单";
    public static final String UPDATED_TEST_MENU = "更新测试菜单";
    @Autowired
    private SysMenuServiceImpl sysMenuService;

    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    @Test
    public void findListMenu() {
        List<SysMenu> listMenu = sysMenuService.findMenuList();

        assertEquals(19, listMenu.size());
    }

    @Test
    public void getMenu() {
        SysMenu menu = sysMenuService.getMenu(2);

        assertEquals(SYSTEM_MANAGE, menu.getAlias());
        assertEquals(SYSTEM_MANAGE, menu.getName());
        assertEquals(SYSTEM_MANAGE, menu.getDescription());
        assertEquals(1, menu.getParentMenu().getId());
        assertEquals(2, menu.getId());
    }

    @Test
//    @Rollback(false) //真的去插入数据库
    public void addSysMenu() {
        SysMenuModel model = expectSysMenuModel();
        List<SysMenu> listMenu = sysMenuService.findMenuList();

        sysMenuService.addSysMenu(model);
        List<SysMenu> newListMenu = sysMenuService.findMenuList();

        assertEquals(listMenu.size() + 1, newListMenu.size());
    }

    @Test
    public void updateSysMenu() {
        SysMenuModel model = expectSysMenuModel();
        sysMenuService.addSysMenu(model);
        List<SysMenu> listMenu = sysMenuService.findMenuList();
        Integer lastId = listMenu.get(listMenu.size() - 1).getId();
        model.setName(UPDATED_TEST_MENU);

        sysMenuService.updateSysMenu(lastId, model);
        SysMenu updatedSysMenu = sysMenuDao.findOne(lastId);

        assertEquals(model.getName(), updatedSysMenu.getName());
    }

    @Test
    public void delSysMenu() {
        List<SysRoleMenu> roleMenuList = sysRoleMenuDao.findByRoleId(1);
        List<SysMenu> menuList = sysMenuService.findMenuList();

        sysMenuService.delSysMenu("61");
        List<SysMenu> afterDeletedMenuList = sysMenuService.findMenuList();
        List<SysRoleMenu> afterDeletedRoleMenuList = sysRoleMenuDao.findByRoleId(1);

        assertEquals(afterDeletedMenuList.size(), menuList.size()-2);
        assertEquals(afterDeletedRoleMenuList.size(), roleMenuList.size()-2);
    }

    private SysMenuModel expectSysMenuModel() {
        SysMenuModel model = new SysMenuModel();
        model.setAlias(TEST_MENU);
        model.setName(TEST_MENU);
        model.setDescription(TEST_MENU);
        model.setParentId(2);
        return model;
    }
}
