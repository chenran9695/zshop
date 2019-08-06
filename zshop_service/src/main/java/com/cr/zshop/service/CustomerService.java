package com.cr.zshop.service;

import com.cr.zshop.common.exception.CustomerNotFoundException;
import com.cr.zshop.pojo.Customer;

public interface CustomerService {
    /**
     * create by: cr
     * description: 通过账号密码登录
     * create time: 2019/3/25 12:40
     * params: [name, password]
     * @return com.cr.zshop.pojo.Customer
     */
    Customer loginByAccount(String name,String password) throws CustomerNotFoundException;
    /**
     * create by: cr
     * description: 通过手机号查询用户是否存在
     * create time: 2019/3/25 12:41
     * params: [phone]
     * @return com.cr.zshop.pojo.Customer
     */
    Customer findByPhone(String phone) throws CustomerNotFoundException;
}
