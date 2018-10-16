package com.dcits.ensemble.om.oms.module.log;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/10/24 09:43:20.
 */
public class EcmTcecirStat extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_TCECIR_STAT.CIR_STAT_ID 
     */
    @TablePk(index = 1)
    private Integer cirStatId;

    /**
     * This field is ECM_TCECIR_STAT.CIR_STAT_DATE 
     */
    private String cirStatDate;

    /**
     * This field is ECM_TCECIR_STAT.CIR_SERVER_SER 
     */
    private String cirServerSer;

    /**
     * This field is ECM_TCECIR_STAT.CIR_SERVER_MTD 
     */
    private String cirServerMtd;

    /**
     * This field is ECM_TCECIR_STAT.CIR_STAT_AMOUNT 
     */
    private String cirStatAmount;

    /**
     * This field is ECM_TCECIR_STAT.CIR_STAT_OKNUM 
     */
    private String cirStatOknum;
    /**
     * This field is ECM_TCECIR_STAT.CIR_STAT_OKNUM
     */
    private String cirStatFailnum;

    /**
     * This field is ECM_TCECIR_STAT.CIR_STAT_OKPERC 
     */
    private String cirStatOkperc;

    /**
     * This field is ECM_TCECIR_STAT.CIR_STAT_OKPERC
     */
    private String cirStatFailperc;

    /**
     * This field is ECM_TCECIR_STAT.CIR_STAT_AVERTIME 
     */
    private String cirStatAvertime;

    /**
     * This field is ECM_TCECIR_STAT.CIR_STAT_AVEREXUTIME 
     */
    private String cirStatAverexutime;

    /**
     * This field is ECM_TCECIR_STAT.CIR_STAT_PEAKNUM 
     */
    private String cirStatPeaknum;


    /**
     * @return the value of  ECM_TCECIR_STAT.CIR_STAT_ID 
     */
    public Integer getCirStatId() {
        return cirStatId;
    }

    /**
     * @param cirStatId the value for ECM_TCECIR_STAT.CIR_STAT_ID 
     */
    public void setCirStatId(Integer cirStatId) {
        this.cirStatId = cirStatId;
    }

    /**
     * @return the value of  ECM_TCECIR_STAT.CIR_STAT_DATE 
     */
    public String getCirStatDate() {
        return cirStatDate;
    }

    /**
     * @param cirStatDate the value for ECM_TCECIR_STAT.CIR_STAT_DATE 
     */
    public void setCirStatDate(String cirStatDate) {
        this.cirStatDate = cirStatDate == null ? null : cirStatDate.trim();
    }

    /**
     * @return the value of  ECM_TCECIR_STAT.CIR_SERVER_SER 
     */
    public String getCirServerSer() {
        return cirServerSer;
    }

    /**
     * @param cirServerSer the value for ECM_TCECIR_STAT.CIR_SERVER_SER 
     */
    public void setCirServerSer(String cirServerSer) {
        this.cirServerSer = cirServerSer == null ? null : cirServerSer.trim();
    }

    /**
     * @return the value of  ECM_TCECIR_STAT.CIR_SERVER_MTD 
     */
    public String getCirServerMtd() {
        return cirServerMtd;
    }

    /**
     * @param cirServerMtd the value for ECM_TCECIR_STAT.CIR_SERVER_MTD 
     */
    public void setCirServerMtd(String cirServerMtd) {
        this.cirServerMtd = cirServerMtd == null ? null : cirServerMtd.trim();
    }

    /**
     * @return the value of  ECM_TCECIR_STAT.CIR_STAT_AMOUNT 
     */
    public String getCirStatAmount() {
        return cirStatAmount;
    }

    /**
     * @param cirStatAmount the value for ECM_TCECIR_STAT.CIR_STAT_AMOUNT 
     */
    public void setCirStatAmount(String cirStatAmount) {
        this.cirStatAmount = cirStatAmount == null ? null : cirStatAmount.trim();
    }

    /**
     * @return the value of  ECM_TCECIR_STAT.CIR_STAT_OKNUM 
     */
    public String getCirStatOknum() {
        return cirStatOknum;
    }

    /**
     * @param cirStatOknum the value for ECM_TCECIR_STAT.CIR_STAT_OKNUM 
     */
    public void setCirStatOknum(String cirStatOknum) {
        this.cirStatOknum = cirStatOknum == null ? null : cirStatOknum.trim();
    }

    /**
     * @return the value of  ECM_TCECIR_STAT.CIR_STAT_OKPERC 
     */
    public String getCirStatOkperc() {
        return cirStatOkperc;
    }

    /**
     * @param cirStatOkperc the value for ECM_TCECIR_STAT.CIR_STAT_OKPERC
     */
    public void setCirStatOkperc(String cirStatOkperc) {
        this.cirStatOkperc = cirStatOkperc == null ? null : cirStatOkperc.trim();
    }

    /**
     * @return the value of  ECM_TCECIR_STAT.CIR_STAT_AVERTIME 
     */
    public String getCirStatAvertime() {
        return cirStatAvertime;
    }

    /**
     * @param cirStatAvertime the value for ECM_TCECIR_STAT.CIR_STAT_AVERTIME 
     */
    public void setCirStatAvertime(String cirStatAvertime) {
        this.cirStatAvertime = cirStatAvertime == null ? null : cirStatAvertime.trim();
    }

    /**
     * @return the value of  ECM_TCECIR_STAT.CIR_STAT_AVEREXUTIME 
     */
    public String getCirStatAverexutime() {
        return cirStatAverexutime;
    }

    /**
     * @param cirStatAverexutime the value for ECM_TCECIR_STAT.CIR_STAT_AVEREXUTIME 
     */
    public void setCirStatAverexutime(String cirStatAverexutime) {
        this.cirStatAverexutime = cirStatAverexutime == null ? null : cirStatAverexutime.trim();
    }

    /**
     * @return the value of  ECM_TCECIR_STAT.CIR_STAT_PEAKNUM 
     */
    public String getCirStatPeaknum() {
        return cirStatPeaknum;
    }

    /**
     * @param cirStatPeaknum the value for ECM_TCECIR_STAT.CIR_STAT_PEAKNUM 
     */
    public void setCirStatPeaknum(String cirStatPeaknum) {
        this.cirStatPeaknum = cirStatPeaknum == null ? null : cirStatPeaknum.trim();
    }

    public String getCirStatFailperc() {
        return cirStatFailperc;
    }

    public void setCirStatFailperc(String cirStatFailperc) {
        this.cirStatFailperc = cirStatFailperc;
    }

    public String getCirStatFailnum() {
        return cirStatFailnum;
    }

    public void setCirStatFailnum(String cirStatFailnum) {
        this.cirStatFailnum = cirStatFailnum;
    }
}