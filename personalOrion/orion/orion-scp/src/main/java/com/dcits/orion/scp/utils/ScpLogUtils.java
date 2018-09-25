package com.dcits.orion.scp.utils;

import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.orion.batch.common.BatchUtils;
import com.dcits.orion.scp.api.exception.UnknownException;
import com.dcits.orion.scp.dao.ScpDao;
import com.dcits.orion.scp.model.Flow;
import com.dcits.orion.scp.model.ServiceNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lixiaobin on 2017/3/20.
 */
public class ScpLogUtils {

    private static final Logger log = LoggerFactory
            .getLogger(ScpLogUtils.class);
    public static void recordFlowLog(Flow flow,Map cacheMap)
    {
        if (!"Y".equals(flow.getLogFlag()))
            return;
        Map map = new HashMap();
        map.put("FLOW_ID",flow.getFlowId());
        map.put("KEY_VALUE",cacheMap.get(ScpConstants.keyFieldValue));
        map.put("BEGIN_TIME",System.currentTimeMillis());
        map.put("STATUS","N");
        map.put("AFTER_STATUS","N");
        map.put("REVERSAL_STATUS","N");
        map.put("IN_MSG", ScpUtils.getString(cacheMap.get(ScpConstants.request)));
        map.put("APP_ID",cacheMap.get(ScpConstants.appId));

        if (ScpUtils.existMapper(flow.getFlowId(),ScpConstants.logFields))
        {
            Map logFields = ScpUtils.getArg(cacheMap,flow.getFlowId(),ScpConstants.logFields);
            map.put(ScpConstants.logFields,logFields);
        }
        getScpDao().insertFlowLog(map);
    }
    public static Map queryFlowLog(Flow flow,String keyValue)
    {
        Map map = new HashMap();
        map.put("FLOW_ID",flow.getFlowId());
        map.put("KEY_VALUE", keyValue);
        return getScpDao().queryFlowLog(map);
    }
    public static void updateFlowLog(Flow flow,String keyValue,Map response,String status,String reversalStatus,String afterStatus)
    {
        updateFlowLog(flow,keyValue,response,status,reversalStatus,afterStatus,0);
    }
    public static void updateFlowLog(Flow flow,String keyValue,Map response,String status,String reversalStatus,String afterStatus,int reversalSeqNo)
    {
        if (!"Y".equals(flow.getLogFlag()))
            return;
        Map map = new HashMap();
        map.put("FLOW_ID",flow.getFlowId());
        map.put("KEY_VALUE",keyValue);
        map.put("END_TIME",System.currentTimeMillis());
        map.put("STATUS",status);
        map.put("AFTER_STATUS",afterStatus);
        map.put("REVERSAL_STATUS",reversalStatus);
        if (reversalSeqNo > 0)
            map.put("REVERSAL_COUNT",reversalSeqNo);
        if (response != null)
            map.put("OUT_MSG",ScpUtils.getString(response));
        try {
            getScpDao().updateFlowLog(map);
        }
        catch (Throwable t)
        {
            if (log.isWarnEnabled()) {
                log.warn("更新fw_flow_log失败：");
                log.warn(ExceptionUtils.getStackTrace(t));
            }
        }

    }
    public static void recordNodeLog(Flow flow, Map cacheMap, ServiceNode serviceNode,int seqNo) throws UnknownException {
        if (!"Y".equals(flow.getLogFlag()))
            return;
        if (!"C".equals(serviceNode.getNodeType()) && !"C".equals(flow.getResumeType()))
            return;
        Map map = new HashMap();
        map.put("FLOW_ID",flow.getFlowId());
        map.put("NODE_ID",serviceNode.getNodeId());
        map.put("KEY_VALUE",cacheMap.get(ScpConstants.keyFieldValue));
        map.put("SEQ_NO",seqNo);
        map.put("BEGIN_TIME",System.currentTimeMillis());
        map.put("STATUS","N");
        map.put("REVERSAL_STATUS","N");
        Map inMsg = ScpUtils.getArg(cacheMap,flow.getFlowId(),serviceNode.getIn());
        map.put("IN_MSG",ScpUtils.getString(inMsg));
        if (ScpUtils.existMapper(flow.getFlowId(),serviceNode.getConfirm()))
        {
            Map confirmMsg;
            try {
                confirmMsg = ScpUtils.getArg(cacheMap, flow.getFlowId(), serviceNode.getConfirm());
                if (confirmMsg != null)
                    map.put("CONFIRM_MSG",ScpUtils.getString(confirmMsg));
            }
            catch (Exception e)
            {
            }
        }


        if (ScpUtils.existMapper(flow.getFlowId(),serviceNode.getReversal()))
        {
            Map reversalMsg;
            try
            {
                reversalMsg = ScpUtils.getArg(cacheMap,flow.getFlowId(),serviceNode.getReversal());
                if (reversalMsg != null)
                    map.put("REVERSAL_MSG",ScpUtils.getString(reversalMsg));
            }
            catch (Exception e)
            {

            }
        }
        if (ScpUtils.existMapper(flow.getFlowId(),serviceNode.getLogFields()))
        {
            Map logFields = ScpUtils.getArg(cacheMap,flow.getFlowId(),serviceNode.getLogFields());;
            if (logFields != null)
                map.put(ScpConstants.logFields,logFields);
        }
        getScpDao().insertNodeLog(map);
    }
    public static void updateNodeLog(Flow flow, String keyValue, ServiceNode serviceNode,Map response,Map reversalMsg,String status,String reversalStatus,int seqNo)
    {
        if (!"Y".equals(flow.getLogFlag()))
            return;
        if (!"C".equals(serviceNode.getNodeType()) && !"C".equals(flow.getResumeType()))
            return;
        Map map = new HashMap();
        map.put("FLOW_ID",flow.getFlowId());
        map.put("NODE_ID",serviceNode.getNodeId());
        map.put("KEY_VALUE",keyValue);
        map.put("SEQ_NO",seqNo);
        map.put("END_TIME",System.currentTimeMillis());
        map.put("STATUS",status);
        map.put("REVERSAL_STATUS",reversalStatus);
        if (response != null)
            map.put("OUT_MSG",ScpUtils.getString(response));
        if (reversalMsg != null)
            map.put("REVERSAL_MSG",ScpUtils.getString(reversalMsg));
        try {
            getScpDao().updateNodeLog(map);
        }
        catch (Throwable t)
        {
            if (log.isWarnEnabled()) {
                log.warn("更新fw_flow_node_log失败：");
                log.warn(ExceptionUtils.getStackTrace(t));
            }
        }
    }

    public static void recordNodeReversalLog(Flow flow,String keyValue ,ServiceNode serviceNode,int seqNo,Map request,Map response,String reversalStatus,long beginTime,long endTime)
    {
        if (!"Y".equals(flow.getLogFlag()))
            return;
        if (!"C".equals(serviceNode.getNodeType()))
            return;
        Map map = new HashMap();
        map.put("FLOW_ID",flow.getFlowId());
        map.put("NODE_ID",serviceNode.getNodeId());
        map.put("KEY_VALUE",keyValue);
        map.put("SEQ_NO",seqNo);
        map.put("BEGIN_TIME",beginTime);
        map.put("END_TIME",endTime);
        map.put("REVERSAL_STATUS",reversalStatus);
        if (request != null)
            map.put("IN_MSG",ScpUtils.getString(request));
        if (response != null)
            map.put("OUT_MSG",ScpUtils.getString(response));
        getScpDao().recordNodeReversalLog(map);
    }

    private static ScpDao getScpDao()
    {
        return SpringApplicationContext.getContext().getBean(ScpDao.class);
    }

    public static Map queryNodeLog(String flowId,String keyValue,String nodeId,int seqNo)
    {
        Map param = new HashMap();
        param.put("FLOW_ID",flowId);
        param.put("KEY_VALUE",keyValue);
        param.put("NODE_ID",nodeId);
        param.put("SEQ_NO",seqNo);
        return getScpDao().queryNodeLog(param);

    }
}
