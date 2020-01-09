package com.system.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.system.core.jsonSerialize.SysDataDictionarySerialize;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * 数据字典
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "sys_datadictionary")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class SysDataDictionary implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "SYS_DATADICTIONARY_SEQ")
    @Column(name = "ID")
    private Integer id;
    /**
     * 名称
     */
    @Column(name = "NAME", length = 50)
    private String name;
    /**
     * 数据字典代码
     */
    @Column(name = "DATA_CODE", length = 50)
    private String dataCode;

    /**
     * 排序字段
     */
    @Column(name = "ORDER_BY")
    private Integer orderBy;

    @JoinColumn(name = "PARENT_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonSerialize(using = SysDataDictionarySerialize.class)
    private SysDataDictionary parentObj;

    @Column(name = "PARENT_ID")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentObj", cascade = {javax.persistence.CascadeType.ALL})
    @JsonIgnore
    private Set<SysDataDictionary> childSet;

    /**
     * 状态 默认1正常，0禁用
     */
    @Column(name = "STATUS", columnDefinition = "int default 1")
    private Integer status;

    /**
     * 是否删除1正常，0删除
     */
    @Column(name = "ISDELETE", columnDefinition = "int default 1")
    private Integer isDelete;

    /**
     * 深度
     */
    @Column(name = "DEPTH")
    private Integer depth;
    /**
     * 创建时间
     */
    @Column(name = "CREATEDATE")
    private String createDate;
    /**
     * 修改时间
     */
    @Column(name = "MODIFYDATE")
    private String modifyDate;
    /**
     * 编号
     */
    @Column(name = "CODE", length = 400)
    private String code;
    /**
     * 0:正常  1：特殊
     */
    @Column(name = "PROPERTY")
    private Integer property = 0;

    public SysDataDictionary(Integer id) {
        super();
        this.id = id;
    }

}
