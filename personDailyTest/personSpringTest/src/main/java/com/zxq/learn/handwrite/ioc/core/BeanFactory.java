package com.zxq.learn.handwrite.ioc.core;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-23 21:23
 **/
public interface BeanFactory {

    /*ByName*/
    Object getBean(String beanName);

}
