package com.dcits.galaxy.dal.mybatis.router.index.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.dcits.galaxy.dal.demo.entities.User;
import com.dcits.galaxy.dal.mybatis.router.index.IndexRouter;
import com.dcits.galaxy.dal.mybatis.router.index.config.IndexRouterXmlFactoryBean;
import com.dcits.galaxy.dal.route.HashFunction;

import junit.framework.TestCase;

public class IndexRouterXmlFactoryBeanTest extends TestCase {
	private IndexRouterXmlFactoryBean indexRouterFactory = null;
	private boolean enableCache = false;
	private int cacheSize = 10000;

	private Resource configLocation;
	private Resource[] configLocations;
	
	private Map<String, Object> functionsMap = new HashMap<String, Object>();
	
	public void setUp() throws Exception {
		functionsMap.put("hash", new HashFunction());
		indexRouterFactory = new IndexRouterXmlFactoryBean();
		configLocation = new ClassPathResource("META-INF/router-test-routing-rules/index-test-routing-rules.xml");
		indexRouterFactory.setConfigLocation(configLocation);
		indexRouterFactory.setFunctionsMap(functionsMap);
	}

	public void testAfterPropertiesSet() throws Exception {
		indexRouterFactory.afterPropertiesSet();
		IndexRouter indexRouter = indexRouterFactory.getObject();
//		String namespace1 = "com.dcits.galaxy.dal.demo.entities.User.findMaxxxxx";
		String namespace1 = "com.dcits.galaxy.dal.demo.entities.User.findOne";
        User user=new User();
        user.setSid(2L);
       System.out.println(indexRouter.route(namespace1, user)); 
	}
	
}
