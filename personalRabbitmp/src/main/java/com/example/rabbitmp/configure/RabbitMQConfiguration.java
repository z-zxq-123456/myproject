package com.example.rabbitmp.configure;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Bean("topicExchange")
    public TopicExchange getTopicExChange(){
        return new TopicExchange("TOPIC_EXCHANGE");
    }

    @Bean("fanoutExchange")
    public FanoutExchange getFanoutExChange(){
        return new FanoutExchange("FANOUT_EXCHANGE");
    }

    @Bean("directQueue")
    public Queue getDirectQueue(){
        return new Queue("FIRST_QUEUE");
    }

    @Bean("topicQueue")
    public Queue getTopicQueue(){
        return new Queue("TOPIC_QUEUE");
    }

    @Bean("fanoutQueue1")
    public Queue getFanoutQueue(){
        return new Queue("FANOUT_QUEUE1");
    }

    @Bean("fanoutQueue2")
    public Queue getFanoutQueue2(){
        return new Queue("FANOUT_QUEUE2");}
        @Bean("fanoutQueue3")
    public Queue getFanoutQueue3(){
        return new Queue("FANOUT_QUEUE3");}

    /**
     * 将队列topic.message与exchange绑定，binding_key为topic.message,就是完全匹配
     * @param topicQueue
     * @param topicExchange
     * @return
     */
/*    @Bean
    public Binding bindingExchangeMessage(Queue topicQueue, Exchange topicExchange){
        return BindingBuilder.bind(topicQueue).to(topicExchange).with("topic.message").noargs();
    }*/

    @Bean
    public Binding bindingExchangeMessageDeclare(Queue topicQueue, Exchange topicExchange){
        return BindingBuilder.bind(topicQueue).to(topicExchange).with("#.topic.message.*").noargs();
    }

   /* @Bean
    public Binding bindingExchangeMessageDeclareSingle(Queue topicQueue, Exchange topicExchange){
        return BindingBuilder.bind(topicQueue).to(topicExchange).with("#.topic.message").noargs();
    }*/

   @Bean
   public Binding bindingExchangeMessage(Queue fanoutQueue1,Exchange fanoutExchange){
       return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange).with("").noargs();
   }
   @Bean
   public Binding bindingExchangeMessage2(Queue fanoutQueue2,Exchange fanoutExchange){
       return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange).with("").noargs();
   }
   @Bean
   public Binding bindingExchangeMessage3(Queue fanoutQueue3,Exchange fanoutExchange){
       return BindingBuilder.bind(fanoutQueue3).to(fanoutExchange).with("").noargs();
   }

}
