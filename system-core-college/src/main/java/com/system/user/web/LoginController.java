package com.system.user.web;

import com.system.core.util.Constant;
import com.system.user.entity.SysMenu;
import com.system.user.entity.SysUser;
import com.system.user.service.LoginService;
import com.system.user.service.SysUserService;
import com.system.user.vo.MessageVO;
import com.system.user.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/system")
public class LoginController extends BaseController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private SysUserService sysUserService;

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @param randomCode
     * @param request
     * @return
     */
    @RequestMapping(value = "/userLogin", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity userLogin(@RequestParam String username,
                                    @RequestParam String password,
                                    @RequestParam String randomCode,
                                    HttpServletRequest request) {
        MessageVO messageVO = loginService.login(username, password, randomCode, request);
        return new ResponseEntity<>(messageVO, HttpStatus.OK);
    }

    /**
     * 注销
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/login_out", method = {RequestMethod.GET})
    public ResponseEntity signOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("USER_ID");
        session.removeAttribute("orgList");
        session.invalidate();
        return new ResponseEntity<>(new MessageVO(Constant.SIGN_OUT_SUCCESS, 1), HttpStatus.OK);
    }


    /**
     * 用户修改密码
     *
     * @param oldPass
     * @param newPass
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateMyPass", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity updateMyPass(@RequestParam("oldPass") String oldPass, @RequestParam("newPass") String newPass,
                                       HttpServletRequest request) {
        MessageVO messageVO = loginService.updateUserPassword(oldPass, newPass, request);
        return new ResponseEntity<>(messageVO, HttpStatus.OK);
    }

    /**
     * 查询当前登录人的菜单
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/listLoginMenu", method = {RequestMethod.GET, RequestMethod.POST})
    public List<SysMenu> listLoginMenu(HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("USER_ID");
        SysUser sysUser = sysUserService.getSysUserById(userId);
        List<SysMenu> list = loginService.listLoginMenu(sysUser.getId());
        List<SysMenu> beans = new ArrayList<>();
        if (1 == sysUser.getRoleId()) {
            String code = sysUser.getCode();
            for (SysMenu sysMenu : list) {
                if (sysMenu.getId() == 28) {
                    if (code.contains("1")) {
                        beans.add(sysMenu);
                    } else {
                        continue;
                    }
                } else if (sysMenu.getId() == 26) {
                    if (code.contains("2")) {
                        beans.add(sysMenu);
                    } else {
                        continue;
                    }
                } else if (sysMenu.getId() == 29) {
                    if (code.contains("3")) {
                        beans.add(sysMenu);
                    } else {
                        continue;
                    }
                } else if (sysMenu.getId() == 27) {
                    if (code.contains("4")) {
                        beans.add(sysMenu);
                    } else {
                        continue;
                    }
                } else if (sysMenu.getId() == 30) {
                    if (code.contains("5")) {
                        beans.add(sysMenu);
                    } else {
                        continue;
                    }
                } else if (sysMenu.getId() == 37) {
                    if (code.contains("6")) {
                        beans.add(sysMenu);
                    } else {
                        continue;
                    }
                } else {
                    beans.add(sysMenu);
                }
            }
            return beans;
        } else {
            return list;
        }
    }


    @RequestMapping(value = "/getLoginUser", method = RequestMethod.GET)
    public ResponseEntity getLoginUser(HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("USER_ID");
        SysUser sysUser = sysUserService.getSysUserById(userId);
        return new ResponseEntity<>(sysUser,HttpStatus.OK);
    }
}
