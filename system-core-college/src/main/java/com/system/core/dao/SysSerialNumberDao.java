package com.system.core.dao;

import com.system.core.entity.SysSerialNumber;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysSerialNumberDao extends CrudRepository<SysSerialNumber, Integer>, JpaSpecificationExecutor<SysSerialNumber> {

    @Query(" from SysSerialNumber where target=?1 ")
    public List<SysSerialNumber> findByTarget(Integer target);

}
