package com.dcits.orion.stria.test;

import com.dcits.orion.stria.rpc.impl.FlowService;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FlowServiceTest {
    private String a;

    public void setTest(String a) {
        this.a = a;
    }


    public static void main(String[] args) {
        String locations = "classpath:META-INF/spring/*.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(locations);
        FlowService service = (FlowService) context.getBean("flowService");
    }
}