package com.dcits.orion.scp.job;

import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.alibaba.fastjson.JSON;
import com.dcits.galaxy.base.tuple.ThreeTuple;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.core.client.ServiceProxy;
import com.dcits.galaxy.core.client.builder.ServiceAttributesBuilder;
import com.dcits.orion.batch.api.IQuartzManager;
import com.dcits.orion.batch.api.bean.ITaskParam;
import com.dcits.orion.batch.common.BatchUtils;
import com.dcits.orion.scp.api.rpc.IFwFlowService;
import com.dcits.orion.scp.dao.ScpDao;
import com.dcits.orion.scp.engine.ScpEngine;
import com.dcits.orion.scp.factory.FlowFactory;
import com.dcits.orion.scp.model.Flow;
import com.dcits.orion.scp.model.ServiceNode;
import com.dcits.orion.scp.utils.ScpConstants;
import com.dcits.orion.scp.utils.ScpLogUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixiaobin on 2017/6/28.
 */
@Repository
public class TimerDeal {

    @Resource
    ScpDao scpDao;

    @Resource
    ScpEngine scpEngine;

    private long timeOut = 1000 * 60 * 1;

    private long expireTime = 1000 * 60 * 60 * 24;

    public void deal(ITaskParam taskParam) {
        if (!StringUtils.isBlank(taskParam.getStaticParam()))
            timeOut = BatchUtils.parseLong(taskParam.getStaticParam());
        dealUnFinishFlow();
        dealTimerReversal();
    }

    //处理未完成的流程
    private void dealUnFinishFlow() {
        long now = System.currentTimeMillis();
        long begin = now - expireTime;
        long end = now - timeOut;
        Map map = new HashMap<>();
        map.put("BEGIN", begin);
        map.put("END",end);
        List<Map> flowLogs = scpDao.queryUnFinishFlow(map);
        if (flowLogs != null && !flowLogs.isEmpty()) {
            for (Map flowLog : flowLogs) {
                String appId = (String) flowLog.get("APP_ID");
                if (!isAppAlive(appId)) {
                    String flowId = (String) flowLog.get("FLOW_ID");
                    String status = (String) flowLog.get("STATUS");
                    String reversalStatus = (String) flowLog.get("REVERSAL_STATUS");
                    Flow flow = scpDao.getFlow(flowId);
                    String keyValue = (String) flowLog.get("KEY_VALUE");
                    if ("N".equals(status))//未完成的流程
                    {
                        if ("R".equals(flow.getResumeType()))//流程冲正
                        {
                            ScpLogUtils.updateFlowLog(flow, keyValue, null, "E", "R", null);
                        } else if ("C".equals(flow.getResumeType()))//流程继续
                        {
                            String inMsg = (String) flowLog.get("IN_MSG");
                            Map request = JSON.parseObject(inMsg);
                            scpEngine.resume(request, flow);
                        } else if ("N".equals(flow.getResumeType()))//流程不恢复,更新流程状态为异常
                        {
                            ScpLogUtils.updateFlowLog(flow, keyValue, null, "E", null, null);
                        }
                    } else if ("P".equals(reversalStatus)) {
                        ScpLogUtils.updateFlowLog(flow, keyValue, null, null, "R", null);
                    }
                }
            }
        }
    }

    //处理定时器冲正的流程
    private void dealTimerReversal() {
        List<Map> flowLogs = scpDao.queryTimerReversalFlow();
        if (flowLogs != null && !flowLogs.isEmpty()) {
            for (Map flowLog : flowLogs) {
                String keyValue = (String) flowLog.get("KEY_VALUE");
                String flowId = (String) flowLog.get("FLOW_ID");
                Object reversalCountObj = flowLog.get("REVERSAL_COUNT");
                int reversalCount = 1;
                if (reversalCountObj != null) {
                    reversalCount = BatchUtils.parseInt(reversalCountObj);
                    reversalCount = reversalCount + 1;
                }
                Flow flow = scpDao.getFlow(flowId);
                List<Map> nodeLogs = scpDao.queryToReversalNode(flowLog);
                List<ThreeTuple<ServiceNode, Map, Integer>> reversalServices = new ArrayList<>();
                if (nodeLogs != null && !nodeLogs.isEmpty()) {
                    for (Map nodeLog : nodeLogs) {
                        String reversalMsg = (String) nodeLog.get("REVERSAL_MSG");
                        if (!StringUtils.isBlank(reversalMsg)) {
                            String nodeId = (String) nodeLog.get("NODE_ID");
                            ServiceNode serviceNode = flow.getNodeMap().get(nodeId);
                            if ("C".equals(serviceNode.getNodeType())) {
                                String status = (String) nodeLog.get("STATUS");
                                if (("N".equals(status) || "E".equals(status)) && (serviceNode.getErrorDeal() == null || !serviceNode.getErrorDeal().endsWith("R"))) {
                                    continue;
                                }
                                Map reversalRequest = JSON.parseObject(reversalMsg);
                                int seqNo = BatchUtils.parseInt(nodeLog.get("SEQ_NO"));
                                ThreeTuple<ServiceNode, Map, Integer> reversalService = new ThreeTuple<>(serviceNode, reversalRequest, seqNo);
                                reversalServices.add(reversalService);
                            }
                        }
                    }
                }
                if (!reversalServices.isEmpty()) {
                    scpEngine.reversal(flow, keyValue, reversalServices, reversalCount);

                } else {
                    ScpLogUtils.updateFlowLog(flow, keyValue, null, null, "S", null);
                }
            }
        }
    }

    //判断应用是否存活
    private boolean isAppAlive(String appId) {
        try {
            String host = appId.split(ScpConstants.split)[0];
            ServiceAttributesBuilder builder = new ServiceAttributesBuilder()
                    .setScope(ServiceAttributesBuilder.SCOPE_REMOTE)
                    .setGroup(ConfigUtils.getProperty("galaxy.application.group"))
                    .setUrl("dubbo://" + host);
            IFwFlowService iFwFlowService = ServiceProxy.getInstance().getService(IFwFlowService.class, builder.build());
            String otherAppId = iFwFlowService.queryAppID();
            if (appId.equals(otherAppId))
                return true;
        }
        catch (Exception e)
        {

        }
        return false;
    }

    public ScpDao getScpDao() {
        return scpDao;
    }

    public void setScpDao(ScpDao scpDao) {
        this.scpDao = scpDao;
    }

    public ScpEngine getScpEngine() {
        return scpEngine;
    }

    public void setScpEngine(ScpEngine scpEngine) {
        this.scpEngine = scpEngine;
    }

    public long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }
}