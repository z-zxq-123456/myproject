package com.dcits.orion.stria.rpc.impl;

import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.galaxy.base.tuple.ThreeTuple;
import com.dcits.galaxy.base.util.StreamUtils;
import com.dcits.galaxy.cache.utils.JedisUtils;
import com.dcits.orion.stria.api.rpc.IFlowService;
import com.dcits.orion.stria.dao.FlowDao;
import com.dcits.orion.stria.dao.LineDao;
import com.dcits.orion.stria.dao.NodeDao;
import com.dcits.orion.stria.dao.mapper.Flow;
import com.dcits.orion.stria.dao.mapper.Line;
import com.dcits.orion.stria.dao.mapper.Node;
import com.dcits.orion.stria.exception.StriaException;
import com.dcits.orion.stria.model.FlowModel;
import com.dcits.orion.stria.parse.FlowParser;
import com.dcits.orion.stria.utils.StriaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tim on 2015/5/19.
 */
@Service
public class FlowService implements IFlowService, InitializingBean {

    private static final String NONE = "none";

    private static final String REDIS = "redis";

    private static final String LOCAL = "local";

    private static final Logger log = LoggerFactory.getLogger(FlowService.class);

    public static final Map<String, Flow> flows = Collections.synchronizedMap(new HashMap<String, Flow>());

    private static final String CACHEKEY = "stria";

    private static String cacheType = REDIS;

    @Resource
    FlowDao flowDao;

    @Resource
    LineDao lineDao;

    @Resource
    NodeDao nodeDao;

    /**
     * 检查流程定义对象
     *
     * @param flow
     *         流程定义对象
     */
    public void check(Flow flow, String flowName) {
        if (null == flow)
            throw new StriaException("流程[id=" + flowName + "]未定义");
        if (flow.getState() != null && flow.getState().intValue() != 1)
            throw new StriaException("流程[id=" + flowName + "]已停用");
    }

    /**
     * 保存流程定义
     *
     * @param flow
     * @param nodes
     * @param lines
     */
    public void saveFlow(Flow flow, List<Node> nodes, List<Line> lines) {
        // 删除原数据
        if (null != flowDao.select(flow)) {
            nodeDao.delete(nodes.get(0)); //根据flowid统一删除
            lineDao.delete(lines.get(0)); //根据flowid统一删除
            flowDao.delete(flow);
        }
        flowDao.insert(flow);
        for (Node node : nodes) {
            nodeDao.insert(node);
        }
        for (Line line : lines) {
            lineDao.insert(line);
        }
    }

    /**
     * 根据主键ID获取流程定义对象
     */
    public List<Flow> getFlow() {
        return flowDao.selectList(null);
    }

    /**
     * 根据主键ID获取流程定义对象
     *
     * @param id
     *         流程定义id
     */
    public Flow getFlowById(String id) {
        if (containsKeyForCache(id))
            return getFlowForCache(id);
        JSONObject flowJson = getFLowById(id);
        if (null == flowJson) return null;
        return deployFlow(getFLowById(id));
    }

    /**
     * 根据流程name获取流程定义对象
     *
     * @param name
     *         流程定义名称
     */
    public Flow getFlowByName(String name) {
        return null;
    }

    /**
     * 根据流程name、version获取流程定义对象
     *
     * @param name
     *         流程定义名称
     * @param version
     *         版本号
     */
    public Flow getFlowByVersion(String name, Integer version) {
        return null;
    }

    /**
     * 根据InputStream输入流，部署流程定义
     *
     * @param input
     *         流程定义输入流
     */
    public Flow deployFlow(InputStream input) {
        return deployFlow(input, "stria");
    }

    /**
     * 根据InputStream输入流，部署流程定义
     *
     * @param input
     *         流程定义输入流
     * @param creator
     *         创建人
     */
    public Flow deployFlow(InputStream input, String creator) {
        if (null == input)
            throw new StriaException("流程定义资源为null");
        byte[] bytes;
        try {
            bytes = StreamUtils.readBytes(input);
        } catch (Exception e) {
            throw new StriaException(e.getMessage(), e.getCause());
        }

        return deployFlow(bytes, creator, "1");
    }

    /**
     * 根据bytes字节流，部署流程定义
     *
     * @param bytes
     *         流程定义输入流
     */
    public Flow deployFlow(byte[] bytes, String type) {
        return deployFlow(bytes, "stria", type);
    }

    /**
     * 提供流程引擎启动加载，根据bytes字节流部署流程定义
     *
     * @param bytes
     *         流程定义输入流
     * @param creator
     *         创建人
     * @param type
     *         0：数据库资源部署，1：文件资源部署，2：Web部署
     */
    public Flow deployFlow(byte[] bytes, String creator, String type) {
        if (null == bytes || bytes.length == 0)
            throw new StriaException("流程定义资源为空或者null");
        try {
            FlowModel model;
            ThreeTuple<Flow, List<com.dcits.orion.stria.dao.mapper.Node>, List<Line>> tuple;
            if ('<' == bytes[0]) {
                model = FlowParser.parseXml(bytes);
                tuple = FlowParser.convertJson2Model(FlowParser.convertXml2Json(bytes));
            } else {
                model = FlowParser.parseJson(bytes);
                tuple = FlowParser.convertJson2Model(FlowParser.convert2Json(bytes));
            }
            Flow entity = tuple.first;
            entity.setFlowModel(model);
            // 持久化流程 ,只有文件资源部署，或者重新部署时。将流程定义持久化到数据库
            if ("1".equals(type) || "2".equals(type))
                saveFlow(entity, tuple.second, tuple.three);
            // 更新缓存工厂 ，后期考虑刷新集群所有节点
            refreshFLow(entity);
            return entity;
        } catch (Exception e) {
            throw new StriaException(e.getMessage(), e.getCause());
        }
    }

    /**
     * 提供流程引擎启动加载，根据bytes字节流部署流程定义
     *
     * @param flowJson
     *         流程定义Json
     */
    public Flow deployFlow(JSONObject flowJson) {
        if (null == flowJson || flowJson.size() == 0)
            throw new StriaException("流程定义资源为空或者null");
        try {
            ThreeTuple<Flow, List<com.dcits.orion.stria.dao.mapper.Node>, List<Line>> tuple = FlowParser.convertJson2Model(flowJson);
            FlowModel model = FlowParser.parseJson(flowJson);
            Flow entity = tuple.first;
            entity.setFlowModel(model);
            // 更新缓存工厂 ，后期考虑刷新集群所有节点
            refreshFLow(entity);
            return entity;
        } catch (Exception e) {
            throw new StriaException(e.getMessage(), e);
        }
    }

    /**
     * 提供web页面流程定义，更新流程数据，以及刷新流程缓存
     *
     * @param flow
     * @param creator
     */
    @Override
    @Transactional
    public void deploy(JSONObject flow, String creator) {
        FlowModel model = FlowParser.parseJson(flow);
        ThreeTuple<Flow, List<com.dcits.orion.stria.dao.mapper.Node>, List<Line>> tuple = FlowParser.convertJson2Model(flow);
        Flow entity = tuple.first;
        entity.setCreator(creator);
        entity.setFlowModel(model);
        saveFlow(entity, tuple.second, tuple.three);
        // 更新缓存工厂 ，后期考虑刷新集群所有节点
        refreshFLow(entity);
    }

    /**
     * 卸载指定的流程定义，只更新状态
     *
     * @param id
     *         流程定义id
     */
    @Override
    @Transactional
    public void unDeploy(String id) {
        // 待实现
    }

    @Override
    @Transactional
    public void cacheRemove(String id) {
        // 待实现
    }

    @Override
    public JSONArray getFLow() {
        return getFLow(null);
    }

    @Override
    public JSONArray getFLow(String flowType) {
        JSONArray flowArray = new JSONArray();
        List<Flow> flows = flowDao.selectList(flowType);
        for (Flow flow : flows) {
            JSONObject flowJson = new JSONObject();
            flowJson.put("flowid", flow.getFlowid() == null ? "" : flow.getFlowid());
            flowJson.put("title", flow.getTitle() == null ? "" : flow.getTitle());
            flowJson.put("creator", flow.getCreator() == null ? "" : flow.getCreator());
            flowJson.put("state", flow.getState().intValue() == 1 ? "开启" : "关闭");
            flowJson.put("createtime", flow.getCreatetime() == null ? "" : flow.getCreatetime());
            flowJson.put("flowType", flow.getFlowType());
            //服务流程返回，全局事务开关和超时时间
            if (flow.getFlowType().equals("01")) {
                flowJson.put("dtpFlag", flow.getDtpFlag().equals("Y") ? "开启" : "关闭");
                flowJson.put("txFlag", flow.getTxFlag().equals("Y") ? "开启" : "关闭");
                flowJson.put("timeOut", flow.getTimeOut() == null ? "" : flow.getTimeOut());
            } else if (flow.getFlowType().equals("02")) {
                flowJson.put("prodType", flow.getProdType());
                flowJson.put("eventType", flow.getEventType());
            }
            //不再返回流程定义内容，通过getFlow获取流程定义
            //flowJosn.put("content", getFLow(flow.getFlowid()));
            flowArray.add(flowJson);
        }
        return flowArray;
    }

    @Override
    public JSONObject getFLowById(String flowid) {
        Flow flow = new Flow();
        flow.setFlowid(flowid);
        flow = flowDao.select(flow);
        if (null != flow) {
            // new jsonObject
            JSONObject flowJson = new JSONObject();
            flowJson.put("flowid", flow.getFlowid() == null ? "" : flow.getFlowid());
            flowJson.put("initNum", flow.getInitNum() == null ? 0 : flow.getInitNum());
            flowJson.put("title", flow.getTitle() == null ? "" : flow.getTitle());
            flowJson.put("state", flow.getState() == null ? "" : flow.getState());
            flowJson.put("dtpFlag", flow.getDtpFlag() == null ? "" : flow.getDtpFlag());
            flowJson.put("txFlag", flow.getTxFlag() == null ? "" : flow.getTxFlag());
            flowJson.put("timeOut", flow.getTimeOut() == null ? -1 : flow.getTimeOut());
            flowJson.put("prodType", flow.getProdType() == null ? "" : flow.getProdType());
            flowJson.put("eventType", flow.getEventType() == null ? "" : flow.getEventType());

            Line line = new Line();
            line.setFlowid(flow.getFlowid());
            List<Line> lines = lineDao.selectList(line);
            JSONObject ls = new JSONObject();
            for (Line l : lines) {
                JSONObject jDetail = new JSONObject();
                jDetail.put("expr", l.getExpr() == null ? "" : l.getExpr());
                jDetail.put("from", l.getFromNode() == null ? "" : l.getFromNode());
                jDetail.put("name", l.getName() == null ? "" : l.getName());
                jDetail.put("to", l.getToNode() == null ? "" : l.getToNode());
                jDetail.put("type", l.getType() == null ? "" : l.getType());
                ls.put(l.getId(), jDetail);
            }
            flowJson.put("lines", ls);

            Node node = new Node();
            node.setFlowid(flow.getFlowid());
            List<Node> nodes = nodeDao.selectList(node);
            JSONObject ns = new JSONObject();
            for (Node n : nodes) {
                JSONObject jDetail = new JSONObject();
                jDetail.put("args", n.getArgs() == null ? "" : n.getArgs());
                jDetail.put("argsClazz", n.getArgsClazz() == null ? "" : n.getArgsClazz());
                jDetail.put("clazz", n.getClazz() == null ? "" : n.getClazz());
                jDetail.put("methodName", n.getMethodName() == null ? "" : n.getMethodName());
                jDetail.put("name", n.getName() == null ? "" : n.getName());
                jDetail.put("sourceType", n.getSourceType() == null ? "" : n.getSourceType());
                jDetail.put("serviceType", n.getServiceType() == null ? "" : n.getServiceType());
                jDetail.put("width", n.getDivWidth() == null ? -1 : n.getDivWidth());
                jDetail.put("height", n.getDivHeight() == null ? -1 : n.getDivHeight());
                jDetail.put("left", n.getCanvasLeft() == null ? -1 : n.getCanvasLeft());
                jDetail.put("top", n.getCanvasTop() == null ? -1 : n.getCanvasTop());
                jDetail.put("type", n.getType() == null ? "" : n.getType());
                jDetail.put("alias", n.getVar() == null ? "" : n.getVar());
                jDetail.put("condition", n.getSubCondition() == null ? "" : n.getSubCondition());
                jDetail.put("request", n.getRequest() == null ? "" : n.getRequest());
                jDetail.put("serviceCode", n.getServiceCode() == null ? "" : n.getServiceCode());
                jDetail.put("messageType", n.getMessageType() == null ? "" : n.getMessageType());
                jDetail.put("messageCode", n.getMessageCode() == null ? "" : n.getMessageCode());
                jDetail.put("eventId", n.getEventId() == null ? "" : n.getEventId());
                ns.put(n.getId(), jDetail);
            }
            flowJson.put("nodes", ns);
            return flowJson;
        }
        return null;
    }

    @Override
    public JSONArray getAllNodeClazz() {
        return (JSONArray) JSON.toJSON(nodeDao.selectAllClazz());
    }

    @Override
    public JSONArray getAllMethodName() {
        return (JSONArray) JSON.toJSON(nodeDao.selectAllMethodName());
    }

    /**
     * 刷新缓存
     *
     * @param flow
     */
    private void refreshFLow(Flow flow) {
        if (NONE.equals(cacheType)) {
            if (log.isDebugEnabled())
                log.debug("缓存未开启");
        } else if (REDIS.equals(cacheType)) {
            // 刷新Radis集群
            if (log.isDebugEnabled())
                log.debug("刷新[" + flow.getFlowid() + "]流程定义模型全局缓存");
            JedisUtils.setObjectResource(CACHEKEY, flow.getFlowid(), flow);
        } else if (LOCAL.equals(cacheType)) {
            // 本地缓存
            if (log.isDebugEnabled())
                log.debug("刷新[" + flow.getFlowid() + "]流程定义模型本地缓存");
            this.flows.put(flow.getFlowid(), flow);
        }
    }

    /**
     * 清除缓存
     *
     * @param flow
     */
    private void removeFLow(Flow flow) {
        if (NONE.equals(cacheType)) {
            if (log.isDebugEnabled())
                log.debug("缓存未开启");
        } else if (REDIS.equals(cacheType)) { // 清除Radis集群
            if (JedisUtils.hexistsObjectKey(CACHEKEY, flow.getFlowid())) {
                if (log.isDebugEnabled())
                    log.debug("清除[" + flow.getFlowid() + "]流程定义模型全局缓存");
                JedisUtils.delResource(CACHEKEY, flow.getFlowid());
            }
        } else if (LOCAL.equals(cacheType)) {
            // 本地缓存
            if (this.flows.containsKey(flow.getFlowid())) {
                if (log.isDebugEnabled())
                    log.debug("清除[" + flow.getFlowid() + "]流程定义模型本地缓存");
                this.flows.remove(flow.getFlowid());
            }
        }
    }

    /**
     * 缓存是否存在流程定义
     *
     * @param flowid
     * @return
     */
    public boolean containsKeyForCache(String flowid) {
        if (NONE.equals(cacheType)) {
            if (log.isDebugEnabled())
                log.debug("缓存未开启");
            return false;
        } else if (REDIS.equals(cacheType)) {
            if (log.isDebugEnabled())
                log.debug("全局缓存检查[" + flowid + "]是否存在流程定义");
            // Radis集群
            return JedisUtils.hexistsObjectKey(CACHEKEY, flowid);
        } else if (LOCAL.equals(cacheType)) {
            if (log.isDebugEnabled())
                log.debug("本地缓存检查[" + flowid + "]是否存在流程定义");
            // 本地缓存
            return this.flows.containsKey(flowid);
        }
        return false;
    }

    /**
     * 缓存中获取流程
     *
     * @param flowid
     * @return
     */
    public Flow getFlowForCache(String flowid) {
        if (NONE.equals(cacheType)) {
            return null;
        } else if (REDIS.equals(cacheType)) {
            // Radis集群获取
            if (log.isDebugEnabled())
                log.debug("从全局缓存获取[" + flowid + "]流程定义模型");
            return JedisUtils.getObjectResource(CACHEKEY, flowid);
        } else if (LOCAL.equals(cacheType)) {
            // 本地缓存获取
            if (log.isDebugEnabled())
                log.debug("从本地缓存获取[" + flowid + "]流程定义模型");
            return this.flows.get(flowid);
        }
        return null;
    }

    /**
     * 获取缓存类型
     * 获取galaxy.business.stria.cache属性进行判断
     * none：不开启缓存 local：本地缓存 redis：内存数据库
     * 默认使用Redis存储
     *
     * @return
     */
    public String useCache() {
        String type = ConfigUtils.getProperty("galaxy.business.stria.cache");
        /**
         * 默认开启REDIS缓存
         */
        if (null == type) return REDIS;
        if (NONE.equals(type)) {
            return NONE;
        } else if (LOCAL.equals(type)) {
            return LOCAL;
        } else {
            return REDIS;
        }
    }

    /**
     * Invoked by a BeanFactory after it has set all bean properties supplied
     * (and satisfied BeanFactoryAware and ApplicationContextAware).
     * <p>This method allows the bean instance to perform initialization only
     * possible when all bean properties have been set and to throw an
     * exception in the event of misconfiguration.
     *
     * @exception Exception
     *         in the event of misconfiguration (such
     *         as failure to set an essential property) or if initialization fails.
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        FlowService.cacheType = this.useCache();
        StriaUtil.f = this;
    }
}
