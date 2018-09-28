package com.qxz.learn.result;

import com.qxz.learn.factory.MyObjectFactory;
import com.qxz.learn.session.MyResultContext;

import java.util.List;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/28
 */
public class MyDefaultResultHandler implements MyResultHandler {
    private final List<Object> list;

    public MyDefaultResultHandler(List<Object> list) {
        this.list = list;
    }

    public MyDefaultResultHandler(MyObjectFactory objectFactory){
        this.list = objectFactory.create(List.class);
    }

    @Override
    public void handleResult(MyResultContext context) {

    }

    public List<Object> getResultList() {
        return list;
    }
}
