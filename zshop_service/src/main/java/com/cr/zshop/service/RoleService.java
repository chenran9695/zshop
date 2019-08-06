package com.cr.zshop.service;

import com.cr.zshop.pojo.Role;

import java.util.List;

/**
 * @author ：cr
 * @date ：Created in 2019/3/18 20:31
 * @description：角色service层
 * @modified By：
 * @version: 0.0.1
 */
public interface RoleService {
    /**
    * @Description: 查询全部的角色
    * @Param: []
    * @return: java.util.List<com.cr.zshop.pojo.Role>
    * @Author: cr
    * @Date: 2019/3/18
    */ 
    List<Role> findAll();
}
