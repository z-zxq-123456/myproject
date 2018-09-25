package com.dcits.galaxy.dal.mybatis.proactor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dal.mybatis.merger.config.MergeRulesFactoryBean;
import com.dcits.galaxy.dal.mybatis.merger.config.vo.MergeRule;
import com.dcits.galaxy.dal.mybatis.proactor.config.vo.ProactorSetting;

public class ProactorExecutor implements ApplicationContextAware {
	
	private static final Logger logger = LoggerFactory.getLogger(ProactorExecutor.class);
	
	/**
	 * 规则容器
	 */
	private MergeRulesFactoryBean mergeRulesFactoryBean = null;
	
	/**
	 * Spring 上下文
	 */
	private ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Deprecated
	public boolean isNeedProactor( String statement ){
		boolean result = false;
		MergeRule mergeRule = getMergeRule(statement);
		if( mergeRule != null ){
			ProactorSetting proactorSetting = mergeRule.getProactorSetting();
			if( proactorSetting != null){				
				result = true;
			}
		}
		return result;
	}
	
	@Deprecated
	public boolean isNeedProactor( String statement ,Object parameter, ShardSqlSessionTemplate shardSqlSessionTemplate){
		boolean result = false;
		MergeRule mergeRule = getMergeRule(statement, parameter, shardSqlSessionTemplate);
		if( mergeRule != null ){
			ProactorSetting proactorSetting = mergeRule.getProactorSetting();
			if( proactorSetting != null){				
				result = true;
			}
		}
		return result;
	}
	
	public  <E> List<E> executor( String statementName, Object parameter,ShardSqlSessionTemplate shardSqlSessionTempalte,MergeRule mergeRule ){
		List<E> result = null;
		if( mergeRule != null && mergeRule.hasProactor() ){
			ProactorSetting proactorSetting = mergeRule.getProactorSetting();
			Proactor proactor = null;
			if( proactorSetting != null ){
				String mergerBeanId = proactorSetting.getBeanId();
				proactor = getProactor(mergerBeanId);
				if (proactor != null) {
					result = proactor.execute(statementName, parameter, proactorSetting, shardSqlSessionTempalte);
				}
			}
		}
		return result;
	}

	public void setMergeRulesFactoryBean(MergeRulesFactoryBean mergeRulesFactoryBean) {
		this.mergeRulesFactoryBean = mergeRulesFactoryBean;
	}

	@Deprecated
	private  MergeRule getMergeRule( String statementName ){
		return mergeRulesFactoryBean.getMergeRule( statementName );
	}
	
	public  MergeRule getMergeRule( String statementName ,Object parameter, ShardSqlSessionTemplate shardSqlSessionTemplate){
		MergeRule mergeRule = null;
		try {
			mergeRule = mergeRulesFactoryBean.getMergeRule( statementName ,parameter,shardSqlSessionTemplate.getSqlSessionFactory());
		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
			logger.warn(e.getMessage());
		}
		return mergeRule;
	}
	
	private Proactor getProactor(String beanID){
		Proactor merger = null;
		Object bean = applicationContext.getBean( beanID );
		if( bean != null && bean instanceof Proactor){
			merger = (Proactor)bean;
		}
		return merger;
	}
}
