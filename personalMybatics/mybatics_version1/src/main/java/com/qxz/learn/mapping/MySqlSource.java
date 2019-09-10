package com.qxz.learn.mapping;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/27
 */
public interface MySqlSource {

    MyBoundSql getBoundSql(Object parameter);
}
