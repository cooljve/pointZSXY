package com.system.user.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "sys_role_menu")
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "SYS_ROLE_SEQ")
    @Column(name = "ID")
    private Integer id;

    //角色的id
    @Column(name = "SYS_ROLE_ID")
    private Integer sysRoleId;

    //菜单id
    @Column(name = "SYS_MENU_ID")
    private Integer sysMenuId;

}
