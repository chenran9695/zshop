package com.cr.zshop.common.exception;

/**
 * @author ：cr
 * @date ：Created in 2019/8/17 17:53
 * @description：异常：用户已存在$
 * @modified By：cr
 * @version: $
 */
public class CustomerExistException extends Exception{
    public CustomerExistException(String message) {
        super(message);
    }
}
