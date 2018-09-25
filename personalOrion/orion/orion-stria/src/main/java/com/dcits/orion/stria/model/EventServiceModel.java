package com.dcits.orion.stria.model;

import com.dcits.orion.api.model.EventRequest;
import com.dcits.orion.stria.core.Runner;
import com.dcits.orion.stria.core.StriaThreadLocal;
import com.dcits.orion.stria.engine.StriaEventEngine;
import com.dcits.orion.stria.exception.StriaException;
import com.dcits.orion.stria.utils.StriaUtil;
import com.dcits.galaxy.base.GalaxyConstants;
import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.base.data.BaseRequest;
import com.dcits.galaxy.base.data.BeanResult;
import com.dcits.galaxy.base.tuple.ThreeTuple;
import com.dcits.galaxy.base.tuple.TwoTuple;
import com.dcits.galaxy.base.util.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.concurrent.Future;

/**
 * 事件节点处理模型
 *
 * @author Tim
 * @version V1.0
 * @description
 * @update 2014年12月16日 上午9:59:13
 */
public class EventServiceModel extends AbstractServiceModel {

    private static final long serialVersionUID = 6580577000951206117L;

    /**
     * 事件节点进入条件
     */
    private String condition;

    /**
     * 事件处理节点的返回
     */
    private String var;

    private static final Logger log = LoggerFactory
            .getLogger(EventServiceModel.class);

    @Override
    public void doService(Runner runner) {
        //增加约束条件判断，如果不满足执行条件直接返回，不再继续执行后续节点
        if (StringUtils.isNotEmpty(this.getCondition()) && !runner.eval(Boolean.class, this.getCondition())) {
            return;
        }
        // 获取事件节点处理方法，获取事件模型。
        invokeObject();
        //节点名称，使用节点名称将三元组的信息，存入StriaLocal中
        String key = new StringBuffer().append(runner.getFlow().getFlowid()).append("-").append(this.getName()).toString();
        ThreeTuple<String, String, AbstractBean> out = null;
        Runner eventRunner = null;
        // 检查本地的ThreadLocal是否已经存在事件服务的三元组
        if (StriaThreadLocal.containsKey(key)) {
            // 通过ThreadLocal中获取三元组对象
            out = (ThreeTuple<String, String, AbstractBean>) StriaThreadLocal.get(key);
            // 事件流程处理
            eventRunner = executeEventFlow(runner.getIn(), runner.getFlow().getFlowid(), out);
        } else {
            // 获取执行入参
            TwoTuple<Class<?>[], Object[]> argumentsObject = StriaUtil.getArguments(runner, this.getArgumentBeans());
            // 执行E的业务服务
            Method m = StriaUtil.getMethod(this.invokeObject, this.methodName, argumentsObject.first);
            // 获取事件处理模型，first：产品类型；second：事件类型；three：事件模型；
            Object o = methodInvoke(invokeObject, m, argumentsObject.second);
            if (o instanceof ThreeTuple) {
                // 事件服务负责流程调度
                out = (ThreeTuple<String, String, AbstractBean>) o;
                // 将节点获取的数据，存入ThreadLocal
                StriaThreadLocal.put(key, out);
                // 事件流程处理
                eventRunner = executeEventFlow(runner.getIn(), runner.getFlow().getFlowid(), out);
            } else if (o instanceof Runner) {
                // 业务自身负责事件流程调用
                eventRunner = (Runner) o;
            } else {
                throw new StriaException("错误的事件服务返回类型！");
            }
        }

        if (runner.isDoCheck() && null != eventRunner && eventRunner.getOut().getRetStatus().equals("S")) {
            //合并事假流程中的别名参数与主服务
            StriaUtil.mergeEventArgs(runner.getArgs(), eventRunner.getArgs(), var);
        }
        // modify for sonar
        if (null != eventRunner) {
            runner.getOut().mergeResult(eventRunner.getOut());
        }
    }

    /**
     * 执行事件服务
     *
     * @param req
     * @param args
     * @return
     */
    private Runner executeEventFlow(BaseRequest req, String flowId, ThreeTuple<String, String, AbstractBean> args) {
        // 事件流程
        StriaEventEngine engine = StriaUtil.getEventEngine();
        // 组织事件流程请求
        EventRequest eventRequest = StriaUtil.getEventRequest(req, args.first, args.second, args.three);
        // 将E服务节点名称作为事件服务ID，服务流程ID + “-” + 事件服务节点ID
        eventRequest.setEventServiceId(new StringBuffer().append(flowId).append("-").append(this.getName()).toString());
        // 初始化事件流程引擎
        engine.init(eventRequest);
        // 调起事件处理流程
        return engine.execute(eventRequest);
    }

    /**
     * @return serviceType : return the property serviceType.
     */
    @Override
    public String getServiceType() {
        // 默认Spring Bean
        return "SPRING BEAN";
    }

    @Override
    protected void checkServiceType() {
    }

    @Override
    protected void targetNodeValidate() {
        if (getOutputs().size() != 1)
            throw new StriaException("[" + getDisplayName() + "] 节点，只能有一个目标节点");
        // 目标节点是否是join节点
        // 原子服务部提供并行节点处理，考虑多服务类型以及分布式事物控制问题
        NodeModel targetNode = getOutputs().get(0).getTarget();
        if (!DecisionModel.class.isInstance(targetNode)
                && !AtomServiceModel.class.isInstance(targetNode)
                && !SubServiceModel.class.isInstance(targetNode)
                && !EventServiceModel.class.isInstance(targetNode)
                && !EndModel.class.isInstance(targetNode)) {
            throw new StriaException("当前节点 [" + getDisplayName() + "] 的目标节点，不允许定义为 ["
                    + targetNode.getDisplayName() + "] 节点类型");
        }
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
//    	if(DtpContext.isInDtp()){
//    		this.bxId = DtpContext.getBxid();
//    		String newBxId = DtpWrapper.addBranchTransaction(this.bxId);
//    		DtpContext.setBxid(newBxId);
//    	}
    }

    /**
     * 恢复私有属性到Dtp上下文
     */
    @Override
    public void reSetBxId() {
//    	if(DtpContext.isInDtp())
//    		DtpContext.setBxid(this.bxId);
    }

    @Override
    protected boolean skipNode(Runner runner) {
        return !(runner.isDoSubmit() || runner.isDoCheck());
    }

    @Override
    public void handleResult(Runner runner, BeanResult br) {
        if (GalaxyConstants.STATUS_SUCCESS.equals(br.getRetStatus())) {
            runner.getOut().mergeResult(br);
        } else {
            runner.getOut().mergeResult(br);
            runner.getOut().setRetStatus(br.getRetStatus());
        }
    }

    @Override
    protected boolean isAuthConfirmCheck(BaseRequest request) {
        // 不做授权与确认检查，默认检查通过
        return true;
    }

    @Override
    protected void handleFutureResult(Runner runner, Future<BeanResult> fooFuture) {
        //异步处理的结果处理，此部分仅仅实现父类的抽象方法，不做实现
    }

    @Override
    protected void addParallelResult(Runner runner, Object result) {
        //增加异步处理的future存储，这里没有异步处理机制，不做实现
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
