package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.itheima.domain.Product;
import com.itheima.mapper.ProductMapper;
import com.itheima.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(interfaceClass = ProductService.class)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    public List<Product> findAll(int pageNum, int pageSize) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        return productMapper.findAll();
    }

    public void save(Product product) throws Exception {
        product.setId(UuidUtil.getUuid());
        productMapper.save(product);
    }

    public void deleteSelect(String[] ids) throws Exception {
        productMapper.deleteSelect(ids);
    }

    public void updateSelectStatus(String[] ids, int flag) throws Exception {
        if (flag == 0) {
            //说明需要修改商品的状态为关闭
            productMapper.updateSelectStatusToClose(ids);
        } else {
            //说明需要修改商品的状态为开启
            productMapper.updateSelectStatusToOpen(ids);
        }
    }

    public Product findProductByPid(String pid) throws Exception {
        return productMapper.findByPid(pid);
    }

    public void updateProductInfo(Product product) throws Exception {
        productMapper.updateProductInfo(product);
    }
}
