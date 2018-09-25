package com.dcits.orion.stria.model;

import com.dcits.orion.api.IProcess;
import com.dcits.orion.base.common.ServiceHandler;
import com.dcits.orion.stria.core.Runner;
import com.dcits.orion.stria.exception.StriaException;
import com.dcits.orion.stria.utils.StriaUtil;
import com.dcits.galaxy.base.GalaxyConstants;
import com.dcits.galaxy.base.data.*;
import com.dcits.galaxy.base.exception.BusinessException;
import com.dcits.galaxy.base.exception.WithoutAuthAndConfirmException;
import com.dcits.galaxy.base.exception.WithoutAuthorizationException;
import com.dcits.galaxy.base.exception.WithoutConfirmationException;
import com.dcits.galaxy.base.tuple.TwoTuple;
import com.dcits.galaxy.base.util.BeanUtils;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.core.client.ServiceProxy;
import com.dcits.galaxy.core.client.builder.Attributes;
import com.dcits.galaxy.core.client.builder.AttributesBuilderSupport;
import com.dcits.galaxy.core.client.builder.ServiceAttributesBuilder;
import com.dcits.galaxy.core.spring.MappingManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Tim on 2015/7/14.
 */
public class SubServiceModel extends RunModel {

    private static final Logger log = LoggerFactory
            .getLogger(SubServiceModel.class);

    /**
     * 子流程进入条件
     */
    private String condition;

    /**
     * 子流程请求
     */
    private String request;

    private String serviceCode;

    private String messageType;

    private String messageCode;

    private String var;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    @Override
    protected void exec(Runner runner) {
        targetNodeValidate();
        BeanResult br = execSubService(runner);
        handleResult(runner, br);
        runOutTransition(runner);
    }

    protected void targetNodeValidate() {
        if (getOutputs().size() != 1)
            throw new StriaException("[" + getDisplayName() + "] 节点，只能有一个目标节点");
        // 目标节点是否是join节点
        // 原子服务部提供并行节点处理，考虑多服务类型以及分布式事物控制问题
        NodeModel targetNode = getOutputs().get(0).getTarget();
        if (!DecisionModel.class.isInstance(targetNode)
                && !AtomServiceModel.class.isInstance(targetNode)
                && !EventServiceModel.class.isInstance(targetNode)
                && !SubServiceModel.class.isInstance(targetNode)
                && !EndModel.class.isInstance(targetNode)) {
            throw new StriaException("当前节点 [" + getDisplayName() + "] 的目标节点，不允许定义为 ["
                    + targetNode.getDisplayName() + "] 节点类型");
        }
    }

    /**
     * 处理bean和java的执行结果
     *
     * @param runner
     * @param br
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年12月19日 上午9:45:57
     */
    public void handleResult(Runner runner, BeanResult br) {
        if (GalaxyConstants.STATUS_SUCCESS.equals(br.getRetStatus())) {
            if (null != br.getResponse() && StringUtils.isNotEmpty(var)) {
                StriaUtil.checkContainArgsKey(runner.getArgs(), var);
                runner.getArgs().put(var, br.getResponse());
            }
        }
        runner.getOut().mergeResult(br);
    }

    /**
     * 执行具体的子服务
     *
     * @param runner
     * @return
     */
    public BeanResult execSubService(Runner runner) {
        // 条件满足处理子流程，否则返回成功
        boolean cond;
        if (StringUtils.isNotEmpty(condition))
            cond = runner.eval(boolean.class, condition);
        else cond = true;
        if (!cond) return new BeanResult();

        BaseRequest req = runner.eval(BaseRequest.class, request);
        ISysHead sys = null;
        try {
            sys = (ISysHead) req.fieldType(GalaxyConstants.SYS_HEAD).newInstance();
            BeanUtils.copy(req.getSysHead(), sys);
            sys.setServiceCode(serviceCode);
            sys.setMessageType(messageType);
            sys.setMessageCode(messageCode);
        } catch (Exception e) {
            throw new StriaException(e);
        }
        req.setSysHead(sys);
        ILocalHead local = null;
        if (null != req.getLocalHead()) {
            try {
                local = (ILocalHead) req.fieldType(GalaxyConstants.LOCAL_HEAD).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            BeanUtils.copy(req.getLocalHead(), local);
            req.setLocalHead(local);
        }
        IAppHead app = null;
        if (null != req.getLocalHead()) {
            app = req.fieldTypeNewInstance("appHead");
            BeanUtils.copy(req.getAppHead(), app);
            req.setAppHead(app);
        }
        req.setSubRequest(true);
        req.setDoCheck(runner.isDoCheck());
        req.setDoSubmit(runner.isDoSubmit());
        req.setDoACCheck(runner.isDoACCheck());
        //将父流程的序号传递给子流程
        req.setSubmitSeqNo(runner.getSubmitSeqNo());

        // 执行子流程
        BeanResult subBr = null;
        try {
            TwoTuple<BeanResult, Integer> subFlowResult = execSubService(req);
            subBr = subFlowResult.first;
            //将子流程的序号同步到父流程，保证Submit Service的序号连续性
            runner.setSubmitSeqNo(subFlowResult.second);
        } catch (BusinessException e) {
            if (log.isErrorEnabled())
                log.error(ExceptionUtils.getStackTrace(e));

            // 处理授权、确认类异常的RetStatus
            String retStatus = "F";
            if (e instanceof WithoutAuthorizationException)
                retStatus = "O";
            else if (e instanceof WithoutConfirmationException)
                retStatus = "C";
            else if (e instanceof WithoutAuthAndConfirmException)
                retStatus = "B";
            subBr = new BeanResult(e.getRets(), retStatus);
        }
        return subBr;
    }

    private TwoTuple<BeanResult, Integer> execSubService(BaseRequest request) {
        // 获取报文中的ServiceId
        String name = ServiceHandler.getServiceName(request);
        Class<?> interFace = MappingManager.getInstance().getInterface(name);

        if (null == interFace)
            throw new BusinessException(GalaxyConstants.CODE_FAILED, "[" + name
                    + "]RPC服务引用未找到，请检查系统配置！");

        ServiceProxy serviceProxy = ServiceProxy.getInstance();
        Attributes attributes = new ServiceAttributesBuilder()
                .setLoadbalance(AttributesBuilderSupport.LOADBALANCE_RANDOM)
                .setCheck(false).build();
        IProcess process = (IProcess) serviceProxy
                .getService(interFace, attributes);
        return process.subProcess(request);
    }

    /**
     * 开启新的分支事务
     * 并获取Dtp上下文中的BxId属性，设置到私有属性中。
     * 新生成的BxId存入Dtp上下文
     *
     * @return
     */
    @Override
    public void addBranchTransaction() {
    }

    /**
     * 恢复私有属性到Dtp上下文
     */
    @Override
    public void reSetBxId() {
    }

    @Override
    protected boolean skipNode(Runner runner) {
        return !runner.isDoSubmit();
    }
}
