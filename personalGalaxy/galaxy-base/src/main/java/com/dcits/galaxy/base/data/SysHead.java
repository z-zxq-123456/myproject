/**
 * <p>Title: SysHead.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 20150127 14:19:15
 * @version V1.0
 */
package com.dcits.galaxy.base.data;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.base.validate.V;

/**
 * @author Tim
 * @version V1.0
 * @description MBSD公共系统头
 * @update 20150127 14:19:15
 */
public class SysHead extends AbstractBean implements ISysHead {

    /**
     * @fields serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 授权柜员标识<br>
     * AUTH_USER_ID<br>
     * seqNo:17<br>
     * dataType:STRING(30)<br>
     * cons:
     */
    @V(desc = "授权柜员标识", notNull = true, restraint = "authFlag=O")
    private String authUserId;

    /**
     * 交易日期<br>
     * TRAN_DATE<br>
     * seqNo:10<br>
     * dataType:STRING(8)<br>
     * cons:交易发起日期字符串，YYYYMMDD，无掩码
     */
    @V(desc = "交易日期", notNull = true)
    private String tranDate;

    /**
     * 渠道流水号<br>
     * SOURCE_TYPE + TRAN_DATE + SEQ_NO保证唯一<br>
     * SEQ_NO<br>
     * seqNo:15<br>
     * dataType:STRING(50)<br>
     * cons:数字
     */
    @V(desc = "渠道流水号")
    private String seqNo;

    /**
     * 交易模式<br>
     * TRAN_MODE<br>
     * seqNo:6<br>
     * dataType:STRING(10)<br>
     * cons:取值范围：<br>
     * ONLINE－联机处理<br>
     * ASYNC－异步处理<br>
     */
    @V(desc = "交易模式", notNull = true, in = "ONLINE,ASYNC")
    private String tranMode;

    /**
     * 源节点编号<br>
     * SOURCE_BRANCH_NO<br>
     * seqNo:22<br>
     * dataType:STRING(6)<br>
     * cons:
     */
    @V(desc = "源节点编号")
    private String sourceBranchNo;

    /**
     * 报文代码<br>
     * MESSAGE_CODE<br>
     * seqNo:26<br>
     * dataType:STRING(6)<br>
     * cons:对于非金融交易的子编码，必输
     */
    @V(desc = "报文代码", notNull = true)
    private String messageCode;

    /**
     * 交易录入柜员标识<br>
     * APPR_USER_ID<br>
     * seqNo:20<br>
     * dataType:STRING(30)<br>
     * cons:
     */
    @V(desc = "交易录入柜员标识")
    private String apprUserId;

    /**
     * 交易屏幕标识<br>
     * PROGRAM_ID<br>
     * seqNo:16<br>
     * dataType:STRING(8)<br>
     * cons:
     */
    @V(desc = "交易屏幕标识", notNull = true, restraint = "sourceType=MT")
    private String programId;

    /**
     * 线程编号<br>
     * THREAD_NO<br>
     * seqNo:1<br>
     * dataType:STRING(512)<br>
     * cons:
     */
    @V(desc = "线程编号")
    private String threadNo;

    /**
     * 目标节点编号<br>
     * DEST_BRANCH_NO<br>
     * seqNo:23<br>
     * dataType:STRING(6)<br>
     * cons:
     */
    @V(desc = "目标节点编号")
    private String destBranchNo;

    /**
     * 分支行标识<br>
     * BRANCH_ID<br>
     * seqNo:8<br>
     * dataType:STRING(20)<br>
     * cons:柜员所属的分支行
     */
    @V(desc = "分支行标识", notNull = true)
    private String branchId;

    /**
     * 柜员标识<br>
     * USER_ID<br>
     * seqNo:9<br>
     * dataType:STRING(30)<br>
     * cons:
     */
    @V(desc = "柜员标识", notNull = true)
    private String userId;

    /**
     * 传输密押<br>
     * MAC_VALUE<br>
     * seqNo:28<br>
     * dataType:STRING(50)<br>
     * cons:
     */
    @V(desc = "传输密押")
    private String macValue;

    /**
     * 复核标志<br>
     * APPR_FLAG<br>
     * seqNo:21<br>
     * dataType:STRING(1)<br>
     * cons:取值范围：<br>
     * E－交易录入<br>
     * A－交易批准<br>
     */
    @V(desc = "复核标志", in = "E,A")
    private String apprFlag;

    /**
     * 渠道类型<br>
     * SOURCE_TYPE<br>
     * seqNo:7<br>
     * dataType:STRING(10)<br>
     * cons:
     */
    @V(desc = "渠道类型", notNull = true)
    private String sourceType;

    /**
     * 操作员语言<br>
     * USER_LANG<br>
     * seqNo:14<br>
     * dataType:STRING(20)<br>
     * cons:取值范围：<br>
     * CHINESE－中文；<br>
     * AMERICAN/ENGLISH－英文；<br>
     */
    @V(desc = "操作员语言", notNull = true, in = "CHINESE,AMERICAN/ENGLISH")
    private String userLang;

    /**
     * 文件绝对路径<br>
     * FILE_PATH<br>
     * seqNo:27<br>
     * dataType:STRING(512)<br>
     * cons:文件交易服务时需要且必输，多个文件时用“;”分割
     */
    @V(desc = "文件绝对路径")
    private String filePath;

    /**
     * 授权标志<br>
     * AUTH_FLAG<br>
     * seqNo:19<br>
     * dataType:STRING(1)<br>
     * cons:取值范围：<br>
     * N－未授权<br>
     * M－授权通过<br>
     * O－确认通过<br>
     */
    @V(desc = "授权标志", in = "N,M,O")
    private String authFlag;

    /**
     * 交易时间<br>
     * TRAN_TIMESTAMP<br>
     * seqNo:11<br>
     * dataType:STRING(9)<br>
     * cons:交易发起时间字符串，HHMMSSNNN，无掩码
     */
    @V(desc = "交易时间", notNull = true)
    private String tranTimestamp;

    /**
     * 报文类型<br>
     * MESSAGE_TYPE<br>
     * seqNo:25<br>
     * dataType:STRING(4)<br>
     * cons:
     */
    @V(desc = "报文类型", notNull = true)
    private String messageType;

    /**
     * 服务代码<br>
     * SERVICE_CODE<br>
     * seqNo:2<br>
     * dataType:STRING(30)<br>
     * cons:
     */
    @V(desc = "服务代码", notNull = true)
    private String serviceCode;

    /**
     * 请求方系统ID<br>
     * SERVICE_CODE<br>
     * seqNo:2<br>
     * dataType:STRING(30)<br>
     * cons:
     */
    @V(desc = "系统ID")
    private String systemId;

    /**
     * 法人
     */
    @V(desc = "法人")
    private String company;

    /**
     * 业务参考号
     * 交易日期+10顺序号,顺序号每次+1
     */
    private String reference;

    /**
     * 系统运行日期
     */
    private String runDate;

    /**
     * SCENE_ID 服务场景
     */
    private String sceneId;

    /**
     * 授权柜员标识<br>
     * AUTH_USER_ID
     */
    public String getAuthUserId() {
        return authUserId;
    }

    /**
     * 授权柜员标识<br>
     * AUTH_USER_ID
     */
    public void setAuthUserId(String authUserId) {
        this.authUserId = authUserId;
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
     * 渠道流水号<br>
     * SEQ_NO
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 渠道流水号<br>
     * SEQ_NO
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    /**
     * 交易模式<br>
     * TRAN_MODE
     */
    public String getTranMode() {
        return tranMode;
    }

    /**
     * 交易模式<br>
     * TRAN_MODE
     */
    public void setTranMode(String tranMode) {
        this.tranMode = tranMode;
    }

    /**
     * 源节点编号<br>
     * SOURCE_BRANCH_NO
     */
    public String getSourceBranchNo() {
        return sourceBranchNo;
    }

    /**
     * 源节点编号<br>
     * SOURCE_BRANCH_NO
     */
    public void setSourceBranchNo(String sourceBranchNo) {
        this.sourceBranchNo = sourceBranchNo;
    }

    /**
     * 报文代码<br>
     * MESSAGE_CODE
     */
    public String getMessageCode() {
        return messageCode;
    }

    /**
     * 报文代码<br>
     * MESSAGE_CODE
     */
    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    /**
     * 交易录入柜员标识<br>
     * APPR_USER_ID
     */
    public String getApprUserId() {
        return apprUserId;
    }

    /**
     * 交易录入柜员标识<br>
     * APPR_USER_ID
     */
    public void setApprUserId(String apprUserId) {
        this.apprUserId = apprUserId;
    }

    /**
     * 交易屏幕标识<br>
     * PROGRAM_ID
     */
    public String getProgramId() {
        return programId;
    }

    /**
     * 交易屏幕标识<br>
     * PROGRAM_ID
     */
    public void setProgramId(String programId) {
        this.programId = programId;
    }

    /**
     * 线程编号<br>
     * THREAD_NO
     */
    public String getThreadNo() {
        return threadNo;
    }

    /**
     * 线程编号<br>
     * THREAD_NO
     */
    public void setThreadNo(String threadNo) {
        this.threadNo = threadNo;
    }

    /**
     * 目标节点编号<br>
     * DEST_BRANCH_NO
     */
    public String getDestBranchNo() {
        return destBranchNo;
    }

    /**
     * 目标节点编号<br>
     * DEST_BRANCH_NO
     */
    public void setDestBranchNo(String destBranchNo) {
        this.destBranchNo = destBranchNo;
    }

    /**
     * 分支行标识<br>
     * BRANCH_ID
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     * 分支行标识<br>
     * BRANCH_ID
     */
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    /**
     * 柜员标识<br>
     * USER_ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 柜员标识<br>
     * USER_ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 传输密押<br>
     * MAC_VALUE
     */
    public String getMacValue() {
        return macValue;
    }

    /**
     * 传输密押<br>
     * MAC_VALUE
     */
    public void setMacValue(String macValue) {
        this.macValue = macValue;
    }

    /**
     * 复核标志<br>
     * APPR_FLAG
     */
    public String getApprFlag() {
        return apprFlag;
    }

    /**
     * 复核标志<br>
     * APPR_FLAG
     */
    public void setApprFlag(String apprFlag) {
        this.apprFlag = apprFlag;
    }

    /**
     * 渠道类型<br>
     * SOURCE_TYPE
     */
    public String getSourceType() {
        return sourceType;
    }

    /**
     * 渠道类型<br>
     * SOURCE_TYPE
     */
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    /**
     * 操作员语言<br>
     * USER_LANG
     */
    public String getUserLang() {
        return userLang;
    }

    /**
     * 操作员语言<br>
     * USER_LANG
     */
    public void setUserLang(String userLang) {
        this.userLang = userLang;
    }

    /**
     * 文件绝对路径<br>
     * FILE_PATH
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 文件绝对路径<br>
     * FILE_PATH
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 授权标志<br>
     * AUTH_FLAG
     */
    public String getAuthFlag() {
        return authFlag;
    }

    /**
     * 授权标志<br>
     * AUTH_FLAG
     */
    public void setAuthFlag(String authFlag) {
        this.authFlag = authFlag;
    }

    /**
     * 交易时间<br>
     * TRAN_TIMESTAMP
     */
    public String getTranTimestamp() {
        return tranTimestamp;
    }

    /**
     * 交易时间<br>
     * TRAN_TIMESTAMP
     */
    public void setTranTimestamp(String tranTimestamp) {
        this.tranTimestamp = tranTimestamp;
    }

    /**
     * 报文类型<br>
     * MESSAGE_TYPE
     */
    public String getMessageType() {
        return messageType;
    }

    /**
     * 报文类型<br>
     * MESSAGE_TYPE
     */
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    /**
     * 服务代码<br>
     * SERVICE_CODE
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     * 服务代码<br>
     * SERVICE_CODE
     */
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    /**
     * 业务参考号
     *
     * @return
     */
    public String getReference() {
        return reference;
    }

    /**
     * 业务参考号
     *
     * @param reference
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * 系统运行日期
     *
     * @return
     */
    public String getRunDate() {
        return runDate;
    }

    /**
     * 系统运行日期
     *
     * @param runDate
     */
    public void setRunDate(String runDate) {
        this.runDate = runDate;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    /**
     * 清除下送SysHead不不必要的返回字段
     */
    @Override
    public void cleanSysHead() {
        setApprFlag(null);
        setApprUserId(null);
        setAuthFlag(null);
        //柜面缺陷，如果上送有AuthUserId，原值下送。modify by Tim 20170421
        //setAuthUserId(null);
        setBranchId(null);
        setProgramId(null);
        setTranMode(null);
        setUserId(null);
        setUserLang(null);
        if (StringUtils.isNotEmpty(getSourceBranchNo())
                && StringUtils.isNotEmpty(getDestBranchNo())) {
            String source = getSourceBranchNo();
            String dest = getDestBranchNo();
            setSourceBranchNo(dest);
            setDestBranchNo(source);
        }
    }
}
