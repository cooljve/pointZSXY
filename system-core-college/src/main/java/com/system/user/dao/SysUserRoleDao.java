package com.system.user.dao;

import com.system.user.entity.SysUserRole;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SysUserRoleDao extends CrudRepository<SysUserRole, Integer>, JpaSpecificationExecutor<SysUserRole> {
    @Modifying
    @Query("delete from SysUserRole where getRoleId=?1 and getUserId=?2")
    public void delSysUserRole(Integer roleid, Integer userid);

    @Modifying
    @Query("delete from SysUserRole where getRoleId=?1")
    public void delByRoleId(Integer roleid);

    @Modifying
    @Query("delete from SysUserRole where getUserId=?1")
    public void delByUserId(Integer userid);
}
