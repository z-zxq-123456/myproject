package com.zxq.learn.handwrite.util;

import com.zxq.learn.handwrite.bean.ContructArg;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-23 21:38
 **/
public class BeanUtils {

    public static <T> T intanceByCglib(Class<T> cls, Constructor arg, Object[] args){

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(cls);
        enhancer.setCallback(NoOp.INSTANCE);
        if (arg == null){
            return (T)enhancer.create();
        }else {
            return (T)enhancer.create(arg.getParameterTypes(),args);
        }
    }
}
