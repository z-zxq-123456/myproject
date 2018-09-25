package com.dcits.galaxy.dal.mybatis.merger;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.dcits.galaxy.dal.mybatis.merger.config.MergeRulesFactoryBean;
import com.dcits.galaxy.dal.mybatis.merger.config.vo.MergeRule;
import com.dcits.galaxy.dal.mybatis.merger.config.vo.MergerSetting;

public class MergeExecutor implements ApplicationContextAware {
	
	private static final Logger logger = LoggerFactory.getLogger(MergeExecutor.class);
	
	/**
	 * 合并规则容器
	 */
	private MergeRulesFactoryBean mergeRulesFactoryBean = null;
	
	/**
	 * Spring 上下文
	 */
	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Deprecated
	public boolean isNeedMerge( String statementName ){
		boolean result = false;
		MergeRule mergeRule = getMergeRule(statementName);
		if( mergeRule != null ){
			result = true;
		}
		return result;
	}
	
	@Deprecated
	public boolean isNeedMerge( String statementName ,Object parameter, SqlSessionFactory  sqlSessionFactory ){
		boolean result = false;
		MergeRule mergeRule = getMergeRule(statementName, parameter, sqlSessionFactory);
		if( mergeRule != null ){
			result = true;
		}
		return result;
	}
	
	public boolean hasMergeRule(String sqlMap){
		return mergeRulesFactoryBean.hasMergeRule(sqlMap);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List executor( List<Object> originalResultList, String statementName, Object parameterObject ,SqlSessionFactory  sqlSessionFactory,MergeRule mergeRule){
		List result = null;
		if( mergeRule != null && mergeRule.hasMerger() ){
			List<MergerSetting> mergerSettings = mergeRule.getMergers();
			IMerger<Object, Object> merger = null ;
			int settingsSize = mergerSettings.size();
			if( settingsSize == 1  ){
				String mergerBeanId = mergerSettings.get(0).getBeanId();
				merger = getMerger(mergerBeanId);
				if (merger != null) {
					result = (List) merger.merge(originalResultList, mergerSettings.get(0), statementName, parameterObject, sqlSessionFactory);
				}
			}else if ( settingsSize >1 ){
				//TODO
			}
		}
		return result;
	}

	public void setMergeRulesFactoryBean(MergeRulesFactoryBean mergeRulesFactoryBean) {
		this.mergeRulesFactoryBean = mergeRulesFactoryBean;
	}

	@Deprecated
	public  MergeRule getMergeRule( String statementName ){
		return mergeRulesFactoryBean.getMergeRule( statementName );
	}
	
	
	public MergeRule getMergeRule( String statementName ,Object parameter, SqlSessionFactory  sqlSessionFactory){
		MergeRule mergeRule = null;
		try {
			mergeRule = mergeRulesFactoryBean.getMergeRule( statementName, parameter ,sqlSessionFactory);
		} catch (Exception e) {
			logger.warn(e.getMessage());
		} finally {
			if(MergeRulesFactoryBean.DYNAMIC_SQL_MERGERULE.equals(mergeRule)) {
				logger.warn("无法根据SQL生成MergeRule, 需手动配置规则MergeRule");
			}
		}
		return mergeRule;
	}

	public void addMergeRule(MergeRule rule){
		mergeRulesFactoryBean.addMergeRule(rule);
	}

	@SuppressWarnings("rawtypes")
	private IMerger getMerger(String statementName){
		IMerger merger = null;
		Object bean = applicationContext.getBean(statementName);
		if( bean != null && bean instanceof IMerger){
			merger = (IMerger)bean;
		}
		return merger;
	}
}
