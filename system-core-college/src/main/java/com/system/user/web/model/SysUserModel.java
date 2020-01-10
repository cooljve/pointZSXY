package com.system.user.web.model;

import lombok.Data;

@Data
public class SysUserModel {
    //姓名
    private String username;
    //昵称
    private String nickname;
    //职位
    private String position;
    //编号
    private String code;
    //性别
    private Integer sex;//0男，1女
    //简介
    private String detail;
    //账号
    private String account;
    //电话
    private String tel;
    //密码
    private String password;
    //邮箱
    private String email;
    //备注
    private String remark;
    //备注2
    private String remark2;
    //状态 0启用，1禁用
    private Integer status;
    //角色id
    private Integer roleId;

}
