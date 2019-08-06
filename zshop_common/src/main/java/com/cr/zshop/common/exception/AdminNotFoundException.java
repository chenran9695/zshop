package com.cr.zshop.common.exception;

/**
 * @author ：cr
 * @date ：Created in 2019/3/20 11:14
 * @description：异常-未查询到响应管理员
 * @modified By：
 * @version: 0.0.1
 */
public class AdminNotFoundException extends Exception{
    public AdminNotFoundException() {
        super();
    }

    public AdminNotFoundException(String message) {
        super(message);
    }

    public AdminNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdminNotFoundException(Throwable cause) {
        super(cause);
    }

    protected AdminNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
