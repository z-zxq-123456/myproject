package com.dcits.ensemble.om.cmc.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by dangyk on 2018/4/18.
 */
public class CmcTranInfo{

    /**
     * 卡交易历史数组<br>
     * CARD_TRAN_HIST_ARRAY<br>
     * seqNo:1<br>
     * dataType:Array<br>


     * cons:
     */
    private List<CardTranHistArray> cardTranHistArray;


    /**
     * 卡交易历史数组<br>
     * CARD_TRAN_HIST_ARRAY
     */
    public List<CardTranHistArray> getCardTranHistArray() {
        return cardTranHistArray;
    }

    /**
     * 卡交易历史数组<br>
     * CARD_TRAN_HIST_ARRAY
     */
    public void setCardTranHistArray(List<CardTranHistArray> cardTranHistArray) {
        this.cardTranHistArray = cardTranHistArray;
    }

    /**
     * @description 卡交易历史数组
     * @version V1.0
     * @author admin
     * @update 20180418
     */
    public static class CardTranHistArray implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * 卡号<br>
         * CARD_NO<br>
         * seqNo:1.1.1<br>
         * dataType:String<br>
         * length:50<br>
         * cons:
         */
        private String cardNo;

        /**
         * 渠道类型<br>
         * CHANNEL_TYPE<br>
         * seqNo:1.1.10<br>
         * dataType:String<br>
         * length:20<br>
         * cons:
         */
        private String channelType;

        /**
         * 状态<br>
         * TRAN_STATUS<br>
         * seqNo:1.1.11<br>
         * dataType:String<br>
         * length:1<br>
         * cons:
         */
        private String tranStatus;

        /**
         * 冲正标志<br>
         * REV_FLAG<br>
         * seqNo:1.1.12<br>
         * dataType:String<br>
         * length:1<br>
         * cons:
         */
        private String revFlag;

        /**
         * 撤销标志<br>
         * CANCEL_FLAG<br>
         * seqNo:1.1.13<br>
         * dataType:String<br>
         * length:1<br>
         * cons:
         */
        private String cancelFlag;

        /**
         * 币种<br>
         * CCY<br>
         * seqNo:1.1.14<br>
         * dataType:String<br>
         * length:3<br>
         * cons:
         */
        private String ccy;

        /**
         * 发起方系统标识<br>
         * SYSTEM_ID<br>
         * seqNo:1.1.15<br>
         * dataType:String<br>
         * length:50<br>
         * cons:
         */
        private String systemId;

        /**
         * 交易类型<br>
         * TRAN_TYPE<br>
         * seqNo:1.1.16<br>
         * dataType:String<br>
         * length:4<br>
         * cons:
         */
        private String tranType;

        /**
         * 借贷方向<br>
         * CD_FLAG<br>
         * seqNo:1.1.17<br>
         * dataType:String<br>
         * length:10<br>
         * cons:
         */
        private String cdFlag;

        /**
         * 交易金额<br>
         * TRAN_AMT<br>
         * seqNo:1.1.18<br>
         * dataType:Double<br>
         * length:17<br>
         * length:2<br>
         * cons:
         */
        private BigDecimal tranAmt;

        /**
         * 原交易全局流水号<br>
         * ORIG_SEQ_NO<br>
         * seqNo:1.1.19<br>
         * dataType:String<br>
         * length:20<br>
         * cons:
         */
        private String origSeqNo;

        /**
         * 账号<br>
         * BASE_ACCT_NO<br>
         * seqNo:1.1.2<br>
         * dataType:String<br>
         * length:50<br>
         * cons:
         */
        private String baseAcctNo;

        /**
         * 原交易子交易序号<br>
         * ORIG_SUB_SEQ_NO<br>
         * seqNo:1.1.20<br>
         * dataType:String<br>
         * length:50<br>
         * cons:
         */
        private String origSubSeqNo;

        /**
         * 结算标志<br>
         * SETTLE_FLAG<br>
         * seqNo:1.1.22<br>
         * dataType:String<br>
         * length:2<br>
         * cons:
         */
        private String settleFlag;

        /**
         * 客户编号<br>
         * CUSTOMER_ID<br>
         * seqNo:1.1.3<br>
         * dataType:String<br>
         * length:12<br>
         * cons:
         */
        private String customerId;

        /**
         * 对手账号<br>
         * OTH_CARD_NO<br>
         * seqNo:1.1.4<br>
         * dataType:String<br>
         * length:50<br>
         * cons:
         */
        private String othCardNo;

        /**
         * 对方客户号<br>
         * OTH_CLIENT_NO<br>
         * seqNo:1.1.5<br>
         * dataType:String<br>
         * length:20<br>
         * cons:
         */
        private String othClientNo;

        /**
         * 渠道流水号<br>
         * CHANNEL_SEQ_NO<br>
         * seqNo:1.1.6<br>
         * dataType:String<br>
         * length:50<br>
         * cons:
         */
        private String channelSeqNo;

        /**
         * 子交易序号<br>
         * CHANNEL_SUB_SEQ_NO<br>
         * seqNo:1.1.7<br>
         * dataType:String<br>
         * length:50<br>
         * cons:
         */
        private String channelSubSeqNo;

        /**
         * 交易日期<br>
         * TRAN_DATE<br>
         * seqNo:1.1.8<br>
         * dataType:String<br>
         * length:8<br>
         * cons:
         */
        private String tranDate;

        /**
         * 渠道结算日期<br>
         * SETTLEMENT_DATE<br>
         * seqNo:1.1.9<br>
         * dataType:String<br>
         * length:8<br>
         * cons:
         */
        private String settlementDate;


        /**
         * 卡号<br>
         * CARD_NO
         */
        public String getCardNo() {
            return cardNo;
        }

        /**
         * 卡号<br>
         * CARD_NO
         */
        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        /**
         * 渠道类型<br>
         * CHANNEL_TYPE
         */
        public String getChannelType() {
            return channelType;
        }

        /**
         * 渠道类型<br>
         * CHANNEL_TYPE
         */
        public void setChannelType(String channelType) {
            this.channelType = channelType;
        }

        /**
         * 状态<br>
         * TRAN_STATUS
         */
        public String getTranStatus() {
            return tranStatus;
        }

        /**
         * 状态<br>
         * TRAN_STATUS
         */
        public void setTranStatus(String tranStatus) {
            this.tranStatus = tranStatus;
        }

        /**
         * 冲正标志<br>
         * REV_FLAG
         */
        public String getRevFlag() {
            return revFlag;
        }

        /**
         * 冲正标志<br>
         * REV_FLAG
         */
        public void setRevFlag(String revFlag) {
            this.revFlag = revFlag;
        }

        /**
         * 撤销标志<br>
         * CANCEL_FLAG
         */
        public String getCancelFlag() {
            return cancelFlag;
        }

        /**
         * 撤销标志<br>
         * CANCEL_FLAG
         */
        public void setCancelFlag(String cancelFlag) {
            this.cancelFlag = cancelFlag;
        }

        /**
         * 币种<br>
         * CCY
         */
        public String getCcy() {
            return ccy;
        }

        /**
         * 币种<br>
         * CCY
         */
        public void setCcy(String ccy) {
            this.ccy = ccy;
        }

        /**
         * 发起方系统标识<br>
         * SYSTEM_ID
         */
        public String getSystemId() {
            return systemId;
        }

        /**
         * 发起方系统标识<br>
         * SYSTEM_ID
         */
        public void setSystemId(String systemId) {
            this.systemId = systemId;
        }

        /**
         * 交易类型<br>
         * TRAN_TYPE
         */
        public String getTranType() {
            return tranType;
        }

        /**
         * 交易类型<br>
         * TRAN_TYPE
         */
        public void setTranType(String tranType) {
            this.tranType = tranType;
        }

        /**
         * 借贷方向<br>
         * CD_FLAG
         */
        public String getCdFlag() {
            return cdFlag;
        }

        /**
         * 借贷方向<br>
         * CD_FLAG
         */
        public void setCdFlag(String cdFlag) {
            this.cdFlag = cdFlag;
        }

        /**
         * 交易金额<br>
         * TRAN_AMT
         */
        public BigDecimal getTranAmt() {
            return tranAmt;
        }

        /**
         * 交易金额<br>
         * TRAN_AMT
         */
        public void setTranAmt(BigDecimal tranAmt) {
            this.tranAmt = tranAmt;
        }

        /**
         * 原交易全局流水号<br>
         * ORIG_SEQ_NO
         */
        public String getOrigSeqNo() {
            return origSeqNo;
        }

        /**
         * 原交易全局流水号<br>
         * ORIG_SEQ_NO
         */
        public void setOrigSeqNo(String origSeqNo) {
            this.origSeqNo = origSeqNo;
        }

        /**
         * 账号<br>
         * BASE_ACCT_NO
         */
        public String getBaseAcctNo() {
            return baseAcctNo;
        }

        /**
         * 账号<br>
         * BASE_ACCT_NO
         */
        public void setBaseAcctNo(String baseAcctNo) {
            this.baseAcctNo = baseAcctNo;
        }

        /**
         * 原交易子交易序号<br>
         * ORIG_SUB_SEQ_NO
         */
        public String getOrigSubSeqNo() {
            return origSubSeqNo;
        }

        /**
         * 原交易子交易序号<br>
         * ORIG_SUB_SEQ_NO
         */
        public void setOrigSubSeqNo(String origSubSeqNo) {
            this.origSubSeqNo = origSubSeqNo;
        }

        /**
         * 结算标志<br>
         * SETTLE_FLAG
         */
        public String getSettleFlag() {
            return settleFlag;
        }

        /**
         * 结算标志<br>
         * SETTLE_FLAG
         */
        public void setSettleFlag(String settleFlag) {
            this.settleFlag = settleFlag;
        }

        /**
         * 客户编号<br>
         * CUSTOMER_ID
         */
        public String getCustomerId() {
            return customerId;
        }

        /**
         * 客户编号<br>
         * CUSTOMER_ID
         */
        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        /**
         * 对手账号<br>
         * OTH_CARD_NO
         */
        public String getOthCardNo() {
            return othCardNo;
        }

        /**
         * 对手账号<br>
         * OTH_CARD_NO
         */
        public void setOthCardNo(String othCardNo) {
            this.othCardNo = othCardNo;
        }

        /**
         * 对方客户号<br>
         * OTH_CLIENT_NO
         */
        public String getOthClientNo() {
            return othClientNo;
        }

        /**
         * 对方客户号<br>
         * OTH_CLIENT_NO
         */
        public void setOthClientNo(String othClientNo) {
            this.othClientNo = othClientNo;
        }

        /**
         * 渠道流水号<br>
         * CHANNEL_SEQ_NO
         */
        public String getChannelSeqNo() {
            return channelSeqNo;
        }

        /**
         * 渠道流水号<br>
         * CHANNEL_SEQ_NO
         */
        public void setChannelSeqNo(String channelSeqNo) {
            this.channelSeqNo = channelSeqNo;
        }

        /**
         * 子交易序号<br>
         * CHANNEL_SUB_SEQ_NO
         */
        public String getChannelSubSeqNo() {
            return channelSubSeqNo;
        }

        /**
         * 子交易序号<br>
         * CHANNEL_SUB_SEQ_NO
         */
        public void setChannelSubSeqNo(String channelSubSeqNo) {
            this.channelSubSeqNo = channelSubSeqNo;
        }

        /**
         * 交易日期<br>
         * TRAN_DATE
         */
        public String getTranDate() {
            return tranDate;
        }

        /**
         * 交易日期<br>
         * TRAN_DATE
         */
        public void setTranDate(String tranDate) {
            this.tranDate = tranDate;
        }

        /**
         * 渠道结算日期<br>
         * SETTLEMENT_DATE
         */
        public String getSettlementDate() {
            return settlementDate;
        }

        /**
         * 渠道结算日期<br>
         * SETTLEMENT_DATE
         */
        public void setSettlementDate(String settlementDate) {
            this.settlementDate = settlementDate;
        }
    }

}
