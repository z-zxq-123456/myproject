package com.dcits.galaxy.dal.mybatis.router.simple;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

import junit.framework.TestCase;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dcits.galaxy.dal.demo.entities.Custom;
import com.dcits.galaxy.dal.mybatis.router.IRouter;
import com.dcits.galaxy.dal.mybatis.shard.ShardManager;

public class SimpleRouterTest extends TestCase {
	
	private ClassPathXmlApplicationContext context = null;
	
	private ShardManager shardManager = null;
	private IRouter simpleRouter = null;
	private String namespace1 = "com.dcits.galaxy.dal.test.entities.Custom";
	private String namespace2 = "com.dcits.galaxy.dal.test.entities.Custom.findOrderBy";
	private String namespace3 = "com.dcits.galaxy.dal.test.entities.Custom.fi";
	
	protected void setUp() throws IOException {
		
		try {
			context = new ClassPathXmlApplicationContext("classpath:META-INF/spring/*.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
		simpleRouter = (IRouter) context.getBean("internalRouter");
		
//		Resource[] configLocations;
//		Map<String, Object> functionsMap = new HashMap<String, Object>();
//		functionsMap.put("hash", new HashFunction());
//		SimpleRouterXmlFactoryBean simpleRouterFactory = new SimpleRouterXmlFactoryBean();
//		Resource configLocation = new ClassPathResource(
//				"META-INF/routing-rules/custom-routing-rules.xml");
//		simpleRouterFactory.setConfigLocation(configLocation);
//		simpleRouterFactory.setEnableCache(true);
//		simpleRouterFactory.setCacheSize(100);
//		simpleRouterFactory.setFunctionsMap(functionsMap);
//		simpleRouterFactory.afterPropertiesSet();
//		simpleRouter = simpleRouterFactory.getObject();
	}

	public void testRoute()  {
		
//		for(String id : shardManager.getShardIds()){
//			System.out.println(shardManager.getShard(id));
//		}
		
		
		Custom custom = new Custom();
		int times = 1000;
		Date beforeDate = new Date();
		for(int n = 0; n < 2000;n++){
			for (long i = 0; i < times; i++) {			
				custom.setCustomId(BigInteger.valueOf(i * n));
				Set<String> shards = simpleRouter.route(namespace1, custom);
//			assertTrue(shards.size() == 1);
//			if (i % 2 == 1)
//				assertTrue(shards.contains("partition1"));
//			else
//				assertTrue(shards.contains("partition2"));
			}
		}
		long costTime = new Date().getTime() - beforeDate.getTime();
		
		System.out.println(costTime);
		System.out.println((double)costTime / (1000 * 200));
		
		
//		custom.setCustomId(BigInteger.valueOf(1));
//		System.out.println(simpleRouter.route(namespace1, "10"));
//		assertTrue(simpleRouter.route(namespace1, custom).size() == 1);
//		assertEquals(namespace1,
//				simpleRouter.getMatchSqlmap(namespace3, custom));
//		System.out.println(simpleRouter.route(namespace1, custom));
	}
}
