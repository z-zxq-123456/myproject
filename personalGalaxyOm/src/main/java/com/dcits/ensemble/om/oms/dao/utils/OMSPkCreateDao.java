package com.dcits.ensemble.om.oms.dao.utils;


import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;


@Repository
public class OMSPkCreateDao {
	@Resource
	private ShardSqlSessionTemplate sessionTemplate;
	
	private static final String  CREATE_PK= "com.dcits.ensemble.om.oms.dao.utils.OMSPkCreateDao.createPk";
	
	private static final String  CREATE_MAX_BY_CON= "com.dcits.ensemble.om.oms.dao.utils.OMSPkCreateDao.createMaxByCon";
	
	public String createPk(Map<String, String> params){
		Object obj = sessionTemplate.selectOne(CREATE_PK,params);
		if(obj==null){
			return null;
		}
		return (String)obj;
	}
	
	public String createMaxByCon(Map<String, String> params){
		Object obj = sessionTemplate.selectOne(CREATE_MAX_BY_CON,params);
		if(obj==null){
			return null;
		}
		return (String)obj;
	}

}
