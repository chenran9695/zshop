package com.cr.zshop.service.impl;

import com.cr.zshop.common.constant.CustomerConstant;
import com.cr.zshop.common.exception.CustomerNotFoundException;
import com.cr.zshop.dao.CustomerDao;
import com.cr.zshop.pojo.Customer;
import com.cr.zshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

/**
 * @author ：cr
 * @date ：Created in 2019/3/22 17:50
 * @description：$顾客Service
 * @modified By：cr
 * @version: $
 */
@Service
@Transactional(propagation = Propagation.REQUIRED ,rollbackFor = Exception.class)
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Customer loginByAccount(String name, String password) throws CustomerNotFoundException {
        Customer customer = customerDao.selectByAccount(name, password, CustomerConstant.IS_VALID);
        if(ObjectUtils.isEmpty(customer)){
            throw new CustomerNotFoundException("登录失败，用户名或者密码错误！");
        }
        else {
            return customer;
        }
    }

    @Override
    public Customer findByPhone(String phone) throws CustomerNotFoundException {
        Customer customer = customerDao.selectByPhone(phone);
        if(!ObjectUtils.isEmpty(customer)){
            return customer;
        }
        else{
            throw new CustomerNotFoundException("该手机号未注册！");
        }
    }
}
