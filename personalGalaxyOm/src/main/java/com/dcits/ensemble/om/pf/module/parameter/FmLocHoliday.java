package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/03/18 18:03:04.
 */
public class FmLocHoliday extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is FM_LOC_HOLIDAY.COUNTRY 鍥藉 
     */
    @TablePk(index = 1)
    private String country;

    /**
     * This field is FM_LOC_HOLIDAY.HOLIDAY_DATE 
     */
    @TablePk(index = 2)
    private String holidayDate;

    /**
     * This field is FM_LOC_HOLIDAY.STATE 鐪佸競 
     */
    @TablePk(index = 3)
    private String state;

    /**
     * This field is FM_LOC_HOLIDAY.HOLIDAY_DESC 
     */
    private String holidayDesc;

    /**
     * This field is FM_LOC_HOLIDAY.HOLIDAY_TYPE 
     */
    private String holidayType;

    /**
     * This field is FM_LOC_HOLIDAY.WORKING_HOLIDAY 宸ヤ綔鏃鍋囨棩 W:宸ヤ綔鏃�H:鍋囨棩
     */
    private String workingHoliday;

    /**
     * This field is FM_LOC_HOLIDAY.APPLY_IND 
     */
    private String applyInd;

    /**
     * This field is FM_LOC_HOLIDAY.COMPANY 
     */
    private String company;

    /**
     * @return the value of  FM_LOC_HOLIDAY.COUNTRY 鍥藉 
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the value for FM_LOC_HOLIDAY.COUNTRY 鍥藉 
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     * @return the value of  FM_LOC_HOLIDAY.STATE 鐪佸競 
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the value for FM_LOC_HOLIDAY.STATE 鐪佸競 
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * @return the value of  FM_LOC_HOLIDAY.HOLIDAY_DATE 
     */
    public String getHolidayDate() {
        return holidayDate;
    }

    /**
     * @param holidayDate the value for FM_LOC_HOLIDAY.HOLIDAY_DATE 
     */
    public void setHolidayDate(String holidayDate) {
        this.holidayDate = holidayDate == null ? null : holidayDate.trim();
    }

    /**
     * @return the value of  FM_LOC_HOLIDAY.HOLIDAY_DESC 
     */
    public String getHolidayDesc() {
        return holidayDesc;
    }

    /**
     * @param holidayDesc the value for FM_LOC_HOLIDAY.HOLIDAY_DESC 
     */
    public void setHolidayDesc(String holidayDesc) {
        this.holidayDesc = holidayDesc == null ? null : holidayDesc.trim();
    }

    /**
     * @return the value of  FM_LOC_HOLIDAY.HOLIDAY_TYPE 
     */
    public String getHolidayType() {
        return holidayType;
    }

    /**
     * @param holidayType the value for FM_LOC_HOLIDAY.HOLIDAY_TYPE 
     */
    public void setHolidayType(String holidayType) {
        this.holidayType = holidayType == null ? null : holidayType.trim();
    }

    /**
     * @return the value of  FM_LOC_HOLIDAY.WORKING_HOLIDAY 宸ヤ綔鏃鍋囨棩 W:宸ヤ綔鏃�H:鍋囨棩
     */
    public String getWorkingHoliday() {
        return workingHoliday;
    }

    /**
     * @param workingHoliday the value for FM_LOC_HOLIDAY.WORKING_HOLIDAY 宸ヤ綔鏃鍋囨棩 W:宸ヤ綔鏃�H:鍋囨棩
     */
    public void setWorkingHoliday(String workingHoliday) {
        this.workingHoliday = workingHoliday == null ? null : workingHoliday.trim();
    }

    /**
     * @return the value of  FM_LOC_HOLIDAY.APPLY_IND 
     */
    public String getApplyInd() {
        return applyInd;
    }

    /**
     * @param applyInd the value for FM_LOC_HOLIDAY.APPLY_IND 
     */
    public void setApplyInd(String applyInd) {
        this.applyInd = applyInd == null ? null : applyInd.trim();
    }

    /**
     * @return the value of  FM_LOC_HOLIDAY.COMPANY 
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for FM_LOC_HOLIDAY.COMPANY 
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}