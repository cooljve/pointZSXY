package com.system.user.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "sys_role")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "SYS_ROLE_SEQ")
    @Column(name = "ID")
    private Integer id;

    //名字
    @Column(name = "NAME", length = 20, nullable = false)
    private String name;
    //描述
    @Column(name = "REMARK", length = 200)
    private String remark;

    //是否删除0正常，1删除
    @Column(name = "ISDELETE", columnDefinition = "int default 0")
    private Integer isDelete;

    //创建时间
    @Column(name = "CREATEDATE")
    private Date createDate;
    //修改时间
    @Column(name = "MODIFYDATE")
    private Date modifyDate;

    @Column(name = "EXTENDF1", length = 200)
    private String extendF1;

    @Column(name = "EXTENDF2", length = 200)
    private String extendF2;

    @Column(name = "EXTENDF3", length = 200)
    private String extendF3;

    @Column(name = "EXTENDF4", length = 200)
    private String extendF4;

    @Column(name = "EXTENDF5", length = 200)
    private String extendF5;

    public SysRole(Integer id) {
        super();
        this.id = id;
    }

    public SysRole(Integer id, String name, String remark) {
        super();
        this.id = id;
        this.name = name;
        this.remark = remark;
    }
}
