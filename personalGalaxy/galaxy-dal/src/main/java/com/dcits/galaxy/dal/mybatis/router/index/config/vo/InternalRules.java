package com.dcits.galaxy.dal.mybatis.router.index.config.vo;

import java.util.List;

/**
 * 
 * @author huang.zhe
 *
 */
public class InternalRules {
	
	private String expression;
	
	private String shards;

    private List<InternalRule> rules;

    public void setRules(List<InternalRule> rules) {
        this.rules = rules;
    }

    public List<InternalRule> getRules() {
        return rules;
    }

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getShards() {
		return shards;
	}

	public void setShards(String shards) {
		this.shards = shards;
	}
    
    
}
