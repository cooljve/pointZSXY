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
    List<SysRoleMenu> findByRoleId(Integer roleId);

    @Modifying
    @Query("delete from SysRoleMenu where sysRoleId=?1")
    void delByRoleId(Integer roleId);

    @Modifying
    @Query("delete from SysRoleMenu where sysMenuId=?1")
    void delByMenuId(Integer menuId);
}
