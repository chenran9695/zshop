package com.cr.zshop.params;

/**
 * @author ：cr
 * @date ：Created in 2019/3/19 20:16
 * @description：管理员查询条件的封装类
 * @modified By：
 * @version: 0.0.1
 */
public class AdminParams {
    private String name;
    private String loginName;
    private String phone;
    private Integer roleId;
    private Integer isValid;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }
}
