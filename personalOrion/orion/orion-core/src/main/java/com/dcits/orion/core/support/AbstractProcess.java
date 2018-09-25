/**
 * <p>Title: AbstractService.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 2014年12月19日 上午11:04:58
 * @version V1.0
 */
package com.dcits.orion.core.support;

import com.dcits.galaxy.base.GalaxyConstants;
import com.dcits.galaxy.base.data.BaseRequest;
import com.dcits.galaxy.base.data.BaseResponse;
import com.dcits.galaxy.base.data.BeanResult;
import com.dcits.galaxy.base.data.ISysHead;
import com.dcits.galaxy.base.exception.BusinessException;
import com.dcits.galaxy.base.tuple.TwoTuple;
import com.dcits.galaxy.base.util.ErrorUtils;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.base.util.SeqUtils;
import com.dcits.galaxy.base.validate.Validator;
import com.dcits.galaxy.core.access.ThreadLocalManager;
import com.dcits.galaxy.dtp.DtpContext;
import com.dcits.orion.api.BusinessEngine;
import com.dcits.orion.api.BusinessTraceNo;
import com.dcits.orion.api.IProcess;
import com.dcits.orion.api.model.BusinessType;
import com.dcits.orion.base.common.ServiceHandler;
import com.dcits.orion.base.exception.NoRollBackException;
import com.dcits.orion.core.BusinessExecutors;
import com.dcits.orion.core.Context;
import com.dcits.orion.core.dtp.DtpWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;

/**
 * @author Tim
 * @version V1.0
 * @description
 * @update 2014年12月19日 上午11:04:58
 */

public abstract class AbstractProcess<T extends BaseRequest, R extends BaseResponse> implements IProcess<T, R> {

    private static final Logger logger = LoggerFactory
            .getLogger(BusinessService.class);

    /**
     * 业务流程引擎
     *
     * @fields businessEngine
     */
    @Resource
    private BusinessEngine businessEngine;

    @Resource
    private PlatformTransactionManager transactionManager;

    private final TransactionDefinition definition = new DefaultTransactionDefinition();

    /**
     * @param businessEngine
     *         : set the property businessEngine.
     */
    public void setBusinessEngine(BusinessEngine businessEngine) {
        this.businessEngine = businessEngine;
    }


    /**
     * 获取业务规则过程工厂
     *
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年12月19日 上午11:30:34
     */
    public BusinessEngine getBusinessEngine() {
        return businessEngine;
    }

    /**
     * @param req
     * @return
     * @description 业务框架执行业务服务流程入口
     * @version 1.0
     * @author Tim
     * @update 2015年1月16日 下午4:52:37
     */
    @Override
    public BeanResult process(T req) {
        BeanResult br = null;
        boolean isAppFlag = false;
        try {
            if (null != req.getAppHead()) {
                isAppFlag = true;
                // 设置AppHead到上下文
                Context.getInstance().setAppHead(req.getAppHead());
            }
            br = execProcess(req, BusinessType.NEW).first;
        } finally {
            // 结果不为空且成功的，结果中不存在AppHead，上下文中有AppHead
            if (isAppFlag && null != br && GalaxyConstants.STATUS_SUCCESS.equals(br.getRetStatus())
                    && null == br.getAppHead()) {
                br = new BeanResult(br.getResponse(), Context.getInstance().getAppHead());
            }
        }
        return br;
    }

    /**
     * @param req
     * @return
     * @description 业务服务内部执行业务服务流程入口
     * @version 1.0
     * @author Tim
     * @update 2015年1月16日 下午4:52:37
     */
    @Override
    public BeanResult innerProcess(T req) {
        return innerProcess(req, BusinessType.EXTENDS);
    }

    /**
     * @param req
     * @param businessType
     * @return
     * @description 业务服务内部执行业务服务流程入口
     * @version 1.0
     * @author Tim
     * @update 2015年1月16日 下午4:52:37
     */
    @Override
    public BeanResult innerProcess(T req, BusinessType businessType) {
        BeanResult br;
        // 当前节点类型
        String currentNodeType = Context.getInstance().getCurrentNodeType();
        // 当前上下文的Context
        ISysHead sysHead = Context.getInstance().getSysHead();
        try {
            if (sysHead != null) {
                //判断是否同服务调用
                if (sysHead.getServiceCode().equals(req.getSysHead().getServiceCode())
                        && sysHead.getMessageType().equals(req.getSysHead().getMessageType())
                        && sysHead.getMessageCode().equals(req.getSysHead().getMessageCode())) {
                    throw new BusinessException("100500", ErrorUtils.getErrorDesc("100500"));
                }
                //设置新的上下文
                Context.getInstance().setSysHead(req.getSysHead());
            }
            // 检查报文合法性
            try {
                Validator.getInstance().validate(req);
            } catch (Throwable t) {
                if (t instanceof BusinessException)
                    throw (BusinessException) t;
                else
                    throw new BusinessException(GalaxyConstants.CODE_FAILED,
                            t.getMessage());
            }
            // 执行服务处理
            br = execProcess(req, businessType).first;
        } finally {
            // 设置当前节点类型
            Context.getInstance().setCurrentNodeType(currentNodeType);
            if (sysHead != null) {
                // 将上下文设置回原服务的上下文
                Context.getInstance().setSysHead(sysHead);
            }
        }
        return br;
    }

    /**
     * 异步内部服务执行服务过程
     *
     * @param req
     * @return
     */
    @Override
    public void asyncInnerProcess(final T req) {
        // 当前节点类型
        final String currentNodeType = Context.getInstance().getCurrentNodeType();
        BusinessExecutors.getInstance().getExecutor().submit(new Runnable() {
            @Override
            public void run() {
                try {
                    ThreadLocalManager.setUID(SeqUtils.getStringSeq());
                    // 设置新的交易序号
                    BusinessTraceNo businessTraceNo = ServiceHandler.getBusinessTraceNo();
                    if (null != businessTraceNo)
                        req.getSysHead().setReference(businessTraceNo.generator());
                    Context.getInstance().setSysHead(req.getSysHead());
                    Context.getInstance().setCurrentNodeType(currentNodeType);
                    // 检查报文合法性
                    Validator.getInstance().validate(req);
                    // 执行服务处理
                    execProcess(req, BusinessType.NEW);
                } catch (Throwable t) {
                    if (logger.isErrorEnabled()) {
                        logger.error(ExceptionUtils.getStackTrace(t));
                    }
                } finally {
                    // 设置当前节点类型
                    Context.getInstance().cleanResource();
                    ThreadLocalManager.remove();
                }
            }
        });
    }

    /**
     * @param request
     * @return
     * @description 执行子业务服务流程入口
     * @version 1.0
     * @author Tim
     * @update 2015年1月16日 下午4:52:37
     */
    @Override
    public TwoTuple<BeanResult, Integer> subProcess(T request) {
        return execProcess(request, BusinessType.EXTENDS);
    }

    /**
     * 执行业务服务
     *
     * @param request
     * @param businessType
     * @return
     */
    private TwoTuple<BeanResult, Integer> execProcess(T request, BusinessType businessType) {
        // 流程引擎初始化，在初始化过程中，对于定义的Spring事务和全局事务放入上下文中。
        this.getBusinessEngine().init(request, businessType);
        boolean dtpSwitch = DtpWrapper.dtpIsOpen() && businessType == BusinessType.NEW;
        boolean txSwitch;
        // 全局事务开启，本地事务必须开启。
        if (dtpSwitch) {
            txSwitch = true;
        } else {
            txSwitch = Context.getInstance().txIsOpen() && businessType == BusinessType.NEW;
        }
        TransactionStatus status = null;
        TwoTuple<BeanResult, Integer> out;
        try {
            if (txSwitch) {
                // 开启Spring事务
                status = transactionManager.getTransaction(definition);
            }
            if (dtpSwitch) {
                // DTP事务开关 业务类型为新业务，并且如果开启了DTP事务控制，则确认是开启DTP
                // 开启DTP
                try {
                    DtpWrapper.tmOpen();
                } catch (Exception e) {
                    return new TwoTuple<>(new BeanResult(e), new Integer(1));
                }
                DtpWrapper.beginBranchTransaction();
            }
            // 服务前处理
            this.beforeProcess(request);
            // 执行流程
            out = this.getBusinessEngine().execFlow(request, businessType);
            if (out.first.getRetStatus().equals(GalaxyConstants.STATUS_SUCCESS)
                    //九鼎问题修改增加强制事务提交异常，判断业务异常为NoRollBackException强制提交服务事务，包含DTP事务 2017/04/06 for Tim
                    || (null != out.first.getThrowable() && out.first.getThrowable() instanceof NoRollBackException)) {
                // 只有成功的才做，服务后处理
                if (out.first.getRetStatus().equals(GalaxyConstants.STATUS_SUCCESS))
                    this.afterProcess(request, (R) out.first.getResponse());
                // 业务类型为新业务，并且如果开启了DTP事务控制
                if (dtpSwitch) {
                    DtpWrapper.tmSetConfirm();
                    try {
                        if (txSwitch) {
                            transactionManager.commit(status);
                        }
                    } catch (Throwable t) {
                    } finally {
                        // 无论commit成功或失败，都交由DTP服务事务一致。
                        DtpWrapper.tmConfirm();
                    }
                } else if (txSwitch) {
                    transactionManager.commit(status);
                }
            } else {
                if (txSwitch) {
                    transactionManager.rollback(status);
                }
                if (dtpSwitch) {
                    DtpWrapper.tmCancel();
                }
            }
        } catch (Throwable t) {
            try {
                if (txSwitch && !status.isCompleted()) {
                    transactionManager.rollback(status);
                }
            } finally {
                if (dtpSwitch) {
                    DtpWrapper.tmCancel();
                }
            }
            out = new TwoTuple<>(new BeanResult(t), new Integer(1));
        } finally {
            if (dtpSwitch) {
                DtpContext.clean();
            }
        }
        return out;
    }
}
