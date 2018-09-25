package com.dcits.galaxy.dal.mybatis.router.simple.config;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.dcits.galaxy.dal.mybatis.router.simple.config.vo.InternalRule;
import com.dcits.galaxy.dal.mybatis.router.simple.config.vo.InternalRules;
import com.thoughtworks.xstream.XStream;

public class XstreamTest extends TestCase {

	private Resource configLocation;
	
	public void setUp() throws Exception {
		configLocation = new ClassPathResource("META-INF/routing-rules/custom-routing-rules.xml");
	}

	public void testAfterPropertiesSet() throws Exception {
		XStream xstream = new XStream();
		xstream.alias("rules", InternalRules.class);
		xstream.alias("rule", InternalRule.class);
		xstream.alias("sqlmap", String.class);
		
		xstream.addImplicitCollection(InternalRules.class, "rules");
		
		List<String> sqlmaps = new ArrayList<String>();
		sqlmaps.add("abc");
		sqlmaps.add("123");
		sqlmaps.add("gjk");
		sqlmaps.add("5667");
		InternalRule in = new InternalRule();
		in.setSqlmaps(sqlmaps);
		
		System.out.println(xstream.toXML(in));
		
//		InternalRules internalRules = (InternalRules) xstream.fromXML(configLocation.getInputStream());
//		System.out.println(internalRules.getRules());

	}

}
