package com.qxz.learn.reflaction;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/10/9
 */
public class MyDefaultReflectorFactory implements MyReflectorFactory {

    private boolean classCacheEnabled = true;
    private final ConcurrentMap<Class<?>, MyReflector> reflectorMap = new ConcurrentHashMap<Class<?>, MyReflector>();

    @Override
    public boolean isClassCacheEnabled() {
        return classCacheEnabled;
    }

    @Override
    public void setClassCacheEnabled(boolean classCacheEnabled) {
        this.classCacheEnabled = classCacheEnabled;
    }

    @Override
    public MyReflector findForClass(Class<?> type) {
        if (classCacheEnabled) {
            MyReflector cached = reflectorMap.get(type);
            if (cached == null) {
                cached = new MyReflector(type);
                reflectorMap.put(type, cached);
            }
            return cached;
        } else {
            return new MyReflector(type);
        }
    }
}
