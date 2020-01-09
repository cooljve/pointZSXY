package com.system.user.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "sys_users_role")
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "SYS_USERS_ROLE_SEQ")
    @Column(name = "ID")
    private Integer id;
    /**
     * 用户id
     */
    @Column(name = "userid", nullable = false)
    private Integer userId;
    /**
     * 角色id
     */
    @Column(name = "roleid", nullable = false)
    private Integer roleId;

}
