package com.dcits.orion.base.dubbo;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.dcits.galaxy.base.GalaxyConstants;
import com.dcits.galaxy.base.exception.BusinessException;
import com.dcits.galaxy.base.util.ErrorUtils;
import com.dcits.galaxy.base.util.ExceptionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对于服务提供者输出堆栈日志。
 * 对于消费者端的RpcException进行异常转换。
 * <p/>
 * Created by Tim on 2016/6/15.
 */
@Activate(group = {Constants.CONSUMER, Constants.PROVIDER})
public class BusinessExceptionFilter implements Filter {

    private static final Logger logger = LoggerFactory
            .getLogger(BusinessExceptionFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation)
            throws RpcException {
        // 忽略injvm协议
        if (Constants.LOCAL_PROTOCOL.equals(invoker.getUrl().getProtocol())) {
            return invoker.invoke(invocation);
        }
        String side = invoker.getUrl().getParameter(Constants.SIDE_KEY);
        Result result = null;
        if (Constants.CONSUMER_SIDE.equals(side)) {
            try {
                result = invoker.invoke(invocation);
            } catch (RpcException e) {
                String methodName = RpcContext.getContext().getMethodName();
                if (logger.isErrorEnabled()) {
                    logger.error("Local {}:{} --> Server {}:{}, invoker [{}.{}] RpcException! {}",
                            new Object[]{RpcContext.getContext().getLocalHost(), RpcContext.getContext().getLocalPort(),
                                    RpcContext.getContext().getRemoteHost(), RpcContext.getContext().getRemotePort(),
                                    invoker.getUrl().getPath(), methodName, e.getMessage()});
                }
                // 将Rpc层面的异常转换为业务异常。
                // Rpc time out error
                if (RpcException.TIMEOUT_EXCEPTION == e.getCode()) {
                    return new RpcResult(new BusinessException(
                            new com.dcits.galaxy.base.data.Result(GalaxyConstants.CODE_TIMEOUT,
                                    ErrorUtils.getErrorDesc(GalaxyConstants.CODE_TIMEOUT)), e));
                    // limitOver error
                } else if (20 == e.getCode()) {
                    return new RpcResult(new BusinessException(
                            new com.dcits.galaxy.base.data.Result("999996", ErrorUtils.getErrorDesc("999996"))
                            , e));
                    // other error
                } else {
                    return new RpcResult(new BusinessException(
                            new com.dcits.galaxy.base.data.Result("999995", ErrorUtils.getErrorDesc("999995"))
                            , e));
                }
            }
        } else if (Constants.PROVIDER_SIDE.equals(side)) {
            result = invoker.invoke(invocation);
            if (result.hasException()) {
                if (logger.isErrorEnabled()) {
                    logger.error(ExceptionUtils.getStackTrace(result.getException()));
                }
            }
        }
        return result;
    }
}
