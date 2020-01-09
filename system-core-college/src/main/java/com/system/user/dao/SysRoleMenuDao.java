package com.system.user.dao;

import com.system.user.entity.SysRoleMenu;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 角色中菜单中间表
 *
 * @author Administrator
 */
public interface SysRoleMenuDao extends CrudRepository<SysRoleMenu, Integer>, JpaSpecificationExecutor<SysRoleMenu> {

    @Query("from SysRoleMenu where sysRoleId=?1")
    public List<SysRoleMenu> findByRoleId(Integer roleid);

    @Modifying
    @Query("delete from SysRoleMenu where sysRoleId=?1")
    public void delByRoleId(Integer roleid);

    @Modifying
    @Query("delete from SysRoleMenu where sysMenuId=?1")
    public void delByMenuId(Integer menuId);
}
