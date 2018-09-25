package com.dcits.galaxy.dtp.defaults.log.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.dcits.galaxy.dtp.defaults.log.DefaultPrepareLog;
import com.dcits.galaxy.dtp.log.PrepareLog;
import com.dcits.galaxy.dtp.log.executor.PrepareLogExecutor;
import com.dcits.galaxy.dtp.log.executor.PrepareLogExecutorFactory;
import com.dcits.galaxy.dtp.resource.DtpResource;

public final class DefaultPrepareLogExecutor implements PrepareLogExecutor, InitializingBean {

	private static final Logger logger = LoggerFactory.getLogger(DefaultPrepareLogExecutor.class);

	@Override
	public String getLogTypeName() {
		return DefaultPrepareLog.LogTypeName;
	}

	@Override
	public boolean execute(PrepareLog prepareLog) {
		boolean result = false;
		DefaultPrepareLog prepareLogImpl = (DefaultPrepareLog) prepareLog;
		DtpResource r = (DtpResource) prepareLogImpl.getContent();
		result = DtpResource.unLock(r);
		if (result) {
			if (logger.isInfoEnabled())
				logger.info("PrepareLog 执行成功: resource = {}", r);
		}
		return result;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		PrepareLogExecutorFactory.registerBean(this);
	}

}
