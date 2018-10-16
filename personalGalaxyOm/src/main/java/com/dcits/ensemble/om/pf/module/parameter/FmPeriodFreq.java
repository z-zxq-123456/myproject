package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

import java.math.BigDecimal;

/**
 * Created by admin on 2016/03/18 18:02:58.
 */
public class FmPeriodFreq extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is FM_PERIOD_FREQ.PERIOD_FREQ 棰戠巼id
     */
    @TablePk(index = 1)
    private String periodFreq;

    /**
     * This field is FM_PERIOD_FREQ.PERIOD_FREQ_DESC 鎻忚堪
     */
    private String periodFreqDesc;

    /**
     * This field is FM_PERIOD_FREQ.FORCE_WORK_DAY 
     */
    private String forceWorkDay;

    /**
     * This field is FM_PERIOD_FREQ.ADD_NO 姣忔湡鏁伴噺
     */
    private String addNo;

    /**
     * This field is FM_PERIOD_FREQ.DAY_MTH 姣忔湡鍗曚綅 D:Day M:Month
     */
    private String dayMth;

    /**
     * This field is FM_PERIOD_FREQ.DAY_NUM 姣忔湡澶╂暟 DAY_MTH涓篋鏃讹紝鍙朅DD_NO鍊硷紱涓篗鏃讹紝鍊间负7* ADD_NO锛涗负Y鏃讹紝鍊间负360* ADD_NO
     */
    private String dayNum;

    /**
     * This field is FM_PERIOD_FREQ.FIRST_LAST 鏈熷垵/鏈熸湯 F:鏈熷垵       L:鏈熸湯      A:瀹為檯澶╂暟
     */
    private String firstLast;

    /**
     * This field is FM_PERIOD_FREQ.PRIOR_DAYS 
     */
    private String priorDays;

    /**
     * This field is FM_PERIOD_FREQ.CLIENT_SPREAD 瀹㈡埛娴姩
     */
    private BigDecimal clientSpread;

    /**
     * This field is FM_PERIOD_FREQ.HALF_MONTH 鍗婃湀鏍囪瘑 Y:鏄�N:鍚�榛樿鍊间负N
     */
    private String halfMonth;

    /**
     * This field is FM_PERIOD_FREQ.COMPANY 
     */
    private String company;

    /**
     * @return the value of  FM_PERIOD_FREQ.PERIOD_FREQ 棰戠巼id
     */
    public String getPeriodFreq() {
        return periodFreq;
    }

    /**
     * @param periodFreq the value for FM_PERIOD_FREQ.PERIOD_FREQ 棰戠巼id
     */
    public void setPeriodFreq(String periodFreq) {
        this.periodFreq = periodFreq == null ? null : periodFreq.trim();
    }

    /**
     * @return the value of  FM_PERIOD_FREQ.PERIOD_FREQ_DESC 鎻忚堪
     */
    public String getPeriodFreqDesc() {
        return periodFreqDesc;
    }

    /**
     * @param periodFreqDesc the value for FM_PERIOD_FREQ.PERIOD_FREQ_DESC 鎻忚堪
     */
    public void setPeriodFreqDesc(String periodFreqDesc) {
        this.periodFreqDesc = periodFreqDesc == null ? null : periodFreqDesc.trim();
    }

    /**
     * @return the value of  FM_PERIOD_FREQ.FORCE_WORK_DAY 
     */
    public String getForceWorkDay() {
        return forceWorkDay;
    }

    /**
     * @param forceWorkDay the value for FM_PERIOD_FREQ.FORCE_WORK_DAY 
     */
    public void setForceWorkDay(String forceWorkDay) {
        this.forceWorkDay = forceWorkDay == null ? null : forceWorkDay.trim();
    }

    /**
     * @return the value of  FM_PERIOD_FREQ.ADD_NO 姣忔湡鏁伴噺
     */
    public String getAddNo() {
        return addNo;
    }

    /**
     * @param addNo the value for FM_PERIOD_FREQ.ADD_NO 姣忔湡鏁伴噺
     */
    public void setAddNo(String addNo) {
        this.addNo = addNo == null ? null : addNo.trim();
    }

    /**
     * @return the value of  FM_PERIOD_FREQ.DAY_MTH 姣忔湡鍗曚綅 D:Day M:Month
     */
    public String getDayMth() {
        return dayMth;
    }

    /**
     * @param dayMth the value for FM_PERIOD_FREQ.DAY_MTH 姣忔湡鍗曚綅 D:Day M:Month
     */
    public void setDayMth(String dayMth) {
        this.dayMth = dayMth == null ? null : dayMth.trim();
    }

    /**
     * @return the value of  FM_PERIOD_FREQ.DAY_NUM 姣忔湡澶╂暟 DAY_MTH涓篋鏃讹紝鍙朅DD_NO鍊硷紱涓篗鏃讹紝鍊间负7* ADD_NO锛涗负Y鏃讹紝鍊间负360* ADD_NO
     */
    public String getDayNum() {
        return dayNum;
    }

    /**
     * @param dayNum the value for FM_PERIOD_FREQ.DAY_NUM 姣忔湡澶╂暟 DAY_MTH涓篋鏃讹紝鍙朅DD_NO鍊硷紱涓篗鏃讹紝鍊间负7* ADD_NO锛涗负Y鏃讹紝鍊间负360* ADD_NO
     */
    public void setDayNum(String dayNum) {
        this.dayNum = dayNum == null ? null : dayNum.trim();
    }

    /**
     * @return the value of  FM_PERIOD_FREQ.FIRST_LAST 鏈熷垵/鏈熸湯 F:鏈熷垵       L:鏈熸湯      A:瀹為檯澶╂暟
     */
    public String getFirstLast() {
        return firstLast;
    }

    /**
     * @param firstLast the value for FM_PERIOD_FREQ.FIRST_LAST 鏈熷垵/鏈熸湯 F:鏈熷垵       L:鏈熸湯      A:瀹為檯澶╂暟
     */
    public void setFirstLast(String firstLast) {
        this.firstLast = firstLast == null ? null : firstLast.trim();
    }

    /**
     * @return the value of  FM_PERIOD_FREQ.PRIOR_DAYS 
     */
    public String getPriorDays() {
        return priorDays;
    }

    /**
     * @param priorDays the value for FM_PERIOD_FREQ.PRIOR_DAYS 
     */
    public void setPriorDays(String priorDays) {
        this.priorDays = priorDays == null ? null : priorDays.trim();
    }

    /**
     * @return the value of  FM_PERIOD_FREQ.CLIENT_SPREAD 瀹㈡埛娴姩
     */
    public BigDecimal getClientSpread() {
        return clientSpread;
    }

    /**
     * @param clientSpread the value for FM_PERIOD_FREQ.CLIENT_SPREAD 瀹㈡埛娴姩
     */
    public void setClientSpread(BigDecimal clientSpread) {
        this.clientSpread = clientSpread;
    }

    /**
     * @return the value of  FM_PERIOD_FREQ.HALF_MONTH 鍗婃湀鏍囪瘑 Y:鏄�N:鍚�榛樿鍊间负N
     */
    public String getHalfMonth() {
        return halfMonth;
    }

    /**
     * @param halfMonth the value for FM_PERIOD_FREQ.HALF_MONTH 鍗婃湀鏍囪瘑 Y:鏄�N:鍚�榛樿鍊间负N
     */
    public void setHalfMonth(String halfMonth) {
        this.halfMonth = halfMonth == null ? null : halfMonth.trim();
    }

    /**
     * @return the value of  FM_PERIOD_FREQ.COMPANY 
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for FM_PERIOD_FREQ.COMPANY 
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}