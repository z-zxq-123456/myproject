/**
 * <p>Title: StriaEngineProcess.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 2014年12月17日 下午3:00:52
 * @version V1.0
 */
package com.dcits.orion.stria.engine;

import com.dcits.orion.api.BusinessEngine;
import com.dcits.orion.api.model.BusinessType;
import com.dcits.orion.core.Context;
import com.dcits.orion.core.encrypt.EncryptUtil;
import com.dcits.orion.base.common.ServiceHandler;
import com.dcits.orion.stria.config.StriaConstants;
import com.dcits.orion.stria.core.Runner;
import com.dcits.orion.stria.core.StriaThreadLocal;
import com.dcits.orion.stria.dao.mapper.Flow;
import com.dcits.orion.stria.exception.StriaException;
import com.dcits.orion.stria.mapping.RequestMapping;
import com.dcits.orion.stria.model.FlowModel;
import com.dcits.orion.stria.model.StartModel;
import com.dcits.orion.stria.model.SubServiceModel;
import com.dcits.orion.stria.utils.StriaUtil;
import com.dcits.galaxy.base.GalaxyConstants;
import com.dcits.galaxy.base.data.BaseRequest;
import com.dcits.galaxy.base.data.BeanResult;
import com.dcits.galaxy.base.data.Results;
import com.dcits.galaxy.base.tuple.TwoTuple;
import com.dcits.galaxy.base.util.ErrorUtils;
import com.dcits.galaxy.base.util.ResultsUtils;
import com.dcits.galaxy.core.access.ThreadLocalManager;
import com.dcits.galaxy.dtp.DtpContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tim
 * @version V1.0
 * @description stria流程引擎实现
 * @update 2014年12月17日 下午3:00:52
 */

public class StriaEngine implements BusinessEngine {

    private static final Logger log = LoggerFactory
            .getLogger(StriaEngineFactory.class);

    private StriaEngineFactory processFactory;

    private RequestMapping mapping;

    private EncryptUtil encryptUtil;

    /**
     * 引擎初始化
     *
     * @param request
     */
    @Override
    public void init(BaseRequest request, BusinessType businessType) {
        // 获取报文中的ServiceId
        String name = ServiceHandler.getServiceName(request);
        // 获取流程定义
        String flowName = name + StriaConstants.FOLW_POSTFIX;
        Flow flow = getProcessFactory().getFlows(flowName);
        // 检查流程是否可用
        getProcessFactory().getFlowService().check(flow, flowName);
        // 主流程设置dtp全局事务标识
        if (businessType == BusinessType.NEW) {
            // 对主业务流程的Request请求，设置dtpFlag
            boolean flag = this.settingDtpFlag(flow, request);
            if (flag) {
                if (null == request.getSysHead().getReference())
                    DtpContext.setTxid(ThreadLocalManager.getUID());
                else
                    DtpContext.setTxid(request.getSysHead().getReference());
            }

            // Spring 事务开启标识设置，事务默认是开启的。
            Context.getInstance().setTxFlag(flow.getTxFlag() == null ? "Y" : flow.getTxFlag());
        }
        //对字段进行解密或者转加密
        if (encryptUtil.switchOn()) {
            encryptUtil.requestEncryptDecrypt(request);
        }
    }

    /**
     * 流程执行主函数
     * 返回流程执行结果和Submit节点的序列号，用来保证父子流程Submit节点序号传递的连续性
     * 增加全局事物控制
     * 1、主流程定义全局事物开关。
     * 2、嵌套子流程如果有全局事物控制，其外围调用的主流程，必须开启全局事物管理。
     * 3、多级子流程嵌套的原理一样，同2点。
     *
     * @param request
     * @return
     */
    @Override
    public TwoTuple<BeanResult, Integer> execFlow(BaseRequest request, BusinessType businessType) {
        Runner runner = null;
        // 流程工程获取流程
        Flow flow = null;
        // 获取报文中的ServiceId
        String name = null;
        // 流程ID
        String flowName = null;
        try {
            name = ServiceHandler.getServiceName(request);
            if (log.isDebugEnabled())
                log.debug("[" + request.getUid() + "] " + (request.isSubRequest() ? "子服务" : "服务") + " [" + name + "] Request:\n" + request.toString());
            flowName = name + StriaConstants.FOLW_POSTFIX;
            flow = getProcessFactory().getFlows(flowName);
            // 参与表达式计算的算子
            Map<String, Object> args = new HashMap<>();
            args.put(StriaConstants.MSG_KEY, request);
            // 创建流程执行者
            runner = new Runner(flow, args);
            // 获取流程模型，并执行
            if (flow.getFlowModel() != null) {
                long start = System.currentTimeMillis();
                try {
                    // 执行服务映射
                    mapping.mapper(name, runner);
                    // 流程处理
                    if (request.isSubRequest()) {
                        // 处理子服务流程
                        this.execSubFlow(runner, flow, flowName);
                    } else {
                        // 处理主服务流程
                        this.execMainFlow(runner, flow, flowName);
                    }
                } finally {
                    long end = System.currentTimeMillis();
                    if (log.isInfoEnabled() && !request.isSubRequest()) {
                        long execTime = (end - start);
                        log.info("[" + runner.getIn().getSysHead().getReference() + "][" + runner.getFlowModel().getDisplayName() + "] 服务执行时间："
                                + execTime);
                        // 流程是否超过超时时间
                        long timeOut = runner.getFlow().getTimeOut().intValue() * 1000;
                        if (timeOut > 0 && execTime > timeOut)
                            // 服务执行超时，报错资源紧张，稍后再试
                            runner.setOut(new BeanResult("999996", ErrorUtils.getErrorDesc("999996",
                                    runner.getIn().getSysHead().getUserLang())));

                    }
                }
            }
            if (GalaxyConstants.STATUS_SUCCESS.equals(runner.getOut().getRetStatus())) {
                if (!request.isSubRequest()) {
                    //对字段进行加密或者转加密
                    if (encryptUtil.switchOn() && null != runner.getOut().getResponse()) {
                        encryptUtil.responseEncryptDecrypt(request, runner.getOut().getResponse());
                    }
                }
            }
            if (log.isDebugEnabled())
                log.debug("BeanResult:\n" + runner.getOut());
            return new TwoTuple(runner.getOut(), new Integer(runner.getSubmitSeqNo()));
        } catch (Throwable t) {
            // modify for sonar
            if (null != runner) {
                return new TwoTuple(new BeanResult(t), new Integer(runner.getSubmitSeqNo()));
            } else {
                return new TwoTuple(new BeanResult(t), 0);
            }
        } finally {
            if (!request.isSubRequest()) {
                // 清理StriaThreadLocal
                StriaThreadLocal.remove();
            }
        }
    }

    /**
     * 设置主流程的全局事物开启标识
     *
     * @param flow
     * @param request
     * @return
     */
    private boolean settingDtpFlag(Flow flow, BaseRequest request) {
        String dtpFlag = flow.getFlowModel().getDepFlag();
        request.setDtpFlag(dtpFlag);
        return "Y".equals(dtpFlag) ? true : false;
    }

    /**
     * 执行子流程
     *
     * @param flowModel
     * @param runner
     * @return
     */
    private BeanResult execSubService(FlowModel flowModel, Runner runner) {
        BeanResult br = new BeanResult();
        for (SubServiceModel subService : flowModel.getSubNodes()) {
            StriaUtil.mergeSubResult(br, subService.execSubService(runner));
        }
        return br;
    }

    private void execMainFlow(Runner runner, Flow flow, String flowName) {
        // 设置三阶段 1、检查 2、授权、确认检查 3、提交
        runner.setDoCheck(true);
        runner.setDoSubmit(false);
        runner.setDoACCheck(false);

        // 主服务流程处理
        BeanResult subBr = null;
        BeanResult checkBr = new BeanResult();

        // 三阶段处理
        // 第一阶段，处理所有子流程和当前流程的check服务，并合并结果。
        // 处理子流程第一阶段
        if (null != flow.getFlowModel().getSubNodes()
                && flow.getFlowModel().getSubNodes().size() > 0) {
            // 第一阶段，处理所有子流程和当前流程的check服务，并合并结果。
            // 检查通过后，处理第二阶段，授权类的原子服务。
            subBr = execSubService(flow.getFlowModel(), runner);
        }

        // 处理当前流程的第一阶段
        StartModel startM = flow.getFlowModel().getStart();
        if (null == startM)
            throw new StriaException("指定的流程定义 [name=" + flowName
                    + ", version=" + flow.getVersion() + "] 没有开始节点");
        if (log.isInfoEnabled())
            log.info("[" + runner.getIn().getSysHead().getReference() + "][" + flow.getFlowModel().getDisplayName() + "] 服务 [检查] 阶段开始执行");
        startM.execute(runner);
        StriaUtil.mergeSubResult(checkBr, runner.getOut());

        // 合并子服务的检查结果
        if (null != subBr) {
            StriaUtil.mergeSubResult(checkBr, subBr);
        }

        // 修复检查阶段，授权报错后。无法执行后续的检查授权检查节点的错误。Modify by Tim 20170421
        if (GalaxyConstants.STATUS_FAILED.equals(checkBr.getRetStatus())) {
            runner.setOut(checkBr);
            return;
        } else {
            // 修复检查阶段，授权报错后。无法执行后续的检查授权检查节点的错误。Modify by Tim 20170421
            // 重置初始runner的Out为成功结果。
            runner.setOut(new BeanResult());
        }

        // 处理第二阶段，当前流程的授权、确认服务。
        // 设置只处理流程提交操作
        // 设置提交处理
        runner.setDoCheck(false);
        runner.setDoACCheck(true);
        runner.setDoSubmit(false);

        // 处理子流程第二阶段
        if (null != flow.getFlowModel().getSubNodes()
                && flow.getFlowModel().getSubNodes().size() > 0) {
            // 第二阶段，处理所有子流程和当前流程的check服务，并合并结果。
            // 检查通过后，处理第二阶段，提交类的原子服务。
            subBr = execSubService(flow.getFlowModel(), runner);
        }

        // 执行流程处理
        if (log.isInfoEnabled())
            log.info("[" + runner.getIn().getSysHead().getReference() + "][" + flow.getFlowModel().getDisplayName() + "] 服务 [授权与确认] 阶段开始执行");
        startM.execute(runner);
        StriaUtil.mergeSubResult(checkBr, runner.getOut());

        // 合并子服务的检查结果
        if (null != subBr) {
            StriaUtil.mergeSubResult(checkBr, subBr);
        }

        if (!GalaxyConstants.STATUS_SUCCESS.equals(checkBr.getRetStatus())) {
            runner.setOut(checkBr);
            return;
        }

        // 授权检查成功，但是LocalHead有上送授权信息，这说明服务没有授权节点，并无合并授权结果的处理。
        // 转换前段上送的授权信息
        Results localResults = null;
        localResults = ResultsUtils.convertRetToResults(runner.getLocalHead(), localResults);
        if (null != localResults) {
            runner.setOut(new BeanResult(localResults, "O"));
            return;
        }

        // 处理第三阶段，当前流程的提交服务。
        // 设置只处理流程提交操作
        // 设置提交处理
        runner.setDoCheck(false);
        runner.setDoACCheck(false);
        runner.setDoSubmit(true);

        // 执行流程处理
        if (log.isInfoEnabled())
            log.info("[" + runner.getIn().getSysHead().getReference() + "][" + flow.getFlowModel().getDisplayName() + "] 服务 [提交] 阶段开始执行");
        startM.execute(runner);
    }

    private void execSubFlow(Runner runner, Flow flow, String flowName) {
        BeanResult subBr = null;
        BeanResult subFlowBr = new BeanResult();

        // 子服务流程处理
        if (runner.isDoCheck() && flow.getFlowModel().getSubNodes().size() > 0) {
            subBr = execSubService(flow.getFlowModel(), runner);
        } else if (runner.isDoACCheck() && flow.getFlowModel().getSubNodes().size() > 0) {
            subBr = execSubService(flow.getFlowModel(), runner);
        }

        // 执行服务流程
        StartModel startM = flow.getFlowModel().getStart();
        if (null == startM) {
            throw new StriaException("指定的流程定义 [name=" + flowName
                    + ", version=" + flow.getVersion() + "] 没有开始节点");
        }

        if (log.isDebugEnabled()) {
            String desc = "提交";
            if (runner.isDoCheck()) {
                desc = "检查";
            } else if (runner.isDoACCheck()) {
                desc = "授权与确认";
            }
            log.debug("[" + runner.getIn().getSysHead().getReference() + "][" + flow.getFlowModel().getDisplayName() + "] 服务 [" + desc + "] 阶段开始执行");
        }

        startM.execute(runner);

        if (runner.isDoCheck() || runner.isDoACCheck()) {
            // 合并子服务的检查结果
            if (null != subBr) {
                StriaUtil.mergeSubResult(subFlowBr, subBr);
            }
            runner.getOut().mergeResult(subFlowBr);
        }
    }


    /**
     * @return processFactory : return the property processFactory.
     */
    public StriaEngineFactory getProcessFactory() {
        return processFactory;
    }

    /**
     * @param processFactory
     *         : set the property processFactory.
     */
    public void setProcessFactory(StriaEngineFactory processFactory) {
        this.processFactory = processFactory;
    }

    public void setMapping(RequestMapping mapping) {
        this.mapping = mapping;
    }

    public EncryptUtil getEncryptUtil() {
        return encryptUtil;
    }

    public void setEncryptUtil(EncryptUtil encryptUtil) {
        this.encryptUtil = encryptUtil;
    }
}