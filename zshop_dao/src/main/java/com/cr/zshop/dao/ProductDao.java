package com.cr.zshop.dao;

import com.cr.zshop.params.ProductParams;
import com.cr.zshop.pojo.Product;

import java.util.List;

public interface ProductDao {
    /**
     * 添加商品
     */
    void insert(Product product);

    /**
     * 通过商品名称查找商品
     */
    Product selectByName(String name);

    /**
     * 查询所有的商品
     */
    List<Product> selectAll();

    /**
     * 通过id查询商品
     */
    Product selectById(int id);

    /**
     * 修改商品信息
     */
    void update(Product product);

    /**
     * 通过id删除商品
     */
    void deleteById(Integer id);
    /**
     * create by: cr
     * description: 通过传入的商品参数来查询商品
     * create time: 2019/3/21 20:37
     * params: [productParams]
     * @return java.util.List<com.cr.zshop.pojo.Product>
     */
    List<Product> selectByProductParams(ProductParams productParams);
}
