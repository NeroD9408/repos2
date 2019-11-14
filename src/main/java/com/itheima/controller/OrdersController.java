package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.Orders;
import com.itheima.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Reference
    private OrdersService ordersService;

    //查询所有订单信息
    @RequestMapping("/findAll")
    public String findAll(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            Model model) throws Exception {

        List<Orders> ordersList = ordersService.findAll(pageNum, pageSize);
        PageInfo pi = new PageInfo(ordersList);
        model.addAttribute("pi", pi);
        return "orders-list";
    }

    //查询订单详情以及商品及旅行人的信息
    @RequestMapping("/findOrderDetail")
    public String findOrderDetail(String oid, Model model) throws Exception {
        Orders orders = ordersService.findOrderDetail(oid);
        System.out.println(orders);
        model.addAttribute("orders", orders);
        return "orders-show";
    }

    //删除选中的订单
    @RequestMapping("/deleteSelect")
    public String deleteSelect(String[] ids) throws Exception {
        ordersService.deleteSelect(ids);
        return "redirect:/orders/findAll";
    }

    //修改订单的状态
    @RequestMapping("/updateSelectStatus")
    public String updateSelectStatus(String[] ids, int flag, String pageNum, String pageSize) throws Exception {
        ordersService.updateSelectStatus(ids, flag);
        return "redirect:/orders/findAll?pageNum=" + pageNum + "&pageSize=" + pageSize + "";
    }

}
