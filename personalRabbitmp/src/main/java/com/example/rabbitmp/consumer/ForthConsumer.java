package com.example.rabbitmp.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "FANOUT_QUEUE1")
public class ForthConsumer {

    @RabbitHandler
    public void receive(String message){
        System.out.println("fanout queue receive message + "+message);
    }
}
