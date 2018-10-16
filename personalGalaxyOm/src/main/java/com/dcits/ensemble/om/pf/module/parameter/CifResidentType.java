package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by para on 2016/03/22 10:32:16.
 */
public class CifResidentType extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is CIF_RESIDENT_TYPE.RESIDENT_TYPE 灞呬綇绫诲瀷
     */
    @TablePk(index = 1)
    private String residentType;

    /**
     * This field is CIF_RESIDENT_TYPE.RESIDENT_TYPE_DESC 灞呬綇绫诲瀷璇存槑
     */
    private String residentTypeDesc;

    /**
     * This field is CIF_RESIDENT_TYPE.COMPANY 娉曚汉浠ｇ爜
     */
    private String company;

    /**
     * @return the value of  CIF_RESIDENT_TYPE.RESIDENT_TYPE 灞呬綇绫诲瀷
     */
    public String getResidentType() {
        return residentType;
    }

    /**
     * @param residentType the value for CIF_RESIDENT_TYPE.RESIDENT_TYPE 灞呬綇绫诲瀷
     */
    public void setResidentType(String residentType) {
        this.residentType = residentType == null ? null : residentType.trim();
    }

    /**
     * @return the value of  CIF_RESIDENT_TYPE.RESIDENT_TYPE_DESC 灞呬綇绫诲瀷璇存槑
     */
    public String getResidentTypeDesc() {
        return residentTypeDesc;
    }

    /**
     * @param residentTypeDesc the value for CIF_RESIDENT_TYPE.RESIDENT_TYPE_DESC 灞呬綇绫诲瀷璇存槑
     */
    public void setResidentTypeDesc(String residentTypeDesc) {
        this.residentTypeDesc = residentTypeDesc == null ? null : residentTypeDesc.trim();
    }

    /**
     * @return the value of  CIF_RESIDENT_TYPE.COMPANY 娉曚汉浠ｇ爜
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for CIF_RESIDENT_TYPE.COMPANY 娉曚汉浠ｇ爜
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}