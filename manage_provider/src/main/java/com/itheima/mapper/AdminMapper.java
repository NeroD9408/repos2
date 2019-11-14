package com.itheima.mapper;

import com.itheima.domain.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AdminMapper {
    //查询用户名密码是否存在
    @Select("select * from admin where username = #{username} and password = #{password}")
    Admin findAdminByUsernameAndPassword(@Param("username") String username, @Param("password") String password) throws Exception;

    //修改用户的头像图片路径
    @Update("update admin set imgpath = #{imgpath} where id = #{id}")
    void updateAdmin(Admin admin) throws Exception;
}
