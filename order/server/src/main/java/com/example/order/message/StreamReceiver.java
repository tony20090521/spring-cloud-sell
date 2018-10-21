package com.example.order.message;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {



    @StreamListener(value = StreamClient.INPUT)
    @SendTo(StreamClient.OUTPUT)
    public String processInput(String message){
        log.info("StreamReceiver: {}",message);
        return "got it ? "+message;
    }


    @StreamListener(value = StreamClient.OUTPUT)
    public void processOutput(String message){
        log.info("StreamReceiver2: {}",message);
    }
}
