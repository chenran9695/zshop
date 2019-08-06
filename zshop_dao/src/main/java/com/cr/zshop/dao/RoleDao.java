package com.cr.zshop.dao;

import com.cr.zshop.pojo.Role;

import java.util.List;

/**
 * @author ：cr
 * @date ：Created in 2019/3/18 20:27
 * @description：角色dao层
 * @modified By：
 * @version: 0.0.1
 */
public interface RoleDao {
    /**
    * @Description: 查询全部的角色
    * @Param: []
    * @return: List<Role>
    * @Author: cr
    * @Date: 2019/3/18
    */ 
    List<Role> selectAll();
}
