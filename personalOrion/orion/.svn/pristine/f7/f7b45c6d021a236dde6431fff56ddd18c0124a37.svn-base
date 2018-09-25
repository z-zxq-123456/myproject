package com.dcits.orion.stria.core;

import com.dcits.orion.stria.Expression;
import com.dcits.orion.stria.config.StriaConstants;
import com.dcits.orion.stria.dao.mapper.Flow;
import com.dcits.orion.stria.exception.StriaException;
import com.dcits.orion.stria.impl.SpelExpression;
import com.dcits.orion.stria.model.AbstractServiceModel;
import com.dcits.orion.stria.model.FlowModel;
import com.dcits.galaxy.base.data.BeanResult;
import com.dcits.galaxy.base.data.ILocalHead;
import com.dcits.galaxy.base.data.BaseRequest;
import com.dcits.galaxy.core.spring.SpringApplicationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 流程执行过程中所传递的执行对象，其中包含流程定义、流程模型、流程实例对象、执行参数、返回的任务列表
 * <p/>
 * Created by Tim on 2015/5/19.
 */
public class Runner implements Serializable {

    private static final Logger log = LoggerFactory
            .getLogger(Runner.class);

    private static final long serialVersionUID = 2333556906277382431L;

    /**
     * 流程定义对象
     */
    private Flow flow;

    /**
     * 执行参数
     */
    private Map<String, Object> args;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 是否已合并 针对join节点的处理
     */
    private boolean isMerged = false;

    /**
     * 并行返回结果future列表
     *
     * @fields future
     */
    private List<List<Object>> parallelResult = new ArrayList<List<Object>>();

    /**
     * 服务执行结果
     */
    private BeanResult out = new BeanResult();

    /**
     * 前端授权信息
     */
    private ILocalHead localHead;

    /**
     * 表达式解析器
     */
    private Expression expression;

    /**
     * 是否进行检查处理
     */
    private boolean doCheck;

    /**
     * 是否进行提交处理
     */
    private boolean doSubmit;

    /**
     * 是否进行授权与确认处理
     */
    private boolean doACCheck;

    /**
     * 当前流程是否子流程处理
     */
    private boolean subService;

    /**
     * submit服务执行序列
     */
    private int submitSeqNo = 0;

    public <T> T eval(Class<T> c, String expr) {
        if (log.isDebugEnabled())
            log.debug("表达式 [{}]", expr);
        return expression.eval(c, expr, this.args, this.args.get(StriaConstants.MSG_KEY));
    }

    /**
     * 构造函数，接收流程定义、流程实例对象、执行参数
     *
     * @param flow
     * @param args
     */
    public Runner(Flow flow, Map<String, Object> args) {
        if (flow == null) {
            throw new StriaException("构造Runner对象失败，请检查Flow是否为空");
        }
        this.flow = flow;
        this.args = args;
        try {
            this.expression = (Expression) SpringApplicationContext.getContext().getBean("expression");
        } catch (NullPointerException e) {
            this.expression = new SpelExpression();
        }
        this.doCheck = getIn().isDoCheck();
        this.doSubmit = getIn().isDoSubmit();
        this.subService = getIn().isSubRequest();
        // 将请求的提交序号，赋值给runner
        // 保证父子流程的序号连续性
        this.submitSeqNo = getIn().getSubmitSeqNo();
        setLocalHead();
    }

    private void setLocalHead() {
        BaseRequest in = getIn();
        if (null == in) {
            return;
        }
        this.localHead = in.getLocalHead();
    }

    /**
     * 获取流程定义对象
     *
     * @return
     */
    public Flow getFlow() {
        return flow;
    }

    /**
     * 获取流程模型对象
     *
     * @return
     */
    public FlowModel getFlowModel() {
        return flow.getFlowModel();
    }

    /**
     * 获取执行参数
     *
     * @return
     */
    public Map<String, Object> getArgs() {
        return args;
    }

    /**
     * 返回当前操作人ID
     *
     * @return
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 设置当前操作人ID
     *
     * @param operator
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }


    /**
     * 判断是否已经成功合并
     *
     * @return
     */
    public boolean isMerged() {
        return isMerged;
    }

    /**
     * 设置是否为已合并
     *
     * @param isMerged
     */
    public void setMerged(boolean isMerged) {
        this.isMerged = isMerged;
    }

    /**
     * @return future : return the property future.
     */
    public List<List<Object>> getParallelResult() {
        return parallelResult;
    }

    public void clearParallelResult() {
        this.parallelResult.clear();
    }

    public void addParallelResult(Object result, AbstractServiceModel service) {
        List<Object> temp = new ArrayList<Object>();
        temp.add(result);
        temp.add(service);
        this.parallelResult.add(temp);
    }

    /**
     * @return out : return the property out.
     */
    public BeanResult getOut() {
        return out;
    }

    /**
     * @param out : set the property out.
     */
    public void setOut(BeanResult out) {
        this.out = out;
    }

    /**
     * @return localHead : return the property localHead.
     */
    public ILocalHead getLocalHead() {
        return localHead;
    }

    public BaseRequest getIn() {
        BaseRequest in = null;
        if (null != args && args.containsKey(StriaConstants.MSG_KEY)) {
            in = (BaseRequest) args.get(StriaConstants.MSG_KEY);
        }
        return in;
    }

    public boolean isDoCheck() {
        return doCheck;
    }

    public void setDoCheck(boolean doCheck) {
        this.doCheck = doCheck;
        this.getIn().setDoCheck(doCheck);
    }

    public boolean isDoSubmit() {
        return doSubmit;
    }

    public void setDoSubmit(boolean doSubmit) {
        this.doSubmit = doSubmit;
        this.getIn().setDoSubmit(doSubmit);
    }

    public boolean isSubService() {
        return subService;
    }

    public void setSubService(boolean subService) {
        this.subService = subService;
    }

    public int getSubmitSeqNo() {
        return submitSeqNo;
    }

    public int getIncreaseSubmitSeqNo() {
        return submitSeqNo++;
    }

    public void setSubmitSeqNo(int submitSeqNo) {
        this.submitSeqNo = submitSeqNo;
    }

    public boolean isDoACCheck() {
        return doACCheck;
    }

    public void setDoACCheck(boolean doACCheck) {
        this.doACCheck = doACCheck;
        this.getIn().setDoACCheck(doACCheck);
    }
}
