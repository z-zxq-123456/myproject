package com.qxz.learn.reflaction.invoker;

import java.lang.reflect.InvocationTargetException;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/10/9
 */
public interface MyInvoker {

    Object invoke(Object target, Object[] args) throws IllegalAccessException, InvocationTargetException;

    Class<?> getType();
}
