package com.zxq.learn.handwrite;

import com.zxq.learn.handwrite.core.JsonApplicationContext;
import com.zxq.learn.handwrite.model.Robot;

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
