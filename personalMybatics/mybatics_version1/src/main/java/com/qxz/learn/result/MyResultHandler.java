package com.qxz.learn.result;

import com.qxz.learn.session.MyResultContext;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/27
 */
public interface MyResultHandler<T> {

    void handleResult(MyResultContext<? extends T> context);
}