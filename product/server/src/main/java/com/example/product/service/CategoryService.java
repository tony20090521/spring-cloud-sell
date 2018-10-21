package com.example.product.service;

import com.example.product.dataobject.ProductCategory;

import java.util.List;

public interface CategoryService {


    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
