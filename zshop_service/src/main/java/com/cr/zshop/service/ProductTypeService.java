package com.cr.zshop.service;

import java.util.List;

import com.cr.zshop.common.exception.ProductTypeExistException;
import com.cr.zshop.pojo.ProductType;

/**  
* <p>Title: ProductTypeService.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2019</p>  
* <p>Company:uestc</p>  
* @author ChenRan  
* @date 2019年3月7日  
* @version 1.0  
*/
public interface ProductTypeService 
{
	/**
	 * 查找全部的商品类型
	 * */
	List<ProductType> findALL();
	/**
	 * 添加商品类型
	 * 判断商品名称是否已经存在，存在则抛出异常，否则添加
	 * */
	void add(String name) throws ProductTypeExistException;

	/**
	 * 通过id查询商品类型
	 * */
	ProductType findById(int id);
	/**
	 *修改id对应的商品类型名称
	 */
	void modifyName(int id, String name) throws ProductTypeExistException;

	/**
	 * 通过id删除对应的商品类型id*/
	void removeById(int id);
	/**
	 * 通过id修改对应的商品类型状态
	 * */
	void modifyStatus(int id, int status);

	List<ProductType> findByStatus(int status);

    List<ProductType> findEnable();
}
