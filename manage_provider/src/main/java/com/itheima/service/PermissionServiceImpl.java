package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.domain.Permission;
import com.itheima.mapper.PermissionMapper;
import com.itheima.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service(interfaceClass = PermissionService.class)
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    public List<Permission> findAll(Integer pageNum, Integer pageSize) throws Exception {
        return permissionMapper.findAll();
    }

    public void save(Permission permission) throws Exception{
        permission.setId(UuidUtil.getUuid());
        permissionMapper.save(permission);
    }

    public List<Permission> findRolePermissionsByRid(String rid) throws Exception{
        return permissionMapper.findRolePermissionsByRid(rid);
    }

    public void clear(String roleId) throws Exception {
        permissionMapper.clear(roleId);
    }

    public void savePermissionByRid(String roleId, String[] pids) throws Exception {
        if (pids != null && pids.length > 0) {
            for (String pid : pids) {
                permissionMapper.savePermissionByRid(roleId, pid);
            }
        }
    }

    public Permission findPermissionByPid(String pid) throws Exception {
        return permissionMapper.findPermissionByPid(pid);
    }

    public void deletePermissionByPid(String pid) throws Exception {
        permissionMapper.clearByPid(pid);
        permissionMapper.deletePermissionByPid(pid);
    }
}
