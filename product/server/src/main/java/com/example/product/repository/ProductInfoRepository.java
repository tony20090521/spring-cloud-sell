package com.example.product.repository;


import com.example.product.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String>{

    List<ProductInfo> findByProductStatus(int productStatus);


    List<ProductInfo> findByProductIdIn(List<String> productIdList);
}
