package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.itheima.domain.Role;
import com.itheima.mapper.RoleMapper;
import com.itheima.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(interfaceClass = RoleService.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public List<Role> findAll(Integer pageNum, Integer pageSize) throws Exception{
        PageHelper.startPage(pageNum, pageSize);
        return roleMapper.findAll();
    }

    public void save(Role role) throws Exception{
        role.setId(UuidUtil.getUuid());
        roleMapper.save(role);
    }

    public void updateRole(String uid) throws Exception{

    }

    public List<Role> findRolesByUserId(String uid) throws Exception{
        return roleMapper.findRolesByUid(uid);
    }

    public void clear(String userId) throws Exception {
        roleMapper.clear(userId);
    }

    public void saveRoleByUid( String userId, String[] ids) throws Exception {
        if(ids != null && ids.length > 0) {
            for (String id : ids) {
                roleMapper.saveRoleByUid(userId, id);
            }
        }
    }

    public void clearByRid(String rid) throws Exception {
        roleMapper.clearByRid(rid);
    }

    public void deleteRole(String rid) throws Exception {
        roleMapper.deleteRole(rid);
    }
}
