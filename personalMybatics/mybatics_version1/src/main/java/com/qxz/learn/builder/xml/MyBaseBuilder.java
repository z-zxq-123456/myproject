package com.qxz.learn.builder.xml;

import com.qxz.learn.configuration.MyConfiguration;
import com.qxz.learn.type.MyTypeAliasRegistry;
import com.qxz.learn.type.MyTypeHandlerRegistry;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/10/8
 */
public abstract class MyBaseBuilder {

    protected MyConfiguration configuration;
    protected MyTypeHandlerRegistry typeHandlerRegistry;
    protected MyTypeAliasRegistry typeAliasRegistry;

    public MyBaseBuilder(MyConfiguration configuration) {
        this.configuration = configuration;
        this.typeAliasRegistry = configuration.getTypeAliasRegistry();
        this.typeHandlerRegistry = configuration.getTypeHandlerRegistry();
    }

    protected Class<?> resolveClass(String alias) {
        if (alias == null) {
            return null;
        }
        try {
            return resolveAlias(alias);
        } catch (Exception e) {
            throw new RuntimeException("Error resolving class. Cause: " + e, e);
        }
    }

    protected Class<?> resolveAlias(String alias) {
        return typeAliasRegistry.resolveAlias(alias);
    }

}
