package com.cr.zshop.pojo;

import java.io.Serializable;

/**  
* <p>Title: ProductType.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2019</p>  
* <p>Company:uestc</p>  
* @author ChenRan  
* @date 2019年3月7日  
* @version 1.0  
*/
public class ProductType implements Serializable
{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
