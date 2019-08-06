package com.cr.zshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cr.zshop.pojo.ProductType;

/**  
* <p>Title: ProductTypeDao.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2019</p>  
* <p>Company:uestc</p>  
* @author ChenRan  
* @date 2019年3月7日  
* @version 1.0  
*/
public interface ProductTypeDao 
{
	/**
	 * 	查找所有类型
	 * */
	List<ProductType> selectAll();
	/**
	 *	 通过商品id选取商品类型
	 * */
	ProductType selectById(int id);
	/**
	 *	 通过商品类型名曾选取商品类型
	 * */
	ProductType	selectByName(String name);
	/**
	 *	 添加商品类型
	 * */
	void insert(@Param("name")String name,@Param("status")int status);
	/**
	 *	 通过id更新商品类型的名称
	 * */
	void updateName(@Param("id")int id,@Param("name")String name);
	/**
	 *	 通过id更新商品状态
	 * */
	void updateStatus(@Param("id")int id,@Param("status")int status);
	/**
	 *	 通过id删除商品类型
	 * */
	void deleteById(int id);
	/**
	 * 选择指定状态（启用或者禁用）的全部商品类型
	 * */
	List<ProductType> selectByStatus(int status);
}
