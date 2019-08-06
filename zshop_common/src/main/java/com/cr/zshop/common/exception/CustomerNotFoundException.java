package com.cr.zshop.common.exception;

/**
 * @author ：cr
 * @date ：Created in 2019/3/22 18:14
 * @description：异常-用户未找到$
 * @modified By：cr
 * @version: $
 */
public class CustomerNotFoundException extends Exception{
    public CustomerNotFoundException() {
        super();
    }

    public CustomerNotFoundException(String message) {
        super(message);
    }

    public CustomerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerNotFoundException(Throwable cause) {
        super(cause);
    }
}
