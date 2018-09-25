package com.dcits.galaxy.junit.hotload.fileload.mapper;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.junit.hotload.fileload.ConfigFileLoad;

public class UpdateMapperOption implements ConfigFileLoad{
	
	protected static final Logger logger =  LoggerFactory.getLogger(UpdateMapperOption.class); 

	@Override
	public Object updateXmlBuilder(Object objbean) {
		logger.debug("some mapper are been update! reloadding those!");
		SqlSessionFactory factory = null;
		try {
			SqlSessionFactoryBean sessionFactory = (SqlSessionFactoryBean) objbean;
			sessionFactory.afterPropertiesSet();
			factory = sessionFactory.getObject();
			logger.debug("update mapperXML Builder! now that SqlSessionFactory is:" + factory.getClass().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return factory;
	}

}
