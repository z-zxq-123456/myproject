package com.dcits.orion.stria.parse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.orion.stria.dao.mapper.Flow;
import com.dcits.orion.stria.dao.mapper.Line;
import com.dcits.orion.stria.exception.StriaException;
import com.dcits.orion.stria.model.*;
import com.dcits.orion.stria.utils.XmlUtil;
import com.dcits.galaxy.base.tuple.ThreeTuple;
import com.dcits.galaxy.base.util.DateUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tim on 2015/5/20.
 */
public class FlowParser {

    public static final String PREFIX = "stria";

    /**
     * 文件读取的json字节数组流程解析
     *
     * @param bytes
     * @return
     */
    public static FlowModel parseJson(byte[] bytes) {
        JSONObject json = (JSONObject) JSONObject.parse(bytes);
        return parseJson(json);
    }

    /**
     * Json格式流程解析
     *
     * @param json
     * @return
     */
    public static FlowModel parseJson(JSONObject json) {
        FlowModel flowModel = new FlowModel();
        flowModel.setName(json.getString(NodeParser.FLOWID));
        flowModel.setDisplayName(json.getString(NodeParser.TITLE));
        // 流程类型
        if (json.containsKey(NodeParser.FLOW_TYPE))
            flowModel.setFlowType(json.getString(NodeParser.FLOW_TYPE));
        else
            flowModel.setFlowType("01");//默认服务流程定义
        // 全局事物管理开关
        if (json.containsKey(NodeParser.DTP_FLAG))
            flowModel.setDepFlag(json.getString(NodeParser.DTP_FLAG));
        else
            flowModel.setDepFlag("N");
        // Spring事物管理开关
        if (json.containsKey(NodeParser.TX_FLAG))
            flowModel.setTxFlag(json.getString(NodeParser.TX_FLAG));
        else
            flowModel.setTxFlag("Y");
        // 流程处理超时时间
        if (json.containsKey(NodeParser.TIMEOUT))
            flowModel.setExpireTime(json.getString(NodeParser.TIMEOUT));
        else
            flowModel.setExpireTime("-1");
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
            node.put(NodeParser.NODEID, nodeEntry.getKey());
            node.put(NodeParser.LINES, linesMap.containsKey(nodeEntry.getKey()) ? linesMap.get(nodeEntry.getKey()) : new JSONArray());
            NodeModel model = parseModelJson(node);
            flowModel.getNodes().add(model);
        }

        StartModel start = null;
        // 循环节点模型，构造变迁输入、输出的source、target
        for (NodeModel node : flowModel.getNodes()) {
            for (TransitionModel transition : node.getOutputs()) {
                String to = transition.getTo();
                for (NodeModel node2 : flowModel.getNodes()) {
                    if (to.equalsIgnoreCase(node2.getName())) {
                        node2.getInputs().add(transition);
                        transition.setTarget(node2);
                    }
                }
            }
            // 判断startModel
            if (node instanceof StartModel) {
                if (start != null)
                    throw new StriaException("服务定义中存在多个开始节点，请检查流程定义！");
                start = (StartModel) node;
            }
        }

        if (start == null)
            throw new StriaException("服务定义中不存在开始节点，请检查流程定义！");
        // 添加流程中的子服务与事件服务
        addSubAndEventServiceModel(flowModel, start);
        return flowModel;
    }

    /**
     * 将流程里面所有有效的事件服务节点与子流程节点，添加到flowModel中
     *
     * @param flowModel
     * @param nodeModel
     */
    public static void addSubAndEventServiceModel(FlowModel flowModel, NodeModel nodeModel) {
        // 节点为null
        if (null == nodeModel) {
            return;
        }

        // 添加事件服务节点和子服务节点
        if (nodeModel instanceof SubServiceModel) {
            if (!flowModel.getSubNodes().contains(nodeModel)) {
                flowModel.getSubNodes().add((SubServiceModel) nodeModel);
            }
        }

        /**
         * 根据线找下个节点。
         */
        for (TransitionModel transitionModel : nodeModel.getOutputs()) {
            NodeModel node = transitionModel.getTarget();
            addSubAndEventServiceModel(flowModel, node);
        }
    }


    /**
     * 当前节点，顶级节点是否开始节点
     *
     * @param nodeModel
     */
    public static boolean findStartModel(NodeModel nodeModel) {
        // 节点为null
        if (null == nodeModel) {
            return false;
        }
        /**
         * 如果是开始节点结束
         */
        if (nodeModel instanceof StartModel) {
            return true;
        }
        /**
         * 如果不是开始节点，继续往上找，直到找到头为止
         */
        for (TransitionModel transitionModel : nodeModel.getInputs()) {
            NodeModel node = transitionModel.getSource();
            return findStartModel(node);
        }
        return false;
    }

    /**
     * 解析流程定义文件，并将解析后的对象放入模型容器中
     *
     * @param bytes
     */
    @Deprecated
    public static FlowModel parseXml(byte[] bytes) {
        DocumentBuilder documentBuilder = XmlUtil.createDocumentBuilder();
        if (documentBuilder != null) {
            Document doc;
            try {
                doc = documentBuilder.parse(new ByteArrayInputStream(bytes));
                Element processE = doc.getDocumentElement();
                FlowModel flowModel = new FlowModel();
                flowModel.setName(processE.getAttribute(NodeParser.NAME));
                flowModel.setDisplayName(processE.getAttribute(NodeParser.DISPLAYNAME));
                flowModel.setDepFlag(processE.getAttribute(NodeParser.DTP_FLAG));
                flowModel.setTxFlag(processE.getAttribute(NodeParser.TX_FLAG));
                flowModel.setExpireTime(processE.getAttribute(NodeParser.EXPIRETIME));
                flowModel.setInstanceNoClass(processE.getAttribute(NodeParser.INSTANCENOCLASS));
                NodeList nodeList = processE.getChildNodes();
                int nodeSize = nodeList.getLength();
                for (int i = 0; i < nodeSize; i++) {
                    Node node = nodeList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        NodeModel model = parseModelXml(node);
                        flowModel.getNodes().add(model);
                    }
                }
                //循环节点模型，构造变迁输入、输出的source、target
                for (NodeModel node : flowModel.getNodes()) {
                    for (TransitionModel transition : node.getOutputs()) {
                        String to = transition.getTo();
                        for (NodeModel node2 : flowModel.getNodes()) {
                            if (to.equalsIgnoreCase(node2.getName())) {
                                node2.getInputs().add(transition);
                                transition.setTarget(node2);
                            }
                        }
                    }
                }
                return flowModel;
            } catch (SAXException | IOException e) {
                throw new StriaException("解析流程出错！", e);
            }
        } else {
            throw new StriaException("documentBuilder is null");
        }
    }

    /**
     * 对流程定义xml的节点，根据其节点对应的解析器解析节点内容
     *
     * @param node
     * @return
     */
    @Deprecated
    private static NodeModel parseModelXml(Node node) {
        String nodeName = node.getNodeName();
        Element element = (Element) node;
        NodeParser nodeParser = null;
        switch (nodeName) {
            case "start":
                nodeParser = new StartParser();
                break;
            case "end":
                nodeParser = new EndParser();
                break;
            case "fork":
                nodeParser = new ForkParser();
                break;
            case "join":
                nodeParser = new JoinParser();
                break;
            case "decision":
                nodeParser = new DecisionParser();
                break;
            case "checkservice":
                nodeParser = new CheckServiceParser();
                break;
            case "authservice":
                nodeParser = new AuthServiceParser();
                break;
            case "confirmservice":
                nodeParser = new ConfirmServiceParser();
                break;
            case "atomservice":
                nodeParser = new AtomServiceParser();
                break;
            case "eventservice":
                nodeParser = new EventServiceParser();
                break;
            case "event":
                nodeParser = new EventParser();
                break;
            case "subservice":
                nodeParser = new SubServiceParser();
                break;
        }
        if (null == nodeParser)
            throw new StriaException("[" + nodeName + "] 未定义XML解析器！");
        nodeParser.parse(element);
        return nodeParser.getModel();
    }

    /**
     * 对流程定义xml的节点，根据其节点对应的解析器解析节点内容
     *
     * @param node
     * @return
     */
    private static NodeModel parseModelJson(JSONObject node) {
        String nodeName = node.getString(NodeParser.TYPE);
        NodeParser nodeParser = null;
        switch (nodeName) {
            case "start round":
                nodeParser = new StartParser();
                break;
            case "end round":
                nodeParser = new EndParser();
                break;
            case "fork round":
                nodeParser = new ForkParser();
                break;
            case "join round":
                nodeParser = new JoinParser();
                break;
            case "chat round":
                nodeParser = new DecisionParser();
                break;
            case "check":
                nodeParser = new CheckServiceParser();
                break;
            case "auth":
                nodeParser = new AuthServiceParser();
                break;
            case "confirm":
                nodeParser = new ConfirmServiceParser();
                break;
            case "atom":
                nodeParser = new AtomServiceParser();
                break;
            case "eventService":
                nodeParser = new EventServiceParser();
                break;
            case "event":
                nodeParser = new EventParser();
                break;
            case "sub":
                nodeParser = new SubServiceParser();
                break;
        }
        if (null == nodeParser)
            throw new StriaException("[" + nodeName + "]未定义Json解析器！");
        nodeParser.parse(node);
        return nodeParser.getModel();
    }

    public static ThreeTuple<Flow, List<com.dcits.orion.stria.dao.mapper.Node>, List<Line>> convertJson2Model(JSONObject flowJson) {
        if (null == flowJson) return null;
        Flow flow = new Flow();
        flow.setTitle(flowJson.getString(NodeParser.TITLE));
        flow.setFlowid(flowJson.getString(NodeParser.FLOWID));
        flow.setInitNum(flowJson.getBigDecimal(NodeParser.INITNUM));
        flow.setState(flowJson.getBigDecimal(NodeParser.STATE));
        flow.setProdType(flowJson.getString(NodeParser.PRODTYPE));
        flow.setEventType(flowJson.getString(NodeParser.EVENTTYPE));
        if (flowJson.containsKey(NodeParser.CREATOR))
            flow.setCreator(flowJson.getString(NodeParser.CREATOR));
        else
            flow.setCreator("stria");
        if (flowJson.containsKey(NodeParser.VERSION))
            flow.setVersion(flowJson.getString(NodeParser.VERSION));
        else
            flow.setVersion("1");
        //全局事物控制开关，默认关闭
        if (flowJson.containsKey(NodeParser.DTP_FLAG))
            flow.setDtpFlag(flowJson.getString(NodeParser.DTP_FLAG));
        else
            flow.setDtpFlag("N");
        //Spring事物控制开关，默认打开
        if (flowJson.containsKey(NodeParser.TX_FLAG))
            flow.setTxFlag(flowJson.getString(NodeParser.TX_FLAG));
        else
            flow.setTxFlag("Y");
        //流程处理超时时间
        if (flowJson.containsKey(NodeParser.TIMEOUT))
            flow.setTimeOut(flowJson.getBigDecimal(NodeParser.TIMEOUT));
        else
            flow.setTimeOut(new BigDecimal(-1));//不限制时间

        //流程类型
        if (flowJson.containsKey(NodeParser.FLOW_TYPE))
            flow.setFlowType(flowJson.getString(NodeParser.FLOW_TYPE));
        else
            flow.setFlowType("01");//默认服务流程定义

        flow.setCreatetime(DateUtils.getDateTime());
        JSONObject nodes = flowJson.getJSONObject(NodeParser.NODES);
        JSONObject lines = flowJson.getJSONObject(NodeParser.LINES);
        List<com.dcits.orion.stria.dao.mapper.Node> nodeList = new ArrayList<>();
        List<Line> lineList = new ArrayList<>();
        // 解析线
        for (Map.Entry lineEntry : lines.entrySet()) {
            JSONObject line = (JSONObject) lineEntry.getValue();
            Line l = new Line();
            l.setType(line.getString(NodeParser.TYPE));
            l.setExpr(line.getString(NodeParser.EXPR));
            l.setFromNode(line.getString(NodeParser.FROM));
            l.setName(line.getString(NodeParser.NAME));
            l.setToNode(line.getString(NodeParser.TO));
            lineList.add(l);
            l.setFlowid(flowJson.getString(NodeParser.FLOWID));
            l.setId((String) lineEntry.getKey());
        }
        // 解析节点
        for (Map.Entry nodeEntry : nodes.entrySet()) {
            JSONObject node = (JSONObject) nodeEntry.getValue();
            com.dcits.orion.stria.dao.mapper.Node n = new com.dcits.orion.stria.dao.mapper.Node();
            n.setCanvasLeft(node.getBigDecimal(NodeParser.LEFT));
            n.setCanvasTop(node.getBigDecimal(NodeParser.TOP));
            n.setDivWidth(node.getBigDecimal(NodeParser.WIDTH));
            n.setDivHeight(node.getBigDecimal(NodeParser.HEIGHT));
            n.setArgs(node.getString(NodeParser.ARGS));
            n.setArgsClazz(node.getString(NodeParser.ARGSCLAZZ));
            n.setClazz(node.getString(NodeParser.CLAZZ));
            n.setMethodName(node.getString(NodeParser.METHODNAME));
            n.setName(node.getString(NodeParser.NAME));
            n.setSourceType(node.getString(NodeParser.SOURCETYPE));
            n.setServiceType(node.getString(NodeParser.SERVICETYPE));
            n.setType(node.getString(NodeParser.TYPE));
            n.setSubCondition(node.getString(NodeParser.CONDITION));
            n.setRequest(node.getString(NodeParser.REQUEST));
            n.setServiceCode(node.getString(NodeParser.SERVICECODE));
            n.setMessageType(node.getString(NodeParser.MESSAGETYPE));
            n.setMessageCode(node.getString(NodeParser.MESSAGECODE));
            n.setVar(node.getString(NodeParser.VAR));
            n.setEventId(node.getString(NodeParser.EVENTID));
            nodeList.add(n);
            n.setFlowid(flowJson.getString(NodeParser.FLOWID));
            n.setId((String) nodeEntry.getKey());
        }
        return new ThreeTuple<>(flow, nodeList, lineList);
    }

    /**
     * 字节流转JsonObject
     *
     * @param bytes
     * @return
     */
    public static JSONObject convert2Json(byte[] bytes) {
        JSONObject json = (JSONObject) JSON.parse(bytes);
        return json;
    }

    /**
     * Xml解析为JsonObject
     *
     * @param bytes
     * @return
     */
    @Deprecated
    public static JSONObject convertXml2Json(byte[] bytes) {
        FlowModel model = parseXml(bytes);
        Flow fm = new Flow();
        fm.setFlowid(model.getName());
        fm.setTitle(model.getDisplayName());
        JSONObject json = JSON.parseObject(JSON.toJSONString(fm));
        Map<String, com.dcits.orion.stria.dao.mapper.Node> jsonNodes = new HashMap<>();
        Map<String, Line> jsonLines = new HashMap<>();
        Map<String, String> iDMapper = new HashMap<>();
        int index = 1;
        List<NodeModel> nodes = model.getNodes();

        for (NodeModel nm : nodes) {
            String id = PREFIX + "_node_" + index;
            iDMapper.put(nm.getName(), id);
            com.dcits.orion.stria.dao.mapper.Node node = new com.dcits.orion.stria.dao.mapper.Node();
            node.setName(nm.getDisplayName());
            node.setLayout(nm.getLayout());
            //["start round","end round","check","auth","confirm","atom","chat round","fork round","join round"],
            if (nm instanceof StartModel) {
                node.setType("start round");
                node.setName("开始");
            } else if (nm instanceof EndModel) {
                node.setType("end round");
                node.setName("结束");
            } else if (nm instanceof DecisionModel) {
                node.setType("chat round");
                node.setName("决策");
            } else if (nm instanceof ForkModel) {
                node.setType("fork round");
                node.setName("分派");
            } else if (nm instanceof JoinModel) {
                node.setType("join round");
                node.setName("合并");
            } else if (nm instanceof AbstractServiceModel) {
                if (nm instanceof AtomServiceModel) {
                    node.setType("atom");
                    node.setVar(((AtomServiceModel) nm).getVar());
                } else if (nm instanceof AuthServiceModel) {
                    node.setType("auth");
                } else if (nm instanceof ConfirmServiceModel) {
                    node.setType("confirm");
                } else if (nm instanceof CheckServiceModel) {
                    node.setType("check");
                }
                AbstractServiceModel as = (AbstractServiceModel) nm;
                node.setSourceType(as.getSourceType());
                node.setServiceType(as.getServiceType());
                node.setClazz(as.getClazz());
                node.setMethodName(as.getMethodName());
                node.setArgs(as.getArgs());
                node.setArgsClazz(as.getArgsClazz());
            }
            for (TransitionModel tm : nm.getOutputs()) {
                index++;
                String lineId = PREFIX + "_line_" + index;
                Line lm = new Line();
                iDMapper.put(tm.getName(), lineId);
                lm.setType("sl");
                lm.setExpr(tm.getExpr());
                lm.setName(tm.getDisplayName());
                lm.setFromNode(id);
                lm.setToNode(tm.getTarget().getName());
                jsonLines.put(lineId, lm);
            }
            jsonNodes.put(id, node);
            index++;
        }
        for (Map.Entry<String, Line> entry : jsonLines.entrySet()) {
            entry.getValue().setToNode(iDMapper.get(entry.getValue().getToNode()));
        }
        json.put("initNum", index);
        json.put("nodes", jsonNodes);
        json.put("lines", jsonLines);
        return json;
    }

    /**
     * 节点解析器
     */
    public static abstract class NodeParser implements INodeParser {
        /**
         * 模型对象
         */
        protected NodeModel model;

        /**
         * 实现NodeParser接口的parse函数
         * 由子类产生各自的模型对象，设置常用的名称属性，并且解析子节点transition，构造TransitionModel模型对象
         */
        public void parse(Element element) {
            model = newModel();
            model.setName(element.getAttribute(NAME));
            model.setDisplayName(element.getAttribute(DISPLAYNAME));
            model.setLayout(element.getAttribute(LAYOUT));

            List<Element> transitions = XmlUtil.elements(element, NODE_TRANSITION);
            for (Element te : transitions) {
                TransitionModel transition = new TransitionModel();
                transition.setName(te.getAttribute(NAME));
                transition.setDisplayName(te.getAttribute(DISPLAYNAME));
                transition.setTo(te.getAttribute(TO));
                transition.setExpr(te.getAttribute(EXPR));
                transition.setG(te.getAttribute(G));
                transition.setOffset(te.getAttribute(OFFSET));
                transition.setSource(model);
                model.getOutputs().add(transition);
            }

            parseNode(model, element);
        }

        /**
         * 实现NodeParser接口的parse函数
         * 由子类产生各自的模型对象，设置常用的名称属性，并且解析子节点transition，构造TransitionModel模型对象
         */
        public void parse(JSONObject node) {
            model = newModel();
            model.setName(node.getString(NODEID));
            model.setDisplayName(node.getString(NAME));
            model.setLayout(new StringBuffer().append(node.getString(TOP)).append(",")
                    .append(node.getString(LEFT)).append(",")
                    .append(node.getString(WIDTH)).append(",")
                    .append(node.getString(HEIGHT)).toString());
            model.setType(node.getString(TYPE));
            JSONArray lines = node.getJSONArray(LINES);
            if (null != lines) {
                for (JSONObject line : lines.toArray(new JSONObject[]{})) {
                    TransitionModel transition = new TransitionModel();
                    transition.setName(line.getString(LINEID));
                    transition.setDisplayName(line.getString(NAME));
                    transition.setTo(line.getString(TO));
                    transition.setFrom(line.getString(FROM));
                    transition.setExpr(line.getString(EXPR));
                    transition.setG("");
                    transition.setOffset("");
                    transition.setSource(model);
                    model.getOutputs().add(transition);
                }
            }
            parseNode(model, node);
        }


        /**
         * 子类可覆盖此方法，完成特定的解析
         *
         * @param model
         * @param element
         */
        protected void parseNode(NodeModel model, Element element) {

        }

        /**
         * 子类可覆盖此方法，完成特定的解析
         *
         * @param model
         * @param node
         */
        protected void parseNode(NodeModel model, JSONObject node) {

        }

        /**
         * 抽象方法，由子类产生各自的模型对象
         *
         * @return
         */
        protected abstract NodeModel newModel();

        /**
         * 返回模型对象
         */
        public NodeModel getModel() {
            return model;
        }
    }

    public static class StartParser extends NodeParser {
        /**
         * 产生StartModel模型对象
         */
        protected NodeModel newModel() {
            return new StartModel();
        }
    }

    public static class JoinParser extends NodeParser {
        /**
         * 产生JoinModel模型对象
         */
        protected NodeModel newModel() {
            return new JoinModel();
        }
    }

    public static class ForkParser extends NodeParser {
        /**
         * 产生ForkModel模型对象
         */
        protected NodeModel newModel() {
            return new ForkModel();
        }
    }

    public static class EndParser extends NodeParser {
        /**
         * 产生EndModel模型对象
         */
        protected NodeModel newModel() {
            return new EndModel();
        }
    }

    public static class DecisionParser extends NodeParser {
        /**
         * 产生DecisionModel模型对象
         */
        protected NodeModel newModel() {
            return new DecisionModel();
        }
    }

    public static class AtomServiceParser extends NodeParser {
        protected void parseNode(NodeModel node, Element element) {
            AtomServiceModel atom = (AtomServiceModel) node;
            atom.setClazz(element.getAttribute(CLAZZ));
            atom.setMethodName(element.getAttribute(METHODNAME));
            atom.setArgs(element.getAttribute(ARGS));
            atom.setArgsClazz(element.getAttribute(ARGSCLAZZ));
            atom.setVar(element.getAttribute(VAR));
            atom.setServiceType(element.getAttribute(SERVICETYPE));
            atom.setSourceType(element.getAttribute(SOURCETYPE));
        }

        protected void parseNode(NodeModel node, JSONObject json) {
            AtomServiceModel atom = (AtomServiceModel) node;
            atom.setClazz(json.getString(CLAZZ));
            atom.setMethodName(json.getString(METHODNAME));
            atom.setArgs(json.getString(ARGS));
            atom.setArgsClazz(json.getString(ARGSCLAZZ));
            atom.setVar(json.getString(VAR));
            atom.setServiceType(json.getString(SERVICETYPE));
            atom.setSourceType(json.getString(SOURCETYPE));
        }

        protected NodeModel newModel() {
            return new AtomServiceModel();
        }
    }

    public static class EventServiceParser extends NodeParser {
        protected void parseNode(NodeModel node, Element element) {
            EventServiceModel eventService = (EventServiceModel) node;
            eventService.setClazz(element.getAttribute(CLAZZ));
            eventService.setMethodName(element.getAttribute(METHODNAME));
            eventService.setArgs(element.getAttribute(ARGS));
            eventService.setArgsClazz(element.getAttribute(ARGSCLAZZ));
            eventService.setSourceType(element.getAttribute(SOURCETYPE));
            eventService.setVar(element.getAttribute(VAR));
        }

        protected void parseNode(NodeModel node, JSONObject json) {
            EventServiceModel eventService = (EventServiceModel) node;
            eventService.setClazz(json.getString(CLAZZ));
            eventService.setMethodName(json.getString(METHODNAME));
            eventService.setArgs(json.getString(ARGS));
            eventService.setArgsClazz(json.getString(ARGSCLAZZ));
            eventService.setSourceType(json.getString(SOURCETYPE));
            eventService.setVar(json.getString(VAR));
        }

        protected NodeModel newModel() {
            return new EventServiceModel();
        }
    }

    public static class EventParser extends NodeParser {
        protected void parseNode(NodeModel node, Element element) {
            EventModel event = (EventModel) node;
            event.setEventId(element.getAttribute(EVENTID));
        }

        protected void parseNode(NodeModel node, JSONObject json) {
            EventModel event = (EventModel) node;
            event.setEventId(json.getString(EVENTID));
        }

        protected NodeModel newModel() {
            return new EventModel();
        }
    }

    public static class AuthServiceParser extends NodeParser {
        protected void parseNode(NodeModel node, Element element) {
            AuthServiceModel auth = (AuthServiceModel) node;
            auth.setClazz(element.getAttribute(CLAZZ));
            auth.setMethodName(element.getAttribute(METHODNAME));
            auth.setArgs(element.getAttribute(ARGS));
            auth.setArgsClazz(element.getAttribute(ARGSCLAZZ));
            auth.setServiceType(element.getAttribute(SERVICETYPE));
            auth.setSourceType(element.getAttribute(SOURCETYPE));
        }

        protected void parseNode(NodeModel node, JSONObject json) {
            AuthServiceModel auth = (AuthServiceModel) node;
            auth.setClazz(json.getString(CLAZZ));
            auth.setMethodName(json.getString(METHODNAME));
            auth.setArgs(json.getString(ARGS));
            auth.setArgsClazz(json.getString(ARGSCLAZZ));
            auth.setServiceType(json.getString(SERVICETYPE));
            auth.setSourceType(json.getString(SOURCETYPE));
        }

        protected NodeModel newModel() {
            return new AuthServiceModel();
        }
    }

    public static class CheckServiceParser extends NodeParser {
        protected void parseNode(NodeModel node, Element element) {
            CheckServiceModel check = (CheckServiceModel) node;
            check.setClazz(element.getAttribute(CLAZZ));
            check.setMethodName(element.getAttribute(METHODNAME));
            check.setArgs(element.getAttribute(ARGS));
            check.setArgsClazz(element.getAttribute(ARGSCLAZZ));
            check.setServiceType(element.getAttribute(SERVICETYPE));
            check.setSourceType(element.getAttribute(SOURCETYPE));
        }

        protected void parseNode(NodeModel node, JSONObject json) {
            CheckServiceModel check = (CheckServiceModel) node;
            check.setClazz(json.getString(CLAZZ));
            check.setMethodName(json.getString(METHODNAME));
            check.setArgs(json.getString(ARGS));
            check.setArgsClazz(json.getString(ARGSCLAZZ));
            check.setServiceType(json.getString(SERVICETYPE));
            check.setSourceType(json.getString(SOURCETYPE));
        }

        protected NodeModel newModel() {
            return new CheckServiceModel();
        }
    }

    public static class ConfirmServiceParser extends NodeParser {
        protected void parseNode(NodeModel node, Element element) {
            ConfirmServiceModel confirm = (ConfirmServiceModel) node;
            confirm.setClazz(element.getAttribute(CLAZZ));
            confirm.setMethodName(element.getAttribute(METHODNAME));
            confirm.setArgs(element.getAttribute(ARGS));
            confirm.setArgsClazz(element.getAttribute(ARGSCLAZZ));
            confirm.setServiceType(element.getAttribute(SERVICETYPE));
            confirm.setSourceType(element.getAttribute(SOURCETYPE));
        }

        protected void parseNode(NodeModel node, JSONObject json) {
            ConfirmServiceModel confirm = (ConfirmServiceModel) node;
            confirm.setClazz(json.getString(CLAZZ));
            confirm.setMethodName(json.getString(METHODNAME));
            confirm.setArgs(json.getString(ARGS));
            confirm.setArgsClazz(json.getString(ARGSCLAZZ));
            confirm.setServiceType(json.getString(SERVICETYPE));
            confirm.setSourceType(json.getString(SOURCETYPE));
        }

        protected NodeModel newModel() {
            return new ConfirmServiceModel();
        }
    }

    public static class SubServiceParser extends NodeParser {
        protected void parseNode(NodeModel node, Element element) {
            SubServiceModel subService = (SubServiceModel) node;
            subService.setCondition(element.getAttribute(CONDITION));
            subService.setRequest(element.getAttribute(REQUEST));
            subService.setServiceCode(element.getAttribute(SERVICECODE));
            subService.setMessageType(element.getAttribute(MESSAGETYPE));
            subService.setMessageCode(element.getAttribute(MESSAGECODE));
            subService.setVar(element.getAttribute(VAR));
        }

        protected void parseNode(NodeModel node, JSONObject json) {
            SubServiceModel subService = (SubServiceModel) node;
            subService.setCondition(json.getString(CONDITION));
            subService.setRequest(json.getString(REQUEST));
            subService.setServiceCode(json.getString(SERVICECODE));
            subService.setMessageType(json.getString(MESSAGETYPE));
            subService.setMessageCode(json.getString(MESSAGECODE));
            subService.setVar(json.getString(VAR));
        }

        protected NodeModel newModel() {
            return new SubServiceModel();
        }
    }

}
