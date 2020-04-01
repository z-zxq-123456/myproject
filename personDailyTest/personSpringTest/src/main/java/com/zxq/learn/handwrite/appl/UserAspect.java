package com.zxq.learn.handwrite.appl;

import com.zxq.learn.handwrite.aop.annotation.MyAspect;
import com.zxq.learn.handwrite.aop.proxy.AspectProxy;

import java.lang.reflect.Method;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-31 16:15
 **/
@MyAspect(pkg = "com.zxq.learn.handwrite.appl",cls = "UserService")
public class UserAspect extends AspectProxy {

    private long begin;

    @Override
    public boolean intercept(Method method, Object[] params) {
        return method.getName().equals("testService");
    }

    @Override
    public void before(Method method, Object[] params) {
        System.out.println("===============begin=============");
        begin = System.currentTimeMillis();
    }

    @Override
    public void after(Method method, Object[] params) {
        System.out.println("===============end=============");
        System.out.println("time elasped " + (System.currentTimeMillis()-begin));
    }
}
