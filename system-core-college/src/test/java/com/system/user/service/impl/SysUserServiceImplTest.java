package com.system.user.service.impl;

import com.system.user.dao.AccessTokenDao;
import com.system.user.dao.SysRoleDao;
import com.system.user.dao.SysUserDao;
import com.system.user.dao.SysUserRoleDao;
import com.system.user.entity.SysUser;
import com.system.user.service.SysUserService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

public class SysUserServiceImplTest {

    private SysUserService sysUserService = new SysUserServiceImpl();

    private SysUserDao sysUserDao;
    private SysUserRoleDao sysUserRoleDao;
    private SysRoleDao sysRoleDao;
    private AccessTokenDao accessTokenDao;

    @Before
    public void setUp(){
        sysUserDao = mock(SysUserDao.class);
        sysUserRoleDao = mock(SysUserRoleDao.class);
        sysRoleDao = mock(SysRoleDao.class);
        accessTokenDao = mock(AccessTokenDao.class);
        setField(sysUserService, "sysUserDao", sysUserDao);
        setField(sysUserService, "sysUserRoleDao", sysUserRoleDao);
        setField(sysUserService, "sysRoleDao", sysRoleDao);
        setField(sysUserService, "accessTokenDao", accessTokenDao);
    }

    @Test
    public void getSysUserById(){
        SysUser sysUser = new SysUser();
        sysUser.setEmail("123456@123.com");
        when(sysUserDao.findOne(56)).thenReturn(sysUser);

        SysUser result = sysUserService.getSysUserById(56);

        assertEquals("123456@123.com", result.getEmail());
    }
}
