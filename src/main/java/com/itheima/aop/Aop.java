package com.itheima.aop;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import com.itheima.utils.UuidUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

//切面对象，存放增强方法
@Component
@Aspect
public class Aop {

    @Autowired
    private HttpServletRequest request;

    @Reference
    private SysLogService sysLogService;

    @Pointcut("execution(* com.itheima.controller.*.*(..))")
    private void pid(){}

    private SysLog sysLog;
    private long visitTime;

    @Before("pid()")
    public void before() {
        sysLog = new SysLog();
        sysLog.setVisitTime(new Date());
        visitTime = new Date().getTime();
    }

    @After("pid()")
    public void after(JoinPoint joinPoint) {
        sysLog.setId(UuidUtil.getUuid());
        sysLog.setUsername("aaa");
        sysLog.setIp(request.getRemoteAddr());
        sysLog.setExecutionTime(new Date().getTime() - visitTime);
        String requestURI = request.getRequestURI();
        String[] split = requestURI.split("/");
        String url = split[2] + "/" + split[3];
        sysLog.setUrl(url);
        sysLog.setMethod(joinPoint.getSignature().getName());
        if (!requestURI.contains("/sysLog/")) {
            sysLogService.save(sysLog);
        }
    }
}

