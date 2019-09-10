package com.qxz.learn.plugin;

import java.util.Properties;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/10/9
 */
public interface MyInterceptor {

    Object intercept(MyInvocation invocation) throws Throwable;

    Object plugin(Object target);

    void setProperties(Properties properties);
}
