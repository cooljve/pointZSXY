package com.system.user.dao;

import com.system.user.entity.SysUser;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SysUserDao extends CrudRepository<SysUser, Integer>, JpaSpecificationExecutor<SysUser> {
    @Modifying
    @Query("update SysUser set getDelete=1 where id in ?1")
    public void deleteSysUser(Integer[] ids);

    @Modifying
    @Query("update SysUser set status=?1 where id in ?2")
    public void updateSysUserStatus(Integer status, Integer[] ids);

    @Modifying
    @Query("update SysUser set password=?1 where id in ?2")
    public void updateSysUserPass(String password, Integer[] ides);

    @Query("from SysUser where username=?1 and getDelete=0 ")
    public SysUser findSysUserByUsername(String username);

    @Query("from SysUser where getDelete=0 and username=?1 and id <> ?2")
    public SysUser findSysUserByUsername(String username, Integer id);

}
