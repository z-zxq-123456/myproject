/**
 * Title: Galaxy(Distributed service platform) 
 * Copyright:Copyright (c) 2014-2016
 * Company: 神州数码融信软件有限公司
 */
package com.dcits.galaxy.core.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.alibaba.dubbo.config.spring.ReferenceBean;

/**
 * 对spring的bean注册进行干预，提取<dcits:reference />对应的ID和Interface的对应关系，备用。
 * 
 * @author xuecy
 *
 */
public class GalaxyBeanPostProcessor implements BeanPostProcessor {
	private static final Logger logger = LoggerFactory
			.getLogger(GalaxyBeanPostProcessor.class);

	@Override
	public Object postProcessAfterInitialization(Object paramObject,
			String paramString) throws BeansException {
		if (paramObject instanceof ReferenceBean) {
			ReferenceBean<?> refBean = (ReferenceBean<?>) paramObject;
			logger.debug("Reference " + refBean.getInterfaceClass()
					+ " has registered, id [" + refBean.getId() + "]");
			MappingManager.getInstance().add(refBean.getId(),
					refBean.getInterfaceClass());
		}
		return paramObject;
	}

	@Override
	public Object postProcessBeforeInitialization(Object paramObject,
			String paramString) throws BeansException {
		return paramObject;
	}

}
