package com.itheima.mapper;

import com.itheima.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysLogMapper {

    @Select("select * from syslog order by visitTime desc")
    List<SysLog> findAll();

    @Insert("insert into syslog values(#{id}, #{visitTime}, #{username}, #{ip}, #{url}, #{executionTime}, #{method})")
    void save(SysLog sysLog);
}
