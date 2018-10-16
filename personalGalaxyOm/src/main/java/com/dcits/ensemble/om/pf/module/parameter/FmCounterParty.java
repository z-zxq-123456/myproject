package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/03/18 18:03:00.
 */
public class FmCounterParty extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is FM_COUNTER_PARTY.COUNTER_PARTY 瀵规墜
     */
    @TablePk(index = 1)
    private String counterParty;

    /**
     * This field is FM_COUNTER_PARTY.COUNTER_PARTY_DESC 瀵规墜鎻忚堪
     */
    private String counterPartyDesc;

    /**
     * This field is FM_COUNTER_PARTY.COMPANY 
     */
    private String company;

    /**
     * @return the value of  FM_COUNTER_PARTY.COUNTER_PARTY 瀵规墜
     */
    public String getCounterParty() {
        return counterParty;
    }

    /**
     * @param counterParty the value for FM_COUNTER_PARTY.COUNTER_PARTY 瀵规墜
     */
    public void setCounterParty(String counterParty) {
        this.counterParty = counterParty == null ? null : counterParty.trim();
    }

    /**
     * @return the value of  FM_COUNTER_PARTY.COUNTER_PARTY_DESC 瀵规墜鎻忚堪
     */
    public String getCounterPartyDesc() {
        return counterPartyDesc;
    }

    /**
     * @param counterPartyDesc the value for FM_COUNTER_PARTY.COUNTER_PARTY_DESC 瀵规墜鎻忚堪
     */
    public void setCounterPartyDesc(String counterPartyDesc) {
        this.counterPartyDesc = counterPartyDesc == null ? null : counterPartyDesc.trim();
    }

    /**
     * @return the value of  FM_COUNTER_PARTY.COMPANY 
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for FM_COUNTER_PARTY.COMPANY 
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}