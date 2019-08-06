package com.cr.zshop.service.impl;

import com.cr.zshop.dao.RoleDao;
import com.cr.zshop.pojo.Role;
import com.cr.zshop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ：cr
 * @date ：Created in 2019/3/18 20:33
 * @description：
 * @modified By：
 * @version: 0.0.1
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Role> findAll() {
        return roleDao.selectAll();
    }
}
