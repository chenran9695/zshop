package com.cr.zshop.back.controller;

import com.cr.zshop.common.constant.AdminConstant;
import com.cr.zshop.common.constant.PaginationConstant;
import com.cr.zshop.common.exception.AdminNotFoundException;
import com.cr.zshop.common.util.ResponseResult;
import com.cr.zshop.params.AdminParams;
import com.cr.zshop.pojo.Admin;
import com.cr.zshop.service.AdminService;
import com.cr.zshop.service.RoleService;
import com.cr.zshop.vo.AdminVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import com.cr.zshop.pojo.Role;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpSession;

/**
* <p>Title: SysuserController.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2019</p>  
* <p>Company:uestc</p>  
* @author ChenRan  
* @version 1.0
*/
@SuppressWarnings("ALL")
@Controller
@RequestMapping("/back/admin")
public class AdminController 
{
	@Autowired
	private AdminService adminService;
	@Autowired
	private RoleService roleService;
	/**
	* @Description:
	* @Param: []
	* @return: java.util.List<com.cr.zshop.pojo.Role>
	* @Author: cr
	* @Date: 2019/3/18
	*/ 
	@ModelAttribute("roles")
	public List<Role> findAllRoles(){
		return roleService.findAll();
	}
	/**
	* @Description:
	* @Param: []
	* @return: java.lang.String
	* @Author: cr
	* @Date: 2019/3/15
	*/ 
	@RequestMapping("/login")
	public String login(String loginName, String password, HttpSession session,Model model) {
		//ʵ�ֵ�½�Ĵ���
		try {
			Admin admin = adminService.login(loginName,password);
			session.setAttribute("admin",admin);
			return "main";
		} catch (AdminNotFoundException e) {
			model.addAttribute("errorMsg",e.getMessage());
		}
		return "login";
	}
	/**
	* @Description:
	* @Param: [pageNum, model]
	* @return: java.lang.String
	* @Author: cr
	* @Date: 2019/3/15
	*/ 
	@RequestMapping("/findAll")
	public String findAll(Integer pageNum,Model model){
		//�ж��Ƿ���ҳ�룬��δ���룬��ҳ��Ĭ��Ϊ1��
		if(ObjectUtils.isEmpty(pageNum)){
			pageNum = PaginationConstant.PAGE_NUM;
		}

		PageHelper.startPage(pageNum,PaginationConstant.PAGE_SIZE);
		List<Admin> admins = adminService.findAll();
		PageInfo<Admin> pageInfo = new PageInfo<>(admins);
		model.addAttribute("pageInfo",pageInfo);
		return "adminManager";
	}
	/**
	* @Description: ͨ
	* @Param: [id]
	* @return: com.cr.zshop.common.util.ResponseResult
	* @Author: cr
	* @Date: 2019/3/15
	*/
	@RequestMapping("/findById")
	@ResponseBody
	public ResponseResult findById(Integer id){
		Admin admin = adminService.findById(id);

		if(admin != null){
			return ResponseResult.success(admin);
		}
		else {
			return ResponseResult.fail("失败");
		}
	}
	/**
	* @Description:
	* @Param: [adminVo]
	* @return: com.cr.zshop.common.util.ResponseResult
	* @Author: cr
	* @Date: 2019/3/18
	*/ 
	@RequestMapping("/modify")
	@ResponseBody
	public ResponseResult modify(AdminVo adminVo){
		Admin admin = new Admin();
		try {
			PropertyUtils.copyProperties(admin,adminVo);

            Role role = new Role();
            role.setId(adminVo.getRoleId());
            admin.setRole(role);
			adminService.modify(admin);
			return ResponseResult.success("成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseResult.fail("失败");
	}
	/**
	* @Description:
	* @Param: [adminVo]
	* @return: com.cr.zshop.common.util.ResponseResult
	* @Author: cr
	* @Date: 2019/3/18
	*/ 
	@RequestMapping("/add")
	@ResponseBody
	public ResponseResult add(AdminVo adminVo){
		Admin admin= new Admin();
		try {
			PropertyUtils.copyProperties(admin,adminVo);
			admin.setCreateDate(new Date());
			admin.setIsValid(AdminConstant.IS_VALIAD);

			Role role = new Role();
			role.setId(adminVo.getRoleId());
			admin.setRole(role);
			adminService.add(admin);
			return ResponseResult.success("成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
			return ResponseResult.fail("失败");
	}
	/**
	* @Description:
	* @Param: [model, adminParams]
	* @return: java.lang.String
	* @Author: cr
	* @Date: 2019/3/19
	*/ 
	@RequestMapping("/findByParams")
	public String findByParams(Model model, AdminParams adminParams,Integer pageNum){
		if(ObjectUtils.isEmpty(pageNum) || pageNum<1){
			pageNum = PaginationConstant.PAGE_NUM;
		}
		PageHelper.startPage(pageNum,PaginationConstant.PAGE_SIZE);
		List<Admin> admins = adminService.findByParams(adminParams);

		PageInfo<Admin> pageInfo = new PageInfo<>(admins);

		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("amdinParams",adminParams);
		return "adminManager";
	}
}
