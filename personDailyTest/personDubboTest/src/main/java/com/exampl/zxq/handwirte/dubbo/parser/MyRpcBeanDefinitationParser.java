package com.exampl.zxq.handwirte.dubbo.parser;

import com.exampl.zxq.handwirte.dubbo.config.ReferenceBean;
import com.sun.org.apache.xml.internal.security.encryption.Reference;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-04-01 16:17
 **/
public class MyRpcBeanDefinitationParser extends AbstractSingleBeanDefinitionParser {


    @Override
    protected Class<?> getBeanClass(Element element) {
        return ReferenceBean.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {

        String interfaceClass = element.getAttribute("interface");
        builder.addPropertyValue("interfaceClass",interfaceClass);
    }
}
