package com.cr.zshop.back.controller;

import java.util.List;

import com.cr.zshop.common.constant.ProductTypeConstant;
import com.cr.zshop.common.constant.ResponseResultConstant;
import com.cr.zshop.common.exception.ProductTypeExistException;
import com.cr.zshop.common.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cr.zshop.common.constant.PaginationConstant;
import com.cr.zshop.pojo.ProductType;
import com.cr.zshop.service.ProductTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.ResponseBody;


/**  
* <p>Title: ProductTypeController.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2019</p>  
* <p>Company:uestc</p>  
* @author ChenRan  
* @date
* @version 1.0  
*/
@Controller
@RequestMapping("/back/productType")
public class ProductTypeController {
	@Autowired
	private ProductTypeService productTypeService;
	@RequestMapping("/findAll")
	public String findAll(Integer pageNum,Model model) {

		if(ObjectUtils.isEmpty(pageNum)) {
			pageNum = PaginationConstant.PAGE_NUM;
		}

		PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);
		List<ProductType> productTypes = productTypeService.findALL();
		PageInfo<ProductType> pageInfo = new PageInfo<>(productTypes);
		

		
		model.addAttribute("pageInfo", pageInfo);
		return "productTypeManager";
 	}

    @RequestMapping("/findByStatus")
    public String findByStatus(int status,Model model){
        List<ProductType> productTypes=productTypeService.findByStatus(status);
        model.addAttribute("productTypes",productTypes);
        return "productManager";
    }

	@RequestMapping("add")
	@ResponseBody
	public ResponseResult add(String name) {
		ResponseResult responseResult = new ResponseResult();
		try {
			productTypeService.add(name);
			responseResult.setStatus(ResponseResultConstant.RESPONSE_STATUS_SUCCESS);
			responseResult.setMessage("成功");

		} catch (ProductTypeExistException e) {
			//e.printStackTrace();
			responseResult.setStatus(ResponseResultConstant.RESPONSE_STATUS_FAIL);
			responseResult.setMessage(e.getMessage());
		}
		return responseResult;
	}

    @RequestMapping("findById")
    @ResponseBody
    public ResponseResult findById(int id){
        ProductType productType=productTypeService.findById(id);
        return ResponseResult.success(productType);
    }

	@RequestMapping("modifyName")
	@ResponseBody
	public ResponseResult modifyName(int id,String name){
		try {
			productTypeService.modifyName(id,name);
			return ResponseResult.success("成功");
		} catch (ProductTypeExistException e) {
			e.printStackTrace();
			return  ResponseResult.fail(e.getMessage());
		}
	}

    @RequestMapping("removeById")
    @ResponseBody
    public ResponseResult removeById(int id){
        productTypeService.removeById(id);
        return ResponseResult.success("成功");
    }

    @RequestMapping("modifyStatus")
    @ResponseBody
    public ResponseResult modifyStatus(int id){
        ProductType productType = productTypeService.findById(id);
        int status = productType.getStatus();
        //״̬ȡ��
        if(status == ProductTypeConstant.PRODUCT_TYPE_ENABLE) {
                status = ProductTypeConstant.PRODUCT_TYPE_DISABLE;
        }
        else {
                status = ProductTypeConstant.PRODUCT_TYPE_ENABLE;
        }
        productTypeService.modifyStatus(id, status);
        return ResponseResult.success();
    }
}
