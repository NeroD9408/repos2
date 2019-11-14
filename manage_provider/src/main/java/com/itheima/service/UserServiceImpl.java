package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.itheima.domain.UserInfo;
import com.itheima.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public List<UserInfo> findAll(Integer pageNum, Integer pageSize) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.findAll();
    }

    public void add(UserInfo user) throws Exception {
        user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        userMapper.add(user);
    }

    public void deleteUser(String[] ids) throws Exception {
        /*for (String uid : ids) {
            userMapper.clearUser(uid);
        }*/
        userMapper.deleteUser(ids);
    }

    public UserInfo findUserById(String uid) throws Exception {
        return userMapper.findUserById(uid);
    }
}
