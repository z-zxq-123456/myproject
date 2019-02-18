package com.zxq.learn.dynamtic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/6/15
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object object;

    MyInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("reflect before invoke!");
        Object rs = method.invoke(object,args);
        System.out.println("reflect after invoke!");
        return rs;
    }
}
