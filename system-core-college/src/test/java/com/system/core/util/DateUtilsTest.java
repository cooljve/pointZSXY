package com.system.core.util;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DateUtilsTest {

    @Test
    public void getNowDate() {
        String result = DateUtils.getNowDate();
        System.out.println(result);
        assertEquals(10, result.length());
    }

    @Test
    public void getNowDateTime() {
        String result = DateUtils.getNowDateTime();
        System.out.println(result);
        assertEquals(19, result.length());
    }

    @Test
    public void getNowDateTimeAsNumber() {
        String result = DateUtils.getNowDateTimeAsNumber();
        System.out.println(result);
        assertEquals(18, result.length());
    }

    @Test
    public void getNowTime() {
        Date result = DateUtils.getNowTime(Constant.YYYY_MM_DD);
        System.out.println(result);
        assertNotEquals(0, result.getTime());
    }

    @Test
    public void getNowDateAsNumber() {
        String result = DateUtils.getNowDateAsNumber();
        System.out.println(result);
        assertEquals(8, result.length());
    }

}
