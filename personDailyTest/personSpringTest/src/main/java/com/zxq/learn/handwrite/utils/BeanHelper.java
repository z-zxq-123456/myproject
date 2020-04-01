package com.zxq.learn.handwrite.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-28 22:54
 **/
public class BeanHelper {

    private static final Map<Class<?>,Object> beanMaps = new HashMap<>();

    static {

        Set<Class<?>> classSet  = ClassHelper.getBeanClassSet();

        for (Class cls:classSet){

            try {
                Object o = cls.newInstance();
                beanMaps.put(cls,o);
                new AopHelper();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


    public static Map<Class<?>,Object> getBeanMaps(){
        return beanMaps;
    }


    public static <T> T getBean(Class<T> tClass){

        return (T)beanMaps.get(tClass);
    }


    public static void setBean(Class<?> cls,Object o){

        beanMaps.put(cls, o);
    }
}
