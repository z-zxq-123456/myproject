package com.exampl.zxq.handwirte.dubbo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-04-01 16:04
 **/
public class ProxyFactory implements InvocationHandler {


    private Class<?> interfaces;

    public ProxyFactory(Class<?> interfaces) {
        this.interfaces = interfaces;
    }

    public <T> T getProxyObject(){
        return (T)Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{interfaces},this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("=======before==========" + method.getName());
        System.out.println("=======send to net==========");
        System.out.println("=======end==========");
        return null;
    }


}
