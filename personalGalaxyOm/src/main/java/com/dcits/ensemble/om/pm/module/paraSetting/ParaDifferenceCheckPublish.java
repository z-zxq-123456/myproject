package com.dcits.ensemble.om.pm.module.paraSetting;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;
import java.math.BigDecimal;

/**
 * Created by maruie on 2016/05/20 16:43:54.
 */
public class ParaDifferenceCheckPublish extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is PARA_DIFFERENCE_CHECK_PUBLISH.SERIES_NUM 序列号
     */
    @TablePk(index = 1)
    private BigDecimal seriesNum;

    /**
     * This field is PARA_DIFFERENCE_CHECK_PUBLISH.TRAN_TIMESTAMP 时间戳
     */
    @TablePk(index = 2)
    private String tranTimestamp;

    /**
     * This field is PARA_DIFFERENCE_CHECK_PUBLISH.REQ_NO 日期+时间
     */
    private String reqNo;

    /**
     * This field is PARA_DIFFERENCE_CHECK_PUBLISH.TABLE_FULL_NAME 参数表全名
     */
    private String tableFullName;

    /**
     * This field is PARA_DIFFERENCE_CHECK_PUBLISH.PRIMARY_KEYVALUE 主键值组合
     */
    private String primaryKeyvalue;

    /**
     * This field is PARA_DIFFERENCE_CHECK_PUBLISH.KEY_VALUE 主键字段和值
     */
    private byte[] keyValue;

    /**
     * This field is PARA_DIFFERENCE_CHECK_PUBLISH.OPERATE_TYPE 操作类型：D/U/I
     */
    private String operateType;

    /**
     * This field is PARA_DIFFERENCE_CHECK_PUBLISH.DATA_DUI 批量删除/添加/修改参数表数据的报文
     */
    private byte[] dataDui;

    /**
     * This field is PARA_DIFFERENCE_CHECK_PUBLISH.OLDDATA_UPD 批量修改之前的参数表数据报文
     */
    private byte[] olddataUpd;

    /**
     * @return the value of  PARA_DIFFERENCE_CHECK_PUBLISH.TRAN_TIMESTAMP 时间戳
     */
    public String getTranTimestamp() {
        return tranTimestamp;
    }

    /**
     * @param tranTimestamp the value for PARA_DIFFERENCE_CHECK_PUBLISH.TRAN_TIMESTAMP 时间戳
     */
    public void setTranTimestamp(String tranTimestamp) {
        this.tranTimestamp = tranTimestamp == null ? null : tranTimestamp.trim();
    }

    /**
     * @return the value of  PARA_DIFFERENCE_CHECK_PUBLISH.SERIES_NUM 序列号
     */
    public BigDecimal getSeriesNum() {
        return seriesNum;
    }

    /**
     * @param seriesNum the value for PARA_DIFFERENCE_CHECK_PUBLISH.SERIES_NUM 序列号
     */
    public void setSeriesNum(BigDecimal seriesNum) {
        this.seriesNum = seriesNum;
    }

    /**
     * @return the value of  PARA_DIFFERENCE_CHECK_PUBLISH.REQ_NO 日期+时间
     */
    public String getReqNo() {
        return reqNo;
    }

    /**
     * @param reqNo the value for PARA_DIFFERENCE_CHECK_PUBLISH.REQ_NO 日期+时间
     */
    public void setReqNo(String reqNo) {
        this.reqNo = reqNo == null ? null : reqNo.trim();
    }

    /**
     * @return the value of  PARA_DIFFERENCE_CHECK_PUBLISH.TABLE_FULL_NAME 参数表全名
     */
    public String getTableFullName() {
        return tableFullName;
    }

    /**
     * @param tableFullName the value for PARA_DIFFERENCE_CHECK_PUBLISH.TABLE_FULL_NAME 参数表全名
     */
    public void setTableFullName(String tableFullName) {
        this.tableFullName = tableFullName == null ? null : tableFullName.trim();
    }

    /**
     * @return the value of  PARA_DIFFERENCE_CHECK_PUBLISH.PRIMARY_KEYVALUE 主键值组合
     */
    public String getPrimaryKeyvalue() {
        return primaryKeyvalue;
    }

    /**
     * @param primaryKeyvalue the value for PARA_DIFFERENCE_CHECK_PUBLISH.PRIMARY_KEYVALUE 主键值组合
     */
    public void setPrimaryKeyvalue(String primaryKeyvalue) {
        this.primaryKeyvalue = primaryKeyvalue == null ? null : primaryKeyvalue.trim();
    }

    /**
     * @return the value of  PARA_DIFFERENCE_CHECK_PUBLISH.KEY_VALUE 主键字段和值
     */
    public byte[] getKeyValue() {
        byte[] tmp = keyValue;
        return tmp;
    }

    /**
     * @param keyValue the value for PARA_DIFFERENCE_CHECK_PUBLISH.KEY_VALUE 主键字段和值
     */
    public void setKeyValue(byte[] keyValue) {

        this.keyValue = keyValue;

    }

    /**
     * @return the value of  PARA_DIFFERENCE_CHECK_PUBLISH.OPERATE_TYPE 操作类型：D/U/I
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * @param operateType the value for PARA_DIFFERENCE_CHECK_PUBLISH.OPERATE_TYPE 操作类型：D/U/I
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType == null ? null : operateType.trim();
    }

    /**
     * @return the value of  PARA_DIFFERENCE_CHECK_PUBLISH.DATA_DUI 批量删除/添加/修改参数表数据的报文
     */
    public byte[] getDataDui() {
        byte[] tmp = dataDui;
        return tmp;
    }

    /**
     * @param dataDui the value for PARA_DIFFERENCE_CHECK_PUBLISH.DATA_DUI 批量删除/添加/修改参数表数据的报文
     */
    public void setDataDui(byte[] dataDui) {
        this.dataDui = dataDui;

    }

    /**
     * @return the value of  PARA_DIFFERENCE_CHECK_PUBLISH.OLDDATA_UPD 批量修改之前的参数表数据报文
     */
    public byte[] getOlddataUpd() {
        byte[] tmp = olddataUpd;
        return tmp;
    }

    /**
     * @param olddataUpd the value for PARA_DIFFERENCE_CHECK_PUBLISH.OLDDATA_UPD 批量修改之前的参数表数据报文
     */
    public void setOlddataUpd(byte[] olddataUpd) {
        this.olddataUpd = olddataUpd;
    }
}