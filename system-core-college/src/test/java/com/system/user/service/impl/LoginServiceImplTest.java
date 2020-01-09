package com.system.user.service.impl;

import com.system.core.dao.base.BaseRepository;
import com.system.core.util.DigestMD5Util;
import com.system.user.dao.SysUserDao;
import com.system.user.entity.SysMenu;
import com.system.user.entity.SysUser;
import com.system.user.service.LoginService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;

import static com.system.core.util.Constant.ENCRYPTED_PWD_123456;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.ReflectionTestUtils.setField;

public class LoginServiceImplTest {

    private LoginService loginService = new LoginServiceImpl();
    private SysUserDao sysUserDao;
    private MockHttpServletRequest request;
    private MockHttpSession session;
    private BaseRepository baseRepository;

    @Before
    public void setUp() {
        sysUserDao = mock(SysUserDao.class);
        baseRepository = mock(BaseRepository.class);
        setField(loginService, "sysUserDao", sysUserDao);
        setField(loginService, "baseRepository", baseRepository);
        request = new MockHttpServletRequest();
        session = new MockHttpSession();
    }

    @Test
    public void login_success() {
        session.setAttribute("randomCode", "123456");
        request.setSession(session);
        SysUser user = expectUser();
        when(sysUserDao.findSysUserByUsername("JoiLiu")).thenReturn(user);

        String result = loginService.login("JoiLiu", "123456", "123456", request);

        assertEquals("{\"message\":\"验证成功\",\"flag\":\"0\"}", result);
        assertEquals(1, session.getAttribute("USER_ID"));
    }

    @Test
    public void login_randomCode_error() {
        session.setAttribute("randomCode", "123456");
        request.setSession(session);
        SysUser user = expectUser();
        when(sysUserDao.findSysUserByUsername("JoiLiu")).thenReturn(user);

        String result = loginService.login("JoiLiu", "123456", "123", request);

        assertEquals("{\"message\":\"验证码错误\",\"flag\":\"1\"}", result);
        assertNull(session.getAttribute("USER_ID"));
    }

    @Test
    public void login_user_not_exist() {
        session.setAttribute("randomCode", "123456");
        request.setSession(session);
        SysUser user = expectUser();
        when(sysUserDao.findSysUserByUsername("Joi")).thenReturn(user);

        String result = loginService.login("JoiLiu", "123456", "123456", request);

        assertEquals("{\"message\":\"用户不存在\",\"flag\":\"2\"}", result);
        assertNull(session.getAttribute("USER_ID"));
    }

    @Test
    public void login_pwd_is_wrong() {
        session.setAttribute("randomCode", "123456");
        request.setSession(session);
        SysUser user = expectUser();
        user.setStatus(1);
        when(sysUserDao.findSysUserByUsername("JoiLiu")).thenReturn(user);

        String result = loginService.login("JoiLiu", "654321", "123456", request);

        assertEquals("{\"message\":\"密码错误\",\"flag\":\"3\"}", result);
        assertNull(session.getAttribute("USER_ID"));
    }

    @Test
    public void login_user_is_disabled() {
        session.setAttribute("randomCode", "123456");
        request.setSession(session);
        SysUser user = expectUser();
        user.setStatus(1);
        when(sysUserDao.findSysUserByUsername("JoiLiu")).thenReturn(user);

        String result = loginService.login("JoiLiu", "123456", "123456", request);

        assertEquals("{\"message\":\"用户已禁用\",\"flag\":\"4\"}", result);
        assertNull(session.getAttribute("USER_ID"));
    }

    @Test
    public void updateUserPassword_old_password_wrong() {
        session.setAttribute("USER_ID", 1);
        request.setSession(session);
        SysUser user = expectUser();
        when(sysUserDao.findOne(1)).thenReturn(user);

        String result = loginService.updateUserPassword("1234561", "654321", request);

        assertEquals("{\"message\":\"原始密码错误!\",\"flag\":\"1\"}", result);
    }

    @Test
    public void updateUserPassword_success() {
        session.setAttribute("USER_ID", 1);
        request.setSession(session);
        SysUser user = expectUser();
        when(sysUserDao.findOne(1)).thenReturn(user);

        String result = loginService.updateUserPassword("123456", "654321", request);
        SysUser newUser = (SysUser) session.getAttribute("sys_user");

        assertEquals("{\"message\":\"修改成功!\",\"flag\":\"0\"}", result);
        assertNotNull(newUser);
        assertEquals(DigestMD5Util.MD5("654321"), newUser.getPassword());
    }

    @Test
    public void listLoginMenu() {
        when(baseRepository.find(anyString(), any(), anyInt(), anyInt())).thenReturn(Arrays.asList(new SysMenu(), new SysMenu()));

        List<SysMenu> sysMenus = loginService.listLoginMenu(1);

        assertEquals(2,sysMenus.size());
    }

    private SysUser expectUser() {
        SysUser user = new SysUser();
        user.setPassword(ENCRYPTED_PWD_123456);
        user.setId(1);
        user.setStatus(0);
        return user;
    }
}
