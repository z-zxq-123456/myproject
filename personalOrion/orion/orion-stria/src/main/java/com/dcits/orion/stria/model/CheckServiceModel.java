package com.dcits.orion.stria.model;

import com.alibaba.dubbo.rpc.RpcException;
import com.dcits.orion.stria.core.Runner;
import com.dcits.orion.stria.exception.StriaException;
import com.dcits.galaxy.base.GalaxyConstants;
import com.dcits.galaxy.base.data.BeanResult;
import com.dcits.galaxy.base.data.BaseRequest;
import com.dcits.galaxy.base.exception.BusinessException;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.util.ExceptionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 检查服务模型
 *
 * @author Tim
 * @version V1.0
 * @description
 * @update 2014年12月16日 上午9:59:13
 */
public class CheckServiceModel extends AbstractServiceModel {

    @Override
    protected void targetNodeValidate() {
        if (getOutputs().size() != 1)
            throw new StriaException("[" + getDisplayName() + "] 节点，只能有一个目标节点");
        // 目标节点是否是join节点
        NodeModel targetNode = getOutputs().get(0).getTarget();
        if (!DecisionModel.class.isInstance(targetNode)
                && !JoinModel.class.isInstance(targetNode)
                && !ForkModel.class.isInstance(targetNode)
                && !CheckServiceModel.class.isInstance(targetNode)
                && !ConfirmServiceModel.class.isInstance(targetNode)
                && !AuthServiceModel.class.isInstance(targetNode)
                && !AtomServiceModel.class.isInstance(targetNode)
                && !EventServiceModel.class.isInstance(targetNode)
                && !SubServiceModel.class.isInstance(targetNode)
                && !EndModel.class.isInstance(targetNode)) {
            throw new StriaException("当前节点 [" + getDisplayName() + "] 的目标节点，不能为 ["
                    + targetNode.getDisplayName() + "] 节点类型");
        }
    }

    @Override
    protected void addParallelResult(Runner runner, Object result) {
        runner.addParallelResult(result, this);
    }

    @Override
    protected void handleFutureResult(Runner runner,
                                      Future<BeanResult> fooFuture) {
        BeanResult out;
        try {
            out = fooFuture.get();
            // 服务结果不允许为null
            if (null == out || null == out.getRs()) {
                throw new BusinessException(GalaxyConstants.CODE_FAILED,
                        "Method return BeanResult and BeanResult.getRs must not be null!");
            }
            if (!GalaxyConstants.STATUS_SUCCESS.equals(out.getRetStatus()))
                throw new BusinessException(out.getRs());
        } catch (RpcException e1) {
            Throwable e = e1.getCause();
            if (e instanceof InvocationTargetException) {
                e = e.getCause();
            }
            if (e instanceof UndeclaredThrowableException) {
                e = e.getCause();
            }
            if (e instanceof InvocationTargetException) {
                e = e.getCause();
            }

            if (e instanceof BusinessException)
                throw (BusinessException) e;
            else if (e instanceof GalaxyException)
                throw new BusinessException(GalaxyConstants.CODE_FAILED,
                        e.getMessage(), e);
            else if (e instanceof RuntimeException)
                throw new BusinessException(ExceptionUtils.parseException(e)
                        .getRets(), e);
            else {
                throw new BusinessException(GalaxyConstants.CODE_FAILED, e
                        .getClass().getSimpleName() + ":" + e.getMessage(), e);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new BusinessException(GalaxyConstants.CODE_FAILED, e
                    .getClass().getSimpleName() + ":" + e.getMessage(), e);
        }
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
    protected boolean isAuthConfirmCheck(BaseRequest inMsg) {
        // 需进行当前节点检查
        return true;
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
        return !runner.isDoCheck();
    }
}
