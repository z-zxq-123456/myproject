package com.dcits.galaxy.core.trace;

import com.dcits.galaxy.core.spring.SpringApplicationContext;

/**
 * 独立的追踪器，将需要追踪的信息跟调用链关联，适用于关键的业务信息追踪。
 * 
 * @author xuecy
 * 
 */
public class LogTracerHelper {
	
	public static void trace(String msg) {
		try {
			Object logTracer =SpringApplicationContext.getContext().getBean("logTracer");
			//Object logTracer = SpringContainer.getContext().getBean("logTracer");
			if (logTracer != null) {
				LogTracer tracer = (LogTracer) logTracer;
				tracer.trace(msg);
			}
		} catch (Exception e) {
		}
	}
}
