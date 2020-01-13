package com.system.user.service.impl;

import com.system.user.dao.SysRoleMenuDao;
import com.system.user.entity.SysRoleMenu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class SysRoleMenuServiceImplTest {

    @Autowired
    private SysRoleMenuServiceImpl sysRoleMenuService;
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    @Test
    public void insertSysRoleMenu() {
        sysRoleMenuService.insertSysRoleMenu(1, "63");
        List<SysRoleMenu> afterInsertSysRoleMenus = sysRoleMenuDao.findByRoleId(1);

        assertEquals(1, afterInsertSysRoleMenus.size());
        assertEquals(63, (int)afterInsertSysRoleMenus.get(0).getSysMenuId());
    }

    @Test
    public void listMenuByRoleId() {
        List<SysRoleMenu> sysRoleMenus = sysRoleMenuService.listMenuByRoleId(1);

        assertEquals(22, sysRoleMenus.size());
        assertEquals(1, (int) sysRoleMenus.get(0).getSysMenuId());
    }
}
