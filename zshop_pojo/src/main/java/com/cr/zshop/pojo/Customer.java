package com.cr.zshop.pojo;

import java.util.Date;

/**
 * @author ：cr
 * @date ：Created in 2019/3/22 17:39
 * @description：顾客pojo$
 * @modified By：cr
 * @version: $
 */
public class Customer {
    private Integer id;
    private String name;
    private String loginName;
    private String password;
    private String phone;
    private Integer isValid;
    private Date registDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }
}
