package com.zxq.learn.handwrite.ioc;

import com.zxq.learn.handwrite.ioc.core.JsonApplicationContext;
import com.zxq.learn.handwrite.ioc.model.Robot;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-23 22:41
 **/
public class TestBeanFactory {

    public static void main(String[] args) {
        JsonApplicationContext context = new JsonApplicationContext("appliction.json");
        context.init();
        Robot robot = (Robot) context.getBean("robot");
        robot.show();
    }

}
