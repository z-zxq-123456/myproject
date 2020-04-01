package com.exampl.zxq.handwirte.dubbo.parser;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-04-01 16:15
 **/
public class MyDefaultNameSpaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("reference",new MyRpcBeanDefinitationParser());
    }


}
