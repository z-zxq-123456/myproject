package com.qxz.learn.session;

import java.io.Reader;
import java.util.Properties;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/10/8
 */
public class MySqlSessionFactoryBuilder {

    public MySqlSessionFactory build(Reader reader){
        return build(reader,null,null);
    }

    public MySqlSessionFactory build(Reader reader,String environment){
        return build(reader,environment,null);
    }

    public MySqlSessionFactory build(Reader reader,Properties properties){
        return build(reader,null,properties);
    }

    public MySqlSessionFactory build(Reader reader, String environment, Properties properties){

    }
}
