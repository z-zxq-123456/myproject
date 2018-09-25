package com.dcits.galaxy.logclient.logback;


import org.springframework.stereotype.Controller;

import ch.qos.logback.classic.pattern.LineOfCallerConverter;
import ch.qos.logback.classic.pattern.MethodOfCallerConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import com.dcits.galaxy.logclient.help.TraceLoggerHelper;
import com.dcits.galaxy.logclient.trace.TraceWriter;
import com.dcits.galaxy.logcover.common.DataUtil;
import com.dcits.galaxy.logcover.config.ConfigConstants;
import com.dcits.galaxy.logcover.config.LogConfCache;
import com.dcits.galaxy.logcover.model.EcmTraceAnnot;


/**
 * 写日志平台
 * @author luolang
 * @date 2016-10-14
 */
@Controller
public class PlatWriter<E> {	
    
	 /**
      * 写日志平台
	  * @param E event logEvent事件
	 */
	static void  writePlat(ILoggingEvent logEvent) {
		String cirId = TraceLoggerHelper.getCirId();
		if(!DataUtil.isNull(cirId)&&LogConfCache.isCollLog()){			
			 MethodOfCallerConverter methodConverter = new  MethodOfCallerConverter();
	    	 LineOfCallerConverter lineOfCallerConverter = new LineOfCallerConverter();    	
			 EcmTraceAnnot annot = new EcmTraceAnnot();
			 annot.setCirId(TraceLoggerHelper.getCirId());		 
			 annot.setAnnotLev(logEvent.getLevel().levelStr);
			 annot.setAnnotType(getAnnotType(logEvent.getLevel().levelStr));
			 annot.setAnnotCls(logEvent.getLoggerName());
			 annot.setAnnotMtd(methodConverter.convert(logEvent));
			 annot.setAnnotRownm(lineOfCallerConverter.convert(logEvent));
			 annot.setAnnotText(logEvent.getFormattedMessage());
			 annot.setAnnotThreadNum(logEvent.getThreadName());
			 TraceWriter.writeAnnot(annot);
		}
	 }
	 /**
	 * 根据标注级别返回标注类型
	 * @param String levelStr 标注级别	 
	 * @return String 标注类型
	 */
	 private  static String getAnnotType(String levelStr) {
		 String level = levelStr.toUpperCase();
		 String annotType = null;
		 switch (level) {
		case "TRACE":
	     annotType = ConfigConstants.ANNOT_TYPE_NORMAL;
	        break;
        case "DEBUG":
       	 annotType = ConfigConstants.ANNOT_TYPE_NORMAL;
            break;
        case "INFO":
       	 annotType = ConfigConstants.ANNOT_TYPE_NORMAL;
            break;
        case "WARN":
       	 annotType = ConfigConstants.ANNOT_TYPE_NORMAL;
            break;
        case "ERROR":
       	 annotType = ConfigConstants.ANNOT_TYPE_ERROR;
            break;
	    }
		return annotType;
	 }
}
