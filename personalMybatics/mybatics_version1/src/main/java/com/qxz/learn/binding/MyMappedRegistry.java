package com.qxz.learn.binding;

import com.qxz.learn.configuration.MyConfiguration;

import java.util.HashMap;
import java.util.Map;

public class MyMappedRegistry {

    private MyConfiguration configuration;
    private final Map<Class,MyMappedRegistry> knowMappers = new HashMap<>();

    public MyMappedRegistry(MyConfiguration configuration) {
        this.configuration = configuration;
    }
}
