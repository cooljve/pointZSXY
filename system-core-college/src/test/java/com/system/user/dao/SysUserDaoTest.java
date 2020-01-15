package com.system.user.dao;

import com.system.user.entity.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
public class SysUserDaoTest {

    @Autowired
    private SysUserDao sysUserDao;

    @Test
    public void deleteSysUser() {
        sysUserDao.deleteSysUser(new Integer[]{50, 51, 52});
        SysUser sysUser = sysUserDao.findOne(52);

        assertEquals(1, sysUser.getIsDelete());
    }

    @Test
    public void updateSysUserStatus() {
        sysUserDao.updateSysUserStatus(1,new Integer[]{50,51,52});
        SysUser sysUser = sysUserDao.findOne(52);

        assertEquals(1,sysUser.getStatus());
    }

    @Test
    public void updateSysUserPassword() {
        sysUserDao.updateSysUserPassword("1",new Integer[]{50,51,52});
        SysUser sysUser = sysUserDao.findOne(52);

        assertEquals("1",sysUser.getPassword());
    }

    @Test
    public void findSysUserByUsername() {
        SysUser sysUser = new SysUser();
        sysUser.setNickname("joiliu");
        sysUser.setUsername("joi");
        sysUserDao.save(sysUser);

        SysUser joi = sysUserDao.findSysUserByUsername("joi");

        assertEquals(sysUser.getNickname(), joi.getNickname());
    }

    @Test
    public void testFindSysUserByUsername() {
        SysUser joi = sysUserDao.findSysUserByUsername("admin", 1);

        assertNotNull(joi);
    }
}
