package com.qxz.learn.configuration;

import com.qxz.learn.binding.MyMappedRegistry;
import com.qxz.learn.executor.MyExecutor;
import com.qxz.learn.executor.MySimpleExecutor;
import com.qxz.learn.mapping.MyEnvironment;
import com.qxz.learn.mapping.MyMappedStatement;
import com.qxz.learn.session.MySqlSession;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/26
 */
public class MyConfiguration {

    private MyEnvironment environment;
    private MyMappedRegistry mappedRegistry = new MyMappedRegistry(this);
    private Map<String,MyMappedStatement>  mappedStatement = new HashMap<>();

    public <T> void addMapper(Class<T> t) {

    }
    public <T> T getMapper(Class<T> t, MySqlSession sqlSession) {
        return null;
    }

    public MyMappedRegistry getMappedRegistry() {
        return mappedRegistry;
    }

    public void setMappedRegistry(MyMappedRegistry mappedRegistry) {
        this.mappedRegistry = mappedRegistry;
    }

    public MyMappedStatement getMappedStatement(String id) {
        return mappedStatement.get(id);
    }


    public void addMappedStatement(MyMappedStatement mappedStatement) {
        this.mappedStatement.put(mappedStatement.getId(),mappedStatement);
    }

    public MyEnvironment getEnvironment() {
        return environment;
    }

    public void setEnvironment(MyEnvironment environment) {
        this.environment = environment;
    }

    public MyExecutor newExecutor(){
        return  new MySimpleExecutor(this);
    }
}
