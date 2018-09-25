package com.dcits.galaxy.core.dubbo.rpc.filter;

import java.util.Map;

import org.slf4j.MDC;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.RpcInvocation;
import com.alibaba.fastjson.JSON;

@Activate(group = {Constants.PROVIDER, Constants.CONSUMER},order = Integer.MIN_VALUE)
public class GalaxyMdcFilter implements Filter {

    public final static String Const_MdcKey = "galaxy.mdc.id";

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

		// 忽略injvm协议
    	if (Constants.LOCAL_PROTOCOL.equals(invoker.getUrl().getProtocol())) {
			return invoker.invoke(invocation);
		}
		
		String side = invoker.getUrl().getParameter(Constants.SIDE_KEY);

		if (Constants.CONSUMER_SIDE.equals(side)) {
			handleConsumer((RpcInvocation) invocation);
			return invoker.invoke(invocation);
		} else if (Constants.PROVIDER_SIDE.equals(side)) {
			return handleProvider(invoker, invocation);
		}
		
		return invoker.invoke(invocation);
    }

	private void handleConsumer(RpcInvocation invocation) {
		Map<String, String> mdc = MDC.getCopyOfContextMap();
    	if(mdc == null||mdc.isEmpty()){
    		return;
    	}
    	invocation.setAttachment(Const_MdcKey, JSON.toJSONString(mdc));
	}
	
    private Result handleProvider(Invoker<?> invoker, Invocation invocation) {
    	String json = invocation.getAttachment(Const_MdcKey);
    	if(json != null){
    		@SuppressWarnings("unchecked")
			Map<String, String> mdc = JSON.parseObject(json, Map.class);
    		MDC.setContextMap(mdc);
    	}
    	Result result = invoker.invoke(invocation);
    	if(json != null){
    		MDC.clear();
    	}
    	return result;
	}

	@Deprecated
    public static String getMdcId() {
        String mdcKey = ConfigUtils.getProperty(Const_MdcKey);
        return mdcKey == null ? "platformId" : mdcKey;
    }
}
