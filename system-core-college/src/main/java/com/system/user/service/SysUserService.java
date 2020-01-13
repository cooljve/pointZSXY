package com.system.user.service;

import com.system.user.entity.SysUser;
import com.system.user.web.model.SysUserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SysUserService {
    /**
     * 分页查询
     *
     * @return
     */
    Page<SysUser> pageList(SysUserModel model, Pageable pageable);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    SysUser getSysUserById(Integer id);

    /**
     * 根据用户名查询
     *
     * @param userName
     * @return
     */
    SysUser getSysUserByUsername(String userName, Integer id);

    /**
     * 添加
     *
     * @param model
     */
    Integer addSysUser(SysUserModel model);

    /**
     * 更新
     *
     * @param model
     */
    void updateSysUser(Integer id, SysUserModel model);


    /**
     * @param id
     * @param nickname
     * @param position
     * @param detail
     * @param sex
     */
    void updateSysUserPhone(Integer id, String nickname, String position, String detail, Integer sex);

    /**
     * 更新vip
     *
     * @param model
     */
    void updateSysUserVip(Integer id, SysUser model);

    /**
     * 删除明星
     *
     * @param ids
     */
    void delSysUser(String ids);

    /**
     * 更新状态
     *
     * @param ids
     */
    void updateSysUserStatus(Integer status, String ids);

    /**
     * 更改密码
     *
     * @param ids
     */
    void updateSysUserPassword(String password, String ids);

    /**
     * 根据token查询用户
     *
     * @param access_token
     * @return
     */
    SysUser findSysUserByToken(String access_token);
}
