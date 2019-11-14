package com.itheima.service;

import com.itheima.domain.Product;

import java.util.List;


public interface ProductService {

    //查询所有商品信息
    List<Product> findAll(int pageNum, int pageSize) throws Exception;

    //新增商品
    void save(Product product) throws Exception;

    //删除选中商品信息
    void deleteSelect(String[] ids) throws Exception;

    //修改选中的商品信息
    void updateSelectStatus(String[] ids, int flag) throws Exception;

    //根据id查询商品信息
    Product findProductByPid(String pid) throws Exception;

    void updateProductInfo(Product product) throws Exception;
}
