package com.dcits.ensemble.om.oms.module.sys;

import com.dcits.galaxy.base.bean.AbstractBean;

/**
 * Created by Administrator on 2017/3/24.
 */
public class EcmEwrInfo extends AbstractBean{
    private Integer ruleId;
    private String ruleName;
    private String ruleRank;
    private String ruleRankName;
    private String ruleDesc;

    public Integer getRuleId(){return ruleId;}
    public String getRuleName(){return ruleName;}
    public String getRuleRank(){return ruleRank;}
    public String getRuleRankName(){return ruleRankName;}
    public String getRuleDesc(){return ruleDesc;}

    public void setRuleId(Integer ruleId){this.ruleId = ruleId;}

    public void setRuleName(String ruleName){this.ruleName = ruleName == null ? null : ruleName.trim();}

    public void setRuleRankName(String ruleRankName){this.ruleRankName = ruleRankName == null ? null : ruleRankName.trim();}

    public void setRuleRank(String ruleRank){this.ruleRank = ruleRank == null ? null : ruleRank.trim();}

    public void setRuleDesc(String ruleDesc){this.ruleDesc = ruleDesc == null ? null : ruleDesc.trim();}

    public String toString(){
        return "EcmEwrInfo [ruleId="+ruleId+",ruleName="+ruleName+",ruleRank="+ruleRank+",ruleRankName="+ruleRankName
                +",ruleDesc="+ruleDesc+"]";
    }
}
