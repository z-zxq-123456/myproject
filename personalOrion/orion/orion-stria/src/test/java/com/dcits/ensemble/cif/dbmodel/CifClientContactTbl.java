package com.dcits.ensemble.cif.dbmodel;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

public class CifClientContactTbl extends AbstractBean {

    //@fields serialVersionUID
    private static final long serialVersionUID = 1L;


    /**
     * This field is cif_client_contact_tbl.contact_type 联系类型
     */
    @TablePk(index = 1)
    private String contactType;


    /**
     * This field is cif_client_contact_tbl.client_no 客户号
     */
    @TablePk(index = 2)
    private String clientNo;


    /**
     * This field is cif_client_contact_tbl.route 联系方式
     */
    private String route;


    /**
     * This field is cif_client_contact_tbl.address 地址
     */
    private String address;


    /**
     * This field is cif_client_contact_tbl.contact_id 联系电话
     */
    private String contactId;


    /**
     * This field is cif_client_contact_tbl.salutation 称呼
     */
    private String salutation;


    /**
     * This field is cif_client_contact_tbl.postal_code 邮政编码
     */
    private String postalCode;


    /**
     * This field is cif_client_contact_tbl.pref_flag 是否为首选地址 Y-首选联系信息 N-不是首选联系信息
     */
    private String prefFlag;


    /**
     * This field is cif_client_contact_tbl.country_tel 国家电话区号
     */
    private String countryTel;


    /**
     * This field is cif_client_contact_tbl.city_tel 城市电话区号
     */
    private String cityTel;


    /**
     * This field is cif_client_contact_tbl.update_date 更新日期
     */
    private String updateDate;


    /**
     * This field is cif_client_contact_tbl.update_user 更新用户
     */
    private String updateUser;


    /**
     * This field is cif_client_contact_tbl.addr_mode 地址模式 1-中文方式 2-英文方式
     */
    private String addrMode;


    /**
     * This field is cif_client_contact_tbl.country 国家
     */
    private String country;


    /**
     * This field is cif_client_contact_tbl.state 省、州
     */
    private String state;


    /**
     * This field is cif_client_contact_tbl.city 城市
     */
    private String city;


    /**
     * This field is cif_client_contact_tbl.address1 地址1
     */
    private String address1;


    /**
     * This field is cif_client_contact_tbl.mobile_phone 手机号码
     */
    private String mobilePhone;


    /**
     * This field is cif_client_contact_tbl.linkman 
     */
    private String linkman;


    /**
     * This field is cif_client_contact_tbl.company 
     */
    private String company;

    /**
     * @return the value of cif_client_contact_tbl.contact_type 联系类型
     */
    public String getContactType() {
        return contactType;
    }

    /**
     * @param contactType the value for cif_client_contact_tbl.contact_type 联系类型
     */
    public void setContactType(String contactType) {
        this.contactType = contactType == null ? null : contactType.trim();
    }

    /**
     * @return the value of cif_client_contact_tbl.client_no 客户号
     */
    public String getClientNo() {
        return clientNo;
    }

    /**
     * @param clientNo the value for cif_client_contact_tbl.client_no 客户号
     */
    public void setClientNo(String clientNo) {
        this.clientNo = clientNo == null ? null : clientNo.trim();
    }

    /**
     * @return the value of cif_client_contact_tbl.route 联系方式
     */
    public String getRoute() {
        return route;
    }

    /**
     * @param route the value for cif_client_contact_tbl.route 联系方式
     */
    public void setRoute(String route) {
        this.route = route == null ? null : route.trim();
    }

    /**
     * @return the value of cif_client_contact_tbl.address 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the value for cif_client_contact_tbl.address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * @return the value of cif_client_contact_tbl.contact_id 联系电话
     */
    public String getContactId() {
        return contactId;
    }

    /**
     * @param contactId the value for cif_client_contact_tbl.contact_id 联系电话
     */
    public void setContactId(String contactId) {
        this.contactId = contactId == null ? null : contactId.trim();
    }

    /**
     * @return the value of cif_client_contact_tbl.salutation 称呼
     */
    public String getSalutation() {
        return salutation;
    }

    /**
     * @param salutation the value for cif_client_contact_tbl.salutation 称呼
     */
    public void setSalutation(String salutation) {
        this.salutation = salutation == null ? null : salutation.trim();
    }

    /**
     * @return the value of cif_client_contact_tbl.postal_code 邮政编码
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode the value for cif_client_contact_tbl.postal_code 邮政编码
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode == null ? null : postalCode.trim();
    }

    /**
     * @return the value of cif_client_contact_tbl.pref_flag 是否为首选地址 Y-首选联系信息 N-不是首选联系信息
     */
    public String getPrefFlag() {
        return prefFlag;
    }

    /**
     * @param prefFlag the value for cif_client_contact_tbl.pref_flag 是否为首选地址 Y-首选联系信息 N-不是首选联系信息
     */
    public void setPrefFlag(String prefFlag) {
        this.prefFlag = prefFlag == null ? null : prefFlag.trim();
    }

    /**
     * @return the value of cif_client_contact_tbl.country_tel 国家电话区号
     */
    public String getCountryTel() {
        return countryTel;
    }

    /**
     * @param countryTel the value for cif_client_contact_tbl.country_tel 国家电话区号
     */
    public void setCountryTel(String countryTel) {
        this.countryTel = countryTel == null ? null : countryTel.trim();
    }

    /**
     * @return the value of cif_client_contact_tbl.city_tel 城市电话区号
     */
    public String getCityTel() {
        return cityTel;
    }

    /**
     * @param cityTel the value for cif_client_contact_tbl.city_tel 城市电话区号
     */
    public void setCityTel(String cityTel) {
        this.cityTel = cityTel == null ? null : cityTel.trim();
    }

    /**
     * @return the value of cif_client_contact_tbl.update_date 更新日期
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate the value for cif_client_contact_tbl.update_date 更新日期
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate == null ? null : updateDate.trim();
    }

    /**
     * @return the value of cif_client_contact_tbl.update_user 更新用户
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * @param updateUser the value for cif_client_contact_tbl.update_user 更新用户
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * @return the value of cif_client_contact_tbl.addr_mode 地址模式 1-中文方式 2-英文方式
     */
    public String getAddrMode() {
        return addrMode;
    }

    /**
     * @param addrMode the value for cif_client_contact_tbl.addr_mode 地址模式 1-中文方式 2-英文方式
     */
    public void setAddrMode(String addrMode) {
        this.addrMode = addrMode == null ? null : addrMode.trim();
    }

    /**
     * @return the value of cif_client_contact_tbl.country 国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the value for cif_client_contact_tbl.country 国家
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     * @return the value of cif_client_contact_tbl.state 省、州
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the value for cif_client_contact_tbl.state 省、州
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * @return the value of cif_client_contact_tbl.city 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the value for cif_client_contact_tbl.city 城市
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * @return the value of cif_client_contact_tbl.address1 地址1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * @param address1 the value for cif_client_contact_tbl.address1 地址1
     */
    public void setAddress1(String address1) {
        this.address1 = address1 == null ? null : address1.trim();
    }

    /**
     * @return the value of cif_client_contact_tbl.mobile_phone 手机号码
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * @param mobilePhone the value for cif_client_contact_tbl.mobile_phone 手机号码
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    /**
     * @return the value of cif_client_contact_tbl.linkman 
     */
    public String getLinkman() {
        return linkman;
    }

    /**
     * @param linkman the value for cif_client_contact_tbl.linkman 
     */
    public void setLinkman(String linkman) {
        this.linkman = linkman == null ? null : linkman.trim();
    }

    /**
     * @return the value of cif_client_contact_tbl.company 
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for cif_client_contact_tbl.company 
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}