/**
 * Title: Galaxy(Distributed service platform) 
 * Copyright:Copyright (c) 2014-2016
 * Company: 神州数码融信软件有限公司
 */
package com.dcits.galaxy.core.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * 对spring的bean定义解析进行拦截，提取所有的bean的名称，供ref初始化时进行是否有内部提供者的判断。
 * 
 * 说明：<dcits.service 对应的bean的名称体现为接口名称。
 * 
 * @author xuecy
 *
 */
public class GalaxyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) throws BeansException {

		GalaxyBeanManager.getInstance().setBeanName(
				beanFactory.getBeanDefinitionNames());

	}

}
