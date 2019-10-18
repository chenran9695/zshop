package com.cr.zshop.common.exception;

/**  
* <p>Title: ProductTypeExistException.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2019</p>  
* <p>Company:uestc</p>  
* @author ChenRan  
* @date 2019年3月7日  
* @version 1.0  
*/
public class ProductTypeExistException extends Exception {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public ProductTypeExistException() {
	        super();
	    }
	 
	    public ProductTypeExistException(String message) {
	        super(message);
	    }

	    public ProductTypeExistException(String message, Throwable cause) {
	        super(message, cause);
	    }

	    public ProductTypeExistException(Throwable cause) {
	        super(cause);
	    }

	    public ProductTypeExistException(String message, Throwable cause,
	                        boolean enableSuppression,
	                        boolean writableStackTrace) {
	        super(message, cause, enableSuppression, writableStackTrace);
	    }
}
