package com.exampl.zxq.handwirte.rpc;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Proxy;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-31 23:07
 **/
public class RpcClinetFactoryBean implements FactoryBean {


    @Autowired
    private RpcDynamicPro rpcDynamicPro;

    private Class<?> classType;

    public RpcClinetFactoryBean(Class<?> classType) {
        this.classType = classType;
    }

    public Object getObject() throws Exception {

        ClassLoader classLoader = classType.getClassLoader();
        Object object = Proxy.newProxyInstance(classLoader,new Class<?>[]{classType},rpcDynamicPro);
        return object;
    }

    public Class<?> getObjectType() {
        return classType;
    }

    public boolean isSingleton() {
        return false;
    }
}
