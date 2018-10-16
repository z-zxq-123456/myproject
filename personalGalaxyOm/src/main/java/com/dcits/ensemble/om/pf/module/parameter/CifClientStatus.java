package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by para on 2016/03/22 10:32:08.
 */
public class CifClientStatus extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is CIF_CLIENT_STATUS.CLIENT_STATUS 
     */
    @TablePk(index = 1)
    private String clientStatus;

    /**
     * This field is CIF_CLIENT_STATUS.CLIENT_STATUS_DESC 
     */
    private String clientStatusDesc;

    /**
     * This field is CIF_CLIENT_STATUS.BAD_CLIENT_IND 
     */
    private String badClientInd;

    /**
     * This field is CIF_CLIENT_STATUS.COMPANY 娉曚汉浠ｇ爜
     */
    private String company;

    /**
     * @return the value of  CIF_CLIENT_STATUS.CLIENT_STATUS 
     */
    public String getClientStatus() {
        return clientStatus;
    }

    /**
     * @param clientStatus the value for CIF_CLIENT_STATUS.CLIENT_STATUS 
     */
    public void setClientStatus(String clientStatus) {
        this.clientStatus = clientStatus == null ? null : clientStatus.trim();
    }

    /**
     * @return the value of  CIF_CLIENT_STATUS.CLIENT_STATUS_DESC 
     */
    public String getClientStatusDesc() {
        return clientStatusDesc;
    }

    /**
     * @param clientStatusDesc the value for CIF_CLIENT_STATUS.CLIENT_STATUS_DESC 
     */
    public void setClientStatusDesc(String clientStatusDesc) {
        this.clientStatusDesc = clientStatusDesc == null ? null : clientStatusDesc.trim();
    }

    /**
     * @return the value of  CIF_CLIENT_STATUS.BAD_CLIENT_IND 
     */
    public String getBadClientInd() {
        return badClientInd;
    }

    /**
     * @param badClientInd the value for CIF_CLIENT_STATUS.BAD_CLIENT_IND 
     */
    public void setBadClientInd(String badClientInd) {
        this.badClientInd = badClientInd == null ? null : badClientInd.trim();
    }

    /**
     * @return the value of  CIF_CLIENT_STATUS.COMPANY 娉曚汉浠ｇ爜
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for CIF_CLIENT_STATUS.COMPANY 娉曚汉浠ｇ爜
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}