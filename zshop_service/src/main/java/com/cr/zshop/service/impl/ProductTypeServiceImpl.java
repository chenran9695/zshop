package com.cr.zshop.service.impl;

import java.util.List;

import com.cr.zshop.common.constant.ProductTypeConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cr.zshop.common.exception.ProductTypeExistException;
import com.cr.zshop.dao.ProductTypeDao;
import com.cr.zshop.pojo.ProductType;
import com.cr.zshop.service.ProductTypeService;

/**  
* <p>Title: ProductTypeServiceImpl.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2019</p>  
* <p>Company:uestc</p>  
* @author ChenRan  
* @date 2019年3月7日  
* @version 1.0  
*/
@Service
@Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
public class ProductTypeServiceImpl implements ProductTypeService 
{
	@Autowired
	private ProductTypeDao productTypeDao;


	//这里不需要使用事务
	@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
	@Override
	public List<ProductType> findALL()
	{
		return productTypeDao.selectAll();
	}

	@Override
	public void add(String name) throws ProductTypeExistException{
		ProductType productType = productTypeDao.selectByName(name);
		if (null != productType)
		{
			throw new ProductTypeExistException("商品类型已经存在！");
		}
		productTypeDao.insert(name, ProductTypeConstant.PRODUCT_TYPE_ENABLE);
	}

	@Override
	public ProductType findById(int id) {
		return productTypeDao.selectById(id);
	}

	@Override
	public void modifyName(int id, String name) throws ProductTypeExistException {
		ProductType productType = productTypeDao.selectByName(name);
		if (null != productType)
		{
			throw new ProductTypeExistException("商品类型名称已存在！");
		}
		productTypeDao.updateName(id,name);
	}

	@Override
	public void removeById(int id) {
		productTypeDao.deleteById(id);
	}

	@Override
	public void modifyStatus(int id, int status) {
		productTypeDao.updateStatus(id,status);
	}

    @Override
    public List<ProductType> findByStatus(int status) {
        return productTypeDao.selectByStatus(status);
    }

	@Override
	public List<ProductType> findEnable() {
		return productTypeDao.selectByStatus(ProductTypeConstant.PRODUCT_TYPE_ENABLE);
	}

}
