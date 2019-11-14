package com.itheima.mapper;

import com.itheima.domain.Member;
import com.itheima.domain.Orders;
import com.itheima.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrdersMapper {

    @Select("select * from orders")
    @Results({
        @Result(
            property = "product",
            javaType = Product.class,
            column = "productId",
            one = @One(select = "com.itheima.mapper.ProductMapper.findByPid")
        )
    })
    List<Orders> findAll() throws Exception;

    @Select("select * from orders where id = #{oid}")
    @Results({
        @Result(
            property = "product",
            javaType = Product.class,
            column = "productId",
            one = @One(select = "com.itheima.mapper.ProductMapper.findByPid")),
        @Result(
            property = "travellers",
            javaType = List.class,
            column = "id",
            many = @Many(select = "com.itheima.mapper.TravellerMapper.findByOid")),
        @Result(
            property = "member",
            javaType = Member.class,
            column = "memberId",
            one = @One(select = "com.itheima.mapper.MemberMapper.findByMid"))
    })
    Orders findByOid(String oid) throws Exception;

    //动态sql，删除选中的订单信息
    void deleteSelect(String[] ids) throws Exception;

    //动态sql，修改订单的状态为关闭
    void updateStatusToClose(String[] ids) throws Exception;

    //动态sql，修改订单的状态为开启
    void updateStatusToOpen(String[] ids) throws Exception;
}
