package com.system.core.util;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CustomDateUtilTest {

    @Test
    public void getNowDate() {
        String result = CustomDateUtil.getNowDate();
        System.out.println(result);
        assertEquals(10, result.length());
    }

    @Test
    public void getNowDateTime() {
        String result = CustomDateUtil.getNowDateTime();
        System.out.println(result);
        assertEquals(19, result.length());
    }

    @Test
    public void getNowDateTimeAsNumber() {
        String result = CustomDateUtil.getNowDateTimeAsNumber();
        System.out.println(result);
        assertEquals(18, result.length());
    }

    @Test
    public void getNowTime() {
        Date result = CustomDateUtil.getNowTime(Constant.YYYY_MM_DD);
        System.out.println(result);
        assertNotEquals(0, result.getTime());
    }

    @Test
    public void getNowDateAsNumber() {
        String result = CustomDateUtil.getNowDateAsNumber();
        System.out.println(result);
        assertEquals(8, result.length());
    }

}
