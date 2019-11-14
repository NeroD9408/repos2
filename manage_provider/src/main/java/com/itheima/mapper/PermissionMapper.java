package com.itheima.mapper;

import com.itheima.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper {

    //查询所有权限信息
    @Select("select * from permission")
    List<Permission> findAll();

    //添加权限信息
    @Insert("insert into permission values(#{id}, #{permissionName}, #{url})")
    void save(Permission permission);

    //查询角色权限
    @Select("select * from permission where id in(select permissionId from role_permission where roleId = #{rid})")
    List<Permission> findRolePermissionsByRid(String rid);

    //清除角色权限
    @Delete("delete from role_permission where roleId = #{roleId}")
    void clear(String roleId);

    //给指定id角色添加权限
    @Insert("insert into role_permission values(#{pid}, #{roleId})")
    void savePermissionByRid(@Param("roleId") String roleId, @Param("pid") String pid);

    //通过id查询权限
    @Select("select * from permission where id = #{pid}")
    Permission findPermissionByPid(String pid);

    //清除角色权限
    @Delete("delete from role_permission where permissionId = #{pid}")
    void clearByPid(String pid);

    //删除权限
    @Delete("delete from permission where id = #{pid}")
    void deletePermissionByPid(String pid);
}
