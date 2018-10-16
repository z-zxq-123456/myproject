package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by para on 2016/03/22 10:32:08.
 */
public class CifClientType extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is CIF_CLIENT_TYPE.CLIENT_TYPE 瀹㈡埛绫诲瀷
     */
    @TablePk(index = 1)
    private String clientType;

    /**
     * This field is CIF_CLIENT_TYPE.CLIENT_TYPE_DESC 绫诲瀷鎻忚堪
     */
    private String clientTypeDesc;

    /**
     * This field is CIF_CLIENT_TYPE.IS_INDIVIDUAL 鏄惁涓綋瀹㈡埛 Y-鏄�N-涓嶆槸
     */
    private String isIndividual;

    /**
     * This field is CIF_CLIENT_TYPE.COMPANY 娉曚汉浠ｇ爜
     */
    private String company;

    /**
     * @return the value of  CIF_CLIENT_TYPE.CLIENT_TYPE 瀹㈡埛绫诲瀷
     */
    public String getClientType() {
        return clientType;
    }

    /**
     * @param clientType the value for CIF_CLIENT_TYPE.CLIENT_TYPE 瀹㈡埛绫诲瀷
     */
    public void setClientType(String clientType) {
        this.clientType = clientType == null ? null : clientType.trim();
    }

    /**
     * @return the value of  CIF_CLIENT_TYPE.CLIENT_TYPE_DESC 绫诲瀷鎻忚堪
     */
    public String getClientTypeDesc() {
        return clientTypeDesc;
    }

    /**
     * @param clientTypeDesc the value for CIF_CLIENT_TYPE.CLIENT_TYPE_DESC 绫诲瀷鎻忚堪
     */
    public void setClientTypeDesc(String clientTypeDesc) {
        this.clientTypeDesc = clientTypeDesc == null ? null : clientTypeDesc.trim();
    }

    /**
     * @return the value of  CIF_CLIENT_TYPE.IS_INDIVIDUAL 鏄惁涓綋瀹㈡埛 Y-鏄�N-涓嶆槸
     */
    public String getIsIndividual() {
        return isIndividual;
    }

    /**
     * @param isIndividual the value for CIF_CLIENT_TYPE.IS_INDIVIDUAL 鏄惁涓綋瀹㈡埛 Y-鏄�N-涓嶆槸
     */
    public void setIsIndividual(String isIndividual) {
        this.isIndividual = isIndividual == null ? null : isIndividual.trim();
    }

    /**
     * @return the value of  CIF_CLIENT_TYPE.COMPANY 娉曚汉浠ｇ爜
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for CIF_CLIENT_TYPE.COMPANY 娉曚汉浠ｇ爜
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}