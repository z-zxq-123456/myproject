package com.example.rabbitmp.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "FANOUT_QUEUE3")
public class ForthConsumer3 {

    @RabbitHandler
    public void receive(String message){
        System.out.println("fanout3 queue receive message + "+message);
    }
}
