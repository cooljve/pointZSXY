package com.system.core.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class RandomCodeUtilTest {

    @Test
    public void getOrderCode() {
        String orderCode = RandomCodeUtil.getOrderCode();
        System.out.println(orderCode);
        assertEquals(19, orderCode.length());
    }

    @Test
    public void createRandom() {
        String random1 = RandomCodeUtil.createRandom(true, 10);
        String random2 = RandomCodeUtil.createRandom(true, 10);
        String random3 = RandomCodeUtil.createRandom(false, 10);
        String random4 = RandomCodeUtil.createRandom(false, 10);

        System.out.println(random1);
        System.out.println(random2);
        System.out.println(random3);
        System.out.println(random4);

        assertNotEquals(random1, random2);
        assertNotEquals(random3, random4);
    }

    @Test
    public void isMobile() {
        String phoneNumber = "13312311231";
        String notPhoneNumber1 = "22312311231";
        String notPhoneNumber2 = "1231231123";
        String notPhoneNumber3 = "12ab2311231";
        String notPhoneNumber4 = "12312311231";

        boolean res1 = RandomCodeUtil.isMobile(phoneNumber);
        boolean res2 = RandomCodeUtil.isMobile(notPhoneNumber1);
        boolean res3 = RandomCodeUtil.isMobile(notPhoneNumber2);
        boolean res4 = RandomCodeUtil.isMobile(notPhoneNumber3);
        boolean res5 = RandomCodeUtil.isMobile(notPhoneNumber4);

        assertTrue(res1);
        assertFalse(res2);
        assertFalse(res3);
        assertFalse(res4);
        assertFalse(res5);
    }

    @Test
    public void isEmail() {
        String email1 = "123@123.com";
        String email2 = "2ab@qq.com";
        String notEmail1 = "2a@bqqcom";
        String notEmail2 = "2abqq.abc";

        boolean res1 = RandomCodeUtil.isEmail(email1);
        boolean res2 = RandomCodeUtil.isEmail(email2);
        boolean res3 = RandomCodeUtil.isEmail(notEmail1);
        boolean res4 = RandomCodeUtil.isEmail(notEmail2);

        assertTrue(res1);
        assertTrue(res2);
        assertFalse(res3);
        assertFalse(res4);
    }
}
