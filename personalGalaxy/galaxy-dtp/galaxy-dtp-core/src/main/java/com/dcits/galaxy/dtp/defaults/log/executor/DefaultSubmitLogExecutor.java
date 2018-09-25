package com.dcits.galaxy.dtp.defaults.log.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.dcits.galaxy.dtp.defaults.log.DefaultSubmitLog;
import com.dcits.galaxy.dtp.defaults.log.ServiceInfo;
import com.dcits.galaxy.dtp.defaults.log.ServiceInfoInvoker;
import com.dcits.galaxy.dtp.log.SubmitLog;
import com.dcits.galaxy.dtp.log.executor.SubmitLogExecutor;
import com.dcits.galaxy.dtp.log.executor.SubmitLogExecutorFactory;

public class DefaultSubmitLogExecutor implements SubmitLogExecutor,InitializingBean {
	
	private  static Logger logger = LoggerFactory.getLogger(DefaultSubmitLogExecutor.class);
	
	@Override
	public String getLogTypeName() {
		return DefaultSubmitLog.LogTypeName;
	}
	
	@Override
	public boolean execute(SubmitLog submitLog) {
		boolean result = false;
		
		DefaultSubmitLog defaultSubmitLog = (DefaultSubmitLog)submitLog;
		
		String logId = defaultSubmitLog.getLogId();

		ServiceInfo serviceInfo = defaultSubmitLog.getServiceInfo();
		result = ServiceInfoInvoker.invokeService(serviceInfo);
		
		if(logger.isInfoEnabled()) {
			String bxid = defaultSubmitLog.getBxid();
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("SubmitLog 执行成功 : bxid=").append(bxid).append(",logid=").append(logId);
			sb.append(",sevice=").append(serviceInfo.getServiceClass()).append('.').append(serviceInfo.getMethodName());
			
			logger.info(sb.toString());
		}
		
		return result;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		SubmitLogExecutorFactory.registerBean( this);
	}

}
