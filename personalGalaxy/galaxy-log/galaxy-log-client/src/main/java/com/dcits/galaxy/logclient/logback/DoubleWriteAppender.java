package com.dcits.galaxy.logclient.logback;



import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.RollingFileAppender;

/**
 * 写日志平台和写日志文件appender* 
 * @author tangxlf
 * @date 2016-10-09
 */
public class DoubleWriteAppender<E> extends RollingFileAppender<E> {
	
	
	 @Override
	 protected void subAppend(E event) {
		 PlatWriter.writePlat((ILoggingEvent)event);
		 super.subAppend(event);
	 }
	 
}
