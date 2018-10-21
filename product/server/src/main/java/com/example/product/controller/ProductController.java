package com.example.product.controller;


import com.example.product.common.DecreaseStockInput;
import com.example.product.common.ProductInfoOutput;
import com.example.product.dataobject.ProductCategory;
import com.example.product.dataobject.ProductInfo;
import com.example.product.service.CategoryService;
import com.example.product.service.ProductService;
import com.example.product.vo.ProductInfoVO;
import com.example.product.vo.ProductVO;
import com.example.product.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.ResultVOUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;


    /**
     * 1.查询所有在架商品
     * 2.获取类目type列表
     * 3.查询类目
     * 4.构造数据
     *
     */
    @GetMapping("/list")
//    @CrossOrigin(allowCredentials = "true")
    public ResultVO list(HttpServletRequest httpServletRequest){

        Cookie[] cookies = httpServletRequest.getCookies();
        if(cookies == null || cookies.length <= 0){
            log.info("cookie: {}","null");
        }else {
            log.info("cookie: {}",cookies.toString() +"  "+cookies.length);
        }

        List<ProductInfo> productInfoList = productService.findUpAll();

        List<Integer> categoryTypeList =  productInfoList.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());

        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        List<ProductVO> productVOList= new ArrayList<>();

        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }

    /**
     * 获取商品列表(给订单服务用的)
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList) {
        return productService.findList(productIdList);
    }



    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> cartDTOList){
         productService.decreaseStock(cartDTOList);
    }

}
