package com.qxz.learn.session;

import com.qxz.learn.configuration.MyConfiguration;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/26
 */
public interface MySqlSessionFactory {

    MySqlSession openSqlSession();

    MySqlSession openSqlSession(boolean autoCommit);

    MyConfiguration getConfiguration();
}
