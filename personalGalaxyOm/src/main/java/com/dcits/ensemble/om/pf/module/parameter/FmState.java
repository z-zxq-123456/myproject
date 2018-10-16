package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/03/18 18:02:59.
 */
public class FmState extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is FM_STATE.COUNTRY 鍥藉
     */
    @TablePk(index = 1)
    private String country;

    /**
     * This field is FM_STATE.STATE 
     */
    @TablePk(index = 2)
    private String state;

    /**
     * This field is FM_STATE.STATE_DESC 
     */
    private String stateDesc;

    /**
     * This field is FM_STATE.WEEKEND_1 鍛ㄦ湯1 FRI  鍛ㄤ簲 MON  鍛ㄤ竴 SAT  鍛ㄥ叚 SUN  鍛ㄦ棩 THU  鍛ㄥ洓 TUE  鍛ㄤ簩 WED  鍛ㄤ笁
     */
    private String weekend1;

    /**
     * This field is FM_STATE.WEEKEND_2 鍛ㄦ湯2 FRI  鍛ㄤ簲 MON  鍛ㄤ竴 SAT  鍛ㄥ叚 SUN  鍛ㄦ棩 THU  鍛ㄥ洓 TUE  鍛ㄤ簩 WED  鍛ㄤ笁
     */
    private String weekend2;

    /**
     * This field is FM_STATE.COMPANY 
     */
    private String company;

    /**
     * @return the value of  FM_STATE.COUNTRY 鍥藉
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the value for FM_STATE.COUNTRY 鍥藉
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     * @return the value of  FM_STATE.STATE 
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the value for FM_STATE.STATE 
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * @return the value of  FM_STATE.STATE_DESC 
     */
    public String getStateDesc() {
        return stateDesc;
    }

    /**
     * @param stateDesc the value for FM_STATE.STATE_DESC 
     */
    public void setStateDesc(String stateDesc) {
        this.stateDesc = stateDesc == null ? null : stateDesc.trim();
    }

    /**
     * @return the value of  FM_STATE.WEEKEND_1 鍛ㄦ湯1 FRI  鍛ㄤ簲 MON  鍛ㄤ竴 SAT  鍛ㄥ叚 SUN  鍛ㄦ棩 THU  鍛ㄥ洓 TUE  鍛ㄤ簩 WED  鍛ㄤ笁
     */
    public String getWeekend1() {
        return weekend1;
    }

    /**
     * @param weekend1 the value for FM_STATE.WEEKEND_1 鍛ㄦ湯1 FRI  鍛ㄤ簲 MON  鍛ㄤ竴 SAT  鍛ㄥ叚 SUN  鍛ㄦ棩 THU  鍛ㄥ洓 TUE  鍛ㄤ簩 WED  鍛ㄤ笁
     */
    public void setWeekend1(String weekend1) {
        this.weekend1 = weekend1 == null ? null : weekend1.trim();
    }

    /**
     * @return the value of  FM_STATE.WEEKEND_2 鍛ㄦ湯2 FRI  鍛ㄤ簲 MON  鍛ㄤ竴 SAT  鍛ㄥ叚 SUN  鍛ㄦ棩 THU  鍛ㄥ洓 TUE  鍛ㄤ簩 WED  鍛ㄤ笁
     */
    public String getWeekend2() {
        return weekend2;
    }

    /**
     * @param weekend2 the value for FM_STATE.WEEKEND_2 鍛ㄦ湯2 FRI  鍛ㄤ簲 MON  鍛ㄤ竴 SAT  鍛ㄥ叚 SUN  鍛ㄦ棩 THU  鍛ㄥ洓 TUE  鍛ㄤ簩 WED  鍛ㄤ笁
     */
    public void setWeekend2(String weekend2) {
        this.weekend2 = weekend2 == null ? null : weekend2.trim();
    }

    /**
     * @return the value of  FM_STATE.COMPANY 
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for FM_STATE.COMPANY 
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}