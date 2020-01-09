package com.system.core.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DigestMD5UtilTest {

    @Test
    public void MD5() {
        String result = DigestMD5Util.MD5("123456");

        assertEquals("E10ADC3949BA59ABBE56E057F20F883E", result);
    }

    @Test
    public void encryptMd5() {
        String result = DigestMD5Util.encryptMd5("E10ADC3949BA59ABBE56E057F20F883E");
        assertEquals("1ED507GM@M65AM5661AB1DAC2FD2LLG1", result);
    }
}
