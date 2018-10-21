package com.example.product.service;

import com.example.product.common.DecreaseStockInput;
import com.example.product.common.ProductInfoOutput;
import com.example.product.dataobject.ProductInfo;
import com.example.product.dto.CartDTO;

import java.util.List;

public interface ProductService {

    /**
     * 查询所有在架商品
     * @return
     */
    List<ProductInfo> findUpAll();


    /**
     * 查询商品列表
     * @param productIdList
     * @return
     */
    List<ProductInfoOutput> findList(List<String> productIdList);


    /**
     * 扣库存
     * @param decreaseStockInputList
     */
    void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);

}
