package com.dcits.orion.stria.test.mapping;

/**
 * Created by cheng.liang on 2015/5/5.
 */
/**
 * <p>Title: Fin10000101In.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2015</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 20150128 17:00:55
 * @version V1.0
 */

import com.dcits.galaxy.base.data.Request;
import com.dcits.galaxy.base.validate.V;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/***
 * @description 活期存入
 * @version V1.0
 * @author Tim
 * @update 20150128 17:00:55
 */
public class Fin10000101In extends Request {

    /***
     * @fields serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @V(desc = "报文体")
    private Body body;

    /**
     * @return body : return the property body.
     */
    public Body getBody() {
        return body;
    }

    /**
     * @param body
     *            : set the property body.
     */
    public void setBody(Body body) {
        this.body = body;
    }

    public static class Body implements Serializable {
        private static final long serialVersionUID = 1L;

        /**
         * 币种<br>
         * CCY<br>
         * seqNo:2<br>
         * dataType:STRING(3)<br>
         * cons:CCY见FM_CURRENCY的CCY字段
         */
        @V(desc = "币种", notNull = false, notEmpty = true, maxSize = 3)
        private String ccy;

        /**
         * 票据开出的银行分行号<br>
         * REFERENCE_BRANCH<br>
         * seqNo:17<br>
         * dataType:STRING(6)<br>
         * cons:见FM_BRANCH_TBL的BRANCH_ID字段
         */
        @V(desc = "票据开出的银行分行号", notNull = false, maxSize = 6)
        private String referenceBranch;

        /**
         * 凭证类型<br>
         * DOC_TYPE<br>
         * seqNo:24<br>
         * dataType:STRING(3)<br>
         * cons:支票或票据票交易时需要，见RB_VOUCHER_DEF的DOC_TYPE字段或RB_CHEQUE_TYPE的CHEQUE_TYPE字段
         */
        @V(desc = "凭证类型", notNull = false, maxSize = 3)
        private String docType;

        /**
         * 服务费信息<br>
         * SERV_DETAIL<br>
         * seqNo:12<br>
         * dataType:ARRAY<br>
         * cons:收取服务费时需要且必输
         */
        @V(desc = "服务费信息", notNull = false)
        private List<ServDetail> servDetail;

        /**
         * 交易说明<br>
         * NARRATIVE<br>
         * seqNo:11<br>
         * dataType:STRING(30)<br>
         * cons:
         */
        @V(desc = "交易说明", notNull = false, maxSize = 30)
        private String narrative;

        /**
         * 等值本币<br>
         * EQUIV_AMT<br>
         * seqNo:39<br>
         * dataType:BigDecimal(17,2)<br>
         * cons:
         */
        @V(desc = "等值本币", notNull = false, maxSize = 17)
        private BigDecimal equivAmt;

        /**
         * <br>
         * OPTION_KW<br>
         * seqNo:53<br>
         * dataType:STRING (21)<br>
         * cons:
         */
        @V(desc = "", notNull = false, maxSize = 21)
        private String optionKw;

        /**
         * 对账批次号<br>
         * CONTRAST_BAT_NO<br>
         * seqNo:31<br>
         * dataType:STRING(20)<br>
         * cons:中间业务使用，MESSAGE_CODE待定
         */
        @V(desc = "对账批次号", notNull = false, maxSize = 20)
        private String contrastBatNo;

        /**
         * 支取密码<br>
         * PASSWORD<br>
         * seqNo:10<br>
         * dataType:STRING(50)<br>
         * cons:密码支取时需要且必输
         */
        @V(desc = "支取密码", notNull = false, maxSize = 50)
        private String password;

        /**
         * 客户号<br>
         * CLIENT_NO<br>
         * seqNo:8<br>
         * dataType:STRING(12)<br>
         * cons:输入见FM_CLIENT的CLIENT_NO字段
         */
        @V(desc = "客户号", notNull = false, maxSize = 12)
        private String clientNo;

        /**
         * 余额类型<br>
         * INPUT_BAL_TYPE<br>
         * seqNo:7<br>
         * dataType:STRING(2)<br>
         * cons:取值范围：CA/TT
         */
        @V(desc = "余额类型", notNull = false, notEmpty = true, maxSize = 2)
        private String inputBalType;

        /**
         * 现金项目<br>
         * CASH_ITEM<br>
         * seqNo:30<br>
         * dataType:STRING(4)<br>
         * cons:现金交易使用
         */
        @V(desc = "现金项目", notNull = false, maxSize = 4)
        private String cashItem;

        /**
         * 交易金额<br>
         * TRAN_AMT<br>
         * seqNo:13<br>
         * dataType:BigDecimal(17,2)<br>
         * cons:
         */
        @V(desc = "交易金额", notNull = false, notEmpty = true, maxSize = 17)
        private BigDecimal tranAmt;

        /**
         * 代办人姓名<br>
         * COMMISSION_CLIENT_NAME<br>
         * seqNo:27<br>
         * dataType:STRING(20)<br>
         * cons:
         */
        @V(desc = "代办人姓名", notNull = false, maxSize = 20)
        private String commissionClientName;

        /**
         * 交易类型<br>
         * TRAN_TYPE<br>
         * seqNo:47<br>
         * dataType:STRING(4)<br>
         * cons:见RB_TRAN_DEF的TRAN_TYPE字段
         */
        @V(desc = "交易类型", notNull = false, notEmpty = true, maxSize = 4)
        private String tranType;

        /**
         * 卡三磁道  <br>
         * TRACK3   <br>
         * seqNo:54<br>
         * dataType:STRING (37) <br>
         * cons:
         */
        @V(desc = "卡三磁道  ", notNull = false, maxSize = 37)
        private String track3   ;

        /**
         * 交易场次<br>
         * SESSION_ID<br>
         * seqNo:22<br>
         * dataType:STRING(4)<br>
         * cons:
         */
        @V(desc = "交易场次", notNull = false, maxSize = 4)
        private String sessionId;

        /**
         * 票据开出的交易银行号<br>
         * REFERENCE_BANK<br>
         * seqNo:16<br>
         * dataType:STRING(8)<br>
         * cons:见FM_BANK的BANK_CODE字段
         */
        @V(desc = "票据开出的交易银行号", notNull = false, maxSize = 8)
        private String referenceBank;

        /**
         * 开出票据帐户产品类型<br>
         * ISSUER_ACCT_TYPE<br>
         * seqNo:21<br>
         * dataType:STRING(3)<br>
         * cons:支票存入时需要,见RB_ACCT_TYPE的ACCT_TYPE字段
         */
        @V(desc = "开出票据帐户产品类型", notNull = false, maxSize = 3)
        private String issuerAcctType;

        /**
         * 票据/凭证前缀<br>
         * PREFIX<br>
         * seqNo:14<br>
         * dataType:STRING(10)<br>
         * cons:支票或票据票交易时需要
         */
        @V(desc = "票据/凭证前缀", notNull = false, maxSize = 10)
        private String prefix;

        /**
         * 卡序列号  <br>
         * IC_CARD_SEQ <br>
         * seqNo:51<br>
         * dataType:STRING (3)  <br>
         * cons:
         */
        @V(desc = "卡序列号  ", notNull = false, maxSize = 3)
        private String icCardSeq ;

        /**
         * 产品类型代码<br>
         * BIZ_TYPE<br>
         * seqNo:32<br>
         * dataType:STRING(4)<br>
         * cons:中间业务使用，MESSAGE_CODE待定
         */
        @V(desc = "产品类型代码", notNull = false, maxSize = 4)
        private String bizType;

        /**
         * 代办人客户号<br>
         * COMMISSION_CLIENT_NO<br>
         * seqNo:26<br>
         * dataType:STRING(12)<br>
         * cons:
         */
        @V(desc = "代办人客户号", notNull = false, maxSize = 12)
        private String commissionClientNo;

        /**
         * 浮动天数<br>
         * FLOAT_DAYS<br>
         * seqNo:18<br>
         * dataType:STRING(2)<br>
         * cons:
         */
        @V(desc = "浮动天数", notNull = false, maxSize = 2)
        private String floatDays;

        /**
         * 卡二磁道  <br>
         * TRACK2      <br>
         * seqNo:50<br>
         * dataType:STRING (37) <br>
         * cons:
         */
        @V(desc = "卡二磁道  ", notNull = false, maxSize = 37)
        private String track2      ;

        /**
         * 子账号/卡号<br>
         * ACCT_NO<br>
         * seqNo:41<br>
         * dataType:STRING(50)<br>
         * cons:
         */
        @V(desc = "子账号/卡号", notNull = false, maxSize = 50)
        private String acctNo;

        /**
         * 原渠道流水号<br>
         * ORIG_CHANNAL_SEQ_NO<br>
         * seqNo:37<br>
         * dataType:STRING(50)<br>
         * cons:仅用于POS消费撤消交易；当POS消费撤消时必输
         */
        @V(desc = "原渠道流水号", notNull = false, maxSize = 50)
        private String origChannalSeqNo;

        /**
         * 入账日期<br>
         * POST_DATE<br>
         * seqNo:23<br>
         * dataType:STRING(8)<br>
         * cons:
         */
        @V(desc = "入账日期", notNull = false, maxSize = 8)
        private String postDate;

        /**
         * 检查标志  <br>
         * CHECK_OPTION<br>
         * seqNo:49<br>
         * dataType:STRING (10) <br>
         * cons:
         */
        @V(desc = "检查标志  ", notNull = false, maxSize = 10)
        private String checkOption;

        /**
         * 产品类型<br>
         * ACCT_TYPE<br>
         * seqNo:3<br>
         * dataType:STRING(3)<br>
         * cons:ACCT_TYPE见RB_ACCT_TYPE的ACCT_TYPE字段
         */
        @V(desc = "产品类型", notNull = false, notEmpty = true, maxSize = 3)
        private String acctType;

        /**
         * 终端流水号<br>
         * TERMINAL_SEQ_NO<br>
         * seqNo:35<br>
         * dataType:STRING(30)<br>
         * cons:
         */
        @V(desc = "终端流水号", notNull = false, maxSize = 30)
        private String terminalSeqNo;

        /**
         * 账号<br>
         * BASE_ACCT_NO<br>
         * seqNo:1<br>
         * dataType:STRING(50)<br>
         * cons:BASE_ACCT_NO见RB_ACCT的BASE_ACCT_NO字段
         */
        @V(desc = "账号", notNull = false, notEmpty = true, maxSize = 50)
        private String baseAcctNo;

        /**
         * 银联清算日期<br>
         * BU_SETTLEMENT_DATE<br>
         * seqNo:36<br>
         * dataType:STRING(8)<br>
         * cons:
         */
        @V(desc = "银联清算日期", notNull = false, maxSize = 8)
        private String buSettlementDate;

        /**
         * 开出票据帐户账号<br>
         * ISSUER_BASE_ACCT_NO<br>
         * seqNo:19<br>
         * dataType:STRING(50)<br>
         * cons:支票存入时需要
         */
        @V(desc = "开出票据帐户账号", notNull = false, maxSize = 50)
        private String issuerBaseAcctNo;

        /**
         * 凭证号码<br>
         * VOUCHER_NO<br>
         * seqNo:38<br>
         * dataType:STRING (16) <br>
         * cons:
         */
        @V(desc = "凭证号码", notNull = false, maxSize = 16)
        private String voucherNo;

        /**
         * 卡号<br>
         * CARD_NO<br>
         * seqNo:5<br>
         * dataType:STRING(20)<br>
         * cons:当CARD_PB_IND=’C’时 需要
         */
        @V(desc = "卡号", notNull = false, maxSize = 20)
        private String cardNo;

        /**
         * IC卡验证域<br>
         * ICC_DATA    <br>
         * seqNo:52<br>
         * dataType:STRING (500)<br>
         * cons:
         */
        @V(desc = "IC卡验证域", notNull = false, maxSize = 500)
        private String iccData    ;

        /**
         * 代办人证件类型<br>
         * COMMISSION_GLOBAL_ID_TYPE<br>
         * seqNo:28<br>
         * dataType:STRING(3)<br>
         * cons:
         */
        @V(desc = "代办人证件类型", notNull = false, maxSize = 3)
        private String commissionGlobalIdType;

        /**
         * 开出票据帐户币种<br>
         * ISSUER_CCY<br>
         * seqNo:20<br>
         * dataType:STRING(3)<br>
         * cons:支票存入时需要,见FM_CURRENCY的CCY字段
         */
        @V(desc = "开出票据帐户币种", notNull = false, maxSize = 3)
        private String issuerCcy;

        /**
         * 代办人证件号码<br>
         * COMMISSION_GLOBAL_ID<br>
         * seqNo:29<br>
         * dataType:STRING(35)<br>
         * cons:
         */
        @V(desc = "代办人证件号码", notNull = false, maxSize = 35)
        private String commissionGlobalId;

        /**
         * 生效日期<br>
         * EFFECT_DATE<br>
         * seqNo:6<br>
         * dataType:STRING(8)<br>
         * cons:
         */
        @V(desc = "生效日期", notNull = false, notEmpty = true, maxSize = 8)
        private String effectDate;

        /**
         * 终端编号<br>
         * TERMINAL_NO<br>
         * seqNo:34<br>
         * dataType:STRING(10)<br>
         * cons:
         */
        @V(desc = "终端编号", notNull = false, maxSize = 10)
        private String terminalNo;

        /**
         * 凭证类型<br>
         * CHANNEL_DOC_TYPE<br>
         * seqNo:44<br>
         * dataType:STRING(3)<br>
         * cons:
         */
        @V(desc = "凭证类型", notNull = false, maxSize = 3)
        private String channelDocType;

        /**
         * 凭证前缀<br>
         * CHANNEL_PREFIX<br>
         * seqNo:45<br>
         * dataType:STRING(10)<br>
         * cons:
         */
        @V(desc = "凭证前缀", notNull = false, maxSize = 10)
        private String channelPrefix;

        /**
         * 汇率<br>
         * EXCH_RATE<br>
         * seqNo:40<br>
         * dataType:BigDecimal(13,8)<br>
         * cons:
         */
        @V(desc = "汇率", notNull = false, maxSize = 13)
        private BigDecimal exchRate;

        /**
         * 票号<br>
         * REFERENCE<br>
         * seqNo:15<br>
         * dataType:STRING(50)<br>
         * cons:票据交易时需要
         */
        @V(desc = "票号", notNull = false, maxSize = 50)
        private String reference;

        /**
         * 收入方结售汇交易编码<br>
         * EXCHANGE_TRAN_CODE<br>
         * seqNo:48<br>
         * dataType:STRING(10)<br>
         * cons:
         */
        @V(desc = "收入方结售汇交易编码", notNull = false, maxSize = 10)
        private String exchangeTranCode;

        /**
         * 是否携带教育储蓄取款证明<br>
         * EVINCIVE_FLAG<br>
         * seqNo:42<br>
         * dataType:STRING(1)<br>
         * cons:取值范围：Y-携带；N-没有携带
         */
        @V(desc = "是否携带教育储蓄取款证明", notNull = false, maxSize = 1)
        private String evinciveFlag;

        /**
         * 支票页数<br>
         * NO_OF_LEAVES<br>
         * seqNo:25<br>
         * dataType:STRING(3)<br>
         * cons:支票或票据票交易时需要
         */
        @V(desc = "支票页数", notNull = false, maxSize = 3)
        private String noOfLeaves;

        /**
         * 交易事项(备注)<br>
         * TRAN_NOTE<br>
         * seqNo:33<br>
         * dataType:STRING(50)<br>
         * cons:
         */
        @V(desc = "交易事项(备注)", notNull = false, maxSize = 50)
        private String tranNote;

        /**
         * 交款单位<br>
         * PAYER<br>
         * seqNo:43<br>
         * dataType:STRING(100)<br>
         * cons:
         */
        @V(desc = "交款单位", notNull = false, maxSize = 100)
        private String payer;

        /**
         * 凭证号码<br>
         * CHANNEL_VOUCHER_NO<br>
         * seqNo:46<br>
         * dataType:STRING(50)<br>
         * cons:
         */
        @V(desc = "凭证号码", notNull = false, maxSize = 50)
        private String channelVoucherNo;

        /**
         * 存折携带标记<br>
         * PB_FLAG<br>
         * seqNo:9<br>
         * dataType:STRING(1)<br>
         * cons:取值范围：Y-携带；N-没有携带
         */
        @V(desc = "存折携带标记", notNull = false, notEmpty = true, maxSize = 1)
        private String pbFlag;

        /**
         * 卡折标志<br>
         * CARD_PB_IND<br>
         * seqNo:4<br>
         * dataType:STRING(1)<br>
         * cons:取值范围：C:卡；P:折
         */
        @V(desc = "卡折标志", notNull = false, notEmpty = true, maxSize = 1)
        private String cardPbInd;


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
         * 票据开出的银行分行号<br>
         * REFERENCE_BRANCH
         */
        public String getReferenceBranch() {
            return referenceBranch;
        }

        /**
         * 票据开出的银行分行号<br>
         * REFERENCE_BRANCH
         */
        public void setReferenceBranch(String referenceBranch) {
            this.referenceBranch = referenceBranch;
        }


        /**
         * 凭证类型<br>
         * DOC_TYPE
         */
        public String getDocType() {
            return docType;
        }

        /**
         * 凭证类型<br>
         * DOC_TYPE
         */
        public void setDocType(String docType) {
            this.docType = docType;
        }


        /**
         * 服务费信息<br>
         * SERV_DETAIL
         */
        public List<ServDetail> getServDetail() {
            return servDetail;
        }

        /**
         * 服务费信息<br>
         * SERV_DETAIL
         */
        public void setServDetail(List<ServDetail> servDetail) {
            this.servDetail = servDetail;
        }

        /**
         * @description 服务费信息
         * @version V1.0
         * @author Tim
         * @update 20150128 17:00:55
         */
        public static class ServDetail implements Serializable {

            private static final long serialVersionUID = 1L;

            public static class Test implements Serializable {

                private static final long serialVersionUID = 1L;

                private String t1;

                private String t2;

                private String t3;

                private String t4;

                public String getT1() {
                    return t1;
                }

                public void setT1(String t1) {
                    this.t1 = t1;
                }

                public String getT2() {
                    return t2;
                }

                public void setT2(String t2) {
                    this.t2 = t2;
                }

                public String getT3() {
                    return t3;
                }

                public void setT3(String t3) {
                    this.t3 = t3;
                }

                public String getT4() {
                    return t4;
                }

                public void setT4(String t4) {
                    this.t4 = t4;
                }
            }

            private List<Test> test;

            public List<Test> getTest() {
                return test;
            }

            public void setTest(List<Test> test) {
                this.test = test;
            }

            /**
             * 服务费合并标志<br>
             * SERV_CONS<br>
             * seqNo:12.6<br>
             * dataType:STRING(1)<br>
             * cons:
             */
            @V(desc = "服务费合并标志", notNull = false, maxSize = 1)
            private String servCons;

            /**
             * 收费币种<br>
             * SERV_CCY<br>
             * seqNo:12.3<br>
             * dataType:STRING(3)<br>
             * cons:输入见FM_CURRENCY的CCY字段
             */
            @V(desc = "收费币种", notNull = false, notEmpty = true, maxSize = 3)
            private String servCcy;

            /**
             * 日终标志<br>
             * SERV_BO_IND<br>
             * seqNo:12.5<br>
             * dataType:STRING(1)<br>
             * cons:取值范围：B-日终；O-在线
             */
            @V(desc = "日终标志", notNull = false, notEmpty = true, maxSize = 1)
            private String servBoInd;

            /**
             * 服务费金额<br>
             * SERV_AMT<br>
             * seqNo:12.4<br>
             * dataType:BigDecimal(17,2)<br>
             * cons:
             */
            @V(desc = "服务费金额", notNull = false, notEmpty = true, maxSize = 17)
            private BigDecimal servAmt;

            /**
             * 科目<br>
             * SERV_GL_CODE<br>
             * seqNo:12.8<br>
             * dataType:STRING(16)<br>
             * cons:输入见FM_GL_MAST_TBL的GL_CODE字段
             */
            @V(desc = "科目", notNull = false, notEmpty = true, maxSize = 16)
            private String servGlCode;

            /**
             * 收取方式<br>
             * CHARGE_TYPE<br>
             * seqNo:12.7<br>
             * dataType:STRING(1)<br>
             * cons:取值范围：C-现金收取；T-转帐收取
             */
            @V(desc = "收取方式", notNull = false, maxSize = 1)
            private String chargeType;

            /**
             * 服务费交易类型<br>
             * SERV_TRAN_TYPE<br>
             * seqNo:12.2<br>
             * dataType:STRING(4)<br>
             * cons:输入见RB_TRAN_DEF的TRAN_TYPE字段
             */
            @V(desc = "服务费交易类型", notNull = false, notEmpty = true, maxSize = 4)
            private String servTranType;

            /**
             * 利润中心<br>
             * PROFIT_CENTRE<br>
             * seqNo:12.9<br>
             * dataType:STRING(12)<br>
             * cons:见FM_PROFIT_CENTRE的PROFIT_CENTRE字段
             */
            @V(desc = "利润中心", notNull = false, notEmpty = true, maxSize = 12)
            private String profitCentre;

            /**
             * 服务费类型<br>
             * SERV_TYPE<br>
             * seqNo:12.1<br>
             * dataType:STRING(4)<br>
             * cons:输入见RB_SERV_TYPE的SERV_TYPE字段
             */
            @V(desc = "服务费类型", notNull = false, notEmpty = true, maxSize = 4)
            private String servType;


            /**
             * 服务费合并标志<br>
             * SERV_CONS
             */
            public String getServCons() {
                return servCons;
            }

            /**
             * 服务费合并标志<br>
             * SERV_CONS
             */
            public void setServCons(String servCons) {
                this.servCons = servCons;
            }


            /**
             * 收费币种<br>
             * SERV_CCY
             */
            public String getServCcy() {
                return servCcy;
            }

            /**
             * 收费币种<br>
             * SERV_CCY
             */
            public void setServCcy(String servCcy) {
                this.servCcy = servCcy;
            }


            /**
             * 日终标志<br>
             * SERV_BO_IND
             */
            public String getServBoInd() {
                return servBoInd;
            }

            /**
             * 日终标志<br>
             * SERV_BO_IND
             */
            public void setServBoInd(String servBoInd) {
                this.servBoInd = servBoInd;
            }


            /**
             * 服务费金额<br>
             * SERV_AMT
             */
            public BigDecimal getServAmt() {
                return servAmt;
            }

            /**
             * 服务费金额<br>
             * SERV_AMT
             */
            public void setServAmt(BigDecimal servAmt) {
                this.servAmt = servAmt;
            }


            /**
             * 科目<br>
             * SERV_GL_CODE
             */
            public String getServGlCode() {
                return servGlCode;
            }

            /**
             * 科目<br>
             * SERV_GL_CODE
             */
            public void setServGlCode(String servGlCode) {
                this.servGlCode = servGlCode;
            }


            /**
             * 收取方式<br>
             * CHARGE_TYPE
             */
            public String getChargeType() {
                return chargeType;
            }

            /**
             * 收取方式<br>
             * CHARGE_TYPE
             */
            public void setChargeType(String chargeType) {
                this.chargeType = chargeType;
            }


            /**
             * 服务费交易类型<br>
             * SERV_TRAN_TYPE
             */
            public String getServTranType() {
                return servTranType;
            }

            /**
             * 服务费交易类型<br>
             * SERV_TRAN_TYPE
             */
            public void setServTranType(String servTranType) {
                this.servTranType = servTranType;
            }


            /**
             * 利润中心<br>
             * PROFIT_CENTRE
             */
            public String getProfitCentre() {
                return profitCentre;
            }

            /**
             * 利润中心<br>
             * PROFIT_CENTRE
             */
            public void setProfitCentre(String profitCentre) {
                this.profitCentre = profitCentre;
            }


            /**
             * 服务费类型<br>
             * SERV_TYPE
             */
            public String getServType() {
                return servType;
            }

            /**
             * 服务费类型<br>
             * SERV_TYPE
             */
            public void setServType(String servType) {
                this.servType = servType;
            }



        }

        /**
         * 交易说明<br>
         * NARRATIVE
         */
        public String getNarrative() {
            return narrative;
        }

        /**
         * 交易说明<br>
         * NARRATIVE
         */
        public void setNarrative(String narrative) {
            this.narrative = narrative;
        }


        /**
         * 等值本币<br>
         * EQUIV_AMT
         */
        public BigDecimal getEquivAmt() {
            return equivAmt;
        }

        /**
         * 等值本币<br>
         * EQUIV_AMT
         */
        public void setEquivAmt(BigDecimal equivAmt) {
            this.equivAmt = equivAmt;
        }


        /**
         * <br>
         * OPTION_KW
         */
        public String getOptionKw() {
            return optionKw;
        }

        /**
         * <br>
         * OPTION_KW
         */
        public void setOptionKw(String optionKw) {
            this.optionKw = optionKw;
        }


        /**
         * 对账批次号<br>
         * CONTRAST_BAT_NO
         */
        public String getContrastBatNo() {
            return contrastBatNo;
        }

        /**
         * 对账批次号<br>
         * CONTRAST_BAT_NO
         */
        public void setContrastBatNo(String contrastBatNo) {
            this.contrastBatNo = contrastBatNo;
        }


        /**
         * 支取密码<br>
         * PASSWORD
         */
        public String getPassword() {
            return password;
        }

        /**
         * 支取密码<br>
         * PASSWORD
         */
        public void setPassword(String password) {
            this.password = password;
        }


        /**
         * 客户号<br>
         * CLIENT_NO
         */
        public String getClientNo() {
            return clientNo;
        }

        /**
         * 客户号<br>
         * CLIENT_NO
         */
        public void setClientNo(String clientNo) {
            this.clientNo = clientNo;
        }


        /**
         * 余额类型<br>
         * INPUT_BAL_TYPE
         */
        public String getInputBalType() {
            return inputBalType;
        }

        /**
         * 余额类型<br>
         * INPUT_BAL_TYPE
         */
        public void setInputBalType(String inputBalType) {
            this.inputBalType = inputBalType;
        }


        /**
         * 现金项目<br>
         * CASH_ITEM
         */
        public String getCashItem() {
            return cashItem;
        }

        /**
         * 现金项目<br>
         * CASH_ITEM
         */
        public void setCashItem(String cashItem) {
            this.cashItem = cashItem;
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
         * 代办人姓名<br>
         * COMMISSION_CLIENT_NAME
         */
        public String getCommissionClientName() {
            return commissionClientName;
        }

        /**
         * 代办人姓名<br>
         * COMMISSION_CLIENT_NAME
         */
        public void setCommissionClientName(String commissionClientName) {
            this.commissionClientName = commissionClientName;
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
         * 卡三磁道  <br>
         * TRACK3
         */
        public String getTrack3   () {
            return track3   ;
        }

        /**
         * 卡三磁道  <br>
         * TRACK3
         */
        public void setTrack3   (String track3   ) {
            this.track3    = track3   ;
        }


        /**
         * 交易场次<br>
         * SESSION_ID
         */
        public String getSessionId() {
            return sessionId;
        }

        /**
         * 交易场次<br>
         * SESSION_ID
         */
        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }


        /**
         * 票据开出的交易银行号<br>
         * REFERENCE_BANK
         */
        public String getReferenceBank() {
            return referenceBank;
        }

        /**
         * 票据开出的交易银行号<br>
         * REFERENCE_BANK
         */
        public void setReferenceBank(String referenceBank) {
            this.referenceBank = referenceBank;
        }


        /**
         * 开出票据帐户产品类型<br>
         * ISSUER_ACCT_TYPE
         */
        public String getIssuerAcctType() {
            return issuerAcctType;
        }

        /**
         * 开出票据帐户产品类型<br>
         * ISSUER_ACCT_TYPE
         */
        public void setIssuerAcctType(String issuerAcctType) {
            this.issuerAcctType = issuerAcctType;
        }


        /**
         * 票据/凭证前缀<br>
         * PREFIX
         */
        public String getPrefix() {
            return prefix;
        }

        /**
         * 票据/凭证前缀<br>
         * PREFIX
         */
        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }


        /**
         * 卡序列号  <br>
         * IC_CARD_SEQ
         */
        public String getIcCardSeq () {
            return icCardSeq ;
        }

        /**
         * 卡序列号  <br>
         * IC_CARD_SEQ
         */
        public void setIcCardSeq (String icCardSeq ) {
            this.icCardSeq  = icCardSeq ;
        }


        /**
         * 产品类型代码<br>
         * BIZ_TYPE
         */
        public String getBizType() {
            return bizType;
        }

        /**
         * 产品类型代码<br>
         * BIZ_TYPE
         */
        public void setBizType(String bizType) {
            this.bizType = bizType;
        }


        /**
         * 代办人客户号<br>
         * COMMISSION_CLIENT_NO
         */
        public String getCommissionClientNo() {
            return commissionClientNo;
        }

        /**
         * 代办人客户号<br>
         * COMMISSION_CLIENT_NO
         */
        public void setCommissionClientNo(String commissionClientNo) {
            this.commissionClientNo = commissionClientNo;
        }


        /**
         * 浮动天数<br>
         * FLOAT_DAYS
         */
        public String getFloatDays() {
            return floatDays;
        }

        /**
         * 浮动天数<br>
         * FLOAT_DAYS
         */
        public void setFloatDays(String floatDays) {
            this.floatDays = floatDays;
        }


        /**
         * 卡二磁道  <br>
         * TRACK2
         */
        public String getTrack2      () {
            return track2      ;
        }

        /**
         * 卡二磁道  <br>
         * TRACK2
         */
        public void setTrack2      (String track2      ) {
            this.track2       = track2      ;
        }


        /**
         * 子账号/卡号<br>
         * ACCT_NO
         */
        public String getAcctNo() {
            return acctNo;
        }

        /**
         * 子账号/卡号<br>
         * ACCT_NO
         */
        public void setAcctNo(String acctNo) {
            this.acctNo = acctNo;
        }


        /**
         * 原渠道流水号<br>
         * ORIG_CHANNAL_SEQ_NO
         */
        public String getOrigChannalSeqNo() {
            return origChannalSeqNo;
        }

        /**
         * 原渠道流水号<br>
         * ORIG_CHANNAL_SEQ_NO
         */
        public void setOrigChannalSeqNo(String origChannalSeqNo) {
            this.origChannalSeqNo = origChannalSeqNo;
        }


        /**
         * 入账日期<br>
         * POST_DATE
         */
        public String getPostDate() {
            return postDate;
        }

        /**
         * 入账日期<br>
         * POST_DATE
         */
        public void setPostDate(String postDate) {
            this.postDate = postDate;
        }


        /**
         * 检查标志  <br>
         * CHECK_OPTION
         */
        public String getCheckOption() {
            return checkOption;
        }

        /**
         * 检查标志  <br>
         * CHECK_OPTION
         */
        public void setCheckOption(String checkOption) {
            this.checkOption = checkOption;
        }


        /**
         * 产品类型<br>
         * ACCT_TYPE
         */
        public String getAcctType() {
            return acctType;
        }

        /**
         * 产品类型<br>
         * ACCT_TYPE
         */
        public void setAcctType(String acctType) {
            this.acctType = acctType;
        }


        /**
         * 终端流水号<br>
         * TERMINAL_SEQ_NO
         */
        public String getTerminalSeqNo() {
            return terminalSeqNo;
        }

        /**
         * 终端流水号<br>
         * TERMINAL_SEQ_NO
         */
        public void setTerminalSeqNo(String terminalSeqNo) {
            this.terminalSeqNo = terminalSeqNo;
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
         * 银联清算日期<br>
         * BU_SETTLEMENT_DATE
         */
        public String getBuSettlementDate() {
            return buSettlementDate;
        }

        /**
         * 银联清算日期<br>
         * BU_SETTLEMENT_DATE
         */
        public void setBuSettlementDate(String buSettlementDate) {
            this.buSettlementDate = buSettlementDate;
        }


        /**
         * 开出票据帐户账号<br>
         * ISSUER_BASE_ACCT_NO
         */
        public String getIssuerBaseAcctNo() {
            return issuerBaseAcctNo;
        }

        /**
         * 开出票据帐户账号<br>
         * ISSUER_BASE_ACCT_NO
         */
        public void setIssuerBaseAcctNo(String issuerBaseAcctNo) {
            this.issuerBaseAcctNo = issuerBaseAcctNo;
        }


        /**
         * 凭证号码<br>
         * VOUCHER_NO
         */
        public String getVoucherNo() {
            return voucherNo;
        }

        /**
         * 凭证号码<br>
         * VOUCHER_NO
         */
        public void setVoucherNo(String voucherNo) {
            this.voucherNo = voucherNo;
        }


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
         * IC卡验证域<br>
         * ICC_DATA
         */
        public String getIccData    () {
            return iccData    ;
        }

        /**
         * IC卡验证域<br>
         * ICC_DATA
         */
        public void setIccData    (String iccData    ) {
            this.iccData     = iccData    ;
        }


        /**
         * 代办人证件类型<br>
         * COMMISSION_GLOBAL_ID_TYPE
         */
        public String getCommissionGlobalIdType() {
            return commissionGlobalIdType;
        }

        /**
         * 代办人证件类型<br>
         * COMMISSION_GLOBAL_ID_TYPE
         */
        public void setCommissionGlobalIdType(String commissionGlobalIdType) {
            this.commissionGlobalIdType = commissionGlobalIdType;
        }


        /**
         * 开出票据帐户币种<br>
         * ISSUER_CCY
         */
        public String getIssuerCcy() {
            return issuerCcy;
        }

        /**
         * 开出票据帐户币种<br>
         * ISSUER_CCY
         */
        public void setIssuerCcy(String issuerCcy) {
            this.issuerCcy = issuerCcy;
        }


        /**
         * 代办人证件号码<br>
         * COMMISSION_GLOBAL_ID
         */
        public String getCommissionGlobalId() {
            return commissionGlobalId;
        }

        /**
         * 代办人证件号码<br>
         * COMMISSION_GLOBAL_ID
         */
        public void setCommissionGlobalId(String commissionGlobalId) {
            this.commissionGlobalId = commissionGlobalId;
        }


        /**
         * 生效日期<br>
         * EFFECT_DATE
         */
        public String getEffectDate() {
            return effectDate;
        }

        /**
         * 生效日期<br>
         * EFFECT_DATE
         */
        public void setEffectDate(String effectDate) {
            this.effectDate = effectDate;
        }


        /**
         * 终端编号<br>
         * TERMINAL_NO
         */
        public String getTerminalNo() {
            return terminalNo;
        }

        /**
         * 终端编号<br>
         * TERMINAL_NO
         */
        public void setTerminalNo(String terminalNo) {
            this.terminalNo = terminalNo;
        }


        /**
         * 凭证类型<br>
         * CHANNEL_DOC_TYPE
         */
        public String getChannelDocType() {
            return channelDocType;
        }

        /**
         * 凭证类型<br>
         * CHANNEL_DOC_TYPE
         */
        public void setChannelDocType(String channelDocType) {
            this.channelDocType = channelDocType;
        }


        /**
         * 凭证前缀<br>
         * CHANNEL_PREFIX
         */
        public String getChannelPrefix() {
            return channelPrefix;
        }

        /**
         * 凭证前缀<br>
         * CHANNEL_PREFIX
         */
        public void setChannelPrefix(String channelPrefix) {
            this.channelPrefix = channelPrefix;
        }


        /**
         * 汇率<br>
         * EXCH_RATE
         */
        public BigDecimal getExchRate() {
            return exchRate;
        }

        /**
         * 汇率<br>
         * EXCH_RATE
         */
        public void setExchRate(BigDecimal exchRate) {
            this.exchRate = exchRate;
        }


        /**
         * 票号<br>
         * REFERENCE
         */
        public String getReference() {
            return reference;
        }

        /**
         * 票号<br>
         * REFERENCE
         */
        public void setReference(String reference) {
            this.reference = reference;
        }


        /**
         * 收入方结售汇交易编码<br>
         * EXCHANGE_TRAN_CODE
         */
        public String getExchangeTranCode() {
            return exchangeTranCode;
        }

        /**
         * 收入方结售汇交易编码<br>
         * EXCHANGE_TRAN_CODE
         */
        public void setExchangeTranCode(String exchangeTranCode) {
            this.exchangeTranCode = exchangeTranCode;
        }


        /**
         * 是否携带教育储蓄取款证明<br>
         * EVINCIVE_FLAG
         */
        public String getEvinciveFlag() {
            return evinciveFlag;
        }

        /**
         * 是否携带教育储蓄取款证明<br>
         * EVINCIVE_FLAG
         */
        public void setEvinciveFlag(String evinciveFlag) {
            this.evinciveFlag = evinciveFlag;
        }


        /**
         * 支票页数<br>
         * NO_OF_LEAVES
         */
        public String getNoOfLeaves() {
            return noOfLeaves;
        }

        /**
         * 支票页数<br>
         * NO_OF_LEAVES
         */
        public void setNoOfLeaves(String noOfLeaves) {
            this.noOfLeaves = noOfLeaves;
        }


        /**
         * 交易事项(备注)<br>
         * TRAN_NOTE
         */
        public String getTranNote() {
            return tranNote;
        }

        /**
         * 交易事项(备注)<br>
         * TRAN_NOTE
         */
        public void setTranNote(String tranNote) {
            this.tranNote = tranNote;
        }


        /**
         * 交款单位<br>
         * PAYER
         */
        public String getPayer() {
            return payer;
        }

        /**
         * 交款单位<br>
         * PAYER
         */
        public void setPayer(String payer) {
            this.payer = payer;
        }


        /**
         * 凭证号码<br>
         * CHANNEL_VOUCHER_NO
         */
        public String getChannelVoucherNo() {
            return channelVoucherNo;
        }

        /**
         * 凭证号码<br>
         * CHANNEL_VOUCHER_NO
         */
        public void setChannelVoucherNo(String channelVoucherNo) {
            this.channelVoucherNo = channelVoucherNo;
        }


        /**
         * 存折携带标记<br>
         * PB_FLAG
         */
        public String getPbFlag() {
            return pbFlag;
        }

        /**
         * 存折携带标记<br>
         * PB_FLAG
         */
        public void setPbFlag(String pbFlag) {
            this.pbFlag = pbFlag;
        }


        /**
         * 卡折标志<br>
         * CARD_PB_IND
         */
        public String getCardPbInd() {
            return cardPbInd;
        }

        /**
         * 卡折标志<br>
         * CARD_PB_IND
         */
        public void setCardPbInd(String cardPbInd) {
            this.cardPbInd = cardPbInd;
        }


    }
}

