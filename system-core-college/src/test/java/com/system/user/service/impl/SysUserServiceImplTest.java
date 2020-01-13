package com.system.user.service.impl;

import com.system.core.util.DigestMD5Util;
import com.system.user.dao.AccessTokenDao;
import com.system.user.dao.SysRoleDao;
import com.system.user.dao.SysUserDao;
import com.system.user.dao.SysUserRoleDao;
import com.system.user.entity.AccessToken;
import com.system.user.entity.SysUser;
import com.system.user.entity.SysUserRole;
import com.system.user.web.model.SysUserModel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.ReflectionTestUtils.setField;

public class SysUserServiceImplTest {

    private SysUserServiceImpl sysUserService = new SysUserServiceImpl();

    private SysUserDao sysUserDao;
    private SysUserRoleDao sysUserRoleDao;
    private SysRoleDao sysRoleDao;
    private AccessTokenDao accessTokenDao;

    @Before
    public void setUp() {
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
    public void getSysUserById() {
        SysUser sysUser = new SysUser();
        sysUser.setEmail("123456@123.com");
        when(sysUserDao.findOne(56)).thenReturn(sysUser);

        SysUser result = sysUserService.getSysUserById(56);

        assertEquals("123456@123.com", result.getEmail());
    }

    @Test
    public void getSysUserByUsername_id_is_null() {
        sysUserService.getSysUserByUsername("joi", null);

        verify(sysUserDao).findSysUserByUsername("joi");
        verify(sysUserDao, never()).findSysUserByUsername("joi", 1);
    }

    @Test
    public void getSysUserByUsername_id_is_not_null() {
        sysUserService.getSysUserByUsername("joi", 1);

        verify(sysUserDao, never()).findSysUserByUsername("joi");
        verify(sysUserDao).findSysUserByUsername("joi", 1);
    }

    @Test
    public void addSysUser() {
        SysUserModel model = expectUserModel();

        Integer result = sysUserService.addSysUser(model);
        System.out.println(result);

        verify(sysUserDao).save((SysUser) any());
        verify(sysUserRoleDao, never()).delByUserId(result);
        verify(sysUserRoleDao).save((SysUserRole) any());
    }

    @Test
    public void updateSysUser() {
        when(sysUserDao.findOne(1)).thenReturn(new SysUser());
        sysUserService.updateSysUser(1, expectUserModel());

        verify(sysUserDao).save((SysUser) any());
        verify(sysUserRoleDao).delByUserId(anyInt());
        verify(sysUserRoleDao).save((SysUserRole) any());
    }

    @Test
    public void updateSysUserPhone_with_null_value() {
        SysUser sysUser = expectUser();
        when(sysUserDao.findOne(1)).thenReturn(sysUser);

        sysUserService.updateSysUserPhone(1, "", "", null, 1);

        assertEquals("liu", sysUser.getNickname());
        assertEquals("DEV", sysUser.getPosition());
        assertEquals("little liu", sysUser.getDetail());
        verify(sysUserDao).save(sysUser);
    }

    @Test
    public void updateSysUserPhone_with_value() {
        SysUser sysUser = expectUser();
        when(sysUserDao.findOne(1)).thenReturn(sysUser);

        sysUserService.updateSysUserPhone(1, "joi", "MANAGER", "little joi", 1);

        assertEquals("joi", sysUser.getNickname());
        assertEquals("MANAGER", sysUser.getPosition());
        assertEquals("little joi", sysUser.getDetail());
        verify(sysUserDao).save(sysUser);
    }

    @Test
    public void updateSysUserVip() {
        SysUser sysUser = expectUser();
        when(sysUserDao.findOne(1)).thenReturn(sysUser);

        sysUserService.updateSysUserVip(1, sysUser);

        verify(sysUserDao).save(sysUser);
        verify(sysUserRoleDao).delByUserId(anyInt());
        verify(sysUserRoleDao).save((SysUserRole) any());
    }

    @Test
    public void delSysUser() {
        sysUserService.delSysUser("1,2,3");

        verify(sysUserRoleDao).delByUserIds(new Integer[]{1, 2, 3});
        verify(sysUserDao).deleteSysUser(any());
    }

    @Test
    public void updateSysUserStatus() {
        sysUserService.updateSysUserStatus(1, "1,2,3");

        verify(sysUserDao).updateSysUserStatus(1, new Integer[]{1, 2, 3});
    }

    @Test
    public void updateSysUserPassword() {
        sysUserService.updateSysUserPassword("123456", "1,2,3");

        verify(sysUserDao).updateSysUserPassword(DigestMD5Util.MD5("123456"), new Integer[]{1, 2, 3});
    }

    @Test
    public void findSysUserByToken() {
        AccessToken accessToken = new AccessToken();
        accessToken.setUserId(1);
        when(accessTokenDao.findAccessTokenByToken("abc")).thenReturn(Arrays.asList(accessToken, new AccessToken()));
        SysUser sysUser = expectUser();
        when(sysUserDao.findOne(1)).thenReturn(sysUser);

        SysUser result = sysUserService.findSysUserByToken("abc");

        assertEquals(sysUser.getNickname(), result.getNickname());
    }

    @Test
    public void findSysUserByToken_with_empty_accessToken() {
        when(accessTokenDao.findAccessTokenByToken("abc")).thenReturn(new ArrayList<>());
        SysUser sysUser = expectUser();
        when(sysUserDao.findOne(1)).thenReturn(sysUser);

        SysUser result = sysUserService.findSysUserByToken("abc");

        assertNull(result);
    }

    private SysUser expectUser() {
        SysUser sysUser = new SysUser();
        sysUser.setNickname("liu");
        sysUser.setPosition("DEV");
        sysUser.setDetail("little liu");
        sysUser.setRoleId(1);
        return sysUser;
    }

    private SysUserModel expectUserModel() {
        SysUserModel model = new SysUserModel();
        model.setUsername("joiliu");
        model.setNickname("joiliu");
        model.setPosition("DEV");
        model.setCode("1");
        model.setSex(1);
        model.setDetail("");
        model.setTel("13112311231");
        model.setPassword("123456");
        model.setEmail("123456@qq.com");
        model.setRemark("remark1");
        model.setRemark2("remark2");
        model.setStatus(0);
        model.setRoleId(1);
        return model;
    }
}
