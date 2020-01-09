package com.system.user.dao;

import com.system.user.entity.SysMenu;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SysMenuDao extends CrudRepository<SysMenu, Integer>, JpaSpecificationExecutor<SysMenu> {
    @Query("from SysMenu order by parentObj.id,orderBy,id")
    public List<SysMenu> findAll();

    @Query("from SysMenu where parentObj.id=?1")
    public List<SysMenu> findByParentId(Integer menuId);

	/*@Query("delete from SysMenu where parentObj.id=?1")
	public void delByParId(Integer menuId);*/
}
