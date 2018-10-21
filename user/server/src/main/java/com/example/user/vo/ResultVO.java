package com.example.user.vo;

import lombok.Data;

@Data
public class ResultVO<T>  {


    private int code;

    private String msg;

    private T data;
}
