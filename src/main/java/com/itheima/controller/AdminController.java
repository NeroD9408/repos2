package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.domain.Admin;
import com.itheima.service.AdminService;
import com.itheima.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Arrays;

@RequestMapping("/admin")
public class AdminController {

    //设置允许上传的文件类型
    private String[] allowType = {"image/jpeg", "application/octet-stream"};


    @Reference
    private AdminService adminService;

    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session, String rememberMe, HttpServletResponse response) throws Exception {
        System.out.println(username);
        System.out.println(password);
        //调用service查询输入的账号密码是否存在
        Admin admin = adminService.login(username, password);
        //判断返回的用户信息是否为空
        if (admin == null) {
            //说明不存在该用户
            return "redirect:/login.jsp";
        }
        //登录成功，将该用户信息存放到session域中
        session.setAttribute("admin", admin);
        //查看用户是否勾选自动登录
        if (rememberMe != null && "autoLogin".equals(rememberMe)) {
            //已经勾选，则需要另外创建cookie
            Cookie cookie = new Cookie("autoLogin", admin.getUsername() + "@" + admin.getPassword());
            cookie.setMaxAge(7 * 24 * 60 * 60);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return "redirect:/index.jsp";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response) {
        //退出，清除session及cookie
        Cookie cookie = new Cookie("autoLogin", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        session.invalidate();
        return "redirect:/login.jsp";
    }

    @RequestMapping("/updateInfo")
    public String updateInfo(HttpSession session, String username, MultipartFile headImg, HttpServletRequest request) throws Exception {
        /*//接收用户传入的图片
        //获取图片名称
        System.out.println(username);
        System.out.println(headImg);
        String originalFilename = headImg.getOriginalFilename();
        String fileName = UuidUtil.getUuid() + originalFilename;
        String path = "/headImg";
        //将图片地址赋值给当前登录用户
        Admin admin = (Admin) session.getAttribute("admin");
        admin.setImgpath(path + fileName);
        //调用service对数据库中的信息进行修改
        adminService.updateAdminInfo(admin);
        Client client = new Client();
        WebResource resource = client.resource(path + fileName);
        resource.put(headImg.getBytes());*/

        //获取上传的文件类型
        String type = headImg.getContentType();
        if (!Arrays.asList(allowType).contains(type)) {
            throw new RuntimeException("上传文件类型不符合要求！！");
        }

        Admin admin = (Admin) session.getAttribute("admin");

        //1.设置存储文件的本地服务器的位置
        String realPath = request.getRealPath("/headImg/");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        //获取文件名称
        String fileName = UuidUtil.getUuid() + headImg.getOriginalFilename();
        headImg.transferTo(new File(realPath, fileName));

        admin.setImgpath(fileName);
        adminService.updateAdminInfo(admin);
        return "redirect:/index.jsp";
    }
}
