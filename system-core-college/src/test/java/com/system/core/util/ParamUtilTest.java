package com.system.core.util;

import com.system.user.entity.SysUser;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.util.StringUtils;

import static org.junit.Assert.*;

public class ParamUtilTest {

    private MockHttpServletRequest request;

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
    }

    @Test
    public void isEmpty() {
        boolean paramUtilRes = ParamUtil.isEmpty("  ");
        boolean stringUtilsRes = StringUtils.isEmpty("  ");

        assertTrue(paramUtilRes);
        assertFalse(stringUtilsRes);
    }

    @Test
    public void getIntParameter_normal() {
        request.addParameter("age", "24");
        int result = ParamUtil.getIntParameter(request, "age", 0);

        assertEquals(24, result);
    }

    @Test
    public void getIntParameter_abnormal() {
        request.addParameter("age", "abc");
        int result = ParamUtil.getIntParameter(request, "age", 0);

        assertEquals(0, result);
    }

    @Test
    public void getStrParameter_normal() {
        request.addParameter("name", "joi");
        String result = ParamUtil.getStrParameter(request, "name", "no");

        assertEquals("joi", result);
    }

    @Test
    public void getStrParameter_abnormal() {
        String result = ParamUtil.getStrParameter(request, "name", "no");

        assertEquals("no", result);
    }

    @Test
    public void bindBean_normal() {
        SysUser source = new SysUser();
        source.setEmail("123456@123.com");
        SysUser dest = new SysUser();

        ParamUtil.bindBean(dest, source);

        assertEquals(source.getEmail(), dest.getEmail());
    }

    @Test
    public void bindBean_abnormal() {
        SysUser source = new SysUser();
        source.setEmail("123456@123.com");
        SysUser dest = new SysUser();

        ParamUtil.bindBean(dest, source);
        dest.setEmail("123@123.com");

        assertNotEquals(source.getEmail(), dest.getEmail());
    }

    @Test
    public void toIntegers() {
        String[] strings = new String[]{"-1", "0", "1", "2"};
        Integer[] result = ParamUtil.toIntegers(strings);

        assertEquals(strings.length, result.length);
        assertEquals(-1, (int) result[0]);
        assertEquals(0, (int) result[1]);
        assertEquals(1, (int) result[2]);
        assertEquals(2, (int) result[3]);
    }

    @Test
    public void isNumber() {
        boolean number = ParamUtil.isNumber("-1234");
        boolean str = ParamUtil.isNumber("1ab12");

        assertTrue(number);
        assertFalse(str);
    }
}
