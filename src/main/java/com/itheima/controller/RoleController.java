package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.PermissionService;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Reference
    private RoleService roleService;

    @Reference
    private PermissionService permissionService;

    @RequestMapping("/findAll")
    public String findAll(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                          Model model) throws Exception{
        List<Role> roleList = roleService.findAll(pageNum, pageSize);
        PageInfo<Role> pi = new PageInfo<Role>(roleList);
        model.addAttribute("pi", pi);
        return "role-list";
    }

    @RequestMapping("/save")
    public String save(Role role) throws Exception{
        roleService.save(role);
        return "redirect:/role/findAll";
    }

    @RequestMapping("/findRolePermissionById/{rid}")
    public String findRolePermissionById(@PathVariable("rid") String rid, Model model) throws Exception {
        List<Permission> permissions = permissionService.findAll(1, 99999);
        List<Permission> rolePermissions = permissionService.findRolePermissionsByRid(rid);
        //循环遍历总权限
        for (Permission permission : permissions) {
            //遍历拥有权限
            for (Permission rolePermission : rolePermissions) {
                if (rolePermission.getId().equals(permission.getId())) {
                    //如果角色拥有权限和总权限的id相同，标记flag为1
                    permission.setFlag(1);
                }
            }
        }
        model.addAttribute("permissions", permissions);
        return "role-permission-add";
    }

    @RequestMapping("/updateRolePermissions")
    public String updateRolePermissions(String roleId, String[] pids) throws Exception{
        permissionService.clear(roleId);
        permissionService.savePermissionByRid(roleId, pids);
        return "redirect:/role/findAll";
    }

    @RequestMapping("/deleteRole")
    public String deleteRole(String rid) throws Exception {
        //清除user-role中的数据
        roleService.clearByRid(rid);
        //清除role-permission中的数据
        permissionService.clear(rid);
        //删除角色信息
        roleService.deleteRole(rid);
        return "redirect:/role/findAll";
    }
}
