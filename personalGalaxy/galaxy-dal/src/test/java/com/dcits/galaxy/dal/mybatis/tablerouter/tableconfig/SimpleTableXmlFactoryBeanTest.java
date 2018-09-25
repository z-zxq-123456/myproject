package com.dcits.galaxy.dal.mybatis.tablerouter.tableconfig;

import junit.framework.TestCase;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.dcits.galaxy.dal.mybatis.table.router.tableconfig.SimpleTableXmlFactoryBean;
import com.dcits.galaxy.dal.mybatis.table.tableshard.TableShardManager;

public class SimpleTableXmlFactoryBeanTest extends TestCase {
	private SimpleTableXmlFactoryBean simpleTableFactory = null;
	private Resource configLocation;
//	private Resource[] configLocations;
	
	public void setUp() throws Exception {
		simpleTableFactory = new SimpleTableXmlFactoryBean();
		configLocation = new ClassPathResource("META-INF/table-config/tableinfo-config.xml");
		simpleTableFactory.setConfigLocation(configLocation);
	}

	public void testAfterPropertiesSet() throws Exception {
		simpleTableFactory.afterPropertiesSet();
		TableShardManager shardTableManager = simpleTableFactory.getObject();
		
		System.out.println(shardTableManager.getShardTable("CustomPartion4"));
		System.out.println(shardTableManager.getShardTable("UserPartion4"));
	}
}
