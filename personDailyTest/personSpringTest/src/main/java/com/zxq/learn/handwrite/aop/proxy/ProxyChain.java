package com.zxq.learn.handwrite.aop.proxy;

import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-25 22:55
 **/
public class ProxyChain {

    private final Class<?> targetClass;
    private final Object targetObject;
    private final Method targetMethod;
    private final MethodProxy methodProxy;
    private final Object[] methodParams;

    private List<MyProxy> proxyList = new ArrayList<>();
    private int proxyIndex = 0;

    public ProxyChain(Class<?> targetClass, Object targetObject, Method targetMethod, MethodProxy methodProxy, Object[] methodParams, List<MyProxy> proxyList) {
        this.targetClass = targetClass;
        this.targetObject = targetObject;
        this.targetMethod = targetMethod;
        this.methodProxy = methodProxy;
        this.methodParams = methodParams;
        this.proxyList = proxyList;
    }

    public Object doProxyChain() throws Throwable {
        Object result;
        if (proxyIndex < proxyList.size()){
            result = proxyList.get(proxyIndex++).doProxy(this);
        }else {
            result = methodProxy.invokeSuper(targetObject,methodParams);
        }
        return result;
    }


    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Object getTargetObject() {
        return targetObject;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    public MethodProxy getMethodProxy() {
        return methodProxy;
    }

    public Object[] getMethodParams() {
        return methodParams;
    }

    public List<MyProxy> getProxyList() {
        return proxyList;
    }

    public void setProxyList(List<MyProxy> proxyList) {
        this.proxyList = proxyList;
    }

    public int getProxyIndex() {
        return proxyIndex;
    }

    public void setProxyIndex(int proxyIndex) {
        this.proxyIndex = proxyIndex;
    }
}
