package com.dcits.orion.stria.model;

import com.dcits.orion.stria.core.Runner;
import com.dcits.orion.stria.exception.StriaException;
import com.dcits.orion.stria.utils.StriaUtil;
import com.dcits.galaxy.base.GalaxyConstants;
import com.dcits.galaxy.base.data.BeanResult;
import com.dcits.galaxy.base.data.BaseRequest;
import com.dcits.galaxy.base.util.StringUtils;

import java.util.concurrent.Future;

/**
 * 原子服务模型
 *
 * @author Tim
 * @version V1.0
 * @description
 * @update 2014年12月16日 上午9:59:13
 */
public class AtomServiceModel extends AbstractServiceModel {

    /**
     * 执行的返回值变量
     */
    private String var;

    /**
     * @fields serialVersionUID
     */
    private static final long serialVersionUID = 3777985088360320592L;

    @Override
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

    @Override
    protected void addParallelResult(Runner runner, Object result) {
        //增加异步处理的future存储，这里没有异步处理机制，不做实现
        // runner.addFuture(fooFuture, this);
    }

    @Override
    protected void handleFutureResult(Runner runner,
                                      Future<BeanResult> fooFuture) {
        //异步处理的结果处理，此部分仅仅实现父类的抽象方法，不做实现
        /**
         BeanResult out;
         try {
         out = fooFuture.get();
         // 服务结果不允许为null
         if (null == out || null == out.getRs()) {
         throw new BusinessException(GalaxyConstants.CODE_FAILED,
         "Method return BeanResult and BeanResult.getRs must not be null!");
         }
         if (GalaxyConstants.STATUS_SUCCESS.equals(out.getRetStatus())) {
         if (StringUtils.isNotEmpty(var) && null != out.getResponse()) {
         runner.getArgs().put(var, out.getResponse());
         }
         runner.getOut().mergeResult(out);// 合并所有原子的结果
         } else
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
         }*/
    }

    @Override
    protected void checkServiceType() {
        if (StringUtils.isEmpty(getServiceType())) {
            throw new StriaException(this.getName() + " serviceType 不能为空\n");
        } else if (!AtomType.RPC.getMsg().equals(getServiceType())
                && !AtomType.LOCAL.getMsg().equals(getServiceType())
                && !AtomType.BEAN.getMsg().equals(getServiceType())) {
            throw new StriaException(this.getName() + " serviceType["
                    + getServiceType() + "] 不能支持");
        }
    }

    @Override
    protected boolean checkSourceType(BaseRequest inMsg) {
        // 需进行当前节点检查
        return true;
    }

    @Override
    public void handleResult(Runner runner, BeanResult br) {
        if (GalaxyConstants.STATUS_SUCCESS.equals(br.getRetStatus())) {
            if (null != br.getResponse() && StringUtils.isNotEmpty(var)) {
                StriaUtil.checkContainArgsKey(runner.getArgs(), var);
                runner.getArgs().put(var, br.getResponse());
            }
            runner.getOut().mergeResult(br);
        } else {
            runner.getOut().mergeResult(br);
            runner.getOut().setRetStatus(br.getRetStatus());
        }
    }

    @Override
    protected boolean isAuthConfirmCheck(BaseRequest inMsg) {
        // 不做授权与确认检查，默认检查通过
        return true;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
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
//    	if(DtpContext.isInDtp()){
//    		DtpContext.setBxid(this.bxId);
//    	}
    }

    @Override
    protected boolean skipNode(Runner runner) {
        return !runner.isDoSubmit();
    }
}
