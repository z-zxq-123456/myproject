package com.dcits.orion.scp.system.impl;

import com.alibaba.fastjson.JSON;
import com.dcits.galaxy.base.tuple.ThreeTuple;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.orion.batch.common.BatchUtils;
import com.dcits.orion.scp.api.ISystem;
import com.dcits.orion.scp.dao.ScpDao;
import com.dcits.orion.scp.engine.ScpEngine;
import com.dcits.orion.scp.factory.FlowFactory;
import com.dcits.orion.scp.factory.SystemFactory;
import com.dcits.orion.scp.mapping.DataMapping;
import com.dcits.orion.scp.model.Flow;
import com.dcits.orion.scp.model.ServiceNode;
import com.dcits.orion.scp.utils.ScpConstants;
import com.dcits.orion.scp.utils.ScpUtils;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixiaobin on 2017/6/27.
 */
public abstract class AbstractReversalSystem implements ISystem {

    @Resource
    ScpEngine scpEngine;

    @Resource
    DataMapping dataMapping;

    @Resource
    ScpDao scpDao;

    private String keyValueExpr;

    private String flowIdExpr;



    private Map getParam(Map request)
    {
        Map cacheMap = new HashMap();
        cacheMap.put(ScpConstants.in,request);
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext(cacheMap);

        Map param = new HashMap();
        if (!StringUtils.isBlank(flowIdExpr))
        {
            param.put("FLOW_ID",dataMapping.getValue(parser,context,flowIdExpr));
        }
        param.put("KEY_VALUE",dataMapping.getValue(parser,context,keyValueExpr));
        return param;
    }


    public abstract Map flowLogCheck(List<Map> flowLogs,Map request);
    /*{
        if (flowLogs.size() > 1)
        {
            return ScpUtils.errorReturn("999999","被冲正交易不唯一");
        }
        Map flowLog = flowLogs.get(0);
        String flowStatus = (String) flowLog.get("STATUS");
        if (!"S".equals(flowStatus))
            return ScpUtils.errorReturn("999999","原交易未成功完成，不允许冲正!");
        String flowReversalStatus = (String)flowLog.get("REVERSAL_STATUS");
        if ("P".equals(flowReversalStatus) || "R".equals(flowReversalStatus))
        {
            return ScpUtils.errorReturn("999999","原交易正在冲正中!");
        }
        if ("S".equals(flowReversalStatus) )
        {
            return ScpUtils.errorReturn("000000","原交易已被冲正!");
        }
        return null;
    }*/


    @Override
    public Map execute(Map request) {

        Map<String,String>param = getParam(request);
        String keyValue = param.get("KEY_VALUE");
        List<Map>flowLogs = scpDao.queryFlowLogs(param);
        Map flowLog = flowLogs.get(0);
        Map flowCheckResult = this.flowLogCheck(flowLogs,request);
        if (flowCheckResult != null)
            return flowCheckResult;
        List<Map> nodeLogs = scpDao.queryCommitted(flowLog);
        if (nodeLogs == null || nodeLogs.isEmpty())
        {
            return ScpUtils.errorReturn("000000","原交易无已提交的服务");
        }
        String flowId = (String)flowLog.get("FLOW_ID");
        Flow flow = scpDao.getFlow(flowId);

        List<ThreeTuple<ServiceNode,Map,Integer>> reversalServices = new ArrayList<>();
        for (Map nodeLog : nodeLogs)
        {

            String reversalMsg = (String)nodeLog.get("REVERSAL_MSG");
            if (!StringUtils.isBlank(reversalMsg)) {
                String nodeId = (String) nodeLog.get("NODE_ID");
                ServiceNode serviceNode = flow.getNodeMap().get(nodeId);
                Map reversalRequest = JSON.parseObject(reversalMsg);
                int seqNo = BatchUtils.parseInt(nodeLog.get("SEQ_NO"));
                ThreeTuple<ServiceNode, Map, Integer> reversalService = new ThreeTuple<>(serviceNode, reversalRequest, seqNo);
                reversalServices.add(reversalService);
            }
        }
        Object reversalCountObj = flowLog.get("REVERSAL_COUNT");
        int reversalCount = 1;
        if (reversalCountObj != null) {
            reversalCount = BatchUtils.parseInt(reversalCountObj);
            reversalCount = reversalCount + 1;
        }
        return scpEngine.reversal(flow,keyValue,reversalServices,reversalCount);
    }

    @Override
    public List<Map> getErrors(Map response) {
        return null;
    }

    @Override
    public boolean isError(Map response) {
        return false;
    }

    @Override
    public boolean isReversalAgain(Map response) {
        return false;
    }

    @Override
    public boolean isConfirmAgain(Map response) {
        return false;
    }

    public String getKeyValueExpr() {
        return keyValueExpr;
    }

    public void setKeyValueExpr(String keyValueExpr) {
        this.keyValueExpr = keyValueExpr;
    }

    public String getFlowIdExpr() {
        return flowIdExpr;
    }

    public void setFlowIdExpr(String flowIdExpr) {
        this.flowIdExpr = flowIdExpr;
    }
}
