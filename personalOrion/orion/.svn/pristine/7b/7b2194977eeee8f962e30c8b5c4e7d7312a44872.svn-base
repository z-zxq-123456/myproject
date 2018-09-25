package com.dcits.orion.core.dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.RpcInvocation;
import com.dcits.orion.core.Context;
import com.dcits.galaxy.base.util.SeqUtils;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.core.access.ThreadLocalManager;

/**
 * 跨JVM的Context上下文传递。
 * <p/>
 * Created by Tim on 2016/6/15.
 */
@Activate(group = {Constants.CONSUMER, Constants.PROVIDER})
public class BusinessContextFilter implements Filter {
    private static final Logger logger = LoggerFactory
            .getLogger(BusinessContextFilter.class);

    private static final String BUSINESS_PLATFORM_ID = "business_platform_id";
    private static final String BUSINESS_CONTEXT = "business_context";

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation)
            throws RpcException {
        // 忽略Injvm协议
    	if (Constants.LOCAL_PROTOCOL.equals(invoker.getUrl().getProtocol())) {
            return invoker.invoke(invocation);
        }

        String side = invoker.getUrl().getParameter(Constants.SIDE_KEY);
        if (Constants.CONSUMER_SIDE.equals(side)) {
            return handleConsumer(invoker, (RpcInvocation) invocation);
        } else if (Constants.PROVIDER_SIDE.equals(side)) {
            return handleProvider(invoker, invocation);
        }
        return invoker.invoke(invocation);
    }

    private Result handleConsumer(Invoker<?> invoker, RpcInvocation invocation) {
        Context context = ThreadLocalManager.getTranContext();
        String platFormId = "";
        if (null != context && StringUtils.isNotEmpty(context.getPlatformId())) {
            platFormId = context.getPlatformId();
            invocation.setAttachment(BUSINESS_CONTEXT, Context.serializeContext(context));
        } else if (StringUtils.isNotEmpty(ThreadLocalManager.getUID())) {
            platFormId = ThreadLocalManager.getUID();
        } else {
            platFormId = SeqUtils.getStringSeq();
        }
        invocation.setAttachment(BUSINESS_PLATFORM_ID, platFormId);
        return invoker.invoke(invocation);
    }

    private Result handleProvider(Invoker<?> invoker, Invocation invocation) {
        String platFormId = invocation.getAttachment(BUSINESS_PLATFORM_ID);
        if (null != platFormId) {
            ThreadLocalManager.setUID(platFormId);
            String contextJson = invocation.getAttachment(BUSINESS_CONTEXT);
            if (null != contextJson) {
                Context context = Context.deserialize(contextJson);
                ThreadLocalManager.setTranContext(context);
            }
        }
        try {
            return invoker.invoke(invocation);
        } finally {
            ThreadLocalManager.remove();
        }
    }
}
