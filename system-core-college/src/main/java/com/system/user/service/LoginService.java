package com.system.user.service;

import com.system.user.entity.SysMenu;
import com.system.user.vo.MessageVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface LoginService {

    /**
     * 用户登录
     *
     * @param username
     * @param inputPwd
     * @param randomCode
     * @param request
     * @return
     */
    public MessageVO login(String username, String inputPwd, String randomCode, HttpServletRequest request);

    /**
     * 用户修改自己的密码
     *
     * @param inputPwd
     * @param newPass
     * @param request
     * @return
     */
    public MessageVO updateUserPassword(String inputPwd, String newPass, HttpServletRequest request);

    /**
     * 查询当前登录人的菜单
     *
     * @param userId
     * @return
     */
    public List<SysMenu> listLoginMenu(Integer userId);
}
