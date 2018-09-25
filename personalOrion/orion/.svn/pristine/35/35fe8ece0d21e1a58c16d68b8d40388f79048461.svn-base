package com.dcits.orion.scp.flow.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.galaxy.base.tuple.ThreeTuple;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.orion.batch.common.bean.CommTableObj;
import com.dcits.orion.batch.common.dao.AutoMapperDao;
import com.dcits.orion.scp.api.rpc.IFwFlowService;
import com.dcits.orion.scp.engine.ScpEngine;
import com.dcits.orion.scp.flow.model.Flow;
import com.dcits.orion.scp.flow.model.Line;
import com.dcits.orion.scp.flow.model.Node;
import com.dcits.orion.scp.flow.parse.FlowParser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qiqsa on 2017/3/13.
 */
@Repository
public class FwFlowService implements IFwFlowService {
    @Resource
    AutoMapperDao autoMapperDao;

    /**
     * @desc :保存流程
     * @param flow  流程
     * @param creator 创建人
     */
    @Override
    public void deploy(com.alibaba.fastjson.JSONObject flow, String creator) {
       // FlowModel model = FlowParser.parseJson(flow);
        ThreeTuple<Flow, List<Node>, List<Line>> tuple = FlowParser.convertJson2Model(flow);
        Flow entity = tuple.first;
        entity.setCreator(creator);
       // entity.setFlowModel(model);
        saveFlow(entity, tuple.second, tuple.three);
    }

    /**
     * @desc query stria by flowId
     * @param flowId
     * @return
     */
    @Override
    public JSONObject getFlowById(String flowId) {
        Map flowMap = new HashMap<>();
        CommTableObj flowObject = new CommTableObj("fw_flow_def");
        flowObject.setCondition("FLOW_ID", flowId);
        flowMap = autoMapperDao.selectOne(flowObject);
        if (null != flowMap) {
            JSONObject flowJson = new JSONObject();
            flowJson.put("flowid", flowMap.get("FLOW_ID") == null ? "" : flowMap.get("FLOW_ID"));
            flowJson.put("title", flowMap.get("FLOW_NAME") == null ? "new_flow" : flowMap.get("FLOW_NAME"));
            flowJson.put("createtime", flowMap.get("CREATETIME") == null ? "" : flowMap.get("CREATETIME"));
            flowJson.put("creator", flowMap.get("CREATOR") == null ? "" : flowMap.get("CREATOR"));
            flowJson.put("exec_type", flowMap.get("EXEC_TYPE") == null ? "" : flowMap.get("EXEC_TYPE"));
            flowJson.put("resume_type", flowMap.get("RESUME_TYPE") == null ? "" : flowMap.get("RESUME_TYPE"));
            flowJson.put("script", flowMap.get("SCRIPT") == null ? "" : flowMap.get("SCRIPT"));
            flowJson.put("reversal_type", flowMap.get("REVERSAL_TYPE") == null ? "" : flowMap.get("REVERSAL_TYPE"));
            flowJson.put("time_out", flowMap.get("TIME_OUT") == null ? "" : flowMap.get("TIME_OUT"));
            flowJson.put("timeout_deal", flowMap.get("ERROR_DEAL") == null ? "" : flowMap.get("ERROR_DEAL"));
            flowJson.put("key_fields", flowMap.get("KEY_FIELDS") == null ? "" : flowMap.get("KEY_FIELDS"));
            flowJson.put("initNum", flowMap.get("INIT_NUM") == null ? 0 : flowMap.get("INIT_NUM"));
            flowJson.put("log_flag",flowMap.get("LOG_FLAG") == null ? "":flowMap.get("LOG_FLAG"));

            CommTableObj lineObject = new CommTableObj("fw_flow_line");
            lineObject.setCondition("FLOW_ID", flowId);
            List<Map> lines = autoMapperDao.selectList(lineObject);
            JSONObject ls = new JSONObject();
            for (Map lineMap : lines) {
                JSONObject jDetail = new JSONObject();
                jDetail.put("expr", lineMap.get("EXPR") == null ? "" : lineMap.get("EXPR"));
                jDetail.put("from", lineMap.get("FROM_NODE") == null ? "" : lineMap.get("FROM_NODE"));
                jDetail.put("to", lineMap.get("TO_NODE") == null ? "" : lineMap.get("TO_NODE"));
                jDetail.put("flowid", lineMap.get("FLOW_ID") == null ? "" : lineMap.get("FLOW_ID"));
                jDetail.put("type","sl");
                ls.put((String)lineMap.get("ID"), jDetail);
            }
            flowJson.put("lines", ls);
            CommTableObj nodeObject = new CommTableObj("fw_flow_node");
            nodeObject.setCondition("FLOW_ID", flowId);
            List<Map> nodes = autoMapperDao.selectList(nodeObject);
            JSONObject ns = new JSONObject();
            for (Map nodeMap : nodes) {
                JSONObject jDetail = new JSONObject();
                jDetail.put("flowid", nodeMap.get("FLOW_ID") == null ? "" : nodeMap.get("FLOW_ID"));
                jDetail.put("node_id", nodeMap.get("NODE_ID") == null ? "" : nodeMap.get("NODE_ID"));
                jDetail.put("id", nodeMap.get("NODE_ID") == null ? "" : nodeMap.get("NODE_ID"));
                jDetail.put("name", nodeMap.get("NODE_DESC") == null ? "" : nodeMap.get("NODE_DESC"));
                //查询时对节点type转译
                jDetail.put("type", nodeMap.get("NODE_TYPE") == null ? "" : nodeMap.get("NODE_TYPE"));
                if("S".equals(nodeMap.get("NODE_TYPE"))){
                    jDetail.put("type","start round");
                }else if("B".equals(nodeMap.get("NODE_TYPE"))){
                    jDetail.put("type","end round");
                }else if("Q".equals(nodeMap.get("NODE_TYPE"))){
                    jDetail.put("type","check");
                }else if("A".equals(nodeMap.get("NODE_TYPE"))){
                    jDetail.put("type","auth");
                } else if("N".equals(nodeMap.get("NODE_TYPE"))){
                    jDetail.put("type","confirm");
                } else if("C".equals(nodeMap.get("NODE_TYPE"))){
                    jDetail.put("type","atom");
                } else if("D".equals(nodeMap.get("NODE_TYPE"))){
                    jDetail.put("type","chat round");
                } else if("P".equals(nodeMap.get("NODE_TYPE"))){
                    jDetail.put("type","fork round");
                } else if("H".equals(nodeMap.get("NODE_TYPE"))){
                    jDetail.put("type","join round");
                }else if("M".equals(nodeMap.get("NODE_TYPE"))){
                    jDetail.put("type","eventService");
                }else if("L".equals(nodeMap.get("NODE_TYPE"))){
                    jDetail.put("type","reload round");
                }else if("I".equals(nodeMap.get("NODE_TYPE"))){
                    jDetail.put("type","undo round");
                }
                jDetail.put("top", nodeMap.get("NODE_TOP") == null ? "" : nodeMap.get("NODE_TOP"));
                jDetail.put("left", nodeMap.get("NODE_LEFT") == null ? "" : nodeMap.get("NODE_LEFT"));
                jDetail.put("width", nodeMap.get("NODE_WIDTH") == null ? "" : nodeMap.get("NODE_WIDTH"));
                jDetail.put("height", nodeMap.get("NODE_HEIGHT") == null ? "" : nodeMap.get("NODE_HEIGHT"));
                jDetail.put("exec_type", nodeMap.get("EXEC_TYPE") == null ? "" : nodeMap.get("EXEC_TYPE"));
                jDetail.put("sytem_id", nodeMap.get("SYSTEM_ID") == null ? "" : nodeMap.get("SYSTEM_ID"));
                jDetail.put("error_deal", nodeMap.get("ERROR_DEAL") == null ? "" : nodeMap.get("ERROR_DEAL"));
                jDetail.put("unknow_deal", nodeMap.get("UNKNOW_DEAL") == null ? "" : nodeMap.get("UNKNOW_DEAL"));
                jDetail.put("retry_cnt", nodeMap.get("RETRY_CNT") == null ? "" : nodeMap.get("RETRY_CNT"));
                jDetail.put("before_script", nodeMap.get("BEFORE_SCRIPT") == null ? "" : nodeMap.get("BEFORE_SCRIPT"));
                jDetail.put("after_script",nodeMap.get("AFTER_SCRIPT") == null ? "" :nodeMap.get("AFTER_SCRIPT"));
                jDetail.put("mapper",nodeMap.get("MAPPER") == null ? "" :nodeMap.get("MAPPER"));
                jDetail.put("loop_idx",nodeMap.get("LOOP_IDX") == null ? "" :nodeMap.get("LOOP_IDX"));
                jDetail.put("loop_expr",nodeMap.get("LOOP_EXPR") == null ? "" :nodeMap.get("LOOP_EXPR"));
                ns.put((String)nodeMap.get("NODE_ID"), jDetail);
            }
            flowJson.put("nodes", ns);
            return flowJson;
        }
        return null;
    }

    /**
     * @DESC 列表查询前段展示数据
     * @param flowType
     * @return
     */
    @Override
    public JSONArray getFlow(String flowType) {
        JSONArray jsonArray = new JSONArray();
        CommTableObj flowTableObject = new CommTableObj("fw_flow_def");
        List<Map> flows =autoMapperDao.selectList(flowTableObject);
        for(Map flow :flows){
            JSONObject flowJson = new JSONObject();
            flowJson.put("FLOW_ID",flow.get("FLOW_ID") == null ? "" : flow.get("FLOW_ID"));
            flowJson.put("FLOW_NAME",flow.get("FLOW_NAME") == null ? "" : flow.get("FLOW_NAME"));
            flowJson.put("CREATETIME",flow.get("CREATETIME") == null ? "" : flow.get("CREATETIME"));
            flowJson.put("CREATOR",flow.get("CREATOR") == null ? "" : flow.get("CREATOR"));
            flowJson.put("EXEC_TYPE",flow.get("EXEC_TYPE") == null ? "" : flow.get("EXEC_TYPE"));
            flowJson.put("RESUME_TYPE",flow.get("RESUME_TYPE") == null ? "01" : flow.get("RESUME_TYPE"));
            flowJson.put("SCRIPT",flow.get("SCRIPT") == null ? "" : flow.get("SCRIPT"));
            flowJson.put("REVERSAL_TYPE",flow.get("REVERSAL_TYPE") == null ? "" : flow.get("REVERSAL_TYPE"));
            flowJson.put("TIME_OUT",flow.get("TIME_OUT") == null ? "" : flow.get("TIME_OUT"));
            flowJson.put("TIMEOUT_DEAL",flow.get("ERROR_DEAL") == null ? "" : flow.get("ERROR_DEAL"));
            flowJson.put("KEY_FIELDS",flow.get("KEY_FIELDS") == null ? "" : flow.get("KEY_FIELDS"));
            jsonArray.add(flowJson);
            flowJson = null;
        }
        return jsonArray;
    }

    @Override
    public String queryAppID() {
        return SpringApplicationContext.getContext().getBean(ScpEngine.class).getAppId();
    }

    /**
     * @desc 保存节点
     * @param flow 属性
     * @param nodes 节点
     * @param lines  直线
     */
    @Transactional
    public void saveFlow(Flow flow ,List<Node> nodes ,List<Line> lines ){
        CommTableObj commTableFlow1 = new CommTableObj("FW_FLOW_DEF");
        CommTableObj commTableNode = new CommTableObj("FW_FLOW_NODE");
        CommTableObj commTableLine = new CommTableObj("FW_FLOW_LINE");
        commTableFlow1.setCondition("FLOW_ID",flow.getFlowId());
        List data = autoMapperDao.selectList(commTableFlow1);
        if(data != null && data.size() > 0){ //如果记录存在，删除后新增记录
            autoMapperDao.delete(commTableFlow1);
            commTableNode.setCondition("FLOW_ID", flow.getFlowId());
            autoMapperDao.delete(commTableNode);
            commTableLine.setCondition("FLOW_ID", flow.getFlowId());
            autoMapperDao.delete(commTableLine);
        }
        //存储fw_flow
        CommTableObj commTableFlow = new CommTableObj("fw_flow_def");
        commTableFlow.setField("FLOW_ID",flow.getFlowId());
        commTableFlow.setField("FLOW_NAME",flow.getFlowName());
        commTableFlow.setField("CREATETIME",System.currentTimeMillis());
        commTableFlow.setField("CREATOR",flow.getCreator());
        commTableFlow.setField("EXEC_TYPE",flow.getExecType());
        commTableFlow.setField("RESUME_TYPE",flow.getResumeType());
        commTableFlow.setField("SCRIPT",flow.getScript());
        commTableFlow.setField("REVERSAL_TYPE",flow.getReversalType());
        commTableFlow.setField("TIME_OUT",flow.getTimeOut());
        commTableFlow.setField("ERROR_DEAL",flow.getTimeOutDeal());
        commTableFlow.setField("KEY_FIELDS",flow.getKeyFields());
        commTableFlow.setField("INIT_NUM",flow.getInitNum());
        commTableFlow.setField("LOG_FLAG",flow.getLog_flag());
        autoMapperDao.insert(commTableFlow);

        //存储fw_flow_node
        CommTableObj tableNode1 = new CommTableObj("fw_flow_node");
        List<Map> nodeList = new ArrayList<>();
        for(Node node :nodes){
            Map tableNode = new HashMap();
            tableNode.put("FLOW_ID", node.getFlowId());
            tableNode.put("NODE_ID", node.getNodeId());
            tableNode.put("NODE_DESC", node.getNodeDesc() == null ? "": node.getNodeDesc());
            tableNode.put("NODE_TYPE", node.getNodeType() == null ? "":node.getNodeType());
            tableNode.put("NODE_TOP", node.getNodeTop());
            tableNode.put("NODE_LEFT", node.getNode_left());
            tableNode.put("NODE_WIDTH", node.getNodeWidth());
            tableNode.put("NODE_HEIGHT", node.getNodeHeight());
            tableNode.put("EXEC_TYPE", node.getExecType() == null ? "":node.getExecType());
            tableNode.put("SYSTEM_ID", node.getSystemId() == null ? "" :node.getSystemId());
            tableNode.put("ERROR_DEAL", node.getErrorDeal() == null ? "":node.getErrorDeal());
            tableNode.put("UNKNOW_DEAL", node.getUnknowDeal() == null ? "":node.getUnknowDeal());
            tableNode.put("RETRY_CNT", node.getRetryCnt());
            tableNode.put("BEFORE_SCRIPT", node.getBeforeScript() == null ?"":node.getBeforeScript());
            tableNode.put("AFTER_SCRIPT", node.getAfterScript() == null ? "":node.getAfterScript());
            tableNode.put("MAPPER",node.getMapper());
            tableNode.put("LOOP_IDX",node.getLoopIdex());
            tableNode.put("LOOP_EXPR",node.getLoopExpr());

            nodeList.add(tableNode);
            tableNode = null;
        }
        tableNode1.setRecords(nodeList);
        autoMapperDao.batchInsert(tableNode1);
        CommTableObj tableObj = new CommTableObj("fw_flow_line");
        List<Map> lineList = new ArrayList<>();
        //存储fw_flow_line
        for(Line line : lines){
            Map lineMap = new HashMap();
            lineMap.put("FLOW_ID", line.getFlowId() == null ? "": line.getFlowId());
            lineMap.put("ID",line.getId() == null ? "":line.getId());
            lineMap.put("FROM_NODE", line.getFromNodeId() == null ? "" : line.getFromNodeId());
            lineMap.put("TO_NODE", line.getToNodeId() == null ? "":line.getToNodeId() );
            lineMap.put("EXPR", line.getExpr() == null ? "":line.getExpr());
            lineMap.put("TYPE",line.getType() == null ? "sl" : line.getType());
            lineList.add(lineMap);
        }
        tableObj.setRecords(lineList);
        autoMapperDao.batchInsert(tableObj);
    }
}
