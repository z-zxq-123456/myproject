package com.dcits.galaxy.dal.mybatis.merger.config.vo;

import java.util.List;

import com.dcits.galaxy.dal.mybatis.proactor.config.vo.ProactorSetting;


/**
 * 查询处理器配置
 * @author yin.weicai
 *
 */
public class MergeRule {

	private String sqlMap ;
	
	private List<MergerSetting> mergers ;
	
	private ProactorSetting proactorSetting;

	public String getSqlMap() {
		return sqlMap;
	}

	public void setSqlMap(String sqlMap) {
		this.sqlMap = sqlMap;
	}

	public List<MergerSetting> getMergers() {
		return mergers;
	}

	public void setMergers(List<MergerSetting> mergers) {
		this.mergers = mergers;
	}
	
	public boolean hasMerger(){
		boolean result = false;
		if( mergers != null && mergers.size() > 0){
			result = true;
		}
		return result;
	}
	
	public boolean hasProactor(){
		boolean result = false;
		if( proactorSetting != null ){
			result = true;
		}
		return result;
	}

	public ProactorSetting getProactorSetting() {
		return proactorSetting;
	}

	public void setProactorSetting(ProactorSetting proactorSetting) {
		this.proactorSetting = proactorSetting;
	}
}
