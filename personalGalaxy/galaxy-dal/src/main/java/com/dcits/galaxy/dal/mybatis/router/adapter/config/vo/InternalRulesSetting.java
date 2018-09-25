package com.dcits.galaxy.dal.mybatis.router.adapter.config.vo;

import java.util.List;
/**
 * 
 * @author huang.zhe
 *
 */
public class InternalRulesSetting {
	private List<InternalRules> rulesList;

	public List<InternalRules> getRulesSetting() {
		return rulesList;
	}

	public void setRulesSetting(List<InternalRules> rulesList) {
		this.rulesList = rulesList;
	}

}
