package com.cr.zshop.front.controller;

import com.cr.zshop.common.constant.ResponseResultConstant;
import com.cr.zshop.common.exception.CustomerExistException;
import com.cr.zshop.common.exception.CustomerNotFoundException;
import com.cr.zshop.common.util.ResponseResult;
import com.cr.zshop.pojo.Customer;
import com.cr.zshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author ：cr
 * @date ：Created in 2019/3/22 17:43
 * @description：顾客Controller$
 * @modified By：cr
 * @version: $
 */
@Controller
@RequestMapping("/front/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    /**
     * create by: cr
     * description: 用户通过账号密码登录
     * create time: 2019/3/22 17:49
     * params: [name, password, session]
     * @return com.cr.zshop.common.util.ResponseResult
     */
    @RequestMapping("/loginByAccount")
    @ResponseBody
    public ResponseResult loginByAccount(String name, String password, HttpSession session){
        ResponseResult responseResult = new ResponseResult();
        try {
            Customer customer = customerService.loginByAccount(name,password);
            session.setAttribute("customer",customer);
            responseResult.setData(customer);
            responseResult.setStatus(ResponseResultConstant.RESPONSE_STATUS_SUCCESS);
            responseResult.setMessage("登录成功");


        } catch (CustomerNotFoundException e) {
           responseResult.setStatus(ResponseResultConstant.RESPONSE_STATUS_FAIL);
           responseResult.setMessage("登录失败，账号或者密码错误！");
        }
        return responseResult;
    }
    /**
     * create by: cr
     * description: 注销
     * create time: 2019/3/25 14:45
     * params: [session]
     * @return com.cr.zshop.common.util.ResponseResult
     */
    @RequestMapping("/logout")
    @ResponseBody
    public ResponseResult logout(HttpSession session){
        session.invalidate();
        return ResponseResult.success();
    }
    /**
     * create by: cr
     * description: 用户注册
     * create time: 2019/8/17 16:12
     * params:
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public ResponseResult register(String name,String loginName,String password,String phone,String address){
       //使用构建器
        Customer customer = new Customer.Builder().name(name).loginName(loginName).password(password)
                .phone(phone).address(address).isValid(1).registerDate(new Date()).build();

        try {
            customerService.register(customer);
        } catch (CustomerExistException e) {
            System.out.println(e.getMessage());
            return ResponseResult.fail(e.getMessage());
        }

        return ResponseResult.success("注册成功！");
    }
}
