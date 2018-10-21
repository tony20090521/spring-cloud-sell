package com.example.user.controller;

import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {

    @Autowired
    UserService userService;

    @GetMapping(value = "getUserInfo")
    public void getUserInfo(){

        userService.findByOpenid("");
    }

}
