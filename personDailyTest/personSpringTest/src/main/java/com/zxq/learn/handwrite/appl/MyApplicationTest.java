package com.zxq.learn.handwrite.appl;

import com.zxq.learn.handwrite.utils.AopHelper;
import com.zxq.learn.handwrite.utils.BeanHelper;
import com.zxq.learn.handwrite.utils.InitHelper;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-31 16:32
 **/
public class MyApplicationTest {


    public static void main(String[] args) {


        InitHelper.init();

        UserService userService = BeanHelper.getBean(UserService.class);
        userService.testService();

    }
}
