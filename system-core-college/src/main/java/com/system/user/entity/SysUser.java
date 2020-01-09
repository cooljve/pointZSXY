package com.system.user.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "sys_users")
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "SYS_USERS_SEQ")
    @Column(name = "ID")
    private Integer id;
    //姓名
    @Column(name = "USERNAME", length = 20)
    private String username;
    //昵称
    @Column(name = "NICKNAME", length = 20)
    private String nickname;
    //职位
    @Column(name = "ZHIWEI", length = 100)
    private String position;
    //编号
    @Column(name = "CODE", length = 100)
    private String code;
    //性别
    @Column(name = "SEX")
    private Integer sex;//0男，1女
    //简介
    @Column(name = "DETAIL", length = 300)
    private String detail;
    //账号
    @Column(name = "ACOUNT", length = 20)
    private String account;
    //电话
    @Column(name = "TEL", length = 20)
    private String tel;
    //创建时间
    @Column(name = "CREATEDATE")
    private Date createDate;
    //修改时间
    @Column(name = "MODIFYDATE")
    private Date modifyDate;
    //角色id
    @Column(name = "ROLE_ID")
    private Integer roleId;
    @Transient
    private String roleName;
    //密码
    @Column(name = "PASSWORD", length = 50)
    private String password;
    //邮箱
    @Column(name = "EMAIL", length = 50)
    private String email;
    //备注
    @Column(name = "REMARK", length = 500)
    private String remark;
    //备注2
    @Column(name = "REMARK2", length = 500)
    private String remark2;
    //状态 0启用，1禁用
    @Column(name = "STATUS", columnDefinition = "int default 0")
    private Integer status;
    //是否删除0正常，1删除
    @Column(name = "ISDELETE", columnDefinition = "int default 0")
    private Integer isDelete;

    public SysUser(Integer id) {
        super();
        this.id = id;
    }
}
