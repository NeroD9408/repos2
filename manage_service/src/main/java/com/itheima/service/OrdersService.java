package com.itheima.service;

import com.itheima.domain.Orders;

import java.util.List;

public interface OrdersService {

    //查询所有订单信息以及订单中的商品信息
    List<Orders> findAll(Integer pageNum, Integer pageSize) throws Exception;

    //根据订单id查询订单的详细信息
    Orders findOrderDetail(String oid) throws Exception;

    //删除选中的订单信息
    void deleteSelect(String[] ids) throws Exception;

    void updateSelectStatus(String[] ids, int flag) throws Exception;
}
