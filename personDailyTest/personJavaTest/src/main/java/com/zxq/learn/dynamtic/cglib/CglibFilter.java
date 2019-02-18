package com.zxq.learn.dynamtic.cglib;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2019/2/18
 */
public class CglibFilter implements CallbackFilter {
    @Override
    public int accept(Method method) {

        if (method.getName().equals("sub")){
            return 0;
        }
        return 1;
    }
}
