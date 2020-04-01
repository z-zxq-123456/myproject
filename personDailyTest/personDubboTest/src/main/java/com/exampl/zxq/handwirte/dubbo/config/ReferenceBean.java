package com.exampl.zxq.handwirte.dubbo.config;

import org.springframework.beans.factory.FactoryBean;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-04-01 16:12
 **/
public class ReferenceBean extends ReferenceConfig implements FactoryBean {


    @Override
    public Object getObject() throws Exception {
        return get();
    }

    @Override
    public Class<?> getObjectType() {
        return getInterfaces();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
