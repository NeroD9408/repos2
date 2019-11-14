package com.itheima.mapper;

import com.github.pagehelper.Page;
import com.itheima.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface ProductMapper {

    @Select("select * from product")
    Page<Product> findAll() throws Exception;

    @Insert("insert into product values(#{id}, #{productNum}, #{productName}, #{cityName}, #{departureTime}, #{productPrice}, #{productDesc}, #{productStatus})")
    void save(Product product) throws Exception;

    @Select("select * from product where id = #{pid}")
    Product findByPid(String pid);

    //动态sql，删除多个数据
    void deleteSelect(String[] ids) throws Exception;

    //动态sql，修改多个数据状态至关闭
    void updateSelectStatusToClose(String[] ids) throws Exception;

    //动态sql，修改多个数据状态至开启
    void updateSelectStatusToOpen(String[] ids) throws Exception;

    //修改商品信息
    @Update("update product set productNum = #{productNum}, productName = #{productName}, cityName = #{cityName}, DepartureTime = #{departureTime}," +
            "productPrice = #{productPrice}, productDesc = #{productDesc}, productStatus = #{productStatus} where id = #{id}")
    void updateProductInfo(Product product) throws Exception;
}
