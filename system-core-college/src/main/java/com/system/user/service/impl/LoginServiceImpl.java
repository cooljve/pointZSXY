package com.system.user.service.impl;

import com.system.core.dao.base.BaseRepository;
import com.system.core.util.DigestMD5Util;
import com.system.user.dao.SysUserDao;
import com.system.user.entity.SysMenu;
import com.system.user.entity.SysUser;
import com.system.user.service.LoginService;
import com.system.user.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.system.core.util.Constant.*;
import static com.system.core.util.DigestMD5Util.isPwdCorrect;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private BaseRepository<SysMenu> baseRepository;

    @Override
    @Transactional
    public MessageVO login(String username, String inputPwd, String randomCode, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String code = null == session.getAttribute(SESSION_ATTR_RANDOM_CODE) ? EMPTY_STR : session.getAttribute("randomCode").toString();
        session.removeAttribute(SESSION_ATTR_RANDOM_CODE);
        if (!code.equals(randomCode)) {
            return new MessageVO(RANDOM_CODE_ERROR, 1);
        } else {
            return handleUserLogin(username, inputPwd, session);
        }
    }

    @Transactional
    public MessageVO updateUserPassword(String inputPwd, String newPwd, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) request.getSession().getAttribute(SESSION_ATTR_USER_ID);
        SysUser sysUser = sysUserDao.findOne(userId);
        if (!isPwdCorrect(inputPwd, sysUser.getPassword())) {
            return new MessageVO(OLD_PWD_ERROR, 1);
        } else {
            sysUserDao.updateSysUserPass(DigestMD5Util.MD5(newPwd), new Integer[]{sysUser.getId()});
            sysUser.setPassword(DigestMD5Util.MD5(newPwd));
            session.setAttribute("sys_user", sysUser);
            return new MessageVO(UPDATE_SUCCESS, 0);
        }
    }

    public List<SysMenu> listLoginMenu(Integer userId) {
        String hql = "from SysMenu where id in (select  sysMenuId from SysRoleMenu where sysRoleId in (select roleId from SysUserRole where userId=?1 ))";
        hql += " order by orderBy asc";
        return baseRepository.find(hql, new Object[]{userId}, -1, -1);
    }

    private MessageVO handleUserLogin(String username, String inputPwd, HttpSession session) {
        SysUser sysUser = sysUserDao.findSysUserByUsername(username);
        if (sysUser == null) {
            return new MessageVO(USER_NOT_EXIST, 2);
        } else {
            if (!isPwdCorrect(inputPwd, sysUser.getPassword())) {
                return new MessageVO(PWD_ERROR, 3);
            } else if (sysUser.getStatus() == 1) {
                return new MessageVO(USER_IS_DISABLED, 4);
            } else {
                session.setAttribute(SESSION_ATTR_USER_ID, sysUser.getId());
                return new MessageVO(VERIFY_SUCCESS, 0);
            }
        }
    }
}
