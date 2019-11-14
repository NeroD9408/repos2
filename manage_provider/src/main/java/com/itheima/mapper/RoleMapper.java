package com.itheima.mapper;

import com.itheima.domain.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {

    //查询所有角色信息
    @Select("select * from role")
    List<Role> findAll() throws Exception;

    //添加角色信息
    @Insert("insert into role values(#{id}, #{roleName}, #{roleDesc})")
    void save(Role role) throws Exception;

    //查询用户角色
    @Select("select * from role where id in(select roleId from users_role where userId = #{uid})")
    List<Role> findRolesByUid(String uid) throws Exception;

    //清除用户对应的角色信息
    @Delete("delete from users_role where userId = #{userId}")
    void clear(String userId) throws Exception;

    //给角色添加角色
    @Insert("insert into users_role values(#{userId}, #{id})")
    void saveRoleByUid(@Param("userId") String userId, @Param("id") String id) throws Exception;

    //清除用户角色
    @Delete("delete from users_role where roleId = #{rid}")
    void clearByRid(String rid) throws Exception;

    //删除角色
    @Delete("delete from role where id = #{rid}")
    void deleteRole(String rid) throws Exception;
}
