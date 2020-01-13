package com.system.user.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
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

    public SysUserRole(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
