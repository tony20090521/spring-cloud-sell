package com.example.order.controller;


import com.example.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SendMessageController {

    @Autowired
    StreamClient streamClient;


    @GetMapping("/sendOutputMessage")
    public void processOutput(){
        String message = "out now: "+new Date();
        streamClient.output().send(MessageBuilder.withPayload(message).build());
    }

    @GetMapping("/sendInputMessage")
    public void processInput(){
        String message = "int now: "+new Date();
        streamClient.input().send(MessageBuilder.withPayload(message).build());
    }
}
