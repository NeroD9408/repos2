package com.itheima.mapper;

import com.itheima.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TravellerMapper {

    //根据订单id查询所有出行的旅行人信息
    @Select("select * from traveller where id in(\n" +
            "select travellerId from order_traveller where orderId in(\n" +
            "select id from orders where productId = (\n" +
            "select productId from orders where id  = #{oid})\n" +
            ")\n" +
            ")")
    List<Traveller> findByOid(String oid);

}
