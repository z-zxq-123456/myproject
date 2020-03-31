package com.zxq.learn.handwrite.aop.factory;

import com.zxq.learn.handwrite.aop.proxy.MyProxy;
import com.zxq.learn.handwrite.aop.proxy.ProxyChain;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import java.util.List;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-28 22:35
 **/
public class ProxyFactory {

    @SuppressWarnings("unchecked")
    public static <T> T createProxy(final Class<?> targetClass, final List<MyProxy> proxyList){

        return (T)Enhancer.create(targetClass,
                (MethodInterceptor) (o, method, objects, methodProxy) -> new ProxyChain(targetClass,o,method,methodProxy,objects,proxyList).doProxyChain());
    }
}
