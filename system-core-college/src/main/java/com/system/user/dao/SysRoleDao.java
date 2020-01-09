package com.system.user.dao;

import com.system.user.entity.SysRole;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SysRoleDao extends CrudRepository<SysRole, Integer>, JpaSpecificationExecutor<SysRole> {
	/*@Modifying
	@Query("update SysRole set isdelete=1 where id in ?1")
	public void delSysUser(Integer[] ids);*/

    @Query("from SysRole where getDelete=0")
    public List<SysRole> findSysRole();

    @Query("from SysRole where getDelete=0 and id in (select  roleId from SysUserRole where userId=?1 ))")
    public List<SysRole> findBySysUserId(Integer sysUserId);
}
