package com.system.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * 序列号
 *
 * @description
 */
@Data
@Entity
@Table(name = "sys_serial_number")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class SysSerialNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "SYS_SERIAL_NUMBER_SEQ")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TARGET")
    private Integer target;//使用序列号的目标

    @Column(name = "NUMBER")
    private Long number;//代码

    @Column(name = "REMARK")
    private String remark;//说明
}
