package com.dcits.orion.stria.test.spel;

import com.dcits.galaxy.base.bean.AbstractBean;

import java.math.BigDecimal;

/**
 * Created by cheng.liang on 2015/5/5.
 */
public class Acct extends AbstractBean{
    private static final long serialVersionUID = 3602850493607958935L;
    // 流水号信息;
    private Long seqNo;
    private String reference;
    private String bankSeqNo;
    // 业务来源部门信息;
    private String branch;
    private String profitCentre;
    private String sourceModule;
    private String businessUnit;
    // 业务来源渠道信息;
    private String sourceType;
    private String sourceRefNo;
    private String terminalId;
    private String terminalRefNo;
    private String sourceSettleDate;
    private String sourceContrastBatchNo;
    private String sourceBizType;
    // 交易基础信息;
    private String tranCcy;
    private String tranDate;
    private String effectDate;
    private String officerId;
    private String overrideOfficer;
    private String password;
    // 交易业务信息;
    private String tranType;
    private BigDecimal tranAmt;
    private String cashItem;
    private String narrative;
    private String servCharge;
    // 账户信息--账户交易使用;
    private String acctNoInd;
    private String cardNo;
    private Long acctKey;
    private String acctBalType;
    private String pbkUpdFlag;
    // 对方账户信息--账户交易使用;
    private String contraAcctNoInd;
    private String contraCardNo;
    private Long contraAcctKey;
    private String contraAcctBalType;
    // 支票信息;
    private String chequeType;
    private String chequePrefix;
    private String chequeNo;
    // 其它流水号信息;
    private String primarySeqNo;
    private String traceId;
    private Long tfrSeqNo;
    // 对方币种信息;
    private String contraCcy;
    private String rateType;
    private BigDecimal crossRate;
    private BigDecimal contraAmt;
    private BigDecimal baseEquivAmt;
    // 其它信息;
    private String calcRetainBal;
    private String tranNote;
    // 交易对手信息;
    private String othBankCode;
    private String othBankName;
    private String othBaseAcctNo;
    private String othAcctDesc;
    private String checkRestraintInd;
    private String payer;


    public String getTranCcy() {
        return tranCcy;
    }

    public void setTranCcy(String tranCcy) {
        this.tranCcy = tranCcy;
    }

    public Long getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Long seqNo) {
        this.seqNo = seqNo;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getBankSeqNo() {
        return bankSeqNo;
    }

    public void setBankSeqNo(String bankSeqNo) {
        this.bankSeqNo = bankSeqNo;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getProfitCentre() {
        return profitCentre;
    }

    public void setProfitCentre(String profitCentre) {
        this.profitCentre = profitCentre;
    }

    public String getSourceModule() {
        return sourceModule;
    }

    public void setSourceModule(String sourceModule) {
        this.sourceModule = sourceModule;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceRefNo() {
        return sourceRefNo;
    }

    public void setSourceRefNo(String sourceRefNo) {
        this.sourceRefNo = sourceRefNo;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getTerminalRefNo() {
        return terminalRefNo;
    }

    public void setTerminalRefNo(String terminalRefNo) {
        this.terminalRefNo = terminalRefNo;
    }

    public String getSourceSettleDate() {
        return sourceSettleDate;
    }

    public void setSourceSettleDate(String sourceSettleDate) {
        this.sourceSettleDate = sourceSettleDate;
    }

    public String getSourceContrastBatchNo() {
        return sourceContrastBatchNo;
    }

    public void setSourceContrastBatchNo(String sourceContrastBatchNo) {
        this.sourceContrastBatchNo = sourceContrastBatchNo;
    }

    public String getSourceBizType() {
        return sourceBizType;
    }

    public void setSourceBizType(String sourceBizType) {
        this.sourceBizType = sourceBizType;
    }

    public String getTranDate() {
        return tranDate;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }

    public String getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(String effectDate) {
        this.effectDate = effectDate;
    }

    public String getOfficerId() {
        return officerId;
    }

    public void setOfficerId(String officerId) {
        this.officerId = officerId;
    }

    public String getOverrideOfficer() {
        return overrideOfficer;
    }

    public void setOverrideOfficer(String overrideOfficer) {
        this.overrideOfficer = overrideOfficer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public BigDecimal getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(BigDecimal tranAmt) {
        this.tranAmt = tranAmt;
    }

    public String getCashItem() {
        return cashItem;
    }

    public void setCashItem(String cashItem) {
        this.cashItem = cashItem;
    }

    public String getNarrative() {
        return narrative;
    }

    public void setNarrative(String narrative) {
        this.narrative = narrative;
    }

    public String getServCharge() {
        return servCharge;
    }

    public void setServCharge(String servCharge) {
        this.servCharge = servCharge;
    }

    public String getAcctNoInd() {
        return acctNoInd;
    }

    public void setAcctNoInd(String acctNoInd) {
        this.acctNoInd = acctNoInd;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Long getAcctKey() {
        return acctKey;
    }

    public void setAcctKey(Long acctKey) {
        this.acctKey = acctKey;
    }

    public String getAcctBalType() {
        return acctBalType;
    }

    public void setAcctBalType(String acctBalType) {
        this.acctBalType = acctBalType;
    }

    public String getPbkUpdFlag() {
        return pbkUpdFlag;
    }

    public void setPbkUpdFlag(String pbkUpdFlag) {
        this.pbkUpdFlag = pbkUpdFlag;
    }

    public String getContraAcctNoInd() {
        return contraAcctNoInd;
    }

    public void setContraAcctNoInd(String contraAcctNoInd) {
        this.contraAcctNoInd = contraAcctNoInd;
    }

    public String getContraCardNo() {
        return contraCardNo;
    }

    public void setContraCardNo(String contraCardNo) {
        this.contraCardNo = contraCardNo;
    }

    public Long getContraAcctKey() {
        return contraAcctKey;
    }

    public void setContraAcctKey(Long contraAcctKey) {
        this.contraAcctKey = contraAcctKey;
    }

    public String getContraAcctBalType() {
        return contraAcctBalType;
    }

    public void setContraAcctBalType(String contraAcctBalType) {
        this.contraAcctBalType = contraAcctBalType;
    }

    public String getChequeType() {
        return chequeType;
    }

    public void setChequeType(String chequeType) {
        this.chequeType = chequeType;
    }

    public String getChequePrefix() {
        return chequePrefix;
    }

    public void setChequePrefix(String chequePrefix) {
        this.chequePrefix = chequePrefix;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public String getPrimarySeqNo() {
        return primarySeqNo;
    }

    public void setPrimarySeqNo(String primarySeqNo) {
        this.primarySeqNo = primarySeqNo;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public Long getTfrSeqNo() {
        return tfrSeqNo;
    }

    public void setTfrSeqNo(Long tfrSeqNo) {
        this.tfrSeqNo = tfrSeqNo;
    }

    public String getContraCcy() {
        return contraCcy;
    }

    public void setContraCcy(String contraCcy) {
        this.contraCcy = contraCcy;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public BigDecimal getCrossRate() {
        return crossRate;
    }

    public void setCrossRate(BigDecimal crossRate) {
        this.crossRate = crossRate;
    }

    public BigDecimal getContraAmt() {
        return contraAmt;
    }

    public void setContraAmt(BigDecimal contraAmt) {
        this.contraAmt = contraAmt;
    }

    public BigDecimal getBaseEquivAmt() {
        return baseEquivAmt;
    }

    public void setBaseEquivAmt(BigDecimal baseEquivAmt) {
        this.baseEquivAmt = baseEquivAmt;
    }

    public String getCalcRetainBal() {
        return calcRetainBal;
    }

    public void setCalcRetainBal(String calcRetainBal) {
        this.calcRetainBal = calcRetainBal;
    }

    public String getTranNote() {
        return tranNote;
    }

    public void setTranNote(String tranNote) {
        this.tranNote = tranNote;
    }

    public String getOthBankCode() {
        return othBankCode;
    }

    public void setOthBankCode(String othBankCode) {
        this.othBankCode = othBankCode;
    }

    public String getOthBankName() {
        return othBankName;
    }

    public void setOthBankName(String othBankName) {
        this.othBankName = othBankName;
    }

    public String getOthBaseAcctNo() {
        return othBaseAcctNo;
    }

    public void setOthBaseAcctNo(String othBaseAcctNo) {
        this.othBaseAcctNo = othBaseAcctNo;
    }

    public String getOthAcctDesc() {
        return othAcctDesc;
    }

    public void setOthAcctDesc(String othAcctDesc) {
        this.othAcctDesc = othAcctDesc;
    }

    public String getCheckRestraintInd() {
        return checkRestraintInd;
    }

    public void setCheckRestraintInd(String checkRestraintInd) {
        this.checkRestraintInd = checkRestraintInd;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }
}
