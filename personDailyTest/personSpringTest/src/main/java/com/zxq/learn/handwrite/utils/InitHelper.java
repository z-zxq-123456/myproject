package com.zxq.learn.handwrite.utils;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-31 16:08
 **/
public class InitHelper {

    public static void init(){

        Class<?> [] arrayList = {
                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class
        };

        for (Class<?> cls: arrayList){
            ClassUtils.loadClass(cls.getName(),false);
        }
    }
}
