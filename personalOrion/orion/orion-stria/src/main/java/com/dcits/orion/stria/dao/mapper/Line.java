package com.dcits.orion.stria.dao.mapper;

import java.io.Serializable;

/**
 * Created by Tim on 2015/5/18.
 */
public class Line implements Serializable {

    private static final long serialVersionUID = -1549156343943919913L;

    /**
     * This field is stria_flow_lines.flowid 流程ID
     */
    private String flowid;


    /**
     * This field is stria_flow_lines.id 连接线ID
     */
    private String id;


    /**
     * This field is stria_flow_lines.name 连接线描述
     */
    private String name;


    /**
     * This field is stria_flow_lines.type 节点类型
     */
    private String type;


    /**
     * This field is stria_flow_lines.from 源节点
     */
    private String fromNode;


    /**
     * This field is stria_flow_lines.to 目标节点
     */
    private String toNode;


    /**
     * This field is stria_flow_lines.expr 布尔类型表达式，用于决策节点后的连接线
     */
    private String expr;

    /**
     * @return the value of stria_flow_lines.flowid 流程ID
     */
    public String getFlowid() {
        return flowid;
    }

    /**
     * @param flowid the value for stria_flow_lines.flowid 流程ID
     */
    public void setFlowid(String flowid) {
        this.flowid = flowid == null ? null : flowid.trim();
    }

    /**
     * @return the value of stria_flow_lines.id 连接线ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the value for stria_flow_lines.id 连接线ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * @return the value of stria_flow_lines.name 连接线描述
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the value for stria_flow_lines.name 连接线描述
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return the value of stria_flow_lines.type 节点类型
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the value for stria_flow_lines.type 节点类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * @return the value of stria_flow_lines.fromNode 源节点
     */
    public String getFromNode() {
        return fromNode;
    }

    /**
     * @param fromNode the value for stria_flow_lines.fromNode 源节点
     */
    public void setFromNode(String fromNode) {
        this.fromNode = fromNode == null ? null : fromNode.trim();
    }

    /**
     * @return the value of stria_flow_lines.to 目标节点
     */
    public String getToNode() {
        return toNode;
    }

    /**
     * @param toNode the value for stria_flow_lines.toNode 目标节点
     */
    public void setToNode(String toNode) {
        this.toNode = toNode == null ? null : toNode.trim();
    }

    /**
     * @return the value of stria_flow_lines.expr 布尔类型表达式，用于决策节点后的连接线
     */
    public String getExpr() {
        return expr;
    }

    /**
     * @param expr the value for stria_flow_lines.expr 布尔类型表达式，用于决策节点后的连接线
     */
    public void setExpr(String expr) {
        this.expr = expr == null ? null : expr.trim();
    }
}
