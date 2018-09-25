package com.dcits.orion.stria.model;

import com.dcits.orion.base.common.ServiceHandler;
import com.dcits.orion.stria.config.StriaConstants;
import com.dcits.orion.stria.core.Runner;
import com.dcits.orion.stria.exception.StriaException;
import com.dcits.orion.stria.handlers.IHandler;
import com.dcits.orion.stria.utils.StriaUtil;
import com.dcits.galaxy.base.BaseGenerator;
import com.dcits.galaxy.base.bean.ArgumentBean;
import com.dcits.galaxy.base.data.BeanResult;
import com.dcits.galaxy.base.data.BaseRequest;
import com.dcits.galaxy.base.exception.BusinessException;
import com.dcits.galaxy.base.tuple.TwoTuple;
import com.dcits.galaxy.base.util.ClassLoaderUtils;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.core.access.ThreadLocalManager;
import com.dcits.galaxy.core.client.ServiceProxy;
import com.dcits.galaxy.core.client.builder.Attributes;
import com.dcits.galaxy.core.client.builder.ServiceAttributesBuilder;
import com.dcits.galaxy.core.spring.SpringApplicationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * 检查服务模型
 *
 * @author Tim
 * @version V1.0
 * @description
 * @update 2014年12月16日 上午9:59:13
 */
public abstract class AbstractServiceModel extends RunModel {

    private static final Logger log = LoggerFactory
            .getLogger(AbstractServiceModel.class);
    /**
     * @fields serialVersionUID
     */

    private static final long serialVersionUID = -5490981029676813528L;

    /**
     * 需要执行的class类路径
     */
    protected String clazz;
    /**
     * 需要执行的class对象的方法名称
     */
    protected String methodName;
    /**
     * 执行方法时传递的参数表达式变量名称
     */
    protected String args;
    /**
     * 加载模型时初始化的对象实例
     */
    protected Object invokeObject;

    /**
     * 执行的渠道类型
     */
    private String sourceType;
    /**
     * 执行的服务类型
     */
    private String serviceType;
    /**
     * 执行的服务参数类型
     */
    private String argsClazz;

    private List<ArgumentBean> argumentBeans;

    @Override
    public void exec(Runner runner) {
        long start = System.currentTimeMillis();
        try {
            if (log.isDebugEnabled())
                log.debug("[" + this.getClass().getSimpleName() + "] [" + getDisplayName()
                        + "] 节点，开始执行");
            // 基本检查
            baseCheck(runner);

            BaseRequest request = runner.getIn();
            // 检查渠道是否进行检查，不检查直接跳到下个节点
            if (!checkSourceType(request)) {
                runOutTransition(runner);
                return;
            }
            // 检查服务是否跳过当前节点，继续执行下一节点
            if (!isAuthConfirmCheck(request)) {
                if (log.isDebugEnabled())
                    log.debug("服务授权已通过，继续执行下一节点");
                runOutTransition(runner);
                return;
            }

            try {
                // 开启新的分支事务,从上下文中的BxId设置私有属性
                addBranchTransaction();
                doService(runner);
            } finally {
                // 重置DtpContext上下文中的BxId
                reSetBxId();
            }
        } finally {
            long end = System.currentTimeMillis();
            if (log.isDebugEnabled())
                log.debug("[" + getDisplayName() + "] 节点，执行时间 [" + (end - start) + "]");
        }
        runOutTransition(runner);
    }

    protected void doService(Runner runner) {
        // 获取服务执行实例
        invokeObject();
        if (invokeObject instanceof IHandler) {
            IHandler handler = (IHandler) invokeObject;
            handler.handle(runner);
        } else {
            TwoTuple<Class<?>[], Object[]> argumentsObject = StriaUtil.getArguments(runner, this.getArgumentBeans());
            // 获取执行方法
            final Method m = StriaUtil.getMethod(this.invokeObject, this.methodName, argumentsObject.first);
            if (AtomType.RPC.getMsg().equals(getServiceType())
                    && m.getReturnType() != BeanResult.class) {
                throw new StriaException("Method return type must be ["
                        + BeanResult.class.getName() + "]");
            }
            // 获取执行入参
            final Object[] o = argumentsObject.second;
            final String uuid = ThreadLocalManager.getUID();
            if (AtomType.RPC.getMsg().equals(getServiceType())) {

                // 是否合并结果
                boolean join = isMergeResult();
                if (join) {
                    // 异步并行检查，发现在压力测试下线程争抢和数据库连接部分开销太大。
                    // 故将异步future方式修改为同步串行合并处理方式
                    // 拿到调用的Future引用，当结果返回后，会被通知和设置到此Future。
                    // Future<BeanResult> fooFuture = RpcContext.getContext().getFuture();
                    // Future<BeanResult> fooFuture = BusinessExecutors.getInstance().getExecutor()
                    //        .submit(new ParallelMethodCallable(this, uuid, invokeObject, m, o));
                    // 增加并行调用的future到列表
                    // addParallelResult(runner, fooFuture);

                    // 同步并行检查
                    Object out;
                    /*try {*/
                    out = methodInvoke(invokeObject, m, o);
                    /*} catch (Throwable t) {
                        out = t;
                    }*/
                    addParallelResult(runner, out);
                } else {
                    Object out = methodInvoke(invokeObject, m, o);
                    // 处理串行处理结果，串行改为同步后，不需要future的结果处理了
                    // handleFutureResult(runner, fooFuture);
                    // 处理返回结果
                    // Bean和Java方式
                    if (out instanceof BeanResult) {
                        // 处理返回结果
                        handleResult(runner, (BeanResult) out);
                    }
                }
            } else {
                Object out = methodInvoke(invokeObject, m, o);
                // Bean和Java方式
                if (out instanceof BeanResult) {
                    // 处理返回结果
                    handleResult(runner, (BeanResult) out);
                }
            }
        }
    }

    /**
     * 执行服务
     *
     * @param invokeObject
     * @param m
     * @param o
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年12月19日 上午10:44:19
     */
    protected Object methodInvoke(Object invokeObject, Method m, Object[] o) {
        if (log.isDebugEnabled()) {
            log.debug("执行服务实例和方法\n服务类型 [" + getServiceType() + "]\n原子服务 [" + getDisplayName() + "]\n服务接口 [" + clazz + "]\n执行方法 [" + methodName + "]");
        }
        Object out = null;
        try {
            out = m.invoke(invokeObject, o);
        } catch (Throwable t) {
            Throwable e = t;
            if (e instanceof InvocationTargetException) {
                e = e.getCause();
            }
            if (e instanceof UndeclaredThrowableException) {
                e = e.getCause();
            }
            if (e instanceof InvocationTargetException) {
                e = e.getCause();
            }
            if (e instanceof BusinessException) {
                out = specialException((BusinessException) e);
                if (null == out)
                    out = new BeanResult(e);
            } else if (e instanceof RuntimeException) {
                BusinessException exception = ExceptionUtils.parseException(e);
                out = specialException(exception);
                if (null == out)
                    out = new BeanResult(e);
            } else {
                out = new BeanResult(e);
            }
        }
        return out;
    }

    /**
     * 特殊异常捕获处理
     *
     * @param e
     * @return
     */
    protected BeanResult specialException(BusinessException e) {
        return null;
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
    public abstract void handleResult(Runner runner, BeanResult br);

    /**
     * 获取服务实例
     *
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年12月19日 上午9:32:57
     */
    protected void invokeObject() {
        try {
            if (AtomType.RPC.getMsg().equals(getServiceType())) {
                Attributes attributes = null;
                boolean async = isMergeResult();
                if (async) {
                    // 获取内部服务的引用，是否需要参数，异步并行RPC远程服务调用处理
                    // 对于并行的检查，需要强制设置Scope为Remote，否则会获取不到future
                    attributes = new ServiceAttributesBuilder()
                            //.setAsync(true)
                            //.setScope(ServiceAttributesBuilder.SCOPE_REMOTE)
                            .setLoadbalance(ServiceHandler.getServiceLoadBalance())
                            .setCheck(false).build();
                } else {
                    attributes = new ServiceAttributesBuilder()
                            .setLoadbalance(ServiceHandler.getServiceLoadBalance())
                            .setCheck(false).build();
                }
                invokeObject = ServiceProxy.getInstance().getService(
                        ClassLoaderUtils.loadClass(clazz), attributes);
            } else if (AtomType.BEAN.getMsg().equals(getServiceType())) {
                invokeObject = SpringApplicationContext.getContext().getBean(
                        ClassLoaderUtils.loadClass(clazz));
            } else {
                invokeObject = BaseGenerator.create(ClassLoaderUtils.loadClass(clazz)).next();
            }
        } catch (BeansException | ClassNotFoundException e) {
            throw new StriaException("[" + getDisplayName() + "] 节点，获取对象实例 ["
                    + clazz + "] 失败", e);
        }

        if (invokeObject == null) {
            throw new StriaException("[" + getDisplayName()
                    + "] 节点，自定义模型 [class=" + clazz + "] 实例化对象失败");
        }
    }

    /**
     * 检查是否通过授权
     *
     * @param request
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年12月18日 下午12:58:15
     */
    protected abstract boolean isAuthConfirmCheck(BaseRequest request);

    /**
     * 节点的基本检查
     *
     * @param runner
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年12月19日 上午9:32:40
     */
    protected void baseCheck(Runner runner) {
        if (null == runner.getIn())
            throw new StriaException("[" + getDisplayName() + "] 节点，args["
                    + StriaConstants.MSG_KEY + "] 未找到");
        // 检查服务类型是否允许交易
        checkServiceType();
        // 检查流程目标节点是否允许的目标节点
        targetNodeValidate();
    }

    /**
     * 检查服务类型是否允许交易
     *
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年12月19日 上午9:28:36
     */
    protected void checkServiceType() {
        if (StringUtils.isEmpty(getServiceType())) {
            throw new StriaException(this.getName() + " serviceType 不能为空\n");
        } else if (!AtomType.RPC.getMsg().equals(getServiceType())) {
            throw new StriaException("[" + getDisplayName() + "] 节点，"
                    + this.getName() + " serviceType [" + getServiceType()
                    + "] 不能支持");
        }
    }

    /**
     * 处理串行执行的RPC服务执行结果
     *
     * @param runner
     * @param fooFuture
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年12月19日 上午9:28:50
     */
    protected abstract void handleFutureResult(Runner runner,
                                               Future<BeanResult> fooFuture);

    /**
     * 渠道检查
     *
     * @param inMsg
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年12月16日 上午10:33:12
     */
    protected boolean checkSourceType(BaseRequest inMsg) {
        if (StringUtils.isEmpty(this.sourceType) || "*".equals(this.sourceType) || "all".equals(this.sourceType))
            return true;

        String sourceType = null;
        if (null != inMsg.getSysHead()) {
            sourceType = inMsg.getSysHead().getSourceType();
            log.debug("交易渠道 [" + sourceType + "]");
            if (this.sourceType.indexOf(sourceType) == -1)
                return false;
        }
        return true;
    }

    /**
     * 结果是否需要合并
     *
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年12月19日 上午9:30:15
     */
    private boolean isMergeResult() {
        // 目标节点是否是join节点x
        NodeModel targetNode = getOutputs().get(0).getTarget();
        if (JoinModel.class.isInstance(targetNode)) {
            return true;
        }
        return false;
    }

    /**
     * 检查model的目标节点是否允许的节点类型
     *
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年12月19日 上午9:30:33
     */
    protected abstract void targetNodeValidate();

    /**
     * 向流程执行对象中添加并行的future，以待合并节点进行结果合并
     *
     * @param runner
     * @param result
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年12月19日 上午9:31:11
     */
    protected abstract void addParallelResult(Runner runner,
                                              Object result);

    /**
     * @return sourceType : return the property sourceType.
     */
    public String getSourceType() {
        return sourceType;
    }

    /**
     * @param sourceType : set the property sourceType.
     */
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    /**
     * @return argsClazz : return the property argsClazz.
     */
    public String getArgsClazz() {
        return argsClazz;
    }

    /**
     * @param argsClazz : set the property argsClazz.
     */
    public void setArgsClazz(String argsClazz) {
        this.argsClazz = argsClazz;
    }

    /**
     * @return serviceType : return the property serviceType.
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * @param serviceType : set the property serviceType.
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }


    /**
     * 根据传递的执行参数、模型的参数列表返回实际的参数对象数组
     *
     * @param execArgs
     * @param args
     * @return
     */
    protected Object[] getArgs(Map<String, Object> execArgs, String args) {
        Object[] objects = null;
        if (StringUtils.isNotEmpty(args)) {
            String[] argArray = args.split(",");
            objects = new Object[argArray.length];
            for (int i = 0; i < argArray.length; i++) {
                objects[i] = execArgs.get(argArray[i]);
            }
        }
        return objects;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public List<ArgumentBean> getArgumentBeans() {
        if (null == argumentBeans)
            this.argumentBeans = StriaUtil.getArgumentBean(args, argsClazz);
        return this.argumentBeans;
    }
}
