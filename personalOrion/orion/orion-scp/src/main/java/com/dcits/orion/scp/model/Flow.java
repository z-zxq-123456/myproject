package com.dcits.orion.scp.model;
import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.orion.scp.api.exception.UnknownException;
import com.dcits.orion.scp.engine.ScpEngine;
import com.dcits.orion.scp.utils.ScpConstants;
import com.dcits.orion.scp.utils.ScpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.ConcurrencyFailureException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by lixiaobin on 2017/3/14.
 */
public class Flow extends AbstractBean{
    String flowId;
    String flowName;
    String execType;
    String resumeType;
    String script;
    String reversalType;
    int timeOut;
    String errorDeal = "R";
    String keyFields;
    ServiceNode startNode;
    ServiceNode returnNode;
    String logFlag;
    Map<String,ServiceNode> nodeMap;
    Map<String,List<String>> lines;
    Map<String,String> lineExpr;
    ExecutorService executorService;


    private static final Logger log = LoggerFactory
            .getLogger(Flow.class);

    //流程执行，递归实现
    public void execute(ServiceNode serviceNode,Map cacheMap)
    {
        if (serviceNode == null)
        {
            if (log.isDebugEnabled())
            {
                log.debug("节点为空,流程终止");
            }
            return;
        }
        if (isTimeOut(cacheMap)) {
            if (log.isInfoEnabled())
                log.info("流程执行超时，退出，flowId=" + flowId+",nodeId=" + serviceNode.getNodeId());
            return;
        }

        List nextServiceNodes = null;
        ServiceNode nextServiceNode = null;
        switch (serviceNode.getNodeType())
        {
            case "S"://开始节点
                if (log.isDebugEnabled())
                    log.debug("流程执行中，当前节点为开始节点");
                nextServiceNode = getNextServiceNode(serviceNode);
                execute(nextServiceNode,cacheMap);
                return;
            case "A"://检查授权节点
                if (log.isDebugEnabled())
                    log.debug("流程执行中，当前节点为检查授权服务,nodeId="+serviceNode.getNodeId() );
                executeCheckService(serviceNode,cacheMap);
                return;
            case "Q"://查询服务
                if (log.isDebugEnabled())
                    log.debug("流程执行中，当前节点为查询服务,nodeId="+serviceNode.getNodeId() );
                executeQueryService(serviceNode,cacheMap);
                return;
            case "C"://提交服务
                if (log.isDebugEnabled())
                    log.debug("流程执行中，当前节点为提交服务,nodeId="+serviceNode.getNodeId() );
                executeCommitService(serviceNode,cacheMap);
                return;
            case "B"://返回节点
                if (log.isDebugEnabled())
                    log.debug("流程执行中，当前节点为返回");
                return;
            case "N"://通知服务
                if (log.isDebugEnabled())
                    log.debug("流程执行中，当前节点为通知服务,nodeId="+serviceNode.getNodeId() );
                executeNoteService(serviceNode,cacheMap);
                return;
            case "D"://决策节点
                if (log.isDebugEnabled())
                    log.debug("流程执行中，当前节点为决策节点");
                nextServiceNode = getDecideNode(serviceNode,cacheMap);
                if (serviceNode != null)
                    execute(nextServiceNode,cacheMap);
                else
                    ScpUtils.addError("999999","决策节点找不到满足条件的后置节点",cacheMap);
                return;
            case "P"://分派节点
                if (log.isDebugEnabled())
                    log.debug("流程执行中，当前节点为分派节点");
                nextServiceNodes = getNextServiceNodes(serviceNode);
                execute(nextServiceNodes,cacheMap,serviceNode.execType);
                nextServiceNode = getAfterCombineNode(serviceNode);//获得合并节点后的第一个节点进行执行
                execute(nextServiceNode,cacheMap);
                return;
            case "H"://合并节点
                if (log.isDebugEnabled())
                    log.debug("流程执行中，当前节点为合并节点");
                return;
            case "M"://数据映射节点
                if (log.isDebugEnabled())
                    log.debug("流程执行中，当前节点为数据映射节点");
                executeMapping(serviceNode,cacheMap);
                nextServiceNode = getNextServiceNode(serviceNode);
                execute(nextServiceNode,cacheMap);
                return;
            case "L": //循环开始节点
                if (log.isDebugEnabled())
                    log.debug("流程执行中，当前节点为循环开始节点");
                executeLoop(serviceNode,cacheMap);
                nextServiceNode = getAfterLoopNode(serviceNode);
                execute(nextServiceNode,cacheMap);
                return;
            case "I"://循环结束节点
                if (log.isDebugEnabled())
                    log.debug("流程执行中，当前节点为循环结束节点");
                return;
        }
    }

    //循环节点
    private void executeLoop(ServiceNode serviceNode,Map cacheMap)
    {
        int i = 0;
        ScpUtils.setVariable(cacheMap,serviceNode.getLoopIdx(),i);
        while(ScpUtils.getExprBoolean(cacheMap,serviceNode.getLoopExpr())&&!ScpUtils.isError(cacheMap))
        {
            ServiceNode nextServiceNode = getNextServiceNode(serviceNode);
            execute(nextServiceNode,cacheMap);
            i++;
            ScpUtils.setVariable(cacheMap,serviceNode.getLoopIdx(),i);
        }
    }

    //获得循环结束节点后的第一个节点
    private ServiceNode getAfterLoopNode(ServiceNode serviceNode)
    {
        int stack = 1;//支持循环节点嵌套
        ServiceNode nextServiceNode = getNextServiceNode(serviceNode);
        if ("L".equals(nextServiceNode.getNodeType()))
            stack ++;
        else if ("I".equals(nextServiceNode.getNodeType()))
            stack --;
        while (!"I".equals(nextServiceNode.getNodeType())||stack > 0)
        {
            nextServiceNode = getNextServiceNode(nextServiceNode);
            if ("L".equals(nextServiceNode.getNodeType()))
                stack ++;
            else if ("I".equals(nextServiceNode.getNodeType()))
                stack --;
        }
        return getNextServiceNode(nextServiceNode);
    }



    //获得决策节点后的第一个满足表达式的节点
    private ServiceNode getDecideNode(ServiceNode serviceNode,Map cacheMap)
    {
        List<ServiceNode> nextServiceNodes = getNextServiceNodes(serviceNode);
        for (ServiceNode nextServiceNode : nextServiceNodes)
        {
            String expr = lineExpr.get(serviceNode.getNodeId()+"-"+nextServiceNode.getNodeId());
            if (StringUtils.isBlank(expr))
            {
               ScpUtils.addError(new UnknownException("999999","决策节点后的表达式为空"),cacheMap);
                return null;
            }
            if (ScpUtils.getExprBoolean(cacheMap,expr))
                return nextServiceNode;
        }
        return null;
    }

    //分派节点，获得合并节点后的第一个节点
    private ServiceNode getAfterCombineNode(ServiceNode serviceNode)
    {
        int stack = 1;//支持分派节点嵌套
        ServiceNode nextServiceNode = getNextServiceNode(serviceNode);
        if ("P".equals(nextServiceNode.getNodeType()))
            stack ++;
        else if ("H".equals(nextServiceNode.getNodeType()))
            stack --;
        while (!"H".equals(nextServiceNode.getNodeType())||stack > 0)
        {
            nextServiceNode = getNextServiceNode(nextServiceNode);
            if ("P".equals(nextServiceNode.getNodeType()))
                stack ++;
            else if ("H".equals(nextServiceNode.getNodeType()))
                stack --;
        }
        return getNextServiceNode(nextServiceNode);
    }
    //执行服务
    private void executeService(ServiceNode serviceNode,Map cacheMap)
    {
        try {
            serviceNode.callService(cacheMap);
        }
        catch (UnknownException e)
        {
            ScpUtils.addError(e.getRetCode(),e.getRetMsg(),cacheMap);
        }
        ServiceNode nextServiceNode = getNextServiceNode(serviceNode);
        execute(nextServiceNode,cacheMap);
    }

    //执行数据映射
    private void executeMapping(ServiceNode serviceNode,Map cacheMap)
    {
        Map map = ScpUtils.getArg(cacheMap,flowId,serviceNode.getIn());
        cacheMap.put(serviceNode.mapper,map);
    }

    //执行检查服务
    private void executeCheckService(ServiceNode serviceNode,Map cacheMap)
    {
        executeService(serviceNode,cacheMap);
    }

    //执行通知服务
    private void executeNoteService(ServiceNode serviceNode,Map cacheMap)
    {
        if (ScpUtils.isError(cacheMap))
            return;
        executeService(serviceNode,cacheMap);
    }

    //执行查询服务
    private void executeQueryService(ServiceNode serviceNode,Map cacheMap)
    {
        if (ScpUtils.isError(cacheMap))
            return;
        executeService(serviceNode,cacheMap);
    }

    //执行提交服务
    private void executeCommitService(ServiceNode serviceNode,Map cacheMap)
    {
        if (ScpUtils.isError(cacheMap))
            return;
        try {
            serviceNode.callService(cacheMap);
            if (ScpUtils.isError(cacheMap))
            {
                return;
            }
            ServiceNode nextServiceNode = getNextServiceNode(serviceNode);
            execute(nextServiceNode,cacheMap);
        }
        catch (UnknownException e)
        {
            ScpUtils.addError(e.getRetCode(),e.getRetMsg(),cacheMap);
        }
    }



    //获得下个节点
    private ServiceNode getNextServiceNode(ServiceNode serviceNode)
    {
        List<ServiceNode> nextNodes = getNextServiceNodes(serviceNode);
        if (nextNodes != null && !nextNodes.isEmpty())
            return nextNodes.get(0);
        else return null;
    }
    //获得后续直接所有节点
    private List<ServiceNode> getNextServiceNodes(ServiceNode serviceNode)
    {
        String nodeId = serviceNode.getNodeId();
        List<String> nodeIds = getLines().get(nodeId);
        if (nodeIds != null && !nodeIds.isEmpty()) {
            List<ServiceNode> serviceNodes = new ArrayList<>();

            for (String id : nodeIds) {
                ServiceNode node = getNodeMap().get(id);
                serviceNodes.add(node);
            }
            return serviceNodes;
        }
        else return null;
    }

    //分派节点执行后续所有节点
    private void execute(List<ServiceNode> serviceNodes,final Map cacheMap,String execType)
    {
        if (serviceNodes == null || serviceNodes.isEmpty())
            return;
        if ("P".equals(execType) && serviceNodes.size() > 1)
        {
            int count = serviceNodes.size();
            final CountDownLatch latch = new CountDownLatch(count);
            for (final ServiceNode serviceNode : serviceNodes)
            {
                Callable callable = new Callable() {
                    @Override
                    public Object call() throws Exception {
                        try {
                            execute(serviceNode,cacheMap);
                            return null;
                        } finally {
                            latch.countDown();
                        }
                    }
                };
                executorService.submit(callable);
            }
            try {
                latch.await();
            } catch (InterruptedException e) {
                throw new ConcurrencyFailureException(
                        "interrupted when processing data access request in concurrency",
                        e);
            }
        }
        else {
            for (ServiceNode serviceNode : serviceNodes)
                execute(serviceNode,cacheMap);
        }
    }
    //流程执行是否超时
    public boolean isTimeOut(long startTime)
    {
        if (getTimeOut() > 0 )
        {
            if (System.currentTimeMillis() - startTime > getTimeOut() * 1000)
                return true;
            else return false;
        }
        else return false;
    }
    //流程执行是否超时
    public boolean isTimeOut(Map cacheMap)
    {
        boolean isAfter = (boolean)cacheMap.get(ScpConstants.isAfter);
        if (isAfter)//返回节点后的流程不关注超时时间
            return false;
        long startTime = (long)cacheMap.get(ScpConstants.startTime);
        return isTimeOut(startTime);
    }


    ////////////////////get and set method ///////////////////

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getExecType() {
        return execType;
    }

    public void setExecType(String execType) {
        this.execType = execType;
    }

    public String getResumeType() {
        return resumeType;
    }

    public void setResumeType(String resumeType) {
        this.resumeType = resumeType;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getReversalType() {
        return reversalType;
    }

    public void setReversalType(String reversalType) {
        this.reversalType = reversalType;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public String getKeyFields() {
        return keyFields;
    }

    public void setKeyFields(String keyFields) {
        this.keyFields = keyFields;
    }

    public ServiceNode getStartNode() {
        return startNode;
    }

    public void setStartNode(ServiceNode startNode) {
        this.startNode = startNode;
    }

    public Map<String, ServiceNode> getNodeMap() {
        return nodeMap;
    }

    public void setNodeMap(Map<String, ServiceNode> nodeMap) {
        this.nodeMap = nodeMap;
    }

    public Map<String, List<String>> getLines() {
        return lines;
    }

    public void setLines(Map<String, List<String>> lines) {
        this.lines = lines;
    }

    public String getLogFlag() {
        return logFlag;
    }

    public void setLogFlag(String logFlag) {
        this.logFlag = logFlag;
    }

    public Map<String, String> getLineExpr() {
        return lineExpr;
    }

    public void setLineExpr(Map<String, String> lineExpr) {
        this.lineExpr = lineExpr;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        if (this.executorService == null)
            this.executorService = executorService;
    }

    public ServiceNode getReturnNode() {
        return returnNode;
    }

    public void setReturnNode(ServiceNode returnNode) {
        this.returnNode = returnNode;
    }

    public String getErrorDeal() {
        return errorDeal;
    }

    public void setErrorDeal(String errorDeal) {
        this.errorDeal = errorDeal;
    }
}
