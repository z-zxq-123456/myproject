package com.dcits.galaxy.dal.merger.config;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dcits.galaxy.dal.mybatis.merger.config.MergeRulesFactoryBean;

public class MergeRulesFactoryBeanTest {

	public static void main(String[] args) {
		String locations = "classpath:META-INF/spring/*.xml";
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext( locations );
		MergeRulesFactoryBean mergeRuleFactory = (MergeRulesFactoryBean)context.getBean("mergeRulesFactory");
		System.out.println( mergeRuleFactory );
	}
}
