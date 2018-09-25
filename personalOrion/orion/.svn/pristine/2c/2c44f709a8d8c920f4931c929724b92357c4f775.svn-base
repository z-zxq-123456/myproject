package com.dcits.orion.stria.engine;

import com.dcits.orion.api.model.EventRequest;
import com.dcits.orion.stria.config.StriaConstants;
import com.dcits.orion.stria.core.Runner;
import com.dcits.orion.stria.dao.mapper.Flow;
import com.dcits.orion.stria.exception.StriaException;
import com.dcits.orion.stria.model.StartModel;
import com.dcits.orion.stria.utils.StriaUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 事件流程引擎处理
 * <p/>
 * Created by Tim on 2015/11/16.
 */
public class StriaEventEngine {

    private static final Logger log = LoggerFactory
            .getLogger(StriaEngineFactory.class);
    /**
     * 流程工厂
     */
    private StriaEngineFactory processFactory;

    /**
     * 引擎初始化
     *
     * @param request
     */
    public void init(EventRequest request) {
        String flowId = StriaUtil.getEventFlowId(request);
        Flow flow = getProcessFactory().getFlows(flowId);
        // 检查流程是否可用
        getProcessFactory().getFlowService().check(flow, flowId);
    }

    /**
     * 执行事件流程处理
     *
     * @param request
     * @return
     */
    public Runner execute(EventRequest request) {
        String flowId = StriaUtil.getEventFlowId(request);
        Flow flow = getProcessFactory().getFlows(flowId);
        // init已经做过check检查，此处不再检查流程是否可用
        // getProcessFactory().getFlowService().check(flow, flowId);
        // 参与表达式计算的算子
        Map<String, Object> args = new HashMap<>();
        args.put(StriaConstants.MSG_KEY, request);
        // 创建流程执行者
        Runner runner = new Runner(flow, args);
        this.execFlow(runner, flow, flowId);
        return runner;
    }

    /**
     * 执行事件处理流程
     *
     * @param runner
     * @param flow
     * @param flowId
     */
    private void execFlow(Runner runner, Flow flow, String flowId) {
        StartModel startM = flow.getFlowModel().getStart();
        if (null == startM)
            throw new StriaException("指定的流程定义 [name=" + flowId + ", version=" + 0 + "] 没有开始节点");
        if (log.isDebugEnabled())
            log.debug("[" + runner.getIn().getSysHead().getReference() + "][" + flow.getFlowModel().getDisplayName() + "] 服务 [" + StriaUtil.getEventFlowId((EventRequest) runner.getIn()) + "] 事件流程 [" + (runner.isDoCheck() ? "检查" : "提交") + "] 开始执行");
        startM.execute(runner);
    }

    public StriaEngineFactory getProcessFactory() {
        return processFactory;
    }

    public void setProcessFactory(StriaEngineFactory processFactory) {
        this.processFactory = processFactory;
    }
}
