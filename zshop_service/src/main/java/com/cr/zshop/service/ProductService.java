package com.cr.zshop.service;

import com.cr.zshop.dto.ProductDto;
import com.cr.zshop.params.ProductParams;
import com.cr.zshop.pojo.Product;
import org.apache.commons.fileupload.FileUploadException;

import java.io.OutputStream;
import java.util.List;

public interface ProductService {
    /**
     * 添加商品
     * */
    void add(ProductDto productDto) throws FileUploadException;
    /**
     * 检查商品名称是否已经存在
    * */
    Boolean checkName(String name);
    /**
     * 查询所有的商品
     * */
    List<Product> findAll();
    /**
    * @Description: 通过id查找商品
    * @Param: [id]
    * @return: com.cr.zshop.pojo.Product
    * @Author: cr
    * @Date: 2019/3/14
    */ 
    Product findById(int id);
    /**
    * @Description: 获取商品图片，并添加到输出流
    * @Param: [path, outputStream]
    * @return: void
    * @Author: cr
    * @Date: 2019/3/14
    */ 
    void getImage(String path, OutputStream outputStream);
    /**
    * @Description: 修改商品信息
    * @Param: [productDto]
    * @return: void
    * @Author: cr
    * @Date: 2019/3/14
    */ 
    void modify(ProductDto productDto) throws FileUploadException;
    /**
    * @Description: 删除商品
    * @Param: [id]
    * @return: void
    * @Author: cr
    * @Date: 2019/3/14
    */
    void remove(Integer id);
    /**
     * create by: cr
     * description: 查询符合输入的商品参数的商品
     * create time: 2019/3/21 20:30
     * params: [productParams]
     * @return java.util.List<com.cr.zshop.pojo.Product>
     */
    List<Product> find(ProductParams productParams);
}
