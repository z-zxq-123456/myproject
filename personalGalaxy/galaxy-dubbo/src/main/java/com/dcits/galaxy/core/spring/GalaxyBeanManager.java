/**
 * Title: Galaxy(Distributed service platform) 
 * Copyright:Copyright (c) 2014-2016
 * Company: 神州数码融信软件有限公司
 */
package com.dcits.galaxy.core.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * spring容器中所有bean名称的管理类
 * 
 * @author xuecy
 */
public class GalaxyBeanManager {
	private static GalaxyBeanManager instance = null;

	private static final Logger logger = LoggerFactory
			.getLogger(GalaxyBeanManager.class);

	/**
	 * spring容器中准备就绪的bean的name集合
	 */
	private List<String> beanNames = new ArrayList<String>();

	public static GalaxyBeanManager getInstance() {
		if (instance == null)
			instance = new GalaxyBeanManager();

		return instance;
	}

	public void setBeanName(String[] beanName) {
		this.beanNames = Arrays.asList(beanName);
		if (logger.isDebugEnabled()) {
			logger.debug("All beans:" + this.beanNames);
		}
	}

	public boolean contains(String name) {
		return this.beanNames.contains(name);
	}

	public static void main(String[] args) {
		GalaxyBeanManager gbm = GalaxyBeanManager.getInstance();
		gbm.setBeanName(new String[] { "service1",
				"com.dcits.galaxy.api.Service1" });
		System.out.println(gbm.contains("service2"));
	}
}
