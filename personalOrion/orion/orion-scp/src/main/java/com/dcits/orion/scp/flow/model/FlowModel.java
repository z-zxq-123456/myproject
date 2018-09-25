package com.dcits.orion.scp.flow.model;
import java.util.ArrayList;
import java.util.List;

/**
 * @author  qiqingshan
 */
public class FlowModel {

    private static final long serialVersionUID = 1296233669450523352L;

    /**
     * 节点元素集合
     */
    private List<Object> nodes = new ArrayList<>();

    public List<Object> getNodes() {
        return nodes;
    }

    public int getInit_num() {
        return init_num;
    }

    public void setInit_num(int init_num) {
        this.init_num = init_num;
    }

    //存放节点总数
    private int init_num;

    public String getLog_flag() {
        return log_flag;
    }

    public void setLog_flag(String log_flag) {
        this.log_flag = log_flag;
    }

    //是否记录流程日志
    private String log_flag;

    public void setNodes(List<Object> nodes) {
        this.nodes = nodes;
    }
    private String flowId;
    /**
     * This field is FW_FLOW_DEF.FLOW_NAME 流程名
     */
    private String flowName;
    /**
     * This field is FW_FLOW_DEF.CREATOR 创建人
     */
    private String createTime;
    /**
     * This field is FW_FLOW_DEF.EXEC_TYPE 流程执行类型
     */
    private String creator;
    /**
     * This field is FW_FLOW_DEF.SCRIPT 流程脚本
     */
    private String execType;
    /**
     * This field is FW_FLOW_DEF.REVERSAL_TYPE 流程ID
     */
    private String resumeType;
    /**
     * This field is FW_FLOW_DEF.FLOW_ID 流程ID
     */
    private String script;
    /**
     * This field is FW_FLOW_DEF.FLOW_ID 流程ID
     */
    private String reversalType;
    /**
     * This field is FW_FLOW_DEF.REVERSAL_TYPE 冲正类型
     */
    private int timeOut;
    /**
     * This field is FW_FLOW_DEF.FLOW_ID 流程ID
     */
    private String timeOutDeal;
    /**
     * This field is FW_FLOW_DEF.FLOW_ID 流程ID
     */
    private String keyFields;

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getExecType() {
        return execType;
    }

    public void setExecType(String execType) {
        this.execType = execType;
    }

    public String getResumeType() {
        return resumeType;
    }

    public void setResumeType(String resumeType) {
        this.resumeType = resumeType;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getReversalType() {
        return reversalType;
    }

    public void setReversalType(String reversalType) {
        this.reversalType = reversalType;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public String getTimeOutDeal() {
        return timeOutDeal;
    }

    public void setTimeOutDeal(String timeOutDeal) {
        this.timeOutDeal = timeOutDeal;
    }

    public String getKeyFields() {
        return keyFields;
    }

    public void setKeyFields(String keyFields) {
        this.keyFields = keyFields;
    }

}
