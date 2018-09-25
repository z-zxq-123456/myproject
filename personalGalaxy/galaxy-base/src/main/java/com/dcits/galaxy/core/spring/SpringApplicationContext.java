package com.dcits.galaxy.core.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @description 获取Spring容器上下文
 * @version V1.0
 * @author Tim
 * @update 2014年12月12日 下午7:07:38 
 */

public class SpringApplicationContext implements ApplicationContextAware, BeanFactoryPostProcessor {
	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringApplicationContext.context = applicationContext;
	}

	/**
	 * @return context : return the property context.
	 */
	public static ApplicationContext getContext() {
		return context;
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory arg0)
			throws BeansException {
	}
}
