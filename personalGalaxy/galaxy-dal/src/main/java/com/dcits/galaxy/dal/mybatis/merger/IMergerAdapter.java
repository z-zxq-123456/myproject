package com.dcits.galaxy.dal.mybatis.merger;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;

import com.dcits.galaxy.dal.mybatis.merger.config.vo.RuleSetting;

public class IMergerAdapter <E, R> extends IMerger<E, R>{

	@Override
	public R merge(List<E> entities, RuleSetting setting,String statement, Object parameter,SqlSessionFactory  sqlSessionFactory) {
		// TODO Auto-generated method stub
		return null;
	}
}
