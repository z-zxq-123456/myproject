package com.qxz.learn.session;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/28
 */
public interface MyResultContext<T> {

    T getResultObject();

    int getResultCount();

    boolean isStopped();

    void stop();
}
