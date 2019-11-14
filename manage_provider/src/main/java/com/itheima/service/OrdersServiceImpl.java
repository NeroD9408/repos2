package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.itheima.domain.Orders;
import com.itheima.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(interfaceClass = OrdersService.class)
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;


    public List<Orders> findAll(Integer pageNum, Integer pageSize) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        return ordersMapper.findAll();
    }

    public Orders findOrderDetail(String oid) throws Exception {
        return ordersMapper.findByOid(oid);
    }

    public void deleteSelect(String[] ids) throws Exception {
        ordersMapper.deleteSelect(ids);
    }

    public void updateSelectStatus(String[] ids, int flag) throws Exception {
        if (flag == 0) {
            //说明需要修改状态为关闭
            ordersMapper.updateStatusToClose(ids);
        } else {
            //说明需要修改状态为开启
            ordersMapper.updateStatusToOpen(ids);
        }
    }
}
