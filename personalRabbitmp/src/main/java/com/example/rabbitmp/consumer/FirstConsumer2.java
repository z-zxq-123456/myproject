package com.example.rabbitmp.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "FIRST_QUEUE")
public class FirstConsumer2 {

    @RabbitHandler
    public void receive(String message){
        System.out.println("first2 queue receive message + "+message);
    }
}
