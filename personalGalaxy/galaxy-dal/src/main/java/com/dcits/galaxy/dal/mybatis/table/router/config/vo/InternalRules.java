package com.dcits.galaxy.dal.mybatis.table.router.config.vo;

import java.util.List;

/**
 * @author huang.zhe
 *
 */
public class InternalRules {
	private List<InternalRule> rules;

	public void setRules(List<InternalRule> rules) {
		this.rules = rules;
	}

	public List<InternalRule> getRules() {
		return rules;
	}
}
