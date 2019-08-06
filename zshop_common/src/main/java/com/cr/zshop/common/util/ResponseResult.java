package com.cr.zshop.common.util;

import com.cr.zshop.common.constant.ResponseResultConstant;

public class ResponseResult {
    //状态
    private int status;
    //消息
    private String message;
    //数据
    private Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResponseResult(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseResult() {
    }

    /**
     * 成功时返回（需要返回数据data）
     * */
    public static ResponseResult success(Object data){
        return new ResponseResult(ResponseResultConstant.RESPONSE_STATUS_SUCCESS, "success", data);
    }
    /**
     * 成功时返回（需要返回消息）
     * */
    public static ResponseResult success(String message){
        return new ResponseResult(ResponseResultConstant.RESPONSE_STATUS_SUCCESS, message, null);
    }
    /**
     * 成功时返回（无返回数据data）
     * */
    public static ResponseResult success(){
        return new ResponseResult(ResponseResultConstant.RESPONSE_STATUS_SUCCESS, "success", null);
    }
    /**
     * 失败时返回（需要返回数据data）
     * */
    public static ResponseResult fail(Object data){
        return new ResponseResult(ResponseResultConstant.RESPONSE_STATUS_FAIL, "fail", data);
    }
    /**
     * 失败时返回（无返回数据data）
     * */
    public static ResponseResult fail(String message){
        return new ResponseResult(ResponseResultConstant.RESPONSE_STATUS_FAIL, message, null);
    }
}
