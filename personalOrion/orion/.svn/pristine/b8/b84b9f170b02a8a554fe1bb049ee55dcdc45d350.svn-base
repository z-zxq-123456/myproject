package com.dcits.orion.scp.dao;

import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.orion.batch.common.BatchUtils;
import com.dcits.orion.batch.common.bean.CommTableObj;
import com.dcits.orion.batch.common.dao.AutoMapperDao;
import com.dcits.orion.scp.model.Flow;
import com.dcits.orion.scp.model.ServiceNode;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lixiaobin on 2017/3/14.
 */
@Repository
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ScpDao {

    @Resource
    ShardSqlSessionTemplate shardSqlSessionTemplate;
    @Resource
    AutoMapperDao autoMapperDao;

    private static final String NAMESPACE = "com.dcits.orion.scp.dao.ScpDao";
    Map<String,Flow> flowCache = new ConcurrentHashMap();

    public Flow getFlow(String flowId)
    {

        Flow flow = flowCache.get(flowId);
        if (flow != null)
            return flow;

        CommTableObj flowTab = new CommTableObj("FW_FLOW_DEF");
        flowTab.setCondition("FLOW_ID",flowId);
        Map flowMap = autoMapperDao.selectOne(flowTab);
        if (flowMap == null)
            return null;

        flow = autoMapperDao.mapToBean(flowMap,Flow.class);
        CommTableObj nodeTab = new CommTableObj("FW_FLOW_NODE");
        nodeTab.setCondition("FLOW_ID",flowId);
        List<Map> nodeMaps = autoMapperDao.selectList(nodeTab);

        Map<String,ServiceNode> nodeMap = new HashMap<>();
        for (Map node : nodeMaps)
        {
            ServiceNode serviceNode = autoMapperDao.mapToBean(node,ServiceNode.class);
            switch (serviceNode.getNodeType())//查询服务和授权服务的错误策略为终止,未知策略为重试
            {
                case "Q":
                case "A":
                    serviceNode.setErrorDeal("N");
                    serviceNode.setUnknowDeal("SN");
            }
            serviceNode.setFlow(flow);
            nodeMap.put(serviceNode.getNodeId(),serviceNode);
        }
        flow.setNodeMap(nodeMap);

        Map<String,List<String>> lines = new HashMap<>();
        Map<String,String> lineExpr = new HashMap<>();
        CommTableObj lineTab = new CommTableObj("FW_FLOW_LINE");
        lineTab.setCondition("FLOW_ID",flowId);
        List<Map> lineMaps = autoMapperDao.selectList(lineTab);
        for (Map<String,String> line : lineMaps)
        {
            String fromNodeId = line.get("FROM_NODE");
            String toNodeId = line.get("TO_NODE");
            List<String> toNodes = lines.get(fromNodeId);
            if (toNodes == null)
            {
                toNodes = new ArrayList<>();
                lines.put(fromNodeId,toNodes);
            }
            toNodes.add(toNodeId);
            String lineID = fromNodeId + "-" +toNodeId;
            String expr = line.get("EXPR");
            if (!StringUtils.isBlank(expr))
                lineExpr.put(lineID,expr);
        }
        flow.setLineExpr(lineExpr);
        flow.setLines(lines);
        ServiceNode startNode = nodeMap.get("START_NODE");
        flow.setStartNode(startNode);
        ServiceNode returnNode = nodeMap.get("RETURN_NODE");
        flow.setReturnNode(returnNode);

        //flowCache.put(flowId,flow);
        return flow;
    }

    public void insertFlowLog(Map map)
    {
        shardSqlSessionTemplate.insert(NAMESPACE,"insertFlowLog",map);
    }
    public void updateFlowLog(Map map)
    {
        shardSqlSessionTemplate.update(NAMESPACE,"updateFlowLog",map);
    }

    public void insertNodeLog(Map map)
    {
        shardSqlSessionTemplate.insert(NAMESPACE,"insertNodeLog",map);
    }
    public void updateNodeLog(Map map)
    {
        shardSqlSessionTemplate.update(NAMESPACE,"updateNodeLog",map);
    }
    public Map queryFlowLog(Map map)
    {
        return (Map)shardSqlSessionTemplate.selectOne(NAMESPACE, "queryFlowLog", map);
    }

    public List queryFlowLogs(Map map)
    {
        return shardSqlSessionTemplate.selectList(NAMESPACE, "queryFlowLog", map);
    }
    public List queryCommitted(Map map)
    {
        return shardSqlSessionTemplate.selectList(NAMESPACE, "queryCommitted", map);
    }
    public void recordNodeReversalLog(Map map){
        shardSqlSessionTemplate.insert(NAMESPACE,"insertNodeReversalLog",map);
    }

    public List queryUnFinishFlow(Map map){
        return shardSqlSessionTemplate.selectList(NAMESPACE, "queryUnFinishFlow", map);
    }
    public List queryTimerReversalFlow(){
        return shardSqlSessionTemplate.selectList(NAMESPACE, "queryTimerReversalFlow");
    }
    public List queryToReversalNode(Map map){
        return shardSqlSessionTemplate.selectList(NAMESPACE, "queryToReversalNode", map);
    }
    public Map queryNodeLog(Map map)
    {
        return (Map)shardSqlSessionTemplate.selectOne(NAMESPACE, "queryNodeLog", map);
    }

}
