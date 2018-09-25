/**
 * <p>Title: SericeImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 2014年10月27日 上午11:25:54
 * @version V1.0
 */
package com.dcits.orion.core.support;

import com.alibaba.dubbo.rpc.RpcException;
import com.dcits.orion.api.AfterService;
import com.dcits.orion.api.IProcess;
import com.dcits.orion.api.Service;
import com.dcits.orion.api.SystemCheck;
import com.dcits.orion.base.common.ServiceHandler;
import com.dcits.galaxy.base.GalaxyConstants;
import com.dcits.galaxy.base.data.BaseRequest;
import com.dcits.galaxy.base.data.BeanResult;
import com.dcits.galaxy.base.data.Results;
import com.dcits.galaxy.base.exception.*;
import com.dcits.galaxy.base.util.ErrorUtils;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.base.validate.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @author Tim
 * @version V1.0
 * @description
 * @update 2014年10月27日 上午11:25:54
 */

public class BusinessService implements Service {

    private static final Logger logger = LoggerFactory
            .getLogger(BusinessService.class);

    /**
     * @param request
     * @return
     * @description 服务执行前系统公共检查
     * @version 1.0
     * @author Tim
     * @update 2015年1月19日 下午5:42:54
     */
    private BeanResult executeSystemCheck(BaseRequest request) {
        // 检查公共报文合法性
        validateCommon(request);
        // 获取ServiceRule
        BeanResult br = null;
        long start = -1L;
        if (logger.isInfoEnabled()) {
            start = System.currentTimeMillis();
        }
        try {
            SystemCheck commonCheck = ServiceHandler.getSystemCheck();
            if (null == commonCheck) {
                if (logger.isWarnEnabled())
                    logger.warn("SystemCheck Service not exist, skip common check!");
                return new BeanResult();
            }
            br = commonCheck.systemCheck(request);
        } finally {
            if (logger.isInfoEnabled()) {
                logger.info("SystemCheck execute time:" + (System.currentTimeMillis() - start));
            }
        }
        return br;
    }

    private void validateCommon(BaseRequest validatedObj) {
        long start = -1L;
        if (logger.isInfoEnabled()) {
            start = System.currentTimeMillis();
        }
        try {
            Validator v = Validator.getInstance();
            v.validate(validatedObj);
        } catch (Throwable t) {
            if (t instanceof BusinessException)
                throw (BusinessException) t;
            else
                throw new BusinessException(GalaxyConstants.CODE_FAILED,
                        t.getMessage(), t);
        } finally {
            if (logger.isInfoEnabled())
                logger.info("Validate - 执行时间["
                        + (System.currentTimeMillis() - start) + "]["
                        + validatedObj.getClass().getName() + "]");
        }
    }

    /**
     * @param request
     * @return
     * @description 执行服务
     * @version 1.0
     * @author Tim
     * @update 2015年1月19日 下午5:43:09
     */
    private BeanResult executeService(BaseRequest request) {
        BeanResult br = null;
        // 获取报文中的ServiceId
        String name = ServiceHandler.getServiceName(request);
        String mode = ServiceHandler.getAppMode();
        if (null == mode)
            mode = "produce";
        IProcess process;
        // 生产模式，强制走RPC方式调用。配合运维平台路由切换
        // 采用Map报文进行Handler接入，不在需要服务处理层进行强制Remote方式进行通讯
        //if ("produce".equals(mode)) {
        //    process = ServiceHandler.getProcess(name, ServiceAttributesBuilder.SCOPE_REMOTE);
        //} else {
        process = ServiceHandler.getProcess(name);
        //}
        long start = -1L;
        if (logger.isInfoEnabled()) {
            start = System.currentTimeMillis();
        }
        try {
            br = process.process(request);
            /**
             if (logger.isDebugEnabled()) {
             String serverIP = RpcContext.getContext().getRemoteHost();
             int serverPort = RpcContext.getContext().getRemotePort();
             String methodName = RpcContext.getContext().getMethodName();
             logger.debug("***********************oms BusinessService***********************\nserverIP =" + serverIP + "serverPort =" + serverPort + " methodName=" + methodName);
             }
             */
        } finally {
            if (logger.isInfoEnabled())
                logger.info("Service[" + name + "] execute time:" + (System.currentTimeMillis() - start));
        }

        return br;
    }

    /**
     * 服务后处理
     *
     * @param request
     * @param br
     * @description
     * @version 1.0
     * @author Tim
     * @update 2015年1月19日 下午3:29:13
     */
    private void executeAfterService(BaseRequest request, BeanResult br) {
        long start = -1L;
        if (logger.isInfoEnabled())
            start = System.currentTimeMillis();
        try {
            AfterService afterService = ServiceHandler.getAfterService();
            if (null == afterService) {
                if (logger.isWarnEnabled())
                    logger.warn("AfterService Service not exist, skip afterService process!");
            } else {
                afterService.afterProcess(request, br);
            }
        } catch (Throwable t) {
            if (logger.isWarnEnabled())
                logger.warn(ExceptionUtils.getStackTrace(t));
        } finally {
            if (logger.isInfoEnabled()) {
                logger.info("AfterService execute time:" + (System.currentTimeMillis() - start));
            }
        }
    }

    @Override
    public BeanResult execute(BaseRequest request) {
        BeanResult outRs = null;
        try {
            try {
                outRs = executeSystemCheck(request);
                if (GalaxyConstants.STATUS_SUCCESS.equals(outRs.getRetStatus()))
                    outRs = executeService(request);
            } catch (Throwable e1) {
                Throwable e = e1;
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
                else if (e instanceof RpcException) {
                    if (((RpcException) e).isTimeout()) {
                        throw new BusinessException(
                                GalaxyConstants.CODE_TIMEOUT,
                                ErrorUtils
                                        .getErrorDesc(GalaxyConstants.CODE_TIMEOUT),
                                e);
                    } else {
                        throw new BusinessException(
                                GalaxyConstants.CODE_FAILED,
                                e.getClass().getSimpleName() + ":"
                                        + e.getMessage(), e);
                    }
                } else if (e instanceof RuntimeException)
                    throw ExceptionUtils.parseException(e);
                else {
                    if (e instanceof BusinessException)
                        throw (BusinessException) e;
                    else
                        throw new BusinessException(
                                GalaxyConstants.CODE_FAILED,
                                e.getClass().getSimpleName() + ":"
                                        + e.getMessage(), e);
                }
            }
        } catch (Throwable e) {
            if (logger.isErrorEnabled())
                logger.error(ExceptionUtils.getStackTrace(e));
            Results rs;
            String retStatus = "F";
            if (e instanceof BusinessException) {
                // 处理授权、确认类异常的RetStatus
                if (e instanceof WithoutAuthorizationException)
                    retStatus = "O";
                else if (e instanceof WithoutConfirmationException)
                    retStatus = "C";
                else if (e instanceof WithoutAuthAndConfirmException)
                    retStatus = "B";
                rs = ((BusinessException) e).getRets();
            } else
                rs = new Results(GalaxyConstants.CODE_FAILED, e.getCause()
                        .getClass().getSimpleName()
                        + ":" + e.getCause().getMessage());
            outRs = new BeanResult(rs, retStatus);
        } finally {
            executeAfterService(request, outRs);
        }
        return outRs;
    }
}
