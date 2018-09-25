package com.dcits.orion.scp.engine;

import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.alibaba.fastjson.JSON;
import com.dcits.galaxy.base.tuple.ThreeTuple;
import com.dcits.galaxy.base.tuple.TwoTuple;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.base.util.JsonUtils;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.core.threadpool.support.NamedThreadFactory;
import com.dcits.orion.batch.common.BatchUtils;
import com.dcits.orion.scp.api.exception.UnknownException;
import com.dcits.orion.scp.model.ServiceNode;
import com.dcits.orion.scp.utils.ScpConstants;
import com.dcits.orion.scp.model.Flow;
import com.dcits.orion.scp.utils.ScpLogUtils;
import com.dcits.orion.scp.utils.ScpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by lixiaobin on 2017/3/14.
 */
@Repository
public class ScpEngine implements InitializingBean{

    private static final Logger log = LoggerFactory
            .getLogger(ScpEngine.class);

    private String appId;


    ExecutorService executorService;

    public void resume(Map request,Flow flow)
    {
        execute(request,flow,true);
    }

    public Map execute(Map request,Flow flow)
    {
        return execute(request,flow,false);
    }

    public Map execute(Map request,Flow flow,boolean isResume)
    {
        if(log.isDebugEnabled()) {
            log.debug("流程执行开始：flowId=" + flow.getFlowId()+",是否为恢复流程：" + isResume);
            if (log.isDebugEnabled())
                log.debug("请求报文：" + JsonUtils.formatJson(JSON.toJSONString(request)));
        }
        flow.setExecutorService(executorService);
        long start = System.currentTimeMillis();
        Map cacheMap = new ConcurrentHashMap();
        cacheMap.put(ScpConstants.request,request);
        cacheMap.put(ScpConstants.flowId,flow.getFlowId());
        cacheMap.put(ScpConstants.startTime,start);
        cacheMap.put(ScpConstants.isAfter,false);
        cacheMap.put(ScpConstants.isResume,isResume);
        if (isResume == false) {
            if ("Y".equals(flow.getLogFlag())) {
                if (!StringUtils.isBlank(flow.getKeyFields())) {
                    String keyFieldValue = ScpUtils.getExprString(cacheMap, flow.getKeyFields());
                    Map map = ScpLogUtils.queryFlowLog(flow, keyFieldValue);
                    if (map != null) {
                        String outMsg = (String) map.get("OUT_MSG");
                        if (!StringUtils.isBlank(outMsg)) {
                            return JSON.parseObject(outMsg);
                        } else return ScpUtils.errorReturn("999999", "交易未完成");
                    }
                    cacheMap.put(ScpConstants.keyFieldValue, keyFieldValue);
                } else return ScpUtils.errorReturn("999999", "记录流程日志时,流程识别关键字不能为空！");
            }
            cacheMap.put(ScpConstants.appId, appId);
            ScpLogUtils.recordFlowLog(flow, cacheMap);
        }
        else {
            String keyFieldValue = ScpUtils.getExprString(cacheMap, flow.getKeyFields());
            cacheMap.put(ScpConstants.keyFieldValue, keyFieldValue);
        }
        try
        {
            flow.execute(flow.getStartNode(),cacheMap);
        }
        catch (Exception e)
        {
            log.error(ExceptionUtils.getStackTrace(e));
            ScpUtils.addError("999999","流程异常终止：" + e.getMessage(),cacheMap);
        }

        Map response=null;
        long timeElapsed = System.currentTimeMillis() - start;
        if(log.isInfoEnabled())
            log.info("流程执行完成：flowId=" + flow.getFlowId() +",执行时间=" + timeElapsed);
        if (flow.isTimeOut(start) && isResume == false)//流程执行超时,添加超时异常
        {
            if(log.isDebugEnabled())
                log.debug("流程执行超时：flowId=" + flow.getFlowId());
            ScpUtils.addError(ScpConstants.timeoutRetCode,ScpConstants.timeoutRetMsg,cacheMap);
        }
        try {
            if (ScpUtils.isError(cacheMap)) {//流程执行出错
                //String reversalStatus = null;
                if("R".equals(flow.getErrorDeal()) && ScpUtils.hasCommittedService(cacheMap) && !ScpUtils.isNotReversal(cacheMap)) {
                    ScpUtils.addReversalFlag(cacheMap);
                    ScpLogUtils.updateFlowLog(flow, (String) cacheMap.get(ScpConstants.keyFieldValue), null, "F", "P", null);
                    reversal(flow, cacheMap);
                    //reversalStatus = "P";
                }
                if (ScpUtils.existMapper(flow.getFlowId(),ScpConstants.error))
                    response = ScpUtils.getArg(cacheMap,flow.getFlowId(),ScpConstants.error);
                else
                    response = ScpUtils.errorReturn(cacheMap);
                ScpLogUtils.updateFlowLog(flow, (String)cacheMap.get(ScpConstants.keyFieldValue), response, "F", null, null);
                return response;
            } else {//流程执行成功
                try {
                    response = ScpUtils.getArg(cacheMap, flow.getFlowId(), ScpConstants.out);
                    ScpLogUtils.updateFlowLog(flow, (String)cacheMap.get(ScpConstants.keyFieldValue), response, "S", null, null);
                    asynExecuteAfter(flow, cacheMap);
                    return response;
                } catch (UnknownException e) {
                    ScpUtils.addError(e, cacheMap);
                    response = ScpUtils.errorReturn(cacheMap);
                    ScpLogUtils.updateFlowLog(flow, (String)cacheMap.get(ScpConstants.keyFieldValue), response, "F", null, null);
                    return response;
                }
            }
        }
        finally {
            if (log.isDebugEnabled()&&response!=null)
                log.debug("流程回应报文：" + JsonUtils.formatJson(JSON.toJSONString(response)));
            /*if (ScpUtils.isReversal(cacheMap)) {
                asynReversal(flow, cacheMap);
            }*/
        }
    }

    public Map reversal(Flow flow,String keyValue,List<ThreeTuple<ServiceNode,Map,Integer>> reversalServices)
    {
        return reversal(flow,keyValue,reversalServices,0);
    }

    public Map reversal(Flow flow,String keyValue,List<ThreeTuple<ServiceNode,Map,Integer>> reversalServices,int reversalSeqNo)
    {
        if (reversalServices != null && reversalServices.size() > 0)
        {
            int reversalCount;
            if (reversalSeqNo == 0)
            {
                //ScpLogUtils.updateFlowLog(flow,keyValue,null,null,"P",null);
                reversalCount = 1;
            }
            else {
                reversalCount = reversalSeqNo;

            }

            Map map = new HashMap();
            Map ret = new HashMap();
            map.put(ScpConstants.reversalResults,ret);
            int count = reversalServices.size();
            String reversalStatus = ScpConstants.reversalSuccess;
            for (int i = count - 1; i >= 0; i--)
            {
                ThreeTuple<ServiceNode,Map,Integer> reversalService = reversalServices.get(i);
                ServiceNode serviceNode = reversalService.first;
                Map reversalRequest = reversalService.second;
                int seqNo = reversalService.three;
                TwoTuple<String,Map> result = serviceNode.reversalService(reversalRequest,keyValue,seqNo);
                if (result.second != null)
                    ret.put(serviceNode.getNodeId() ,result.second);
                if ("S".equals(flow.getReversalType()))//倒序冲正
                {
                    if (!"S".equals(result.first))
                    {
                        reversalStatus = result.first;
                        break;
                    }
                }
                else {
                    if (ScpConstants.reversalSuccess.equals(reversalStatus))
                        reversalStatus = result.first;
                    else if(ScpConstants.reversalFail.equals(reversalStatus) && ScpConstants.reversalAgain.equals(result.first))
                        reversalStatus = result.first;
                }
            }
            ScpLogUtils.updateFlowLog(flow,keyValue,null,null,reversalStatus,null,reversalCount);
            map.put(ScpConstants.reversalStatus,reversalStatus);
            return map;
        }
        else
            return ScpUtils.errorReturn("000000","原交易无已提交的服务");

    }

    //同步冲正
    public void reversal(Flow flow, Map cacheMap) {
        List<ThreeTuple<ServiceNode, Map, Integer>> commitServices = ScpUtils.getCommitServices(cacheMap);
        Map reversalMap = reversal(flow, (String) cacheMap.get(ScpConstants.keyFieldValue), commitServices);
        cacheMap.put(ScpConstants.reversalResults,reversalMap);
    }
    //异步冲正
    public void asynReversal(final Flow flow, final Map cacheMap) {
        final List<ThreeTuple<ServiceNode, Map, Integer>> commitServices = ScpUtils.getCommitServices(cacheMap);
        Callable callable = new Callable() {
            @Override
            public Object call() {
                try {
                    reversal(flow, (String) cacheMap.get(ScpConstants.keyFieldValue), commitServices);
                }
                catch (Exception e)
                {
                    log.error(ExceptionUtils.getStackTrace(e));
                }
                return null;
            }
        };
        executorService.submit(callable);
    }

    //异步执行返回节点后的流程
    public void asynExecuteAfter(final Flow flow, final Map cacheMap)
    {
        final ServiceNode returnNode = flow.getReturnNode();
        List<String> afterReturns = flow.getLines().get(returnNode.getNodeId());
        if (afterReturns != null && !afterReturns.isEmpty())
        {
            String afterReturnNodeId = afterReturns.get(0);
            final ServiceNode afterReturnNode = flow.getNodeMap().get(afterReturnNodeId);
            Callable callable = new Callable() {
                @Override
                public Object call() throws Exception {
                    if(log.isDebugEnabled())
                        log.debug("异步执行后置流程：flowId=" + flow.getFlowId());
                    ScpLogUtils.updateFlowLog(flow,(String)cacheMap.get(ScpConstants.keyFieldValue),null,null,null,"P");
                    String afterStatus = "S";
                    long start = System.currentTimeMillis();
                    try {
                        cacheMap.put(ScpConstants.isAfter, true);
                        flow.execute(afterReturnNode, cacheMap);
                        if (ScpUtils.isError(cacheMap))
                            afterStatus = "F";
                    }
                    catch (Exception e)
                    {
                        afterStatus = "E";
                        if (log.isErrorEnabled())
                            log.error(ExceptionUtils.getStackTrace(e));
                    }
                    long timeElapsed = System.currentTimeMillis() - start;
                    ScpLogUtils.updateFlowLog(flow,(String)cacheMap.get(ScpConstants.keyFieldValue),null,null,null,afterStatus);
                    if(log.isDebugEnabled())
                        log.debug("异步执行后置流程完成：flowId=" + flow.getFlowId() +",执行时间=" + timeElapsed+",执行状态=" + afterStatus);
                    return null;
                }
            };
            executorService.submit(callable);
        }
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        int threadPoolSize = corePoolSize * 5;
        ThreadFactory namedThreadFactory = new NamedThreadFactory("scpAsynWorker");
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
        executorService = new ThreadPoolExecutor(corePoolSize,threadPoolSize,60, TimeUnit.SECONDS,queue,namedThreadFactory);
        appId = BatchUtils.getLocalIP() + ":" + ConfigUtils.getProperty("galaxy.protocol.port")+ ScpConstants.split + System.currentTimeMillis();
    }
    public String getAppId()
    {
        return appId;
    }
}
