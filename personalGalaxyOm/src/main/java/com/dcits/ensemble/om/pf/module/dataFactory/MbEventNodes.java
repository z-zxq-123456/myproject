package com.dcits.ensemble.om.pf.module.dataFactory;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by lixue on 2015/11/30 16:59:11.
 */
public class MbEventNodes extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is MB_EVENT_NODES.PROD_TYPE 
     */
    @TablePk(index = 1)
    private String prodType;

    /**
     * This field is MB_EVENT_NODES.EVENT_TYPE 
     */
    @TablePk(index = 2)
    private String eventType;

    /**
     * This field is MB_EVENT_NODES.EVENT_NODE 
     */
    @TablePk(index = 3)
    private String eventNode;

    /**
     * This field is MB_EVENT_NODES.METHOD_TYPE 
     */
    @TablePk(index = 4)
    private String methodType;

    /**
     * This field is MB_EVENT_NODES.EVENT_NODE_DESC 
     */
    private String eventNodeDesc;

    /**
     * This field is MB_EVENT_NODES.ALIAS 
     */
    private String alias;

    /**
     * This field is MB_EVENT_NODES.CLAZZ 
     */
    private String clazz;

    /**
     * This field is MB_EVENT_NODES.METHOD_NAME 
     */
    private String methodName;

    /**
     * This field is MB_EVENT_NODES.ARGS 
     */
    private String args;

    /**
     * This field is MB_EVENT_NODES.ARG_CLAZZ 
     */
    private String argClazz;

    /**
     * This field is MB_EVENT_NODES.VAR 
     */
    private String var;

    /**
     * @return the value of  MB_EVENT_NODES.PROD_TYPE 
     */
    public String getProdType() {
        return prodType;
    }

    /**
     * @param prodType the value for MB_EVENT_NODES.PROD_TYPE 
     */
    public void setProdType(String prodType) {
        this.prodType = prodType == null ? null : prodType.trim();
    }

    /**
     * @return the value of  MB_EVENT_NODES.EVENT_TYPE 
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * @param eventType the value for MB_EVENT_NODES.EVENT_TYPE 
     */
    public void setEventType(String eventType) {
        this.eventType = eventType == null ? null : eventType.trim();
    }

    /**
     * @return the value of  MB_EVENT_NODES.EVENT_NODE 
     */
    public String getEventNode() {
        return eventNode;
    }

    /**
     * @param eventNode the value for MB_EVENT_NODES.EVENT_NODE 
     */
    public void setEventNode(String eventNode) {
        this.eventNode = eventNode == null ? null : eventNode.trim();
    }

    /**
     * @return the value of  MB_EVENT_NODES.EVENT_NODE_DESC 
     */
    public String getEventNodeDesc() {
        return eventNodeDesc;
    }

    /**
     * @param eventNodeDesc the value for MB_EVENT_NODES.EVENT_NODE_DESC 
     */
    public void setEventNodeDesc(String eventNodeDesc) {
        this.eventNodeDesc = eventNodeDesc == null ? null : eventNodeDesc.trim();
    }

    /**
     * @return the value of  MB_EVENT_NODES.ALIAS 
     */
    public String getAlias() {
        return alias;
    }

    /**
     * @param alias the value for MB_EVENT_NODES.ALIAS 
     */
    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    /**
     * @return the value of  MB_EVENT_NODES.CLAZZ 
     */
    public String getClazz() {
        return clazz;
    }

    /**
     * @param clazz the value for MB_EVENT_NODES.CLAZZ 
     */
    public void setClazz(String clazz) {
        this.clazz = clazz == null ? null : clazz.trim();
    }

    /**
     * @return the value of  MB_EVENT_NODES.METHOD_NAME 
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * @param methodName the value for MB_EVENT_NODES.METHOD_NAME 
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    /**
     * @return the value of  MB_EVENT_NODES.METHOD_TYPE 
     */
    public String getMethodType() {
        return methodType;
    }

    /**
     * @param methodType the value for MB_EVENT_NODES.METHOD_TYPE 
     */
    public void setMethodType(String methodType) {
        this.methodType = methodType == null ? null : methodType.trim();
    }

    /**
     * @return the value of  MB_EVENT_NODES.ARGS 
     */
    public String getArgs() {
        return args;
    }

    /**
     * @param args the value for MB_EVENT_NODES.ARGS 
     */
    public void setArgs(String args) {
        this.args = args == null ? null : args.trim();
    }

    /**
     * @return the value of  MB_EVENT_NODES.ARG_CLAZZ 
     */
    public String getArgClazz() {
        return argClazz;
    }

    /**
     * @param argClazz the value for MB_EVENT_NODES.ARG_CLAZZ 
     */
    public void setArgClazz(String argClazz) {
        this.argClazz = argClazz == null ? null : argClazz.trim();
    }

    /**
     * @return the value of  MB_EVENT_NODES.VAR 
     */
    public String getVar() {
        return var;
    }

    /**
     * @param var the value for MB_EVENT_NODES.VAR 
     */
    public void setVar(String var) {
        this.var = var == null ? null : var.trim();
    }
}