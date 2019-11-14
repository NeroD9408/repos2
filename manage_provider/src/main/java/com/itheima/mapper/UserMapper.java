package com.itheima.mapper;

import com.itheima.domain.UserInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("select * from users")
    List<UserInfo> findAll();

    @Insert("insert into users set id = #{id}, email = #{email}, username = #{username}, password = #{password}, phoneNum = #{phoneNum}, status = #{status}")
    void add(UserInfo user);

    @Delete("delete from users where id in (#{ids})")
    void deleteUser(String[] ids);

    @Select("select * from users where id = #{uid}")
    UserInfo findUserById(String uid);
}
