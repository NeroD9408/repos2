package com.itheima.service;

import com.itheima.domain.SysLog;

import java.util.List;

public interface SysLogService {
    List<SysLog> findAll(Integer currentPage, Integer rows);

    void save(SysLog sysLog);
}
