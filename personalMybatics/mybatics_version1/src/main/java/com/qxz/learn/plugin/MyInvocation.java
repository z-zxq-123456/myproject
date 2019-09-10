package com.qxz.learn.plugin;

import java.lang.reflect.Method;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/10/9
 */
public class MyInvocation {

    private Object target;
    private Method method;
    private Object[] args;

    public MyInvocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }
}
