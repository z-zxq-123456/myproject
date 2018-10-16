package com.dcits.ensemble.om.cmc.dbModel;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

import java.math.BigDecimal;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/8/26
 */
public class CmcProductInfo extends AbstractBean {

    private static final long serialVersionUID = 1L;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.CARD_PRODUCT_CODE
     * @description: 卡产品编号
     */
    @TablePk(index = 1)
    private String cardProductCode;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.CARD_PRODUCT_NAME
     * @description: 卡产品名称
     */
    private String cardProductName;


    private String binOrder;

    public String getBinOrder() {
        return binOrder;
    }

    public void setBinOrder(String binOrder) {
        this.binOrder = binOrder;
    }

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.PUBLISH_CHANNEL
     * @description: 发行渠道类型 1-本行渠道发卡 2-行业合作发卡
     */
    private String publishChannel;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.CARD_TYPE_CODE
     * @description: 产品系列编号
     */
    private String cardTypeCode;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.CARD_TYPE_NAME
     * @description: 产品系列名称 如: 虚户借记卡、白条卡、实户借贷合一卡等
     */
    private String cardTypeName;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.CARD_PRODUCT_TYPE
     * @description: 产品种类 0-虚户类 1-实户类
     */
    private String cardProductType;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.CARD_CRDBFLG
     * @description: 借贷标识 1-借机 2-贷记 3-借贷合一
     */
    private String cardCrdbflg;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.CARD_NO_TYPE
     * @description: 规则序号, 参见卡号规则定义表
     */
    private String cardNoType;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.FEE_LEVEL
     * @description: 年费等级
     */
    private String feeLevel;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.CARD_LEVEL
     * @description: 级别编号
     */
    private String cardLevel;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.ENABLE_FLAG
     * @description: 启用标志 0：启用1：不启用
     */
    private String enableFlag;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.ENABLE_DATE
     * @description: 启用日期
     */
    private String enableDate;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.ORDER_FLAG
     * @description: 序号启用标志（1全部做序号2：部分（使用起始序号和终止序号）3有吉祥号可选）
     */
    private String orderFlag;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.BEGIN_SEQ
     * @description: 起始序号
     */
    private String beginSeq;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.END_SEQ
     * @description: 终止序号
     */
    private String endSeq;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.CARD_PHY_SORT
     * @description: 卡片物理性质 1:虚拟卡
     */
    private String cardPhySort;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.ATM_ERR_NUM
     * @description: atm密码错误限制次数 0 不控制;只对atm设备上的密码出错的次数限制
     */
    private String atmErrNum;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.TOTAL_ERR_NUM
     * @description: 密码错误总限制次数 0 不控制;对atm、柜台、电话银行等设备上的所有密码出错的次数限制
     */
    private String totalErrNum;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.CVN_TOT_ERR_NUM
     * @description: cvn错误总限制次数 0 不控制
     */
    private String cvnTotErrNum;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.CVN2_TOT_ERR_NUM
     * @description: cvn2错误总限制次数 0 不控制
     */
    private String cvn2TotErrNum;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.LAST_TOT_ERR_NUM
     * @description: 卡号校验位错误总限制次数 0 不控制
     */
    private String lastTotErrNum;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.CCY
     * @description: 主帐户的币种 默认为CNY-人民币
     */
    private String ccy;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.MARK_FLG
     * @description: 记名卡/非记名卡标志 a：全部都可以0：非记名卡1： 记名卡
     */
    private String markFlg;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.ACTIVATE_FEE
     * @description: 开卡手续费
     */
    private BigDecimal activateFee;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.MAX_HOLD_NO
     * @description: 最大持卡量，0不限制
     */
    private String maxHoldNo;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.PSWD_MARK
     * @description: 密码标志 0：不检查密码1：按交易判断2: 必须检查密码
     */
    private String pswdMark;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.LOST_MARK
     * @description: 挂失标志 0：挂失1：不挂失
     */
    private String lostMark;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.EX_DATE
     * @description: 卡有效期期限, 以月为单位(0 表示无期限)
     */
    private String exDate;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.SET_FIX_EX_DATE
     * @description: 有效期使用方式 0：使用有效期1：使用固定有效期2：不使用有效期
     */
    private String setFixExDate;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.FIX_EX_DATE
     * @description: 固定有效期 YYYYMMDD
     */
    private String fixExDate;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.INVALID_DATE
     * @description: 截止有效期
     */
    private String invalidDate;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.INTERFACE_PERMIT
     * @description: 限制接口（用于插入限制代码）
     */
    private String interfacePermit;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.ISSUE_TARGET
     * @description: 发卡对象，2：单位卡1：个人卡
     */
    private String issueTarget;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.BEGINAMT
     * @description: 起存金额 单位:分
     */
    private BigDecimal beginAmt;

    /**
     * This field was generated by DAO-Tools Generator.
     * This field corresponds to the database column cmc_product_info.CONVALUE
     * @description: 限制贡献值
     */
    private String convalue;

    private String reqNum;

    private String operateType;

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getReqNum() {
        return reqNum;
    }

    public void setReqNum(String reqNum) {
        this.reqNum = reqNum;
    }

    public String getCardProductCode() {
        return cardProductCode;
    }

    public void setCardProductCode(String cardProductCode) {
        this.cardProductCode = cardProductCode;
    }

    public String getCardProductName() {
        return cardProductName;
    }

    public void setCardProductName(String cardProductName) {
        this.cardProductName = cardProductName;
    }

    public String getPublishChannel() {
        return publishChannel;
    }

    public void setPublishChannel(String publishChannel) {
        this.publishChannel = publishChannel;
    }

    public String getCardTypeCode() {
        return cardTypeCode;
    }

    public void setCardTypeCode(String cardTypeCode) {
        this.cardTypeCode = cardTypeCode;
    }

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName;
    }

    public String getCardProductType() {
        return cardProductType;
    }

    public void setCardProductType(String cardProductType) {
        this.cardProductType = cardProductType;
    }

    public String getCardCrdbflg() {
        return cardCrdbflg;
    }

    public void setCardCrdbflg(String cardCrdbflg) {
        this.cardCrdbflg = cardCrdbflg;
    }

    public String getCardNoType() {
        return cardNoType;
    }

    public void setCardNoType(String cardNoType) {
        this.cardNoType = cardNoType;
    }

    public String getFeeLevel() {
        return feeLevel;
    }

    public void setFeeLevel(String feeLevel) {
        this.feeLevel = feeLevel;
    }

    public String getCardLevel() {
        return cardLevel;
    }

    public void setCardLevel(String cardLevel) {
        this.cardLevel = cardLevel;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getEnableDate() {
        return enableDate;
    }

    public void setEnableDate(String enableDate) {
        this.enableDate = enableDate;
    }

    public String getOrderFlag() {
        return orderFlag;
    }

    public void setOrderFlag(String orderFlag) {
        this.orderFlag = orderFlag;
    }

    public String getBeginSeq() {
        return beginSeq;
    }

    public void setBeginSeq(String beginSeq) {
        this.beginSeq = beginSeq;
    }

    public String getEndSeq() {
        return endSeq;
    }

    public void setEndSeq(String endSeq) {
        this.endSeq = endSeq;
    }

    public String getCardPhySort() {
        return cardPhySort;
    }

    public void setCardPhySort(String cardPhySort) {
        this.cardPhySort = cardPhySort;
    }

    public String getAtmErrNum() {
        return atmErrNum;
    }

    public void setAtmErrNum(String atmErrNum) {
        this.atmErrNum = atmErrNum;
    }

    public String getTotalErrNum() {
        return totalErrNum;
    }

    public void setTotalErrNum(String totalErrNum) {
        this.totalErrNum = totalErrNum;
    }

    public String getCvnTotErrNum() {
        return cvnTotErrNum;
    }

    public void setCvnTotErrNum(String cvnTotErrNum) {
        this.cvnTotErrNum = cvnTotErrNum;
    }

    public String getCvn2TotErrNum() {
        return cvn2TotErrNum;
    }

    public void setCvn2TotErrNum(String cvn2TotErrNum) {
        this.cvn2TotErrNum = cvn2TotErrNum;
    }

    public String getLastTotErrNum() {
        return lastTotErrNum;
    }

    public void setLastTotErrNum(String lastTotErrNum) {
        this.lastTotErrNum = lastTotErrNum;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getMarkFlg() {
        return markFlg;
    }

    public void setMarkFlg(String markFlg) {
        this.markFlg = markFlg;
    }

    public BigDecimal getActivateFee() {
        return activateFee;
    }

    public void setActivateFee(BigDecimal activateFee) {
        this.activateFee = activateFee;
    }

    public String getMaxHoldNo() {
        return maxHoldNo;
    }

    public void setMaxHoldNo(String maxHoldNo) {
        this.maxHoldNo = maxHoldNo;
    }

    public String getPswdMark() {
        return pswdMark;
    }

    public void setPswdMark(String pswdMark) {
        this.pswdMark = pswdMark;
    }

    public String getLostMark() {
        return lostMark;
    }

    public void setLostMark(String lostMark) {
        this.lostMark = lostMark;
    }

    public String getExDate() {
        return exDate;
    }

    public void setExDate(String exDate) {
        this.exDate = exDate;
    }

    public String getSetFixExDate() {
        return setFixExDate;
    }

    public void setSetFixExDate(String setFixExDate) {
        this.setFixExDate = setFixExDate;
    }

    public String getFixExDate() {
        return fixExDate;
    }

    public void setFixExDate(String fixExDate) {
        this.fixExDate = fixExDate;
    }

    public String getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(String invalidDate) {
        this.invalidDate = invalidDate;
    }

    public String getInterfacePermit() {
        return interfacePermit;
    }

    public void setInterfacePermit(String interfacePermit) {
        this.interfacePermit = interfacePermit;
    }

    public String getIssueTarget() {
        return issueTarget;
    }

    public void setIssueTarget(String issueTarget) {
        this.issueTarget = issueTarget;
    }

    public BigDecimal getBeginAmt() {
        return beginAmt;
    }

    public void setBeginAmt(BigDecimal beginAmt) {
        this.beginAmt = beginAmt;
    }

    public String getConvalue() {
        return convalue;
    }

    public void setConvalue(String convalue) {
        this.convalue = convalue;
    }
}