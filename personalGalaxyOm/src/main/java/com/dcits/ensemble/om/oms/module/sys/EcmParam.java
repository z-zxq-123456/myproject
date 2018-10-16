package com.dcits.ensemble.om.oms.module.sys;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by tangxl on 2014/07/24 13:54:58.
 */
public class EcmParam extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    @Override
	public String toString() {
		return "EcmParam [paraCode=" + paraCode + ", paraName=" + paraName
				+ ", paraParentId=" + paraParentId + ", remark1=" + remark1
				+ ", remark2=" + remark2 + ", remark3=" + remark3
				+ ", isValidate=" + isValidate + "]";
	}

	/**
     * This field is ECM_PARAM.PARA_CODE 
     */
    @TablePk(index = 1)
    private String paraCode;

    /**
     * This field is ECM_PARAM.PARA_NAME 
     */
    private String paraName;
    /**
     * This field is ECM_PARAM.PARA_PARENT_ID 
     */
    private String paraParentId;
    /**
     * This field is ECM_PARAM.REMARK1 
     */
    private String remark1;
    /**
     * This field is ECM_PARAM.REMARK2 
     */
    private String remark2;
    /**
     * This field is ECM_PARAM.REMARK3 
     */
    private String remark3;
    /**
     * This field is ECM_PARAM.IS_VALIDATE 
     */
    private String isValidate;
    
    
    private String isValidateName;

    public String getIsValidateName() {
		return isValidateName;
	}

	public void setIsValidateName(String isValidateName) {
		this.isValidateName = isValidateName;
	}

	/**
     * @return the value of  ECM_PARAM.PARA_CODE 
     */
    public String getParaCode() {
        return paraCode;
    }

    /**
     * @param paraCode the value for ECM_PARAM.PARA_CODE 
     */
    public void setParaCode(String paraCode) {
        this.paraCode = paraCode == null ? null : paraCode.trim();
    }
    /**
     * @return the value of  ECM_PARAM.PARA_NAME 
     */
    public String getParaName() {
        return paraName;
    }

    /**
     * @param paraName the value for ECM_PARAM.PARA_NAME 
     */
    public void setParaName(String paraName) {
        this.paraName = paraName == null ? null : paraName.trim();
    }
    /**
     * @return the value of  ECM_PARAM.PARA_PARENT_ID 
     */
    public String getParaParentId() {
        return paraParentId;
    }

    /**
     * @param paraParentId the value for ECM_PARAM.PARA_PARENT_ID 
     */
    public void setParaParentId(String paraParentId) {
        this.paraParentId = paraParentId == null ? null : paraParentId.trim();
    }
    /**
     * @return the value of  ECM_PARAM.REMARK1 
     */
    public String getRemark1() {
        return remark1;
    }

    /**
     * @param remark1 the value for ECM_PARAM.REMARK1 
     */
    public void setRemark1(String remark1) {
        this.remark1 = remark1 == null ? null : remark1.trim();
    }
    /**
     * @return the value of  ECM_PARAM.REMARK2 
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * @param remark2 the value for ECM_PARAM.REMARK2 
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }
    /**
     * @return the value of  ECM_PARAM.REMARK3 
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * @param remark3 the value for ECM_PARAM.REMARK3 
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }
    /**
     * @return the value of  ECM_PARAM.IS_VALIDATE 
     */
    public String getIsValidate() {
        return isValidate;
    }

    /**
     * @param isValidate the value for ECM_PARAM.IS_VALIDATE 
     */
    public void setIsValidate(String isValidate) {
        this.isValidate = isValidate == null ? null : isValidate.trim();
    }
}