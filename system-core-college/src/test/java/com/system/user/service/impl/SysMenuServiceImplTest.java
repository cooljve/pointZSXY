package com.system.user.service.impl;

import com.system.user.entity.SysMenu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysMenuServiceImplTest {

    public static final String SYSTEM_MANAGE = "系统管理";
    @Autowired
    private SysMenuServiceImpl sysMenuService;

    @Test
    public void findListMenu() {
        List<SysMenu> listMenu = sysMenuService.findListMenu();

        assertEquals(19, listMenu.size());
    }

    @Test
    public void getMenu() {
        SysMenu menu = sysMenuService.getMenu(2);

        assertEquals(SYSTEM_MANAGE, menu.getAlias());
        assertEquals(SYSTEM_MANAGE, menu.getName());
        assertEquals(SYSTEM_MANAGE, menu.getDescription());
        assertEquals(1, menu.getParentMenu().getId());
    }

    @Test
    public void addSysMenu() {
    }

    @Test
    public void updateSysMenu() {
    }

    @Test
    public void delSysMenu() {
    }
}
