package com.exampl.zxq.handwirte.dubbo.config;

import com.exampl.zxq.handwirte.dubbo.proxy.ProxyFactory;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-04-01 16:08
 **/
public class ReferenceConfig<T> {


    private Class<?> interfaces;

    private transient volatile T ref;

    public synchronized T get(){

        if (ref == null){

            init();
        }
        return ref;
    }

    private void init(){

        ref = new ProxyFactory(interfaces).getProxyObject();
    }

    public Class<?> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Class<?> interfaces) {
        this.interfaces = interfaces;
    }

}
