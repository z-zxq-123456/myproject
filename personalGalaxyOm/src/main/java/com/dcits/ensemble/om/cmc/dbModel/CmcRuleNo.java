package com.dcits.ensemble.om.cmc.dbModel;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * @program: ensemble-om
 * @Date: 2018/9/20 9:13
 * @Author: Mr.Zhang
 * @Description:
 */
public class CmcRuleNo extends AbstractBean {
    private static final long serialVersionUID = 1L;

    @TablePk(index = 1)
    private String ruleCode;
    private String ruleNo;

    private String ruleDesc;

    private String ruleEx;

    public String getRuleNo() {
        return ruleNo;
    }

    public void setRuleNo(String ruleNo) {
        this.ruleNo = ruleNo;
    }

    public String getRuleDesc() {
        return ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public String getRuleEx() {
        return ruleEx;
    }

    public void setRuleEx(String ruleEx) {
        this.ruleEx = ruleEx;
    }
}
