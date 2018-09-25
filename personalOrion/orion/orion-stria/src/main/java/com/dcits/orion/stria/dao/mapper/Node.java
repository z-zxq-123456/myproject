package com.dcits.orion.stria.dao.mapper;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Tim on 2015/5/18.
 */
public class Node implements Serializable {

    private static final long serialVersionUID = -352490173598349352L;
    /**
     * This field is stria_flow_nodes.flowid 流程ID
     */
    private String flowid;


    /**
     * This field is stria_flow_nodes.id 节点ID
     */
    private String id;


    /**
     * This field is stria_flow_nodes.name 节点描述
     */
    private String name;


    /**
     * This field is stria_flow_nodes.type 节点类型
     */
    private String type;


    /**
     * This field is stria_flow_nodes.canvasTop 据上边界偏移量
     */
    private BigDecimal canvasTop;


    /**
     * This field is stria_flow_nodes.canvasLeft 据左边界偏移量
     */
    private BigDecimal canvasLeft;


    /**
     * This field is stria_flow_nodes.divWidth 节点图形宽度
     */
    private BigDecimal divWidth;


    /**
     * This field is stria_flow_nodes.divHeight 节点图形高度
     */
    private BigDecimal divHeight;


    /**
     * This field is stria_flow_nodes.source_type 渠道
     */
    private String sourceType;


    /**
     * This field is stria_flow_nodes.service_type 服务类型
     */
    private String serviceType;


    /**
     * This field is stria_flow_nodes.clazz 服务执行class
     */
    private String clazz;

    /**
     * This field is stria_flow_nodes.method_name 服务执行方法
     */
    private String methodName;

    /**
     * This field is stria_flow_nodes.args 参数取值表达式
     */
    private String args;


    /**
     * This field is stria_flow_nodes.args_clazz 参数class
     */
    private String argsClazz;


    /**
     * This field is stria_flow_nodes.var 输出别名定义
     */
    private String var;

    private String subCondition;
    /**
     * This field is STRIA_FLOW_NODES.REQUEST
     */
    private String request;
    /**
     * This field is STRIA_FLOW_NODES.SERVICE_CODE
     */
    private String serviceCode;
    /**
     * This field is STRIA_FLOW_NODES.MESSAGE_TYPE
     */
    private String messageType;
    /**
     * This field is STRIA_FLOW_NODES.MESSAGE_CODE
     */
    private String messageCode;

    /**
     * This field is STRIA_FLOW_NODES.EVENT_ID
     */
    private String eventId;

    /**
     * @return the value of stria_flow_nodes.flowid 流程ID
     */
    public String getFlowid() {
        return flowid;
    }

    /**
     * @param flowid the value for stria_flow_nodes.flowid 流程ID
     */
    public void setFlowid(String flowid) {
        this.flowid = flowid == null ? null : flowid.trim();
    }

    /**
     * @return the value of stria_flow_nodes.id 节点ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the value for stria_flow_nodes.id 节点ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * @return the value of stria_flow_nodes.name 节点描述
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the value for stria_flow_nodes.name 节点描述
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return the value of stria_flow_nodes.type 节点类型
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the value for stria_flow_nodes.type 节点类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * @return the value of stria_flow_nodes.canvasTop 据上边界偏移量
     */
    public BigDecimal getCanvasTop() {
        return canvasTop;
    }

    /**
     * @param canvasTop the value for stria_flow_nodes.canvasTop 据上边界偏移量
     */
    public void setCanvasTop(BigDecimal canvasTop) {
        this.canvasTop = canvasTop;
    }

    /**
     * @return the value of stria_flow_nodes.left 据左边界偏移量
     */
    public BigDecimal getCanvasLeft() {
        return canvasLeft;
    }

    /**
     * @param canvasLeft the value for stria_flow_nodes.canvasLeft 据左边界偏移量
     */
    public void setCanvasLeft(BigDecimal canvasLeft) {
        this.canvasLeft = canvasLeft;
    }

    /**
     * @return the value of stria_flow_nodes.divWidth 节点图形宽度
     */
    public BigDecimal getDivWidth() {
        return divWidth;
    }

    /**
     * @param divWidth the value for stria_flow_nodes.divWidth 节点图形宽度
     */
    public void setDivWidth(BigDecimal divWidth) {
        this.divWidth = divWidth;
    }

    /**
     * @return the value of stria_flow_nodes.divHeight 节点图形高度
     */
    public BigDecimal getDivHeight() {
        return divHeight;
    }

    /**
     * @param divHeight the value for stria_flow_nodes.divHeight 节点图形高度
     */
    public void setDivHeight(BigDecimal divHeight) {
        this.divHeight = divHeight;
    }

    /**
     * @return the value of stria_flow_nodes.source_type 渠道
     */
    public String getSourceType() {
        return sourceType;
    }

    /**
     * @param sourceType the value for stria_flow_nodes.source_type 渠道
     */
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType == null ? null : sourceType.trim();
    }

    /**
     * @return the value of stria_flow_nodes.service_type 服务类型
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * @param serviceType the value for stria_flow_nodes.service_type 服务类型
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType == null ? null : serviceType.trim();
    }

    /**
     * @return the value of stria_flow_nodes.clazz 服务执行class
     */
    public String getClazz() {
        return clazz;
    }

    /**
     * @param clazz the value for stria_flow_nodes.clazz 服务执行class
     */
    public void setClazz(String clazz) {
        this.clazz = clazz == null ? null : clazz.trim();
    }

    /**
     * @return the value of stria_flow_nodes.method_name 服务执行方法
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * @param methodName the value for stria_flow_nodes.method_clazz 服务执行方法
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    /**
     * @return the value of stria_flow_nodes.args 参数取值表达式
     */
    public String getArgs() {
        return args;
    }

    /**
     * @param args the value for stria_flow_nodes.args 参数取值表达式
     */
    public void setArgs(String args) {
        this.args = args == null ? null : args.trim();
    }

    /**
     * @return the value of stria_flow_nodes.args_clazz 参数class
     */
    public String getArgsClazz() {
        return argsClazz;
    }

    /**
     * @param argsClazz the value for stria_flow_nodes.args_clazz 参数class
     */
    public void setArgsClazz(String argsClazz) {
        this.argsClazz = argsClazz == null ? null : argsClazz.trim();
    }

    /**
     * @return the value of stria_flow_nodes.var 输出别名定义
     */
    public String getVar() {
        return var;
    }

    /**
     * @param var the value for stria_flow_nodes.var 输出别名定义
     */
    public void setVar(String var) {
        this.var = var == null ? null : var.trim();
    }


    public void setLayout(String layout) {
        String[] lay = layout.split(",");
        this.canvasLeft = new BigDecimal(lay[0]);
        this.canvasTop = new BigDecimal(lay[1]);
        this.divWidth = new BigDecimal(lay[2]);
        this.divHeight = new BigDecimal(lay[3]);
    }

    /**
     * @return the value of  STRIA_FLOW_NODES.CONDITION
     */
    public String getSubCondition() {
        return subCondition;
    }

    /**
     * @param condition the value for STRIA_FLOW_NODES.CONDITION
     */
    public void setSubCondition(String condition) {
        this.subCondition = condition == null ? null : condition.trim();
    }

    /**
     * @return the value of  STRIA_FLOW_NODES.REQUEST
     */
    public String getRequest() {
        return request;
    }

    /**
     * @param request the value for STRIA_FLOW_NODES.REQUEST
     */
    public void setRequest(String request) {
        this.request = request == null ? null : request.trim();
    }

    /**
     * @return the value of  STRIA_FLOW_NODES.SERVICE_CODE
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     * @param serviceCode the value for STRIA_FLOW_NODES.SERVICE_CODE
     */
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode == null ? null : serviceCode.trim();
    }

    /**
     * @return the value of  STRIA_FLOW_NODES.MESSAGE_TYPE
     */
    public String getMessageType() {
        return messageType;
    }

    /**
     * @param messageType the value for STRIA_FLOW_NODES.MESSAGE_TYPE
     */
    public void setMessageType(String messageType) {
        this.messageType = messageType == null ? null : messageType.trim();
    }

    /**
     * @return the value of  STRIA_FLOW_NODES.MESSAGE_CODE
     */
    public String getMessageCode() {
        return messageCode;
    }

    /**
     * @param messageCode the value for STRIA_FLOW_NODES.MESSAGE_CODE
     */
    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode == null ? null : messageCode.trim();
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId == null ? null : eventId.trim();
    }
}
