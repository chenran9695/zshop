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
    private Date registerDate;
    private String address;


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

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //构建器实现
    public static class Builder{
        private Integer id;
        private String name;
        private String loginName;
        private String password;
        private String phone;
        private Integer isValid;
        private Date registerDate;
        private String address;

        public Builder id(Integer id){
            this.id = id;
            return this;
        }
        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder loginName(String loginName){
            this.loginName = loginName;
            return this;
        }
        public Builder password(String password){
            this.password = password;
            return this;
        }
        public Builder phone(String phone){
            this.phone = phone;
            return this;
        }
        public Builder isValid(Integer isValid){
            this.isValid = isValid;
            return this;
        }
        public Builder registerDate(Date registerDate){
            this.registerDate = registerDate;
            return this;
        }
        public Builder address(String address){
            this.address = address;
            return this;
        }
        public Customer build(){
            return new Customer(this);
        }
    }
    private Customer(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.loginName = builder.loginName;
        this.password = builder.password;
        this.phone = builder.phone;
        this.isValid = builder.isValid;
        this.registerDate = builder.registerDate;
        this.address = builder.address;
    }
}
