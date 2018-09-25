package com.dcits.galaxy.dal.mybatis.router.adapter.config;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.dcits.galaxy.dal.demo.entities.User;
import com.dcits.galaxy.dal.mybatis.router.adapter.AdapterRouter;
import com.dcits.galaxy.dal.route.HashFunction;

public class AdapterRouterXmlFactoryBeanTest extends TestCase {
	private AdapterRouterXmlFactoryBean adapterRouterFactory = null;
	private boolean enableCache = false;
	private int cacheSize = 10000;

	private Resource configLocation;
	private Resource[] configLocations;
	
	private Map<String, Object> functionsMap = new HashMap<String, Object>();
	
	public void setUp() throws Exception {
		functionsMap.put("hash", new HashFunction());
		adapterRouterFactory = new AdapterRouterXmlFactoryBean();
		configLocation = new ClassPathResource("META-INF/router-test-routing-rules/adapter-test-routing-rules.xml");
		adapterRouterFactory.setConfigLocation(configLocation);
		adapterRouterFactory.setFunctionsMap(functionsMap);
	}

	public void testAfterPropertiesSet() throws Exception {
		adapterRouterFactory.afterPropertiesSet();
		AdapterRouter adapterRouter = adapterRouterFactory.getObject();
//		String namespace1 = "com.dcits.galaxy.dal.demo.entities.User.xxxx";
		String namespace1 = "com.dcits.galaxy.dal.demo.entities.User.findMax";
        User user=new User();
        user.setSid(2L);
       System.out.println(adapterRouter.route(namespace1, user)); 
	}
	
}
