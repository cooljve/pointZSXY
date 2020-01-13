package com.system.user.dao;

import com.system.user.entity.SysUserRole;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SysUserRoleDao extends CrudRepository<SysUserRole, Integer>, JpaSpecificationExecutor<SysUserRole> {
    @Modifying
    @Query("delete from SysUserRole where roleId=?1 and userId=?2")
    void delSysUserRole(Integer roleId, Integer userId);

    @Modifying
    @Query("delete from SysUserRole where roleId=?1")
    void delByRoleId(Integer roleId);

    @Modifying
    @Query("delete from SysUserRole where userId=?1")
    void delByUserId(Integer userId);

    @Modifying
    @Query("delete from SysUserRole where userId in ?1")
    void delByUserIds(Integer[] ids);
}
