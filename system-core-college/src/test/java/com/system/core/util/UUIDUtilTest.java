package com.system.core.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UUIDUtilTest {

    @Test
    public void getUUID() {
        String uuid = UUIDUtil.getUUID();

        assertEquals(32, uuid.length());
    }

    @Test
    public void generateShortUuid() {
        String shortUuid = UUIDUtil.generateShortUuid();

        assertEquals(8, shortUuid.length());
    }
}
