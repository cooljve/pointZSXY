package com.system.user.web;

import com.system.user.entity.SysUser;
import com.system.user.service.SysUserService;
import com.system.user.web.base.BaseController;
import com.system.user.web.model.SysUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(value = "/system")
public class SysUserController extends BaseController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 分页查询
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/sysUser", method = {RequestMethod.GET, RequestMethod.POST})
    public Page<SysUser> findAll(@ModelAttribute SysUserModel model, HttpServletRequest request, HttpServletResponse response) {
        Pageable pageable = super.getPageable(request);
        return sysUserService.pageList(model, pageable);
    }

    /**
     * 添加用户
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/addSysUser", method = {RequestMethod.POST})
    public String addSysUser(@ModelAttribute SysUserModel model, HttpServletRequest request) {
        SysUser sysUser = sysUserService.getSysUserByUsername(model.getUsername(), null);
        if (sysUser != null) {
            return super.message("", "对不起,用户名重复!", "error");
        }
        Integer id = sysUserService.addSysUser(model);
        return super.message(id.toString(), "操作成功", "success");
    }

    /**
     * 获取用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getSysUser/{id}", method = {RequestMethod.GET})
    public SysUser getSysUser(@PathVariable Integer id) {
        return sysUserService.getSysUserById(id);
    }

    /**
     * 更新用户
     *
     * @param id
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateSysUser/{id}", method = {RequestMethod.POST})
    public String updateSysUser(@PathVariable Integer id, @ModelAttribute SysUserModel model, HttpServletRequest request) {
        SysUser sysUser = sysUserService.getSysUserByUsername(model.getUsername(), id);
        if (sysUser != null) {
            return super.message("", "对不起,用户名重复!", "error");
        }
        sysUserService.updateSysUser(id, model);
        return super.message(id.toString(), "操作成功", "success");
    }

    /**
     * 删除用户
     *
     * @param ids
     * @param request
     * @return
     */
    @RequestMapping(value = "/delSysUser", method = {RequestMethod.POST})
    public String delSysUser(@RequestParam() String ids, HttpServletRequest request) {
        sysUserService.delSysUser(ids);
        return super.message(ids, "操作成功", "success");
    }

    @RequestMapping(value = "/upSysUserStatus", method = {RequestMethod.POST})
    public String upSysUserStatus(@RequestParam() Integer status, @RequestParam() String ids, HttpServletRequest request) {
        sysUserService.upSysUserStatus(status, ids);
        return super.message(ids, "操作成功", "success");
    }

    /**
     * 更新密码
     */
    @RequestMapping(value = "/upSysUserPass", method = {RequestMethod.POST})
    public String upSysUserPass(@RequestParam() String password, @RequestParam() String ids, HttpServletRequest request) {
        sysUserService.upSysUserPass(password, ids);
        return super.message(ids, "操作成功", "success");
    }
}
