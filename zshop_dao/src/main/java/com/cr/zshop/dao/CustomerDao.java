package com.cr.zshop.dao;

import com.cr.zshop.pojo.Customer;
import org.apache.ibatis.annotations.Param;

public interface CustomerDao {
    /**
     * create by: cr
     * description: 通过用户名和密码来查询顾客
     * create time: 2019/3/22 17:54
     * params: [name, password, isValid]
     * @return com.cr.zshop.pojo.Customer
     */
    Customer selectByAccount(@Param("loginName") String name, @Param("password") String password,@Param("isValid") Integer isValid);
    /**
     * create by: cr
     * description: 通过手机号查询用户是否存在
     * create time: 2019/3/25 12:39
     * params: [phone]
     * @return com.cr.zshop.pojo.Customer
     */
    Customer selectByPhone(String phone);
}
