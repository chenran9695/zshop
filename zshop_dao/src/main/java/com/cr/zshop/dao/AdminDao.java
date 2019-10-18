package com.cr.zshop.dao;
/**
 * @author     ：cr
 * @date       ：Created in ${DATE} ${TIME}
 * @description：${description}
 * @modified By：
 * @version:     0.0.1
 */
import com.cr.zshop.params.AdminParams;
import com.cr.zshop.pojo.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminDao {
    /**
    * @Description: 查询全部的管理员
    * @Param: []
    * @return: java.util.List<com.cr.zshop.pojo.Admin>
    * @Author: cr
    * @Date: 2019/3/15
    */ 
    List<Admin> selectAll();
    /**
    * @Description: 查询指定id的管理员
    * @Param: [id]
    * @return: com.cr.zshop.pojo.Admin
    * @Author: cr
    * @Date: 2019/3/15
    */ 
    Admin selectById(Integer id);
    /**
    * @Description: 添加管理员
    * @Param: [admin]
    * @return: void
    * @Author: cr
    * @Date: 2019/3/15
    */ 
    void insert(Admin admin);
    /**
    * @Description: 修改管理员信息
    * @Param: [admin]
    * @return: void
    * @Author: cr
    * @Date: 2019/3/15
    */ 
    void update(Admin admin);
    /**
    * @Description: 修改管理员状态
    * @Param: [isValid]
    * @return: void
    * @Author: cr
    * @Date: 2019/3/15
    */ 
    void updateStatus(@Param("id") Integer id, @Param("isValid") Integer isValid);
    /**
    * @Description: 根据条件查询管理员
    * @Param: [adminParams]
    * @return: java.util.List<com.cr.zshop.pojo.Admin>
    * @Author: cr
    * @Date: 2019/3/19
    */
    List<Admin> selectByParams(AdminParams adminParams);
    /**
    * @Description: 根据登录名，密码，状态选择管理员，用于登陆
    * @Param: [loginName, password, status]
    * @return: com.cr.zshop.pojo.Admin
    * @Author: cr
    * @Date: 2019/3/20
    */ 
    Admin selectByLoginNameAndPassword(@Param("loginName") String loginName,@Param("password")String password,@Param("isValid")Integer isValid);
}
