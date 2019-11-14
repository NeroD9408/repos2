package com.itheima.service;

import com.itheima.domain.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll(Integer currentPage, Integer rows) throws Exception;

    void save(Role role) throws Exception;

    void updateRole(String uid) throws Exception;

    List<Role> findRolesByUserId(String uid) throws Exception;

    void clear(String userId) throws Exception;

    void saveRoleByUid(String userId, String[] ids) throws Exception;

    void clearByRid(String rid) throws Exception;

    void deleteRole(String rid) throws Exception;
}
