package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by para on 2016/03/22 10:32:05.
 */
public class CifCategoryType extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is CIF_CATEGORY_TYPE.CATEGORY_TYPE 瀹㈡埛缁嗗垎绫诲瀷
     */
    @TablePk(index = 1)
    private String categoryType;

    /**
     * This field is CIF_CATEGORY_TYPE.CATEGORY_DESC 绫诲瀷鎻忚堪
     */
    private String categoryDesc;

    /**
     * This field is CIF_CATEGORY_TYPE.CLIENT_TYPE 瀹㈡埛绫诲瀷
     */
    private String clientType;

    /**
     * This field is CIF_CATEGORY_TYPE.BANK 鏄惁涓洪摱琛�Y-鏄�N-涓嶆槸
     */
    private String bank;

    /**
     * This field is CIF_CATEGORY_TYPE.CENTRAL_BANK 鏄惁涓轰腑澶摱琛�Y-鏄�N-涓嶆槸
     */
    private String centralBank;

    /**
     * This field is CIF_CATEGORY_TYPE.FIN_INSTITUTION 鏄惁涓洪噾铻嶆満鏋�Y-鏄�N-涓嶆槸
     */
    private String finInstitution;

    /**
     * This field is CIF_CATEGORY_TYPE.REP_OFFICE 鏄惁涓轰唬琛ㄥ Y-鏄�N-涓嶆槸
     */
    private String repOffice;

    /**
     * This field is CIF_CATEGORY_TYPE.BROKER 鏄惁涓虹粡绾汉 Y-鏄�N-涓嶆槸
     */
    private String broker;

    /**
     * This field is CIF_CATEGORY_TYPE.CORPORATION 鏄惁涓轰紒涓�Y-鏄�N-涓嶆槸
     */
    private String corporation;

    /**
     * This field is CIF_CATEGORY_TYPE.INDIVIDUAL 鏄惁涓轰釜浣�Y-鏄�N-涓嶆槸
     */
    private String individual;

    /**
     * This field is CIF_CATEGORY_TYPE.GOVERNMENT 鏄惁涓烘斂搴滈儴闂�Y-鏄�N-涓嶆槸
     */
    private String government;

    /**
     * This field is CIF_CATEGORY_TYPE.INTL_INSTITUTION 鏄惁涓哄浗闄呯粍缁�Y-鏄�N-涓嶆槸
     */
    private String intlInstitution;

    /**
     * This field is CIF_CATEGORY_TYPE.JOINT 鏄惁涓鸿仈鍚堜綋 Y-鏄�N-涓嶆槸
     */
    private String joint;

    /**
     * This field is CIF_CATEGORY_TYPE.OTHER 鏄惁鏄叾浠�Y-鏄�N-涓嶆槸
     */
    private String other;

    /**
     * This field is CIF_CATEGORY_TYPE.COMPANY 娉曚汉浠ｇ爜
     */
    private String company;

    /**
     * @return the value of  CIF_CATEGORY_TYPE.CATEGORY_TYPE 瀹㈡埛缁嗗垎绫诲瀷
     */
    public String getCategoryType() {
        return categoryType;
    }

    /**
     * @param categoryType the value for CIF_CATEGORY_TYPE.CATEGORY_TYPE 瀹㈡埛缁嗗垎绫诲瀷
     */
    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType == null ? null : categoryType.trim();
    }

    /**
     * @return the value of  CIF_CATEGORY_TYPE.CATEGORY_DESC 绫诲瀷鎻忚堪
     */
    public String getCategoryDesc() {
        return categoryDesc;
    }

    /**
     * @param categoryDesc the value for CIF_CATEGORY_TYPE.CATEGORY_DESC 绫诲瀷鎻忚堪
     */
    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc == null ? null : categoryDesc.trim();
    }

    /**
     * @return the value of  CIF_CATEGORY_TYPE.CLIENT_TYPE 瀹㈡埛绫诲瀷
     */
    public String getClientType() {
        return clientType;
    }

    /**
     * @param clientType the value for CIF_CATEGORY_TYPE.CLIENT_TYPE 瀹㈡埛绫诲瀷
     */
    public void setClientType(String clientType) {
        this.clientType = clientType == null ? null : clientType.trim();
    }

    /**
     * @return the value of  CIF_CATEGORY_TYPE.BANK 鏄惁涓洪摱琛�Y-鏄�N-涓嶆槸
     */
    public String getBank() {
        return bank;
    }

    /**
     * @param bank the value for CIF_CATEGORY_TYPE.BANK 鏄惁涓洪摱琛�Y-鏄�N-涓嶆槸
     */
    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    /**
     * @return the value of  CIF_CATEGORY_TYPE.CENTRAL_BANK 鏄惁涓轰腑澶摱琛�Y-鏄�N-涓嶆槸
     */
    public String getCentralBank() {
        return centralBank;
    }

    /**
     * @param centralBank the value for CIF_CATEGORY_TYPE.CENTRAL_BANK 鏄惁涓轰腑澶摱琛�Y-鏄�N-涓嶆槸
     */
    public void setCentralBank(String centralBank) {
        this.centralBank = centralBank == null ? null : centralBank.trim();
    }

    /**
     * @return the value of  CIF_CATEGORY_TYPE.FIN_INSTITUTION 鏄惁涓洪噾铻嶆満鏋�Y-鏄�N-涓嶆槸
     */
    public String getFinInstitution() {
        return finInstitution;
    }

    /**
     * @param finInstitution the value for CIF_CATEGORY_TYPE.FIN_INSTITUTION 鏄惁涓洪噾铻嶆満鏋�Y-鏄�N-涓嶆槸
     */
    public void setFinInstitution(String finInstitution) {
        this.finInstitution = finInstitution == null ? null : finInstitution.trim();
    }

    /**
     * @return the value of  CIF_CATEGORY_TYPE.REP_OFFICE 鏄惁涓轰唬琛ㄥ Y-鏄�N-涓嶆槸
     */
    public String getRepOffice() {
        return repOffice;
    }

    /**
     * @param repOffice the value for CIF_CATEGORY_TYPE.REP_OFFICE 鏄惁涓轰唬琛ㄥ Y-鏄�N-涓嶆槸
     */
    public void setRepOffice(String repOffice) {
        this.repOffice = repOffice == null ? null : repOffice.trim();
    }

    /**
     * @return the value of  CIF_CATEGORY_TYPE.BROKER 鏄惁涓虹粡绾汉 Y-鏄�N-涓嶆槸
     */
    public String getBroker() {
        return broker;
    }

    /**
     * @param broker the value for CIF_CATEGORY_TYPE.BROKER 鏄惁涓虹粡绾汉 Y-鏄�N-涓嶆槸
     */
    public void setBroker(String broker) {
        this.broker = broker == null ? null : broker.trim();
    }

    /**
     * @return the value of  CIF_CATEGORY_TYPE.CORPORATION 鏄惁涓轰紒涓�Y-鏄�N-涓嶆槸
     */
    public String getCorporation() {
        return corporation;
    }

    /**
     * @param corporation the value for CIF_CATEGORY_TYPE.CORPORATION 鏄惁涓轰紒涓�Y-鏄�N-涓嶆槸
     */
    public void setCorporation(String corporation) {
        this.corporation = corporation == null ? null : corporation.trim();
    }

    /**
     * @return the value of  CIF_CATEGORY_TYPE.INDIVIDUAL 鏄惁涓轰釜浣�Y-鏄�N-涓嶆槸
     */
    public String getIndividual() {
        return individual;
    }

    /**
     * @param individual the value for CIF_CATEGORY_TYPE.INDIVIDUAL 鏄惁涓轰釜浣�Y-鏄�N-涓嶆槸
     */
    public void setIndividual(String individual) {
        this.individual = individual == null ? null : individual.trim();
    }

    /**
     * @return the value of  CIF_CATEGORY_TYPE.GOVERNMENT 鏄惁涓烘斂搴滈儴闂�Y-鏄�N-涓嶆槸
     */
    public String getGovernment() {
        return government;
    }

    /**
     * @param government the value for CIF_CATEGORY_TYPE.GOVERNMENT 鏄惁涓烘斂搴滈儴闂�Y-鏄�N-涓嶆槸
     */
    public void setGovernment(String government) {
        this.government = government == null ? null : government.trim();
    }

    /**
     * @return the value of  CIF_CATEGORY_TYPE.INTL_INSTITUTION 鏄惁涓哄浗闄呯粍缁�Y-鏄�N-涓嶆槸
     */
    public String getIntlInstitution() {
        return intlInstitution;
    }

    /**
     * @param intlInstitution the value for CIF_CATEGORY_TYPE.INTL_INSTITUTION 鏄惁涓哄浗闄呯粍缁�Y-鏄�N-涓嶆槸
     */
    public void setIntlInstitution(String intlInstitution) {
        this.intlInstitution = intlInstitution == null ? null : intlInstitution.trim();
    }

    /**
     * @return the value of  CIF_CATEGORY_TYPE.JOINT 鏄惁涓鸿仈鍚堜綋 Y-鏄�N-涓嶆槸
     */
    public String getJoint() {
        return joint;
    }

    /**
     * @param joint the value for CIF_CATEGORY_TYPE.JOINT 鏄惁涓鸿仈鍚堜綋 Y-鏄�N-涓嶆槸
     */
    public void setJoint(String joint) {
        this.joint = joint == null ? null : joint.trim();
    }

    /**
     * @return the value of  CIF_CATEGORY_TYPE.OTHER 鏄惁鏄叾浠�Y-鏄�N-涓嶆槸
     */
    public String getOther() {
        return other;
    }

    /**
     * @param other the value for CIF_CATEGORY_TYPE.OTHER 鏄惁鏄叾浠�Y-鏄�N-涓嶆槸
     */
    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    /**
     * @return the value of  CIF_CATEGORY_TYPE.COMPANY 娉曚汉浠ｇ爜
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for CIF_CATEGORY_TYPE.COMPANY 娉曚汉浠ｇ爜
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}