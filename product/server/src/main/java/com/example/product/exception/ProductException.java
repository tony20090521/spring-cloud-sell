package com.example.product.exception;

import com.example.product.enums.ResultEnum;

public class ProductException extends RuntimeException {

    private Integer code;


    public ProductException(int code,String message){
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

}
