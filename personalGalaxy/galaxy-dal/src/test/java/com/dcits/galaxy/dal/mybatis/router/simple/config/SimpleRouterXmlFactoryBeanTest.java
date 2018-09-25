package com.dcits.galaxy.dal.mybatis.router.simple.config;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.dcits.galaxy.dal.demo.entities.Custom;
import com.dcits.galaxy.dal.mybatis.router.simple.SimpleRouter;
import com.dcits.galaxy.dal.mybatis.router.simple.config.SimpleRouterXmlFactoryBean;
import com.dcits.galaxy.dal.route.HashFunction;

public class SimpleRouterXmlFactoryBeanTest extends TestCase {

	private SimpleRouterXmlFactoryBean simpleRouterFactory = null;
	private boolean enableCache = false;
	private int cacheSize = 10000;

	private Resource configLocation;
	private Resource[] configLocations;
	
	private Map<String, Object> functionsMap = new HashMap<String, Object>();
	
	public void setUp() throws Exception {
		functionsMap.put("hash", new HashFunction());
		simpleRouterFactory = new SimpleRouterXmlFactoryBean();
		configLocation = new ClassPathResource("META-INF/routing-rules/custom-routing-rules.xml");
		simpleRouterFactory.setConfigLocation(configLocation);
		simpleRouterFactory.setFunctionsMap(functionsMap);
	}

	public void testAfterPropertiesSet() throws Exception {
		simpleRouterFactory.afterPropertiesSet();
		SimpleRouter simpleRouter = simpleRouterFactory.getObject();
		String namespace1 = "com.dcits.galaxy.dal.test.entities.Custom";
		String namespace3 = "com.dcits.galaxy.dal.test.entities.Custom.fi";
		Custom custom = new Custom();
		custom.setCustomId(BigInteger.valueOf(10l));
		System.out.println(simpleRouter.route(namespace1, "10"));
		System.out.println(simpleRouter.getMatchSqlmap(namespace3,custom));
		System.out.println(simpleRouter.getMatchSqlmap(namespace3,custom));
		System.out.println(simpleRouter.route(namespace1, custom));
	}

}
