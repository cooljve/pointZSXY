package com.system.core.dao;

import com.system.core.entity.SysDataDictionary;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据字典
 */
@Repository
public interface SysDataDictionaryDao extends CrudRepository<SysDataDictionary, Integer>, JpaSpecificationExecutor<SysDataDictionary> {
    @Modifying
    @Query("update SysDataDictionary set isDelete=1 where id=?1")
    void delDataDictionary(Integer id);

    @Query(" from SysDataDictionary d where d.parentMenu.id is null and d.isDelete=0 and d.status=1 order by d.orderBy ")
    List<SysDataDictionary> findParentDataDictionary();

    @Query(" from SysDataDictionary d where d.parentMenu.id=?1 and d.isDelete=0 and d.status=1 order by d.orderBy ")
    List<SysDataDictionary> findByParentId(Integer parentId);


    @Query(" from SysDataDictionary d where  d.dataCode=?1 and d.depth=?2 and d.isDelete=0 and d.status=1 order by d.orderBy ")
    List<SysDataDictionary> findDataDictionaryByCode(String dataCode, Integer depth);

    @Query(" from SysDataDictionary d where  d.dataCode=?1 and d.depth=?2 and d.id<>?3 and d.isDelete=0 and d.status=1 order by d.orderBy ")
    List<SysDataDictionary> findDataDictionaryByCode(String dataCode, Integer depth, Integer id);

    @Query(" from SysDataDictionary d where  d.dataCode=?1 and d.name=?2  and d.depth=?3  and d.isDelete=0 and d.status=1 order by d.orderBy ")
    List<SysDataDictionary> findDataDictionaryByCodeAndName(String dataCode, String name, Integer depth);

    @Query(" from SysDataDictionary d where  d.dataCode=?1 and d.name=?2  and d.depth=?3  and id<>?4 and d.isDelete=0 and d.status=1 order by d.orderBy ")
    List<SysDataDictionary> findDataDictionaryByCodeAndName(String dataCode, String name, Integer depth, Integer id);

    @Query(" from SysDataDictionary d where  d.code=?1 and d.isDelete=0 and d.status=1 order by d.orderBy ")
    List<SysDataDictionary> findByCode(String code);

}
