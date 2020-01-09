package com.system.user.service.impl;

import com.system.core.dao.base.BaseRepository;
import com.system.core.util.DigestMD5Util;
import com.system.user.dao.SysUserDao;
import com.system.user.entity.SysMenu;
import com.system.user.entity.SysUser;
import com.system.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.system.core.util.Constant.*;
import static com.system.core.util.Constant.USER_NOT_EXIST;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private BaseRepository<SysMenu> baseRepository;

    /**
     * 用户登录
     */
    @Transactional
    public String login(String username, String password, String randomCode, HttpServletRequest request) {
        int flag = 0;
        String mess = "";
        HttpSession session = request.getSession();
        String code = null == session.getAttribute(SESSION_ATTR_RANDOM_CODE) ? EMPTY_STR : session.getAttribute("randomCode").toString();
        session.removeAttribute(SESSION_ATTR_RANDOM_CODE);//清除session
        if (!code.equals(randomCode)) {
            flag = 1;//验证码错误
            mess = RANDOM_CODE_ERROR;
        } else {
            SysUser sysUser = sysUserDao.findSysUserByUsername(username);
            if (sysUser == null) {
                flag = 2;
                mess = USER_NOT_EXIST;
            } else {
                if (!DigestMD5Util.MD5(password).equals(sysUser.getPassword())) {
                    flag = 3;
                    mess = PWD_ERROR;
                } else if (sysUser.getStatus() == 1) {
                    flag = 4;
                    mess = USER_IS_DISABLED;
                } else {
                    session.setAttribute(SESSION_ATTR_USER_ID, sysUser.getId());
                    mess = VERIFY_SUCCESS;
                }
            }
        }
        return "{\"message\":\"" + mess + "\",\"flag\":\"" + flag + "\"}";
    }

    /**
     * 用户修改自己的密码
     */
    @Transactional
    public String updateUserPassword(String oldPwd, String newPwd, HttpServletRequest request) {
        int flag = 0;
        String mess = "";
        HttpSession session = request.getSession();
        Integer userId = (Integer) request.getSession().getAttribute(SESSION_ATTR_USER_ID);
        SysUser sysUser = sysUserDao.findOne(userId);
        if (!DigestMD5Util.MD5(oldPwd).equals(sysUser.getPassword())) {
            flag = 1;//原始密码错误
            mess = OLD_PWD_ERROR;
        } else {
            sysUserDao.updateSysUserPass(DigestMD5Util.MD5(newPwd), new Integer[]{sysUser.getId()});
            sysUser.setPassword(DigestMD5Util.MD5(newPwd));
            session.setAttribute("sys_user", sysUser);
            mess = UPDATE_SUCCESS;
        }
        return "{\"message\":\"" + mess + "\",\"flag\":\"" + flag + "\"}";
    }

    /**
     * 查询当前登录人的菜单
     */
    public List<SysMenu> listLoginMenu(Integer userId) {
        String hql = "from SysMenu where id in (select  sysMenuId from SysRoleMenu where sysRoleId in (select roleId from SysUserRole where userId=?1 ))";
        hql += " order by orderBy asc";
        return baseRepository.find(hql, new Object[]{userId}, -1, -1);
    }
}
