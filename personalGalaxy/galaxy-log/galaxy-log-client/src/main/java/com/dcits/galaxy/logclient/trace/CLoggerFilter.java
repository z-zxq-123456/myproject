package com.dcits.galaxy.logclient.trace;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.dcits.galaxy.logclient.help.TraceLoggerHelper;
import com.dcits.galaxy.logcover.common.DataUtil;
import com.dcits.galaxy.logcover.config.ConfigConstants;
import com.dcits.galaxy.logcover.config.LogConfCache;
import com.dcits.galaxy.logcover.model.EcmTcechainStart;
import com.dcits.galaxy.logcover.model.EcmTcecinEnd;
import com.dcits.galaxy.logcover.model.EcmTcecirCr;
import com.dcits.galaxy.logcover.model.EcmTcecirCs;

@Activate(group = Constants.CONSUMER)
public class CLoggerFilter implements Filter {
	
	private final static String Key_Trace_Class = "com.dcits.orion.api.Handler";
	
	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation)
			throws RpcException {
		String serviceClass = invocation.getInvoker().getInterface().getName();
		if(ConfigConstants.EXCLUDE_SERVICE_MAP.containsKey(serviceClass)||!LogConfCache.isCollChain()){
			return invoker.invoke(invocation);
		}
		Map<String, String> inJvmContext = new HashMap<>();
		
		String traceId = TraceLoggerHelper.getTraceId();
		
		if( DataUtil.isNull(traceId)&& Key_Trace_Class.equals(serviceClass) ){			
			startTrace(invoker, invocation,inJvmContext);
		}
		
		Result result = null;
		try {			
			invokeBefore(invoker, invocation,inJvmContext);			
			result = invoker.invoke(invocation);			
			invokeAfter(invoker, result, invocation,true,inJvmContext);
			
			if( Key_Trace_Class.equals(serviceClass) ){		
				endTrace(invoker, invocation, true,inJvmContext);
			}
			
		} catch( Throwable e ){
			invokeErrorAfter(invoker, invocation,false,inJvmContext,e);
			if( Key_Trace_Class.equals(serviceClass) ){
				endTrace(invoker, invocation, false, inJvmContext);
			}
			throw e;
		}
		return result;
	}
	
	private void startTrace(Invoker<?> invoker, Invocation invocation,Map<String, String> InJvmContext){
		String traceId = UUID.randomUUID().toString();
		EcmTcechainStart chainStart = new EcmTcechainStart();
		chainStart.setTraceId(traceId);
		chainStart.setTraceSttime( System.currentTimeMillis() + "");
		TraceWriter.writeStartTrace(chainStart);
		RpcContext.getContext().setAttachment(TraceLoggerHelper.Key_TraceId, traceId);
		TraceLoggerHelper.putTraceId(traceId);
	}
	
	private void invokeBefore(Invoker<?> invoker, Invocation invocation,Map<String, String> inJvmContext){
		URL url = invoker.getUrl();
		
		boolean isInJvm = false;
		String protocol = url.getProtocol();
		if( Constants.LOCAL_PROTOCOL.equals(protocol) ){
			isInJvm = true;
		}
		
		String localHost = RpcContext.getContext().getLocalHost();
		int localPort = RpcContext.getContext().getLocalPort();
		
		String cycleId = UUID.randomUUID().toString();
		String parentCycleId = TraceLoggerHelper.getParentCirId();
		String traceId = TraceLoggerHelper.getTraceId();
		
		EcmTcecirCs cs = new EcmTcecirCs();
		cs.setCirId(cycleId);
		cs.setCirParentId(parentCycleId);
		cs.setTraceId( traceId );
		
		if( !isInJvm){
			cs.setCirType(ConfigConstants.CIR_TYPE_RPC);
		}
		cs.setCirClientIpport( localHost , localPort+"");
		
		Object[] arguments = invocation.getArguments();
		if( arguments != null && arguments.length > 0){
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < arguments.length; i++) {
				buffer.append( arguments[i]==null?"":arguments[i] + "||" );
			}
			cs.setCirInMsg(buffer.toString());
		}
		String service = invocation.getInvoker().getInterface().getName();
		if(Key_Trace_Class.equals(service) ){			
			writeLogPlat(arguments[0]);
		}
		String remoteHost = url.getHost();
		int remotePort = url.getPort();
		cs.setCirServerIpport(remoteHost, remotePort + "");
		
		String threadName = Thread.currentThread().getName();
		cs.setCirClientThdnum( threadName );
		
		String serviceClass = invocation.getInvoker().getInterface().getName();
		cs.setCirServerSer( serviceClass );
		String serviceMethod = invocation.getMethodName();
		cs.setCirServerMtd( serviceMethod );		
		TraceWriter.writeCircleCS(cs);
		RpcContext.getContext().setAttachment(TraceLoggerHelper.Key_CirId, cycleId);
		RpcContext.getContext().setAttachment(TraceLoggerHelper.Key_TraceId, traceId);
		TraceLoggerHelper.putCirId(cycleId);
		
	}
	
	private void invokeAfter(Invoker<?> invoker,Result result, Invocation invocation,boolean isSuccesed,Map<String, String> inJvmContext){
		String traceId = TraceLoggerHelper.getTraceId();
		if(DataUtil.isNull(traceId)){
			return;
		}
		String cycleId = TraceLoggerHelper.getCirId();
		if(DataUtil.isNull(cycleId)){
			return;
		}
		EcmTcecirCr cr = new EcmTcecirCr();
		cr.setCirId(cycleId);
		cr.setTraceId( traceId );
		cr.setCirClientStatus( isSuccesed );		
		//TODO 输出报文,以及失败信息。	
		if( result != null){
			Object resultValue= result.getValue();
			cr.setCirOutMsg( resultValue==null? "" : resultValue.toString() );
			Throwable e = result.getException();
			if( e != null){
				String message = e.getMessage();
				cr.setCirServerFailinfo(message==null ? "" : message );				
			}
		}
		TraceWriter.writeCircleCr( cr );
		TraceLoggerHelper.clearCirId();
	}
	private void invokeErrorAfter(Invoker<?> invoker, Invocation invocation,boolean isSuccesed,Map<String, String> inJvmContext,Throwable e){
		String traceId = TraceLoggerHelper.getTraceId();
		if(DataUtil.isNull(traceId)){
			return;
		}
		String cycleId = TraceLoggerHelper.getCirId();
		if(DataUtil.isNull(cycleId)){
			return;
		}
		EcmTcecirCr cr = new EcmTcecirCr();
		cr.setCirId(cycleId);
		cr.setTraceId( traceId );
		cr.setCirClientStatus( isSuccesed );		
		//TODO 输出报文,以及失败信息。	
		if( e != null){
			String message= e.getMessage();
			cr.setCirServerFailinfo(message==null ? "" : message );
		}
		TraceWriter.writeCircleCr( cr );
		TraceLoggerHelper.clearCirId();
	}
	
	private void endTrace(Invoker<?> invoker, Invocation invocation,boolean isSuccesed,Map<String, String> inJvmContext){
		String traceId = TraceLoggerHelper.getTraceId();
		if(DataUtil.isNull(traceId)){
			return;
		}
		EcmTcecinEnd chainEnd = new EcmTcecinEnd();
		chainEnd.setTraceId(traceId);
		chainEnd.setTraceStatus(isSuccesed);
		TraceWriter.writeEndTrace(chainEnd);		
		TraceLoggerHelper.clearTraceId();
	}
	 //tangxlf 2016-10-27 添加 用于写日志平台的调用链业务字段
    @SuppressWarnings("unchecked")
	private void  writeLogPlat(Object msg){      	    
    		TraceWriter.writeBusColTrace((Map<String,Map<String,Object>>)msg);
    }
	
}
