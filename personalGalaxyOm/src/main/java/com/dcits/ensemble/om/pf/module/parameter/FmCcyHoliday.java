package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/03/18 18:03:05.
 */
public class FmCcyHoliday extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is FM_CCY_HOLIDAY.HOLIDAY_DATE 鍋囨棩鏃ユ湡
     */
    @TablePk(index = 1)
    private String holidayDate;

    /**
     * This field is FM_CCY_HOLIDAY.CCY 甯佺
     */
    private String ccy;

    /**
     * This field is FM_CCY_HOLIDAY.HOLIDAY_DESC 鍋囨棩鎻忚堪
     */
    private String holidayDesc;

    /**
     * This field is FM_CCY_HOLIDAY.HOLIDAY_TYPE 
     */
    private String holidayType;

    /**
     * This field is FM_CCY_HOLIDAY.APPLY_IND 
     */
    private String applyInd;

    /**
     * This field is FM_CCY_HOLIDAY.COMPANY 
     */
    private String company;

    /**
     * @return the value of  FM_CCY_HOLIDAY.CCY 甯佺
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * @param ccy the value for FM_CCY_HOLIDAY.CCY 甯佺
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * @return the value of  FM_CCY_HOLIDAY.HOLIDAY_DATE 鍋囨棩鏃ユ湡
     */
    public String getHolidayDate() {
        return holidayDate;
    }

    /**
     * @param holidayDate the value for FM_CCY_HOLIDAY.HOLIDAY_DATE 鍋囨棩鏃ユ湡
     */
    public void setHolidayDate(String holidayDate) {
        this.holidayDate = holidayDate == null ? null : holidayDate.trim();
    }

    /**
     * @return the value of  FM_CCY_HOLIDAY.HOLIDAY_DESC 鍋囨棩鎻忚堪
     */
    public String getHolidayDesc() {
        return holidayDesc;
    }

    /**
     * @param holidayDesc the value for FM_CCY_HOLIDAY.HOLIDAY_DESC 鍋囨棩鎻忚堪
     */
    public void setHolidayDesc(String holidayDesc) {
        this.holidayDesc = holidayDesc == null ? null : holidayDesc.trim();
    }

    /**
     * @return the value of  FM_CCY_HOLIDAY.HOLIDAY_TYPE 
     */
    public String getHolidayType() {
        return holidayType;
    }

    /**
     * @param holidayType the value for FM_CCY_HOLIDAY.HOLIDAY_TYPE 
     */
    public void setHolidayType(String holidayType) {
        this.holidayType = holidayType == null ? null : holidayType.trim();
    }

    /**
     * @return the value of  FM_CCY_HOLIDAY.APPLY_IND 
     */
    public String getApplyInd() {
        return applyInd;
    }

    /**
     * @param applyInd the value for FM_CCY_HOLIDAY.APPLY_IND 
     */
    public void setApplyInd(String applyInd) {
        this.applyInd = applyInd == null ? null : applyInd.trim();
    }

    /**
     * @return the value of  FM_CCY_HOLIDAY.COMPANY 
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for FM_CCY_HOLIDAY.COMPANY 
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}