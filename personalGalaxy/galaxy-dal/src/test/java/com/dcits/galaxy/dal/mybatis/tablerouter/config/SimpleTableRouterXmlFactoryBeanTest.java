package com.dcits.galaxy.dal.mybatis.tablerouter.config;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.dcits.galaxy.dal.demo.entities.Custom;
import com.dcits.galaxy.dal.mybatis.table.router.SimpleTableRouter;
import com.dcits.galaxy.dal.mybatis.table.router.config.SimpleTableRouterXmlFactoryBean;
import com.dcits.galaxy.dal.mybatis.table.router.tableconfig.SimpleTableXmlFactoryBean;
import com.dcits.galaxy.dal.mybatis.table.tableshard.TableShardManager;
import com.dcits.galaxy.dal.route.HashFunction;

import junit.framework.TestCase;

public class SimpleTableRouterXmlFactoryBeanTest extends TestCase {
	private SimpleTableRouterXmlFactoryBean simpleRouterFactory = null;
	private SimpleTableXmlFactoryBean simpleTableFactory = new SimpleTableXmlFactoryBean();
	private Resource configLocation;

	
	private Map<String, Object> functionsMap = new HashMap<String, Object>();
	public void setUp() throws Exception {
		functionsMap.put("hash", new HashFunction());
		simpleRouterFactory = new SimpleTableRouterXmlFactoryBean();
		configLocation = new ClassPathResource("META-INF/table-routing-rules/custom-routing-rules.xml");
		simpleRouterFactory.setConfigLocation(configLocation);
		simpleRouterFactory.setFunctionsMap(functionsMap);
		configLocation = new ClassPathResource("META-INF/table-config/tableinfo-config.xml");
		simpleTableFactory.setConfigLocation(configLocation);
	}

	public void testAfterPropertiesSet() throws Exception {
		simpleTableFactory.afterPropertiesSet();
		TableShardManager tableShardManager = simpleTableFactory.getObject();
		simpleRouterFactory.afterPropertiesSet();
		SimpleTableRouter simpleRouter = simpleRouterFactory.getObject();
		simpleRouter.setTableShardManager(tableShardManager);
		String namespace1 = "com.dcits.galaxy.dal.demo.entities.Custom.findPageTotal";
		Custom custom = new Custom();
		custom.setCustomId(BigInteger.valueOf(11l));
		System.out.println(simpleRouter.routeTableShard(namespace1, custom));
	}
}
