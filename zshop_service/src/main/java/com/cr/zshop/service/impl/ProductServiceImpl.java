package com.cr.zshop.service.impl;

import com.cr.zshop.common.util.StringUtils;
import com.cr.zshop.dao.ProductDao;
import com.cr.zshop.dto.ProductDto;
import com.cr.zshop.params.ProductParams;
import com.cr.zshop.pojo.Product;
import com.cr.zshop.pojo.ProductType;
import com.cr.zshop.service.ProductService;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StreamUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public void add(ProductDto productDto) throws FileUploadException {
      Product product = this.dealWithImageUpload(productDto);
      productDao.insert(product);
    }

    @Override
    public Boolean checkName(String name) {
        Product product = productDao.selectByName(name);
        if(product != null)
        {
            return  false;
        }
        return true;
    }

    @Override
    public List<Product> findAll() {
        return productDao.selectAll();
    }

    @Override
    public Product findById(int id) {
        return productDao.selectById(id);
    }

    @Override
    public void getImage(String path, OutputStream outputStream) {
        try {
            StreamUtils.copy(new FileInputStream(path),outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modify(ProductDto productDto) throws FileUploadException {
        Product product = this.dealWithImageUpload(productDto);
        productDao.update(product);
    }

    @Override
    public void remove(Integer id) {
            productDao.deleteById(id);
    }

    @Override
    public List<Product> find(ProductParams productParams) {
        return productDao.selectByProductParams(productParams);
    }
    /**
     * create by: cr
     * description: 处理商品图片上传及商品dto对象转换为商品对象的问题
     * create time: 2019/3/21 20:35
     * params: [productDto]
     * @return com.cr.zshop.pojo.Product
     */
    private Product dealWithImageUpload(ProductDto productDto) throws FileUploadException{
        //1.文件上传
        String fileName = StringUtils.renameFileName(productDto.getFileName());
        String filePath = productDto.getUploadPath()+"/"+fileName;
        try {
            StreamUtils.copy(productDto.getInputStream(),new FileOutputStream(filePath));
        } catch (IOException e) {
            throw new FileUploadException("文件上传失败:"+e.getMessage());
        }
        //2.保存到数据库,将dto对象转化为pojo对象
        Product product = new Product();
        try {
            PropertyUtils.copyProperties(product,productDto);

            ProductType productType = new ProductType();
            productType.setId(productDto.getProductTypeId());


            product.setProductType(productType);
            product.setImage(filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }
}
