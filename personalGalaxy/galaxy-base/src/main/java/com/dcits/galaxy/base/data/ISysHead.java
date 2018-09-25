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

import com.dcits.galaxy.base.bean.IBean;

/**
 * @author Tim
 * @version V1.0
 * @description MBSD公共系统头
 * @update 20150127 14:19:15
 */
public interface ISysHead extends IBean {

    /**
     * 授权柜员标识<br>
     * AUTH_USER_ID
     */
    String getAuthUserId();

    /**
     * 授权柜员标识<br>
     * AUTH_USER_ID
     */
    void setAuthUserId(String authUserId);

    /**
     * 交易日期<br>
     * TRAN_DATE
     */
    String getTranDate();

    /**
     * 交易日期<br>
     * TRAN_DATE
     */
    void setTranDate(String tranDate);

    /**
     * 渠道流水号<br>
     * SEQ_NO
     */
    String getSeqNo();

    /**
     * 渠道流水号<br>
     * SEQ_NO
     */
    void setSeqNo(String seqNo);

    /**
     * 交易模式<br>
     * TRAN_MODE
     */
    String getTranMode();

    /**
     * 交易模式<br>
     * TRAN_MODE
     */
    void setTranMode(String tranMode);

    /**
     * 源节点编号<br>
     * SOURCE_BRANCH_NO
     */
    String getSourceBranchNo();

    /**
     * 源节点编号<br>
     * SOURCE_BRANCH_NO
     */
    void setSourceBranchNo(String sourceBranchNo);

    /**
     * 报文代码<br>
     * MESSAGE_CODE
     */
    String getMessageCode();

    /**
     * 报文代码<br>
     * MESSAGE_CODE
     */
    void setMessageCode(String messageCode);

    /**
     * 交易录入柜员标识<br>
     * APPR_USER_ID
     */
    String getApprUserId();

    /**
     * 交易录入柜员标识<br>
     * APPR_USER_ID
     */
    void setApprUserId(String apprUserId);

    /**
     * 交易屏幕标识<br>
     * PROGRAM_ID
     */
    String getProgramId();

    /**
     * 交易屏幕标识<br>
     * PROGRAM_ID
     */
    void setProgramId(String programId);

    /**
     * 线程编号<br>
     * THREAD_NO
     */
    String getThreadNo();

    /**
     * 线程编号<br>
     * THREAD_NO
     */
    void setThreadNo(String threadNo);

    /**
     * 目标节点编号<br>
     * DEST_BRANCH_NO
     */
    String getDestBranchNo();

    /**
     * 目标节点编号<br>
     * DEST_BRANCH_NO
     */
    void setDestBranchNo(String destBranchNo);

    /**
     * 分支行标识<br>
     * BRANCH_ID
     */
    String getBranchId();

    /**
     * 分支行标识<br>
     * BRANCH_ID
     */
    void setBranchId(String branchId);

    /**
     * 柜员标识<br>
     * USER_ID
     */
    String getUserId();

    /**
     * 柜员标识<br>
     * USER_ID
     */
    void setUserId(String userId);

    /**
     * 传输密押<br>
     * MAC_VALUE
     */
    String getMacValue();

    /**
     * 传输密押<br>
     * MAC_VALUE
     */
    void setMacValue(String macValue);

    /**
     * 复核标志<br>
     * APPR_FLAG
     */
    String getApprFlag();

    /**
     * 复核标志<br>
     * APPR_FLAG
     */
    void setApprFlag(String apprFlag);

    /**
     * 渠道类型<br>
     * SOURCE_TYPE
     */
    String getSourceType();

    /**
     * 渠道类型<br>
     * SOURCE_TYPE
     */
    void setSourceType(String sourceType);

    /**
     * 操作员语言<br>
     * USER_LANG
     */
    String getUserLang();

    /**
     * 操作员语言<br>
     * USER_LANG
     */
    void setUserLang(String userLang);

    /**
     * 文件绝对路径<br>
     * FILE_PATH
     */
    String getFilePath();

    /**
     * 文件绝对路径<br>
     * FILE_PATH
     */
    void setFilePath(String filePath);

    /**
     * 授权标志<br>
     * AUTH_FLAG
     */
    String getAuthFlag();

    /**
     * 授权标志<br>
     * AUTH_FLAG
     */
    void setAuthFlag(String authFlag);

    /**
     * 交易时间<br>
     * TRAN_TIMESTAMP
     */
    String getTranTimestamp();

    /**
     * 交易时间<br>
     * TRAN_TIMESTAMP
     */
    void setTranTimestamp(String tranTimestamp);

    /**
     * 报文类型<br>
     * MESSAGE_TYPE
     */
    String getMessageType();

    /**
     * 报文类型<br>
     * MESSAGE_TYPE
     */
    void setMessageType(String messageType);

    /**
     * 服务代码<br>
     * SERVICE_CODE
     */
    String getServiceCode();

    /**
     * 服务代码<br>
     * SERVICE_CODE
     */
    void setServiceCode(String serviceCode);

    /**
     * 业务参考号
     *
     * @return
     */
    String getReference();

    /**
     * 业务参考号
     *
     * @param reference
     */
    void setReference(String reference);

    /**
     * 系统运行日期
     *
     * @return
     */
    String getRunDate();

    /**
     * 系统运行日期
     *
     * @param runDate
     */
    void setRunDate(String runDate);

    /**
     * 系统ID
     *
     * @return
     */
    String getSystemId();

    /**
     * 系统ID
     *
     * @param systemId
     */
    void setSystemId(String systemId);

    /**
     * 法人
     *
     * @return
     */
    String getCompany();

    /**
     * 法人
     *
     * @param company
     */
    void setCompany(String company);

    /**
     * 为了兼容ESB，服务场景
     *
     * @return
     */
    String getSceneId();

    /**
     * 为了兼容ESB，服务场景
     *
     * @param sceneId
     */
    void setSceneId(String sceneId);

    /**
     * 清除下送SysHead不不必要的返回字段
     */
    void cleanSysHead();
}
