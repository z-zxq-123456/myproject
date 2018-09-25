package com.example.rabbitmp.provider;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    AmqpTemplate amqpTemplate;

    public void sendDirectMessage(){

        amqpTemplate.convertAndSend("","FIRST_QUEUE","hello this is a first direct rabbitMQ test!");

        amqpTemplate.convertAndSend("TOPIC_EXCHANGE","topic.message","hello this is a first topic rabbitMQ test!");
        amqpTemplate.convertAndSend("TOPIC_EXCHANGE","zxq.topic.message","hello this is a first topic zxq.topic.message rabbitMQ test!");
        amqpTemplate.convertAndSend("TOPIC_EXCHANGE","zxq.topic.message.learn","hello this is a first topic zxq.topic.message.learn rabbitMQ test!");
        amqpTemplate.convertAndSend("TOPIC_EXCHANGE","com.zxq.topic.message.learn","hello this is a first topic com.zxq.topic.message.learn rabbitMQ test!");

        amqpTemplate.convertAndSend("FANOUT_EXCHANGE","","hello this is a fanout queue test!");


    }
}
