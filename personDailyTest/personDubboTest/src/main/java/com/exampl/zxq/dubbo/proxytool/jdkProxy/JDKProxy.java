package com.exampl.zxq.dubbo.proxytool.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理
 */
public class JDKProxy {

    public static Object createProxy(ClassLoader classLoader, Class[] interfaces, InvocationHandler h){
        
        return Proxy.newProxyInstance(classLoader,interfaces,h);
    }
}
