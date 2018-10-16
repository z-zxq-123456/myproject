package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by para on 2016/03/22 10:32:09.
 */
public class CifContactType extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is CIF_CONTACT_TYPE.CONTACT_TYPE 鑱旂郴绫诲瀷
     */
    @TablePk(index = 1)
    private String contactType;

    /**
     * This field is CIF_CONTACT_TYPE.CONTACT_TYPE_DESC 鑱旂郴绫诲瀷鎻忚堪
     */
    private String contactTypeDesc;

    /**
     * This field is CIF_CONTACT_TYPE.ROUTE 鑱旂郴鏂瑰紡
     */
    private String route;

    /**
     * This field is CIF_CONTACT_TYPE.SWIFT_ID SWIFT ID
     */
    private String swiftId;

    /**
     * This field is CIF_CONTACT_TYPE.COMPANY 娉曚汉浠ｇ爜
     */
    private String company;

    /**
     * @return the value of  CIF_CONTACT_TYPE.CONTACT_TYPE 鑱旂郴绫诲瀷
     */
    public String getContactType() {
        return contactType;
    }

    /**
     * @param contactType the value for CIF_CONTACT_TYPE.CONTACT_TYPE 鑱旂郴绫诲瀷
     */
    public void setContactType(String contactType) {
        this.contactType = contactType == null ? null : contactType.trim();
    }

    /**
     * @return the value of  CIF_CONTACT_TYPE.CONTACT_TYPE_DESC 鑱旂郴绫诲瀷鎻忚堪
     */
    public String getContactTypeDesc() {
        return contactTypeDesc;
    }

    /**
     * @param contactTypeDesc the value for CIF_CONTACT_TYPE.CONTACT_TYPE_DESC 鑱旂郴绫诲瀷鎻忚堪
     */
    public void setContactTypeDesc(String contactTypeDesc) {
        this.contactTypeDesc = contactTypeDesc == null ? null : contactTypeDesc.trim();
    }

    /**
     * @return the value of  CIF_CONTACT_TYPE.ROUTE 鑱旂郴鏂瑰紡
     */
    public String getRoute() {
        return route;
    }

    /**
     * @param route the value for CIF_CONTACT_TYPE.ROUTE 鑱旂郴鏂瑰紡
     */
    public void setRoute(String route) {
        this.route = route == null ? null : route.trim();
    }

    /**
     * @return the value of  CIF_CONTACT_TYPE.SWIFT_ID SWIFT ID
     */
    public String getSwiftId() {
        return swiftId;
    }

    /**
     * @param swiftId the value for CIF_CONTACT_TYPE.SWIFT_ID SWIFT ID
     */
    public void setSwiftId(String swiftId) {
        this.swiftId = swiftId == null ? null : swiftId.trim();
    }

    /**
     * @return the value of  CIF_CONTACT_TYPE.COMPANY 娉曚汉浠ｇ爜
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for CIF_CONTACT_TYPE.COMPANY 娉曚汉浠ｇ爜
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}