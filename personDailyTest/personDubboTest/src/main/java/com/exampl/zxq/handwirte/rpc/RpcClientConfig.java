package com.exampl.zxq.handwirte.rpc;


import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Map;
import java.util.Set;

/**
 * @description @RpcClient注解的扫描类
 * @author: zhouxqh
 * @create: 2020-03-31 22:44
 **/
public class RpcClientConfig implements ImportBeanDefinitionRegistrar {


    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {

        ClassPathScanningCandidateComponentProvider provider = getScanner();

        provider.addIncludeFilter(new AnnotationTypeFilter(RpcClient.class));

        //扫描此包下的所有带有@RpcClient的注解的类
        Set<BeanDefinition> beanDefinitionSet = provider.findCandidateComponents("com.exampl.zxq.handwirte");
        for (BeanDefinition beanDefinition:beanDefinitionSet){
            if (beanDefinition instanceof  AnnotatedBeanDefinition){
                AnnotatedBeanDefinition annotatedBeanDefinition = (AnnotatedBeanDefinition)beanDefinition;
                String beanClassAllName = beanDefinition.getBeanClassName();
                Map<String, Object> paraMap = annotatedBeanDefinition.getMetadata().getAnnotationAttributes(RpcClient.class.getCanonicalName());
                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(RpcClinetFactoryBean.class);
                builder.addConstructorArgValue(beanClassAllName);
                builder.getBeanDefinition().setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
                beanDefinitionRegistry.registerBeanDefinition(beanClassAllName,builder.getBeanDefinition());
            }
        }
    }


    protected ClassPathScanningCandidateComponentProvider getScanner(){
        return new ClassPathScanningCandidateComponentProvider(false){
            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
            }
        };
    }
}
