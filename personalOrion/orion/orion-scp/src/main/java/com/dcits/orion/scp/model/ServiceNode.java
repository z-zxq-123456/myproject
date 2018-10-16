package com.dcits.orion.scp.model;

import com.alibaba.fastjson.JSON;
import com.dcits.galaxy.base.tuple.TwoTuple;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.base.util.JsonUtils;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.orion.scp.api.ISystem;
import com.dcits.orion.scp.api.exception.UnknownException;
import com.dcits.orion.scp.factory.SystemFactory;
import com.dcits.orion.scp.utils.ScpConstants;
import com.dcits.orion.scp.utils.ScpLogUtils;
import com.dcits.orion.scp.utils.ScpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lixiaobin on 2017/3/14.
 */
//服务节点模型
public class ServiceNode {

    private static final Logger log = LoggerFactory
            .getLogger(ServiceNode.class);
    String flowId;
    String nodeId;
    String nodeDesc;
    String nodeType;
    String execType;
    String systemId;
    String errorDeal = "N";
    String unknowDeal="N";
    String mapper;
    int retryCnt;
    String beforeScript;
    String afterScript;
    String loopIdx;
    String loopExpr;
    Flow flow;


    //服务调用入口
    public void callService(Map cacheMap)
    {
        Map request = ScpUtils.getArg(cacheMap,(String)cacheMap.get(ScpConstants.flowId),getIn());
        List inList = (List)cacheMap.get(getIn());
        if (inList == null)
        {
            inList = new ArrayList();
            cacheMap.put(getIn(),inList);
        }
        inList.add(request);
        int seqNo = 0;
        List outList = (List)cacheMap.get(getNodeId()+ScpConstants.split+ScpConstants.out);
        if (outList != null)
        {
            seqNo = outList.size();
        }

        boolean isResume = ScpUtils.isResume(cacheMap);
        ISystem system = SystemFactory.getSystem(getSystemId());

        if (isResume == false)
            ScpLogUtils.recordNodeLog(flow,cacheMap,this,seqNo);

        Map response = null;

        if (isResume == true)
        {
            Map nodeLog = ScpLogUtils.queryNodeLog(flowId,(String)cacheMap.get(ScpConstants.keyFieldValue),nodeId,seqNo);
            if (nodeLog == null)
            {
                isResume = false;
                ScpLogUtils.recordNodeLog(flow,cacheMap,this,seqNo);
            }
            else {
                String outMsg = (String)nodeLog.get("OUT_MSG");
                if (!StringUtils.isBlank(outMsg))
                    response = JSON.parseObject(outMsg);
            }
        }
        if (isResume == false) {
            try {
                if (log.isDebugEnabled()) {
                    log.debug("开始调用服务：nodeId=" + nodeId + ",systemId=" + systemId);
                    log.debug("请求报文：" + JsonUtils.formatJson(JSON.toJSONString(request)));
                }
                ScpUtils.addServiceNode(this);
                response = system.execute(request);
                if (log.isDebugEnabled())
                    log.debug("响应报文：" + JsonUtils.formatJson(JSON.toJSONString(response)));
            } catch (Exception e) {
                if (log.isErrorEnabled()) {
                    log.error("服务执行异常：nodeId=" + nodeId + ",systemId=" + systemId);
                    log.error(ExceptionUtils.getStackTrace(e));
                }
            }
        }

        if (response == null)
            response = exceptionDeal(cacheMap,request);
        String status = "S";
        if (response == null)
        {
            response = ScpUtils.errorReturn(ScpConstants.unKnownRetCode,"服务节点：<" + getNodeDesc() + "> 执行异常");
            switch (getUnknowDeal())
            {
                case "R":
                case "SR":
                case "CR":
                    ScpUtils.addError(ScpConstants.unKnownRetCode,"服务节点：<" + getNodeDesc() + "> 执行异常" ,cacheMap);
                    ScpUtils.addCommittedService(this,cacheMap,seqNo);
                    break;
                case "N":
                case "SN":
                case "CN":
                    ScpUtils.addError(ScpConstants.unKnownRetCode,"服务节点：<" + getNodeDesc() + "> 执行异常" ,cacheMap);
                    if ("C".equals(getNodeType()))//提交服务执行未知时，不进行冲正
                        ScpUtils.addNotReversalFlag(cacheMap);
                    break;
            }
            status = "E";
        }
        else {
            ScpUtils.addServiceNode(this);
            if (system.isError(response))
            {
                status = "F";
                if ("N".equals(getErrorDeal())) {
                    ScpUtils.addError(system.getErrors(response), cacheMap);
                    if (system.isUnknownError(response))//如果存在未明错误，则不进行冲正
                        ScpUtils.addNotReversalFlag(cacheMap);
                }
            }
        }

        if (outList == null)
        {
            outList = new ArrayList();
            cacheMap.put(getNodeId()+ScpConstants.split+ScpConstants.out,outList);

        }
        outList.add(response);
        Map reversalMsg = null;
        if ("S".equals(status) && "C".equals(getNodeType())) {
            ScpUtils.addCommittedService(this, cacheMap, seqNo);
            if (ScpUtils.existMapper(flow.getFlowId(),getReversal()))
            {

                try
                {
                    reversalMsg = ScpUtils.getArg(cacheMap,flow.getFlowId(),getReversal());
                }
                catch (Exception e)
                {

                }
            }
        }
        ScpLogUtils.updateNodeLog(flow,(String)cacheMap.get(ScpConstants.keyFieldValue),this,response,reversalMsg,status,null,seqNo);
    }


    //服务调用异常分支
    public Map exceptionDeal(Map cacheMap,Map request)
    {
        if (getUnknowDeal() == null)
            return null;
        switch (getUnknowDeal()) {
            case "N":
            case "K":
            case "R":
                return null;
            case "SN":
            case "SR":
            case "SC":
                return reCallService(request);
            case "CN":
            case "CK":
            case "CR":
                Map confirmRequest = ScpUtils.getArg(cacheMap, flowId, getConfirm());
                return confirmCallService(confirmRequest);
            default:
                return null;
        }
    }

    //确认服务
    public Map confirmCallService(Map request)
    {
        Map out = null;
        int retry = getRetryCnt();
        ISystem system = SystemFactory.getSystem(getSystemId());
        if (retry > 0) {
            if (log.isDebugEnabled())
                log.debug("确认服务请求报文：" + JsonUtils.formatJson(JSON.toJSONString(request)));
            while (retry > 0 && out == null)
            {
                retry--;
                try {
                    out = system.execute(request);
                }
                catch (Exception e)
                {
                    if (log.isDebugEnabled())
                        log.debug("确认服务执行异常：" + ExceptionUtils.getStackTrace(e));
                }
                if (out != null)
                {
                    if (log.isDebugEnabled())
                        log.debug("确认服务返回报文：" + JsonUtils.formatJson(JSON.toJSONString(out)));
                    if (system.isError(out) && system.isConfirmAgain(out))//判断是否需要重新确认
                    {
                        if (log.isDebugEnabled())
                            log.debug("确认服务需要再次确认");
                        out = null;
                    }
                }
            }
        }
        return out;
    }
    //重发服务
    public Map reCallService(Map request)
    {
        Map out = null;
        int retry = getRetryCnt();
        ISystem system = SystemFactory.getSystem(getSystemId());
        if (retry > 0) {
            if (log.isDebugEnabled())
                log.debug("重发服务请求报文：" + JsonUtils.formatJson(JSON.toJSONString(request)));
            while (retry > 0 && out == null)
            {
                retry--;
                try {
                    out = system.execute(request);
                    if (log.isDebugEnabled() && out != null)
                        log.debug("重发服务返回报文：" + JsonUtils.formatJson(JSON.toJSONString(out)));
                }
                catch (Exception e)
                {
                    if (log.isDebugEnabled())
                        log.debug("重发服务执行异常：" + ExceptionUtils.getStackTrace(e));
                }
            }
        }
        return out;
    }


    public TwoTuple<String,Map> reversalService(Map reversalRequest, String keyValue, int seqNo)
    {
        Map out = null;
        long beginTime = System.currentTimeMillis();
        ISystem system = SystemFactory.getSystem(getSystemId());
        if (log.isDebugEnabled())
            log.debug("冲正服务请求报文：" + JsonUtils.formatJson(JSON.toJSONString(reversalRequest)));
        try {
            ScpUtils.addServiceNode(this);
            out = system.execute(reversalRequest);
        }
        catch (Throwable t)
        {
            if(log.isWarnEnabled())
                log.warn(ExceptionUtils.getStackTrace(t));
            ScpLogUtils.updateNodeLog(flow,keyValue,this,null,null,null,ScpConstants.reversalAgain,seqNo);
            ScpLogUtils.recordNodeReversalLog(flow,keyValue,this,seqNo,reversalRequest,null,ScpConstants.reversalException,beginTime,System.currentTimeMillis());
            return new TwoTuple<>(ScpConstants.reversalAgain,null);
        }
        ScpUtils.addServiceNode(this);
        if (!system.isError(out) || !system.isReversalError(out) ) {
            ScpLogUtils.updateNodeLog(flow,keyValue,this,null,null,null,ScpConstants.reversalSuccess,seqNo);
            ScpLogUtils.recordNodeReversalLog(flow,keyValue,this,seqNo,reversalRequest,out,ScpConstants.reversalSuccess,beginTime,System.currentTimeMillis());
            return new TwoTuple<>(ScpConstants.reversalSuccess,out);
        }
        else if(!system.isReversalAgain(out))
        {
            ScpLogUtils.updateNodeLog(flow,keyValue,this,null,null,null,ScpConstants.reversalFail,seqNo);
            ScpLogUtils.recordNodeReversalLog(flow,keyValue,this,seqNo,reversalRequest,out,ScpConstants.reversalFail,beginTime,System.currentTimeMillis());
            return new TwoTuple<>(ScpConstants.reversalFail,out);
        }
        else {
            ScpLogUtils.updateNodeLog(flow,keyValue,this,null,null,null,ScpConstants.reversalAgain,seqNo);
            ScpLogUtils.recordNodeReversalLog(flow,keyValue,this,seqNo,reversalRequest,out,ScpConstants.reversalFail,beginTime,System.currentTimeMillis());
            return new TwoTuple<>(ScpConstants.reversalAgain,out);
        }
    }

    public String getIn() {
        return nodeId+ScpConstants.split+ScpConstants.in;
    }
    public String getReversal() {
        return nodeId+ScpConstants.split+ScpConstants.reversal;
    }

    public String getLogFields() {
        return nodeId+ScpConstants.split+ScpConstants.logFields;
    }
    public String getConfirm() {
        return nodeId+ScpConstants.split+ScpConstants.confirm;
    }

//////////////////////////get and set method ////////////////////////////

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeDesc() {
        return nodeDesc;
    }

    public void setNodeDesc(String nodeDesc) {
        this.nodeDesc = nodeDesc;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getExecType() {
        return execType;
    }

    public void setExecType(String execType) {
        this.execType = execType;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }



    public String getErrorDeal() {
        return errorDeal;
    }

    public void setErrorDeal(String errorDeal) {
        this.errorDeal = errorDeal;
    }



    public int getRetryCnt() {
        return retryCnt;
    }

    public void setRetryCnt(int retryCnt) {
        this.retryCnt = retryCnt;
    }

    public String getBeforeScript() {
        return beforeScript;
    }

    public void setBeforeScript(String beforeScript) {
        this.beforeScript = beforeScript;
    }

    public String getAfterScript() {
        return afterScript;
    }

    public void setAfterScript(String afterScript) {
        this.afterScript = afterScript;
    }

    public Flow getFlow() {
        return flow;
    }

    public void setFlow(Flow flow) {
        this.flow = flow;
    }

    public String getMapper() {
        return mapper;
    }

    public void setMapper(String mapper) {
        this.mapper = mapper;
    }

    public String getLoopIdx() {
        return loopIdx;
    }

    public void setLoopIdx(String loopIdx) {
        this.loopIdx = loopIdx;
    }

    public String getLoopExpr() {
        return loopExpr;
    }

    public void setLoopExpr(String loopExpr) {
        this.loopExpr = loopExpr;
    }


    public String getUnknowDeal() {
        return unknowDeal;
    }

    public void setUnknowDeal(String unknowDeal) {
        this.unknowDeal = unknowDeal;
    }
}