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
    public Page<SysUser> pageList(SysUserModel model, Pageable pageable);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    public SysUser getSysUserByid(Integer id);

    /**
     * 根据用户名查询
     *
     * @param userName
     * @return
     */
    public SysUser getSysUserByUsername(String userName, Integer id);

    /**
     * 添加
     *
     * @param model
     */
    public Integer addSysUser(SysUserModel model);

    /**
     * 更新
     *
     * @param model
     */
    public void updateSysUser(Integer id, SysUserModel model);

    /**
     * 更新
     *
     * @param model
     */
    public void updateSysUserPhone(Integer id, String nickname, String zhiwei, String jiesao, Integer sex);

    /**
     * 更新vip
     *
     * @param model
     */
    public void updateSysUserVip(Integer id, SysUser model);

    /**
     * 删除明星
     *
     * @param ids
     */
    public void delSysUser(String ids);

    /**
     * 更新状态
     *
     * @param ids
     */
    public void upSysUserStatus(Integer status, String ids);

    /**
     * 更改密码
     *
     * @param ids
     */
    public void upSysUserPass(String password, String ids);

    /**
     * 根据token查询用户
     *
     * @param access_token
     * @return
     */
    public SysUser findSysUserByToken(String access_token);
}
