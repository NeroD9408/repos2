package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.itheima.domain.SysLog;
import com.itheima.mapper.SysLogMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(interfaceClass = SysLogService.class)
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    public List<SysLog> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return sysLogMapper.findAll();
    }

    @Override
    public void save(SysLog sysLog) {
        sysLogMapper.save(sysLog);
    }
}
