package com.dcits.ensemble.om.pm.module.paraSetting;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;
import java.math.BigDecimal;

/**
 * Created by admin on 2017/03/29 20:35:31.
 */
public class FmRefCode extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is FM_REF_CODE.REF_LANG 语言
     */
    @TablePk(index = 1)
    private String refLang;

    /**
     * This field is FM_REF_CODE.DOMAIN 表字段
     */
    @TablePk(index = 2)
    private String domain;

    /**
     * This field is FM_REF_CODE.FIELD_VALUE 取值范围
     */
    @TablePk(index = 3)
    private String fieldValue;

    /**
     * This field is FM_REF_CODE.ABBREVIATION 缩写
     */
    private String abbreviation;

    /**
     * This field is FM_REF_CODE.MEANING 取值的含义说明
     */
    private String meaning;

    /**
     * This field is FM_REF_CODE.COMPANY 法人
     */
    private String company;

    /**
     * This field is FM_REF_CODE.TRAN_TIMESTAMP 交易时间戳
     */
    private String tranTimestamp;

    /**
     * This field is FM_REF_CODE.TRAN_TIME 交易时间
     */
    private Long tranTime;

    /**
     * @return the value of  FM_REF_CODE.REF_LANG 语言
     */
    public String getRefLang() {
        return refLang;
    }

    /**
     * @param refLang the value for FM_REF_CODE.REF_LANG 语言
     */
    public void setRefLang(String refLang) {
        this.refLang = refLang == null ? null : refLang.trim();
    }

    /**
     * @return the value of  FM_REF_CODE.DOMAIN 表字段
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @param domain the value for FM_REF_CODE.DOMAIN 表字段
     */
    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }

    /**
     * @return the value of  FM_REF_CODE.FIELD_VALUE 取值范围
     */
    public String getFieldValue() {
        return fieldValue;
    }

    /**
     * @param fieldValue the value for FM_REF_CODE.FIELD_VALUE 取值范围
     */
    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue == null ? null : fieldValue.trim();
    }

    /**
     * @return the value of  FM_REF_CODE.ABBREVIATION 缩写
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * @param abbreviation the value for FM_REF_CODE.ABBREVIATION 缩写
     */
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation == null ? null : abbreviation.trim();
    }

    /**
     * @return the value of  FM_REF_CODE.MEANING 取值的含义说明
     */
    public String getMeaning() {
        return meaning;
    }

    /**
     * @param meaning the value for FM_REF_CODE.MEANING 取值的含义说明
     */
    public void setMeaning(String meaning) {
        this.meaning = meaning == null ? null : meaning.trim();
    }

    /**
     * @return the value of  FM_REF_CODE.COMPANY 法人
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for FM_REF_CODE.COMPANY 法人
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    /**
     * @return the value of  FM_REF_CODE.TRAN_TIMESTAMP 交易时间戳
     */
    public String getTranTimestamp() {
        return tranTimestamp;
    }

    /**
     * @param tranTimestamp the value for FM_REF_CODE.TRAN_TIMESTAMP 交易时间戳
     */
    public void setTranTimestamp(String tranTimestamp) {
        this.tranTimestamp = tranTimestamp == null ? null : tranTimestamp.trim();
    }

    /**
     * @return the value of  FM_REF_CODE.TRAN_TIME 交易时间
     */
    public Long getTranTime() {
        return tranTime;
    }

    /**
     * @param tranTime the value for FM_REF_CODE.TRAN_TIME 交易时间
     */
    public void setTranTime(Long tranTime) {
        this.tranTime = tranTime;
    }
}