package com.dcits.galaxy.logclient.trace;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.dcits.galaxy.logclient.help.TraceLoggerHelper;
import com.dcits.galaxy.logcover.common.DataUtil;
import com.dcits.galaxy.logcover.config.ConfigConstants;
import com.dcits.galaxy.logcover.config.LogConfCache;
import com.dcits.galaxy.logcover.model.EcmTcecirSr;
import com.dcits.galaxy.logcover.model.EcmTcecirSs;

@Activate(group = Constants.PROVIDER )
public class SLoggerFilter implements Filter {	
	
	
	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation)
			throws RpcException {			
		String serviceClass = invocation.getInvoker().getInterface().getName();
		if(ConfigConstants.EXCLUDE_SERVICE_MAP.containsKey(serviceClass)||!LogConfCache.isCollChain()){
			return invoker.invoke(invocation);
		}
		Result result = null;
		try {
			invokeBefore(invoker, invocation);
			result = invoker.invoke(invocation);
			invokeAfter(invoker, invocation, true);
		} catch (Exception e) {
			invokeAfter(invoker, invocation, false);
			throw e;
		}
		return result;
	}
	
	private void invokeBefore(Invoker<?> invoker, Invocation invocation){
		String traceId = TraceLoggerHelper.getTraceId();
		if(DataUtil.isNull(traceId)){
			return;
		}
		String cycleId = TraceLoggerHelper.getCirId();
		if(DataUtil.isNull(cycleId)){
			return;
		}
		TraceLoggerHelper.putTraceId(traceId);		
		String parentCycleId = cycleId;
		TraceLoggerHelper.putParentCirId(parentCycleId);
		
		EcmTcecirSr sr = new EcmTcecirSr();
		sr.setCirId(cycleId);
		sr.setTraceId( traceId );
		String threadName = Thread.currentThread().getName();
		sr.setCirServerThdnum( threadName );
		TraceWriter.writeCircleSR( sr );		
		TraceLoggerHelper.putCirId(cycleId);
	}
	
	private void invokeAfter(Invoker<?> invoker, Invocation invocation,boolean isSuccesed){		
		String traceId = TraceLoggerHelper.getTraceId();
		if(DataUtil.isNull(traceId)){
			return;
		}
		String cycleId = TraceLoggerHelper.getCirId();
		if(DataUtil.isNull(cycleId)){
			return;
		}
		EcmTcecirSs ss = new EcmTcecirSs();
		ss.setCirId(cycleId);
		ss.setTraceId( traceId );
		ss.setCirServerStatus(isSuccesed);
		TraceWriter.writeCircleSS(ss);
		
		TraceLoggerHelper.clearParentCirId();
		TraceLoggerHelper.clearCirId();
	}
	
	
}