package com.dcits.galaxy.dtp.db.log.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.galaxy.dtp.db.dao.SubmitLogDao;
import com.dcits.galaxy.dtp.exception.DTPException;
import com.dcits.galaxy.dtp.log.LogStatus;
import com.dcits.galaxy.dtp.log.SubmitLog;
import com.dcits.galaxy.dtp.log.service.SubmitLogService;
import com.dcits.galaxy.dtp.log.service.SubmitLogServiceFactory;

public class SubmitLogServiceImplDB implements SubmitLogService, ApplicationContextAware {

	private static final long serialVersionUID = 6192752406148677827L;

	public static final String ServiceName = "SubmitLogService_DB";

	@Resource
	private SubmitLogDao submitLogDao;

	@Override
	public String getServiceName() {
		return ServiceName;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean saveLog(SubmitLog submitLog) {
		boolean result = false;
		try {
			submitLogDao.save(submitLog);
			result = true;
		} catch (Exception e) {
			throw new DTPException(e.getMessage(), e.getCause());
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean updateLogStatus(String logId, LogStatus logStatus) {
		boolean result = false;
		try {
			submitLogDao.updateStatus(logId, logStatus);
			result = true;
		} catch (Exception e) {
			throw new DTPException(e.getMessage(), e.getCause());
		}
		return result;
	}

	@Override
	public List<SubmitLog> getUndoLogsByBxid(String bxid) {
		List<SubmitLog> logs = new ArrayList<SubmitLog>();
		try {
			logs = submitLogDao.getLogsByBxid(bxid, LogStatus.undo);
		} catch (Exception e) {
			throw new DTPException(e.getMessage(), e.getCause());
		}
		return logs;
	}

	@Override
	public SubmitLog getSubmitLog(String logId) {
		SubmitLog result = null;
		try {
			result = submitLogDao.getSubmitInfo(logId);
		} catch (Exception e) {
			throw new DTPException(e.getMessage(), e.getCause());
		}
		return result;
	}

	@Override
	public List<SubmitLog> getUndoLogsByAppName(String appName) {
		return null;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		
		SubmitLogService service = applicationContext.getBean(this.getClass());
		SubmitLogServiceFactory.registerBean(service);
	}
}
