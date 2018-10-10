package com.qxz.learn.reflaction;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/10/9
 */
public interface MyReflectorFactory {

    boolean isClassCacheEnabled();

    void setClassCacheEnabled(boolean classCacheEnabled);

    MyReflector findForClass(Class<?> type);
}
