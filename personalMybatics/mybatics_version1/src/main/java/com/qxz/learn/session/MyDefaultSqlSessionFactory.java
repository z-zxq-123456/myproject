package com.qxz.learn.session;

import com.qxz.learn.configuration.MyConfiguration;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/26
 */
public class MyDefaultSqlSessionFactory implements MySqlSessionFactory {

    private MyConfiguration myConfiguration;

    @Override
    public MyConfiguration getConfiguration() {
        return myConfiguration;
    }

    public void setMyConfiguration(MyConfiguration myConfiguration) {
        this.myConfiguration = myConfiguration;
    }

    @Override
    public MySqlSession openSqlSession() {
        return openSqlSessionFromDbSource(false);
    }

    @Override
    public MySqlSession openSqlSession(boolean autoCommit) {
        return openSqlSessionFromDbSource(autoCommit);
    }


    public MySqlSession openSqlSessionFromDbSource(boolean autoCommit){

        return null;
    }
}
