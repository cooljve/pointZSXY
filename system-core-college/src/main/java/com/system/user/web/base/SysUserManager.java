package com.system.user.web.base;

import com.system.user.entity.SysUser;
import com.system.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class SysUserManager {

    @Autowired
    private SysUserService sysUserService;

    public SysUser getSysUser(HttpServletRequest request, boolean flag) {
        if (flag) {
            Integer userId = (Integer) request.getSession().getAttribute("USER_ID");
            if (null == userId) {
                return null;
            }
            SysUser sysUser = sysUserService.getSysUserByid(userId);
            return sysUser;
        } else {
            Cookie jsessionId = getSessionCookie(request);
            if (jsessionId != null) {
                String access_token = jsessionId.getValue();
                return sysUserService.findSysUserByToken(access_token);
            }
        }
        return null;
    }

    private Cookie getSessionCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies) {
                if ("ACCESS_TOKEN".equals(cookie.getName())) {
                    return cookie;
                }
            }
        return null;
    }
}
