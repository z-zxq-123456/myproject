package com.zxq.learn.handwrite.ioc.util;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-23 21:32
 **/
public class ClassUtils {


    public static Class loadClass(String className){
        try {
            return getDafaultLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static ClassLoader getDafaultLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

}
