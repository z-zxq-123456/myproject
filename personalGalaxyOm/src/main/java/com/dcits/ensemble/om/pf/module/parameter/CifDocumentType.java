package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by para on 2016/03/22 10:32:10.
 */
public class CifDocumentType extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is CIF_DOCUMENT_TYPE.DOCUMENT_TYPE 璇佷欢绫诲瀷
     */
    @TablePk(index = 1)
    private String documentType;

    /**
     * This field is CIF_DOCUMENT_TYPE.DOCUMENT_TYPE_DESC 璇佷欢绫诲瀷鎻忚堪
     */
    private String documentTypeDesc;

    /**
     * This field is CIF_DOCUMENT_TYPE.DOCUMENT_TYPE_SHORT 
     */
    private String documentTypeShort;

    /**
     * This field is CIF_DOCUMENT_TYPE.APP_IND 閫傜敤鑼冨洿 I-涓綋瀹㈡埛 C-闈炰釜浣�B-涓綋鎴栬€呴潪涓綋
     */
    private String appInd;

    /**
     * This field is CIF_DOCUMENT_TYPE.COMPANY 娉曚汉浠ｇ爜
     */
    private String company;

    /**
     * @return the value of  CIF_DOCUMENT_TYPE.DOCUMENT_TYPE 璇佷欢绫诲瀷
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     * @param documentType the value for CIF_DOCUMENT_TYPE.DOCUMENT_TYPE 璇佷欢绫诲瀷
     */
    public void setDocumentType(String documentType) {
        this.documentType = documentType == null ? null : documentType.trim();
    }

    /**
     * @return the value of  CIF_DOCUMENT_TYPE.DOCUMENT_TYPE_DESC 璇佷欢绫诲瀷鎻忚堪
     */
    public String getDocumentTypeDesc() {
        return documentTypeDesc;
    }

    /**
     * @param documentTypeDesc the value for CIF_DOCUMENT_TYPE.DOCUMENT_TYPE_DESC 璇佷欢绫诲瀷鎻忚堪
     */
    public void setDocumentTypeDesc(String documentTypeDesc) {
        this.documentTypeDesc = documentTypeDesc == null ? null : documentTypeDesc.trim();
    }

    /**
     * @return the value of  CIF_DOCUMENT_TYPE.DOCUMENT_TYPE_SHORT 
     */
    public String getDocumentTypeShort() {
        return documentTypeShort;
    }

    /**
     * @param documentTypeShort the value for CIF_DOCUMENT_TYPE.DOCUMENT_TYPE_SHORT 
     */
    public void setDocumentTypeShort(String documentTypeShort) {
        this.documentTypeShort = documentTypeShort == null ? null : documentTypeShort.trim();
    }

    /**
     * @return the value of  CIF_DOCUMENT_TYPE.APP_IND 閫傜敤鑼冨洿 I-涓綋瀹㈡埛 C-闈炰釜浣�B-涓綋鎴栬€呴潪涓綋
     */
    public String getAppInd() {
        return appInd;
    }

    /**
     * @param appInd the value for CIF_DOCUMENT_TYPE.APP_IND 閫傜敤鑼冨洿 I-涓綋瀹㈡埛 C-闈炰釜浣�B-涓綋鎴栬€呴潪涓綋
     */
    public void setAppInd(String appInd) {
        this.appInd = appInd == null ? null : appInd.trim();
    }

    /**
     * @return the value of  CIF_DOCUMENT_TYPE.COMPANY 娉曚汉浠ｇ爜
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for CIF_DOCUMENT_TYPE.COMPANY 娉曚汉浠ｇ爜
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}