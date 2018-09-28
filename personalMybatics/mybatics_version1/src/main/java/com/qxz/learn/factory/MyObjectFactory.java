package com.qxz.learn.factory;

import java.util.List;
import java.util.Properties;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/28
 */
public interface MyObjectFactory {

    void setProperties(Properties properties);

    <T> T create(Class<T> type);

    <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs);

    <T> boolean isCollection(Class<T> type);
}
