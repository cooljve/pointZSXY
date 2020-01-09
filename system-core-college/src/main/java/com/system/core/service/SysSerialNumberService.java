package com.system.core.service;


public interface SysSerialNumberService {
    /**
     * 根据目标获取序列号
     *
     * @param target
     * @return
     * @description
     */
    public String getNumberByTarget(Integer target, Integer count);
}
