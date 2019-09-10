package com.qxz.learn.reflaction.invoker;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/10/9
 */
public class MyGetFieldInvoker implements MyInvoker {

    private Field field;

    public MyGetFieldInvoker(Field field) {
        this.field = field;
    }

    @Override
    public Object invoke(Object target, Object[] args) throws IllegalAccessException, InvocationTargetException {
        return field.get(target);
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }
}
