package com.cr.zshop.back.controller;

import com.cr.zshop.back.vo.ProductVo;
import com.cr.zshop.common.constant.PaginationConstant;
import com.cr.zshop.common.util.ResponseResult;
import com.cr.zshop.dto.ProductDto;
import com.cr.zshop.pojo.Product;
import com.cr.zshop.pojo.ProductType;
import com.cr.zshop.service.ProductService;
import com.cr.zshop.service.ProductTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/back/product")
public class ProductController {
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private ProductService productService;

   /**
    * create by: cr
    * description: 查询所有
    * create time: 2019/3/21 22:52
    * params: []
    * @return java.util.List<com.cr.zshop.pojo.ProductType>
    */
    @ModelAttribute("productTypes")
    public List<ProductType> loadProductType(){
       return productTypeService.findEnable();
    }

    @RequestMapping("/findAll")
    public String findAll(Integer pageNum,Model model){

        if(ObjectUtils.isEmpty(pageNum))
        {
            pageNum = PaginationConstant.PAGE_NUM;
        }

        PageHelper.startPage(pageNum,PaginationConstant.PAGE_SIZE);
        List<Product> products = productService.findAll();
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        model.addAttribute("pageInfo",pageInfo);

        return "productManager";
    }
    /**
     * create by: cr
     * description: 添加
     * create time: 2019/3/21 22:52
     * params: [productVo, session, model, pageNum]
     * @return java.lang.String
     */
    @RequestMapping("/add")
    public String add(ProductVo productVo, HttpSession session, Model model,Integer pageNum) {
        String uploadPath = session.getServletContext().getRealPath("/WEB-INF/upload");

        try {

            ProductDto productDto = new ProductDto();
            PropertyUtils.copyProperties(productDto, productVo);
            productDto.setInputStream(productVo.getFile().getInputStream());
            productDto.setFileName(productVo.getFile().getOriginalFilename());
            productDto.setUploadPath(uploadPath);
            productService.add(productDto);
            model.addAttribute("successMsg", "修改成功");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
        }
        return "forward:findAll?pageNum="+pageNum;
    }

    /**
     * create by: cr
     * description: 检查名称是否合法
     * create time: 2019/3/21 22:52
     * params: [name]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("/checkName")
    @ResponseBody
    public Map<String,Object> checkName(String name){
        Map<String,Object> map = new HashMap<>();
        if(productService.checkName(name)){
            map.put("valid",true);
        }
        else {
            map.put("valid",false);
            map.put("message","名称已存在！");
        }
        return map;
    }
    /**
    * @Description: 通过id查询
    * @Param: [id]
    * @return: com.cr.zshop.common.util.ResponseResult
    * @Author: cr
    * @Date: 2019/3/13
    */
    @RequestMapping("/findById")
    @ResponseBody
    public ResponseResult findById(int id){
        Product product = productService.findById(id);
        return ResponseResult.success(product);
    }
    /**
    * @Description: 获取图片
    * @Param: [path, outputStream]
    * @return: void
    * @Author: cr
    * @Date: 2019/3/13
    */
    @RequestMapping("/getImage")
    @ResponseBody
    public void getImage(String path, OutputStream outputStream){
        productService.getImage(path,outputStream);
    }
    /**
    * @Description: 修改
    * @Param: [productVo, session, model]
    * @return: java.lang.String
    * @Author: cr
    * @Date: 2019/3/13
    */
    @RequestMapping("/modify")
    public String modify(ProductVo productVo, HttpSession session, Model model,Integer pageNum) {
        String uploadPath = session.getServletContext().getRealPath("/WEB-INF/upload");
        try {
            ProductDto productDto = new ProductDto();
            PropertyUtils.copyProperties(productDto, productVo);
            productDto.setInputStream(productVo.getFile().getInputStream());
            productDto.setFileName(productVo.getFile().getOriginalFilename());
            productDto.setUploadPath(uploadPath);
            productService.modify(productDto);
            model.addAttribute("successMsg", "修改成功");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
        }
        return "forward:findAll?pageNum="+pageNum;
    }
    /**
    * @Description: 删除
    * @Param: [id, pageNum]
    * @return: java.lang.String
    * @Author: cr
    * @Date: 2019/3/14
    */ 
    @RequestMapping("/removeById")
    public String removeById(Integer id,Integer pageNum){
        productService.remove(id);
        return "forward:findAll?pageNum="+pageNum;
    }
}
