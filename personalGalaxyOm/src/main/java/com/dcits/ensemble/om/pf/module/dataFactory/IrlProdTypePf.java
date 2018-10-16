package com.dcits.ensemble.om.pf.module.dataFactory;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;
import java.math.BigDecimal;

/**
 * Created by admin on 2016/05/10 09:49:37.
 */
public class IrlProdTypePf extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is IRL_PROD_TYPE.PROD_TYPE 
     */
    @TablePk(index = 1)
    private String prodType;

    /**
     * This field is IRL_PROD_TYPE.PROD_TYPE_DESC 
     */
    private String prodTypeDesc;

    /**
     * This field is IRL_PROD_TYPE.PROD_GRP 
     */
    private String prodGrp;



    /**
     * This field is IRL_PROD_TYPE.INT_DATE_TYPE 
     */
    private String intDateType;

    /**
     * This field is IRL_PROD_TYPE.COMPANY 
     */
    private String company;

    /**
     * This field is IRL_PROD_TYPE.IS_LCZQ 是否为零存整取
            Y：零存整取产品类型
            N：非零存整取产品类型
            
     */
    private String fixedCall;

    /**
     * @return the value of  IRL_PROD_TYPE.PROD_TYPE 
     */
    public String getProdType() {
        return prodType;
    }

    /**
     * @param prodType the value for IRL_PROD_TYPE.PROD_TYPE 
     */
    public void setProdType(String prodType) {
        this.prodType = prodType == null ? null : prodType.trim();
    }

    /**
     * @return the value of  IRL_PROD_TYPE.PROD_TYPE_DESC 
     */
    public String getProdTypeDesc() {
        return prodTypeDesc;
    }

    /**
     * @param prodTypeDesc the value for IRL_PROD_TYPE.PROD_TYPE_DESC 
     */
    public void setProdTypeDesc(String prodTypeDesc) {
        this.prodTypeDesc = prodTypeDesc == null ? null : prodTypeDesc.trim();
    }

    /**
     * @return the value of  IRL_PROD_TYPE.PROD_GRP 
     */
    public String getProdGrp() {
        return prodGrp;
    }

    /**
     * @param prodGrp the value for IRL_PROD_TYPE.PROD_GRP 
     */
    public void setProdGrp(String prodGrp) {
        this.prodGrp = prodGrp == null ? null : prodGrp.trim();
    }



    /**
     * @return the value of  IRL_PROD_TYPE.INT_DATE_TYPE 
     */
    public String getIntDateType() {
        return intDateType;
    }

    /**
     * @param intDateType the value for IRL_PROD_TYPE.INT_DATE_TYPE 
     */
    public void setIntDateType(String intDateType) {
        this.intDateType = intDateType == null ? null : intDateType.trim();
    }

    /**
     * @return the value of  IRL_PROD_TYPE.COMPANY 
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for IRL_PROD_TYPE.COMPANY 
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    /**
     * @return the value of  IRL_PROD_TYPE.IS_LCZQ 是否为零存整取
            Y：零存整取产品类型
            N：非零存整取产品类型
            
     */
    public String getFixedCall() {
        return fixedCall;
    }

    /**
     * @param fixedCall the value for IRL_PROD_TYPE.FIXED_CALL 是否为零存整取
            Y：零存整取产品类型
            N：非零存整取产品类型
            
     */
    public void setFixedCall(String fixedCall) {
        this.fixedCall = fixedCall == null ? null : fixedCall.trim();
    }


}