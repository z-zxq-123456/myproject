package com.zxq.learn.dynamtic.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2019/2/18
 */
public class CglibProxy2 implements MethodInterceptor{

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("begin " +System.currentTimeMillis());
        methodProxy.invokeSuper(o,objects);
        System.out.println("after " +System.currentTimeMillis());
        return o;
    }
}
