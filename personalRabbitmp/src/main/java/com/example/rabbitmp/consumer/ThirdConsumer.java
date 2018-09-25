package com.example.rabbitmp.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "TOPIC_QUEUE")
public class ThirdConsumer {

    @RabbitHandler
    public void receive(String message){
        System.out.println("topic2 queue receive message + "+message);
    }
}
