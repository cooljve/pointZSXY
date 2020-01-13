package com.system.user.dao;

import com.system.user.entity.SysUserRole;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class SysUserRoleDaoTest {

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Test
    public void delSysUserRole() {
        List<SysUserRole> sysUserRoleList = Lists.newArrayList(sysUserRoleDao.findAll());

        sysUserRoleDao.delSysUserRole(1, 1);
        List afterDeleteUserRoleList = Lists.newArrayList(sysUserRoleDao.findAll());

        assertEquals(sysUserRoleList.size() - 1, afterDeleteUserRoleList.size());
    }

    @Test
    public void delByRoleId() {
        List<SysUserRole> sysUserRoleList = Lists.newArrayList(sysUserRoleDao.findAll());

        sysUserRoleDao.delByRoleId(1);
        List afterDeleteUserRoleList = Lists.newArrayList(sysUserRoleDao.findAll());

        assertEquals(sysUserRoleList.size() - 1, afterDeleteUserRoleList.size());
    }

    @Test
    public void delByUserId() {
        List<SysUserRole> sysUserRoleList = Lists.newArrayList(sysUserRoleDao.findAll());

        sysUserRoleDao.delByUserId(1);
        List afterDeleteUserRoleList = Lists.newArrayList(sysUserRoleDao.findAll());

        assertEquals(sysUserRoleList.size() - 1, afterDeleteUserRoleList.size());
    }

    @Test
    public void delByUserIds() {
        List<SysUserRole> sysUserRoleList = Lists.newArrayList(sysUserRoleDao.findAll());

        sysUserRoleDao.delByUserIds(new Integer[]{1,52});
        List afterDeleteUserRoleList = Lists.newArrayList(sysUserRoleDao.findAll());

        assertEquals(sysUserRoleList.size() - 2, afterDeleteUserRoleList.size());

    }
}
