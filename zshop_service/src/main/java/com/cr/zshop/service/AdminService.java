package com.cr.zshop.service;

import com.cr.zshop.common.exception.AdminNotFoundException;
import com.cr.zshop.params.AdminParams;
import com.cr.zshop.pojo.Admin;

import java.util.List;

/**
 * @author ：cr
 * @date ：Created in 2019/3/15 11:00
 * @description：管理员管理service层
 * @modified By：
 * @version: 0.0.1
 */
public interface AdminService {
    /**
    * @Description: 查询全部管理员
    * @Param: []
    * @return: java.util.List<com.cr.zshop.pojo.Admin>
    * @Author: cr
    * @Date: 2019/3/15
    */ 
    List<Admin> findAll();
    /**
    * @Description: 通过id查询管理员
    * @Param: [id]
    * @return: com.cr.zshop.pojo.Admin
    * @Author: cr
    * @Date: 2019/3/15
    */ 
    Admin findById(Integer id);
    /**
    * @Description: 添加管理员
    * @Param: [admin]
    * @return: void
    * @Author: cr
    * @Date: 2019/3/15
    */ 
    void add(Admin admin);
    /** 
    * @Description: 修改管理员信息
    * @Param: [admin]
    * @return: void
    * @Author: cr
    * @Date: 2019/3/15
    */ 
    void modify(Admin admin);
    /**
    * @Description: 更改管理员状态
    * @Param: [status]
    * @return: void
    * @Author: cr
    * @Date: 2019/3/15
    */ 
    void modifyStatus(Integer id,Integer status);
    /**
    * @Description: 根据条件查询管理员
    * @Param: [adminParams]
    * @return: java.util.List<com.cr.zshop.params.AdminParams>
    * @Author: cr
    * @Date: 2019/3/19
    */
    List<Admin> findByParams(AdminParams adminParams);
    /**
    * @Description: 管理员登陆
    * @Param: [loginName, password]
    * @return: void
    * @Author: cr
    * @Date: 2019/3/20
    */ 
    Admin login(String loginName, String password) throws AdminNotFoundException;
}
