package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sysLog")
public class sysLogController {

    @Reference
    private SysLogService sysLogService;

    @RequestMapping("/findAll")
    public String findAll(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                          Model model) throws Exception {
        List<SysLog> roleList = sysLogService.findAll(pageNum, pageSize);
        PageInfo<SysLog> pi = new PageInfo<SysLog>(roleList);
        model.addAttribute("pi", pi);
        return "syslog-list";
    }

    @RequestMapping("/exportTxt")
    @ResponseBody
    public Map<String, Object> exportTxt() throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        try {
            //从数据库中查询所有信息
            List<SysLog> list = sysLogService.findAll(1, 9999999);
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("D:\\syslog.txt")));
            for (SysLog sysLog : list) {
                bw.write(sysLog.toString());
                bw.newLine();
                bw.flush();
            }
            bw.close();
            data.put("flag", true);
        } catch (IOException e) {
            e.printStackTrace();
            data.put("flag", false);
        }
        return data;
    }
}
