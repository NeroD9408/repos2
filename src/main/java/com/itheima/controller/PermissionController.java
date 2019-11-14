package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.Permission;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Reference
    private PermissionService permissionService;

    @RequestMapping("/findAll")
    public String findAll(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            Model model) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        List<Permission> permissionList = permissionService.findAll(pageNum, pageSize);
        PageInfo pi = new PageInfo(permissionList);
        model.addAttribute("pi", pi);
        return "permission-list";
    }

    @RequestMapping("/save")
    public String save(Permission permission) throws Exception{
        permissionService.save(permission);
        return "redirect:/permission/findAll";
    }

    @RequestMapping("/findPermissionByPid")
    public String findPermissionByPid(String pid, Model model) throws Exception {
        Permission permission = permissionService.findPermissionByPid(pid);
        model.addAttribute("permission", permission);
        return "permission-show";
    }

    @RequestMapping("/deletePermissionByPid")
    public String deletePermissionByPid(String pid) throws Exception {
        permissionService.deletePermissionByPid(pid);
        return "redirect:/permission/findAll";
    }

}
