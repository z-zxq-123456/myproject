package com.qxz.learn.binding;

import com.qxz.learn.session.MySqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyMapperProxy<T> implements InvocationHandler, Serializable {

    private final MySqlSession session;
    private final Class<T> mapperInterface;

    public MyMapperProxy(MySqlSession session, Class<T> mapperInterface) {
        this.session = session;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }



}
