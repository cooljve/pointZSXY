package com.system.core.dao;

import com.system.core.entity.SysAffix;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysAffixDao extends CrudRepository<SysAffix, Integer>, JpaSpecificationExecutor<SysAffix> {

}
