package com.cr.zshop.front.controller;

import com.cr.zshop.common.constant.PaginationConstant;
import com.cr.zshop.params.ProductParams;
import com.cr.zshop.pojo.Product;
import com.cr.zshop.pojo.ProductType;
import com.cr.zshop.service.ProductService;
import com.cr.zshop.service.ProductTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.OutputStream;
import java.util.List;

/**
 * @author ：cr
 * @date ：Created in 2019/3/21 20:20
 * @description：商品展示Controller$
 * @modified By：cr
 * @version: $
 */
@Controller
@RequestMapping("/front/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTypeService productTypeService;
    /**
     * create by: cr
     * description: 查询商品的全部类型
     * create time: 2019/3/21 20:24
     * params: []
     * @return java.util.List<com.cr.zshop.pojo.ProductType>
     */
    @ModelAttribute("productTypes")
    public List<ProductType> getProductTypes(){
        return productTypeService.findALL();
    }

    @RequestMapping("/search")
    public String search(Model model,Integer pageNum, ProductParams productParams){
        //判断是否传入页码，如未传入，则页码默认为1；
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum = PaginationConstant.PAGE_NUM;
        }

        PageHelper.startPage(pageNum,PaginationConstant.FRONT_PAGE_SIZE);
        List<Product> products = productService.find(productParams);
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("productParams",productParams);
        return "main";
    }
    /**
     * @Description: 获取商品图片
     * @Param: [path, outputStream]
     * @return: void
     * @Author: cr
     * @Date: 2019/3/13
     */
    @RequestMapping("/getImage")
    @ResponseBody
    public void getImage(String path, OutputStream outputStream){
        productService.getImage(path,outputStream);
    }
}
