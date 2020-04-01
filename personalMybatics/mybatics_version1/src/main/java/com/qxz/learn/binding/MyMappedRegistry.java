package com.qxz.learn.binding;

import com.qxz.learn.configuration.MyConfiguration;
import com.qxz.learn.session.MySqlSession;

import java.util.HashMap;
import java.util.Map;

public class MyMappedRegistry {

    private MyConfiguration configuration;

    private final Map<Class,MyMapperProxyFactory> knowMappers = new HashMap<>();

    public MyMappedRegistry(MyConfiguration configuration) {
        this.configuration = configuration;
    }

    public <T> void addMapper(Class<T> type){
        this.knowMappers.put(type,new MyMapperProxyFactory<T>(type));
    }

    public <T> T getMapper(Class<T> type, MySqlSession session){
        MyMapperProxyFactory<T> mapperProxyFactory = (MyMapperProxyFactory<T> )this.knowMappers.get(type);
        return mapperProxyFactory.newInstance(session);
    }

}
