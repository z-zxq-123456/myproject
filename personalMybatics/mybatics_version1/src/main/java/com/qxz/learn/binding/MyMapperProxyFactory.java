package com.qxz.learn.binding;

import com.qxz.learn.session.MySqlSession;

import java.lang.reflect.Proxy;

public class MyMapperProxyFactory <T>{

    private final Class<T> mapperInterfaces;

    public MyMapperProxyFactory(Class<T> mapperInterfaces) {
        this.mapperInterfaces = mapperInterfaces;
    }

    public T newInstance(MySqlSession session){
        MyMapperProxy<T> proxy = new MyMapperProxy<T>(session,this.mapperInterfaces);
        return (T)Proxy.newProxyInstance(this.mapperInterfaces.getClassLoader(),new Class[]{this.mapperInterfaces},proxy);
    }

}
