package com.dcits.orion.scp.flow.parse;
import com.alibaba.fastjson.JSONObject;
import com.dcits.galaxy.base.tuple.ThreeTuple;
import com.dcits.galaxy.base.util.DateUtils;
import com.dcits.orion.scp.flow.model.Flow;
import com.dcits.orion.scp.flow.model.Line;
import com.dcits.orion.scp.flow.model.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * create by qiqingshan
 */
public class FlowParser {

/*    public static FlowModel parseJson(JSONObject json) {
        //解析flow
        FlowModel flowModel = new FlowModel();
        flowModel.setFlowId(json.getString(NodeParser.FLOW_ID));
        flowModel.setFlowName(json.getString(NodeParser.FLOW_NAME));
        flowModel.setCreateTime(DateUtils.getDateTime());//设置时间
        flowModel.setCreator(json.getString(NodeParser.CREATOR));
        flowModel.setExecType(json.getString(NodeParser.EXEC_TYPE));
        flowModel.setResumeType(json.getString(NodeParser.RESUME_TYPE));
        flowModel.setScript(json.getString(NodeParser.SCRIPT));
        flowModel.setReversalType(json.getString(NodeParser.REVERSAL_TYPE));
        flowModel.setTimeOut(json.getIntValue(NodeParser.TIME_OUT));
        flowModel.setTimeOutDeal(json.getString(NodeParser.TIMEOUT_DEAL));
        flowModel.setKeyFields(json.getString(NodeParser.KEY_FIELDS));
        flowModel.setInit_num(json.getIntValue(NodeParser.INITNUM));
        flowModel.setLog_flag(json.getString(NodeParser.LOG_FLAG));

        //解析节点和直线
        JSONObject nodes = json.getJSONObject(NodeParser.NODES);
        JSONObject lines = json.getJSONObject(NodeParser.LINES);
        Map<String, JSONArray> linesMap = new HashMap<>();
        String fromNodeId;
        // 解析线
        for (Map.Entry lineEntry : lines.entrySet()) {
            JSONObject line = (JSONObject) lineEntry.getValue();

            line.put(NodeParser.LINEID, lineEntry.getKey());
            fromNodeId = line.getString(NodeParser.FROM);
            if (linesMap.containsKey(fromNodeId)) {
                linesMap.get(fromNodeId).add(line);
            } else {
                JSONArray lineArray = new JSONArray();
                lineArray.add(line);
                linesMap.put(fromNodeId, lineArray);
            }
        }
        // 解析节点
        for (Map.Entry nodeEntry : nodes.entrySet()) {
            JSONObject node = (JSONObject) nodeEntry.getValue();
            node.put(NodeParser.NODES, nodeEntry.getKey());
            node.put(NodeParser.LINES, linesMap.containsKey(nodeEntry.getKey()) ? linesMap.get(nodeEntry.getKey()) : new JSONArray());
            flowModel.getNodes().add(node);
        }
        return flowModel;
    }*/

    /**
     * 2017年3月14日09:29:50
     * @param flowJson
     * @return
     */
    public static ThreeTuple<Flow, List<Node>, List<Line>> convertJson2Model(JSONObject flowJson) {
        if (null == flowJson) return null;
        Flow flow = new Flow();
        flow.setFlowId(flowJson.getString(NodeParser.FLOW_ID) == null ? "" : flowJson.getString(NodeParser.FLOW_ID));
        flow.setFlowName(flowJson.getString(NodeParser.FLOW_NAME) == null ? "" : flowJson.getString(NodeParser.FLOW_NAME));
        flow.setCreateTime(DateUtils.getDateTime());
        flow.setCreator(flowJson.getString(NodeParser.CREATOR));
        flow.setExecType(flowJson.getString(NodeParser.EXEC_TYPE));
        flow.setResumeType(flowJson.getString(NodeParser.RESUME_TYPE));
        flow.setScript(flowJson.getString(NodeParser.SCRIPT));
        flow.setReversalType(flowJson.getString(NodeParser.REVERSAL_TYPE));
        flow.setTimeOut(flowJson.getIntValue(NodeParser.TIME_OUT));
        flow.setTimeOutDeal(flowJson.getString(NodeParser.TIMEOUT_DEAL));
        flow.setKeyFields(flowJson.getString(NodeParser.KEY_FIELDS));
        flow.setInitNum(flowJson.getIntValue(NodeParser.INITNUM));
        flow.setLog_flag(flowJson.getString(NodeParser.LOG_FLAG));

        JSONObject nodes = flowJson.getJSONObject(NodeParser.NODES);
        JSONObject lines = flowJson.getJSONObject(NodeParser.LINES);
        List<Node> nodeList = new ArrayList<>();
        List<Line> lineList = new ArrayList<>();
        // 解析线
        for (Map.Entry lineEntry : lines.entrySet()) {
            JSONObject line = (JSONObject) lineEntry.getValue();
            Line l = new Line();
            if(line.containsKey("from_node") && line.getString("from_node") != null && !"".equals(line.getString("from_node"))){
                l.setFromNodeId(line.getString(NodeParser.FROM_NODE));
            }else {
                l.setFromNodeId(line.getString(NodeParser.FROM));
            }
            if(line.containsKey("to_node") && !"".equals(line.getString("to_node")) && line.getString("to_node") != null){
                l.setToNodeId(line.getString(NodeParser.TO_NODE));
            }else{
                l.setToNodeId(line.getString(NodeParser.TO));
            }
            l.setFlowId(flowJson.getString(NodeParser.FLOW_ID));
            l.setType(line.getString(NodeParser.LINE_TYPE));
            l.setExpr(line.getString(NodeParser.EXPR));
            l.setId((String)lineEntry.getKey());
            lineList.add(l);

        }
        // 解析节点
        for (Map.Entry nodeEntry : nodes.entrySet()) {
            JSONObject node = (JSONObject) nodeEntry.getValue();
            Node n = new Node();
            n.setFlowId(node.getString(NodeParser.FLOW_ID) == null ? "" : node.getString(NodeParser.FLOW_ID));
            n.setNodeDesc(node.getString(NodeParser.NODE_DESC) == null ? "" : node.getString(NodeParser.NODE_DESC));
            //转译节点类型
            n.setNodeType(node.getString(NodeParser.NODE_TYPE) == null ? "" : node.getString(NodeParser.NODE_TYPE));
            if("start round".equals(node.getString(NodeParser.NODE_TYPE))){// S:开始节点
                n.setNodeType("S");
            }else if("end round".equals(node.getString(NodeParser.NODE_TYPE))){//B:返回节点
                n.setNodeType("B");
            }else if("check".equals(node.getString(NodeParser.NODE_TYPE))){//Q: 查询服务
                n.setNodeType("Q");
            }else if("auth".equals(node.getString(NodeParser.NODE_TYPE))){//A：检查授权服务
                n.setNodeType("A");
            }else if("confirm".equals(node.getString(NodeParser.NODE_TYPE))){ //C:提交服务
                n.setNodeType("N");
            }else if("atom".equals(node.getString(NodeParser.NODE_TYPE))){//N:通知服务
                n.setNodeType("C");
            }else if("chat round".equals(node.getString(NodeParser.NODE_TYPE))){//D:决策节点
                n.setNodeType("D");
            }else if("fork round".equals(node.getString(NodeParser.NODE_TYPE))){//P:分派节点
                n.setNodeType("P");
            }else if("join round".equals(node.getString(NodeParser.NODE_TYPE))) {//H:合并节点
                n.setNodeType("H");
            }else if("eventService".equals(node.getString(NodeParser.NODE_TYPE))){   //M映射节点
                n.setNodeType("M");
            }else if("reload round".equals(node.getString(NodeParser.NODE_TYPE))){
                n.setNodeType("L");
            }else if("undo round".equals(node.getString(NodeParser.NODE_TYPE))){
                n.setNodeType("I");
            }

            n.setNodeTop(node.getIntValue(NodeParser.NODE_TOP));
            n.setNode_left(node.getIntValue(NodeParser.NODE_LEFT));
            n.setNodeWidth(node.getIntValue(NodeParser.NODE_WIDTH));
            n.setNodeHeight(node.getIntValue(NodeParser.NODE_HEIGHT));
            n.setExecType(node.getString(NodeParser.EXEC_TYPE) == null ? "" : node.getString(NodeParser.EXEC_TYPE));
            n.setSystemId(node.getString(NodeParser.SYTEM_ID) == null ? "" : node.getString(NodeParser.SYTEM_ID));
            n.setErrorDeal(node.getString(NodeParser.ERROR_DEAL) == null ? "" : node.getString(NodeParser.ERROR_DEAL));
            n.setUnknowDeal(node.getString(NodeParser.UNKNOW_DEAL) == null ? "" : node.getString(NodeParser.UNKNOW_DEAL));
            n.setRetryCnt((node.getString(NodeParser.RETRY_CNT) == null || "".equals(node.getString(NodeParser.RETRY_CNT))) ? 0 : node.getIntValue(NodeParser.RETRY_CNT));
            n.setBeforeScript(node.getString(NodeParser.BEFORE_SCRIPT) == null ? "" : node.getString(NodeParser.BEFORE_SCRIPT));
            n.setAfterScript(node.getString(NodeParser.AFTER_SCRIPT) == null ? "" : node.getString(NodeParser.AFTER_SCRIPT));
            n.setMapper(node.getString(NodeParser.MAPPER) == null ? "" : node.getString(NodeParser.MAPPER));
            n.setLoopIdex(node.getString(NodeParser.LOOP_IDX) == null ? "" : node.getString(NodeParser.LOOP_IDX));
            n.setLoopExpr(node.getString(NodeParser.LOOP_EXPRE) == null ? "": node.getString(NodeParser.LOOP_EXPRE));
            n.setFlowId(flowJson.getString(NodeParser.FLOW_ID) == null ? "" : flowJson.getString(NodeParser.FLOW_ID));
            n.setNodeId(node.getString(NodeParser.ID) == null ? "" : node.getString(NodeParser.ID));
            //开始节点和返回节点ID特殊处理
            if(node.getString(NodeParser.NODE_TYPE).equals("start round")){
                n.setNodeId("START_NODE");
            }else if(node.getString(NodeParser.NODE_TYPE).equals("end round")){
                n.setNodeId("RETURN_NODE");
            }else if(node.getString(NodeParser.NODE_TYPE).equals("fork round")){
                n.setNodeId((String)nodeEntry.getKey());
            }else if(node.getString(NodeParser.NODE_TYPE).equals("chat round")){
                n.setNodeId((String)nodeEntry.getKey());
            }else if(node.getString(NodeParser.NODE_TYPE).equals("join round")){
                n.setNodeId((String)nodeEntry.getKey());
            }else if(node.getString(NodeParser.NODE_TYPE).equals("reload round")){
                n.setNodeId((String)nodeEntry.getKey());
            }else if(node.getString(NodeParser.NODE_TYPE).equals("undo round")){
                n.setNodeId((String)nodeEntry.getKey());
            }
            nodeList.add(n);
        }
        return new ThreeTuple<>(flow, nodeList, lineList);
    }

    public static abstract class NodeParser implements INodeParser {
    }
}


