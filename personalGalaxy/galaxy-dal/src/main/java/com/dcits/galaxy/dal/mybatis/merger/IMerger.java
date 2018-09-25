package com.dcits.galaxy.dal.mybatis.merger;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;

import com.dcits.galaxy.dal.mybatis.merger.config.vo.RuleSetting;

public abstract class IMerger<E, R> {
	
   public abstract R merge(List<E> entities, RuleSetting setting, String statement, Object parameter, SqlSessionFactory  sqlSessionFactory);
}
