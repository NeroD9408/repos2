package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.domain.Admin;
import com.itheima.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Service(interfaceClass = AdminService.class)
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    //验证用户登录
    public Admin login(String username, String password) throws Exception {
        return adminMapper.findAdminByUsernameAndPassword(username, password);
    }

    //更新admin对象信息
    public void updateAdminInfo(Admin admin) throws Exception {
        System.out.println(admin);
        adminMapper.updateAdmin(admin);
    }
}
