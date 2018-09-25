package com.dcits.galaxy.logclient.help;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import com.alibaba.dubbo.rpc.RpcContext;


public class TraceLoggerHelper {

	public final static String Key_TraceId = "traceId";
	public final static String Key_CirId = "cirId";
	public final static String Key_ParentCirId = "parentCirId";
	
	private static final ThreadLocal<Map<String, String>> localContext = new ThreadLocal<Map<String, String>>(){
		@Override
		protected Map<String, String> initialValue() {
			return new HashMap<String, String>();
		}
	}; 
	
	private static final ThreadLocal<Stack<String>> cirIdContext = new ThreadLocal<Stack<String>>(){
		@Override
		protected Stack<String> initialValue() {
			Stack<String> stack = new Stack<String>();
			return stack;
		}
	}; 
	
	private static final ThreadLocal<Stack<String>> parentCirIdContext = new ThreadLocal<Stack<String>>(){
		@Override
		protected Stack<String> initialValue() {
			Stack<String> stack = new Stack<String>();
			return stack;
		}
	}; 
	
	
	public static void putTraceId(String traceId){
		localContext.get().put(Key_TraceId, traceId);
	}
	
	public static void clearTraceId(){
		localContext.get().remove(Key_TraceId);
	}
	
	public static void putCirId(String cirId){
		cirIdContext.get().push(cirId);
	}
	
	public static void clearCirId(){
		Stack<String>  cirIdStack = cirIdContext.get();
		if(cirIdStack!=null&&!cirIdStack.empty()){
			cirIdStack.pop();
		}
	}
	
	public static void putParentCirId(String parentCirId){
		parentCirIdContext.get().push(parentCirId);
	}
	
	public static void clearParentCirId(){
		Stack<String> parentStack = parentCirIdContext.get();
		if(parentStack!=null&&!parentStack.empty()){
			parentStack.pop();
		}
	}
	
	
	public static String getTraceId(){
		String traceId = RpcContext.getContext().getAttachment(Key_TraceId);
		if( traceId == null || "".equals(traceId)){
			traceId = localContext.get().get(Key_TraceId);
		}
		return traceId;
	}
	
	public static String getCirId(){
		String cirId = RpcContext.getContext().getAttachment(Key_CirId);
		if( cirId == null || "".equals(cirId)){
			try {
				cirId = cirIdContext.get().peek();
			} catch (Exception e) {
				//ignored
			}
		}
		return cirId;
	}
	
	
	public static String getParentCirId(){
		String parentCirId = null;
		try {
			parentCirId = parentCirIdContext.get().peek();
		} catch (Exception e) {
			//ignored
		}
		return parentCirId;
	}
}
