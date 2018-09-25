package com.dcits.galaxy.dal.mybatis.table.proactor;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.dcits.galaxy.dal.mybatis.merger.config.vo.MergeRule;
import com.dcits.galaxy.dal.mybatis.proactor.config.vo.ProactorSetting;
import com.dcits.galaxy.dal.mybatis.table.TableShardSqlSessionTemplate;

public class ProactorExecutor implements ApplicationContextAware {
	
	/**
	 * Spring 上下文
	 */
	private ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	public  <E> List<E> executor( String statementName, Object parameter,TableShardSqlSessionTemplate tableShardSqlSessionTempalte,MergeRule mergeRule ){
		List<E> result = null;
		if( mergeRule != null && mergeRule.hasProactor() ){
			ProactorSetting proactorSetting = mergeRule.getProactorSetting();
			Proactor proactor = null;
			if( proactorSetting != null ){
				String mergerBeanId = proactorSetting.getBeanId();
				proactor = getProactor(mergerBeanId);
				if (proactor != null) {
					result = proactor.execute(statementName, parameter, proactorSetting, tableShardSqlSessionTempalte);
				}
			}
		}
		return result;
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
