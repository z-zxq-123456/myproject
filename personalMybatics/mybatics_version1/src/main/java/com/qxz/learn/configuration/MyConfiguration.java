package com.qxz.learn.configuration;

import com.qxz.learn.mapping.MyEnvironment;
import com.qxz.learn.session.MySqlSession;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/26
 */
public class MyConfiguration {

    private MyEnvironment environment;

    public <T> void addMapper(Class<T> t) {

    }
    public <T> T getMapper(Class<T> t, MySqlSession sqlSession) {
        return null;
    }

    public MyEnvironment getEnvironment() {
        return environment;
    }

    public void setEnvironment(MyEnvironment environment) {
        this.environment = environment;
    }


}
