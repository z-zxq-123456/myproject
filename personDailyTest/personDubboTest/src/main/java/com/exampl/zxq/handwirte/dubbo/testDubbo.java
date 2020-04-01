package com.exampl.zxq.handwirte.dubbo;

import com.exampl.zxq.handwirte.dubbo.Interface.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-04-01 16:03
 **/
public class testDubbo {


    public static void main(String[] args) {


        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:consumer.xml");
        context.start();

        UserService userService = (UserService) context.getBean("userService");
        userService.testUser();


    }
}
