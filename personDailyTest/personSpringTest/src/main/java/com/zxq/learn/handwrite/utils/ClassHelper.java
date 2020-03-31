package com.zxq.learn.handwrite.utils;

import com.zxq.learn.handwrite.aop.annotation.MyService;

import java.util.HashSet;
import java.util.Set;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-28 23:01
 **/
public class ClassHelper {

    private static final Set<Class<?>> class_set;

    static {
        String basePackage = "com.zxq.learn.handwrite";
        class_set = ClassUtils.getClasses(basePackage);
    }

    public static Set<Class<?>> getClassSet(){
        return class_set;
    }

    public static Set<Class<?>> getBeanClassSet(){
        Set<Class<?>> classes = new HashSet<>();
        classes.addAll(getServiceClassSet());
        return classes;
    }

    private static Set<Class<?>> getServiceClassSet(){
        Set<Class<?>> classes = new HashSet<>();
        for (Class<?> clz:class_set){
            if (clz.isAnnotationPresent(MyService.class)){
                classes.add(clz);
            }
        }
        return classes;
    }

    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass){
        Set<Class<?>> classes = new HashSet<>();
        for (Class clz:class_set){
            if (superClass.isAssignableFrom(clz) && !superClass.equals(clz)){
                classes.add(clz);
            }
        }
        return classes;
    }

}
