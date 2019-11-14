package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Reference
    private ProductService productService;

    //查询所有商品信息
    @RequestMapping("/findAll")
    public String findAll(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", required = false, defaultValue = "3") Integer pageSize,
                          Model model) throws Exception {
        List<Product> productList = productService.findAll(pageNum, pageSize);
        PageInfo<Product> pi = new PageInfo<Product>(productList);
        model.addAttribute("pi", pi);
        return "product-list";
    }

    //新增商品
    @RequestMapping("/save")
    public String save(Product product) throws Exception {
        productService.save(product);
        return "redirect:/product/findAll";
    }

    //删除选中的商品
    @RequestMapping("/deleteSelect")
    public String deleteSelect(String[] ids) throws Exception {
        productService.deleteSelect(ids);
        return "redirect:/product/findAll";
    }

    //修改商品的状态
    @RequestMapping("/updateSelectStatus")
    public String updateSelectStatus(String[] ids, int flag, String pageNum, String pageSize) throws Exception {
        productService.updateSelectStatus(ids, flag);
        return "redirect:/product/findAll?pageNum=" + pageNum + "&pageSize=" + pageSize + "";
    }

    //修改商品信息之前的数据回显
    @RequestMapping("/findProductByPid")
    public String findProductByPid(String pid, String pageNum, String pageSize, Model model) throws Exception {
        Product product = productService.findProductByPid(pid);
        System.out.println(pageNum);
        System.out.println(pageSize);
        model.addAttribute("p", product);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("pageSize", pageSize);
        return "product-update";
    }

    //修改商品的信息
    @RequestMapping("/updateProductInfo")
    public String updateProductInfo(Product product, String pageSize, String pageNum) throws Exception {
        productService.updateProductInfo(product);
        return "redirect:/product/findAll?pageNum=" + pageNum + "&pageSize=" + pageSize + "";
    }
}
