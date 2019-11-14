package com.itheima.mapper;

import com.itheima.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface MemberMapper {

    //根据id查询member信息
    @Select("select * from member where id = #{mid}")
    Member findByMid(String mid);
}
