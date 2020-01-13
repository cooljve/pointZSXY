package com.system.user.web;

import com.system.user.entity.SysRole;
import com.system.user.entity.SysRoleMenu;
import com.system.user.service.SysRoleMenuService;
import com.system.user.service.SysRoleService;
import com.system.user.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping(value = "/system")
public class SysRoleController extends BaseController {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMenuService roleMenuService;

    @RequestMapping(value = "/getSysRole/{id}", method = {RequestMethod.GET})
    public SysRole updateSysRole(@PathVariable Integer id) {
        return sysRoleService.getSysRoleById(id);
    }

    @RequestMapping(value = "/findSysRole", method = {RequestMethod.POST})
    public List<SysRole> findSysRole(HttpServletRequest request, HttpServletResponse response) {
        List<SysRole> list = sysRoleService.findSysRole();
        return list;
    }

    /**
     * 根据角色查询菜单
     *
     * @param roleid
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/findMenuByRoles/{roleId}", method = {RequestMethod.GET})
    public List<SysRoleMenu> findMenuByRoles(@PathVariable Integer roleid, HttpServletRequest request, HttpServletResponse response) {
        return roleMenuService.listMenuByRoleId(roleid);
    }

    /**
     * 插入角色菜单中间表
     *
     * @param roleId
     * @param menusId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/insertSysRoleMenu/{roleId}", method = {RequestMethod.POST})
    public String findMenuByRoles(@PathVariable Integer roleId, @RequestParam String menusId, HttpServletRequest request, HttpServletResponse response) {
        roleMenuService.insertSysRoleMenu(roleId, menusId);
        return super.message("", "操作成功", "success");
    }

}
