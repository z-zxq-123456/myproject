package com.qxz.learn.reflaction.invoker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/10/9
 */
public class MyMethodInvoker implements MyInvoker {

    private Class<?> type;
    private Method method;

    public MyMethodInvoker(Method method) {
        this.method = method;
        if (method.getParameterTypes().length == 1) {
            type = method.getParameterTypes()[0];
        } else {
            type = method.getReturnType();
        }
    }

    @Override
    public Object invoke(Object target, Object[] args) throws IllegalAccessException, InvocationTargetException {
        return method.invoke(target, args);
    }

    @Override
    public Class<?> getType() {
        return type;
    }
}
