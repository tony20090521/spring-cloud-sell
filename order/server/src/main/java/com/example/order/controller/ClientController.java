package com.example.order.controller;


import com.example.product.client.ProductClient;
import com.example.product.common.DecreaseStockInput;
import com.example.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class ClientController {


    @Autowired
    ProductClient productClient;



    @GetMapping("/getProductList")
    public String getProductList(){
        List<ProductInfoOutput>  list = productClient.listForOrder(Arrays.asList("123123"));
        log.info("response:{}",list);
        return "ok";
    }

    @GetMapping("/productDecreaseStock")
    public String productDecreaseStock(){
        productClient.decreaseStock(Arrays.asList(new DecreaseStockInput("123123",3)));
        return "ok";
    }


}
