package com.exampl.zxq.dubbo.proxytool.jdkProxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JdkInvocationHandler implements InvocationHandler {

    private Object object;

    public JdkInvocationHandler(Object object) {
        this.object = object;
    }

    public JdkInvocationHandler() {

    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before jdk proxy ....");
        Object obj =  method.invoke(object,args);
        System.out.println("after jdk proxy ....");
        return obj;
    }

}
