package com.cr.zshop.service.impl;

import com.cr.zshop.common.constant.AdminConstant;
import com.cr.zshop.common.exception.AdminNotFoundException;
import com.cr.zshop.dao.AdminDao;
import com.cr.zshop.params.AdminParams;
import com.cr.zshop.pojo.Admin;
import com.cr.zshop.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ：cr
 * @date ：Created in 2019/3/15 11:06
 * @description：
 * @modified By：
 * @version: 0.0.1
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Override
    public List<Admin> findAll() {
        return adminDao.selectAll();
    }

    @Override
    public Admin findById(Integer id) {
        return adminDao.selectById(id);
    }

    @Override
    public void add(Admin admin) {
        adminDao.insert(admin);
    }

    @Override
    public void modify(Admin admin) {
        adminDao.update(admin);
    }

    @Override
    public void modifyStatus(Integer id,Integer status) {
        adminDao.updateStatus(id,status);
    }

    @Override
    public List<Admin> findByParams(AdminParams adminParams) {
        return adminDao.selectByParams(adminParams);
    }

    @Override
    public Admin login(String loginName, String password) throws AdminNotFoundException {
        Admin admin=  adminDao.selectByLoginNameAndPassword(loginName,password, AdminConstant.IS_VALIAD);
        if(admin != null){
            return admin;
        }
        throw new AdminNotFoundException("登陆失败，管理员用户名或者密码错误！");
    }
}
