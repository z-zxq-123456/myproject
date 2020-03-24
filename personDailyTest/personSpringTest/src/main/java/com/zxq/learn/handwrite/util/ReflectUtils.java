package com.zxq.learn.handwrite.util;

import java.lang.reflect.Field;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-23 21:56
 **/
public class ReflectUtils {

    public static void injectField(Field field,Object bean,Object fieldBean) throws IllegalAccessException {

        if (field != null){

            field.setAccessible(true);
            field.set(bean,fieldBean);
        }

    }
}
