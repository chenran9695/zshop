package com.cr.zshop.front.controller;

import com.cr.zshop.common.constant.ResponseResultConstant;
import com.cr.zshop.common.exception.CustomerNotFoundException;
import com.cr.zshop.common.util.HttpClientUtils;
import com.cr.zshop.common.util.RedisUtils;
import com.cr.zshop.common.util.ResponseResult;
import com.cr.zshop.pojo.Customer;
import com.cr.zshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author ：cr
 * @date ：Created in 2019/3/25 12:25
 * @description：短信服务Controller$
 * @modified By：cr
 * @version: $
 */
@Controller
@RequestMapping("/front/sms")
public class SmsController {
    @Value("${sms.url}")
    private String url;

    @Value("${sms.tplId}")
    private String tplId;

    @Value("${sms.tplValue}")
    private String tplValue;

    @Value("${sms.key}")
    private String key;

    @Value("${sms.dtype}")
    private String dtpye;

    private String verificationCode;
    @Autowired
    private CustomerService customerService;
    /**
     * create by: cr
     * description: 判断手机哈是否已经注册
     * create time: 2019/3/25 14:49
     * params: [session, phone]
     * @return com.cr.zshop.common.util.ResponseResult
     */
    @RequestMapping("/sendVerificationCode")
    @ResponseBody
    public ResponseResult sendVerificationCode(HttpSession session,String phone){
        ResponseResult responseResult = new ResponseResult();

        try {
            //先判断手机号是否已注册
            Customer customer = customerService.findByPhone(phone);


            //创造6位随机验证码
            verificationCode = String.valueOf(new Random().nextInt(899999)+10000);
            //改为Redis
            RedisUtils.set(session.getId()+phone,verificationCode,60*3);
            //改用上述Redis
            //   session.setAttribute("verificationCode",verificationCode);

            //为请求参数赋值
            Map<String,String> params = new HashMap<>();
            params.put("mobile",phone);
            params.put("tpl_id",tplId);
            params.put("tpl_value",tplValue+verificationCode);
            params.put("key",key);
            params.put("dtype",dtpye);
            //发送验证码请求
            HttpClientUtils.doPost(url,params);

            responseResult.setStatus(ResponseResultConstant.RESPONSE_STATUS_SUCCESS);
            responseResult.setMessage("短信发送成功！");

        } catch (CustomerNotFoundException e) {
            responseResult.setStatus(ResponseResultConstant.RESPONSE_STATUS_FAIL);
            responseResult.setMessage(e.getMessage());
        }

        return responseResult;

    }
    /**
     * create by: cr
     * description: 通过短信验证登录
     * create time: 2019/3/25 14:49
     * params: [phone, verificationCode, session]
     * @return com.cr.zshop.common.util.ResponseResult
     */
    @RequestMapping("/login")
    @ResponseBody
    public ResponseResult login(String phone,String verificationCode,HttpSession session){
        ResponseResult responseResult = new ResponseResult();
        //改用Redis
        //String code = (String)session.getAttribute("verificationCode");
        String code = RedisUtils.get(session.getId()+phone);
        try {
            //未输入验证码
            if(ObjectUtils.isEmpty(verificationCode)){
                responseResult = ResponseResult.fail("请输入验证码！");
            }
            //登录成功
            else if(verificationCode.equals(code)){

                Customer customer = customerService.findByPhone(phone);
                session.setAttribute("customer",customer);
                responseResult = ResponseResult.success("登录成功！");
                responseResult.setData(customer);
            }
        } catch (CustomerNotFoundException e) {
                responseResult = ResponseResult.fail("验证码错误，请重新输入！");
        }
        return responseResult;

    }
}
