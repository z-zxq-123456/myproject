package com.dcits.galaxy.dal.mybatis.router.simple.config.vo;

import java.util.List;

/**
 * 
 * @author fan.kaijie
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
