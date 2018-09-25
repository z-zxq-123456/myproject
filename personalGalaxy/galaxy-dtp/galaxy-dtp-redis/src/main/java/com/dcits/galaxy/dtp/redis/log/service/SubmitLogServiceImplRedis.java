package com.dcits.galaxy.dtp.redis.log.service;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;

import com.dcits.galaxy.dtp.exception.DTPException;
import com.dcits.galaxy.dtp.log.LogStatus;
import com.dcits.galaxy.dtp.log.SubmitLog;
import com.dcits.galaxy.dtp.log.service.SubmitLogService;
import com.dcits.galaxy.dtp.log.service.SubmitLogServiceFactory;
import com.dcits.galaxy.dtp.redis.dao.SubmitLogDao;

/**
 * 提交日志服务的基于redis的实现
 * @author fan.kaijie
 *
 */
public class SubmitLogServiceImplRedis implements SubmitLogService,InitializingBean {

	private static final long serialVersionUID = 4619099605405199603L;

	public static final String ServiceName = "SubmitLogService_Redis";
	
	private SubmitLogDao logDao = SubmitLogDao.getInstance();
	
	@Override
	public String getServiceName() {
		return ServiceName;
	}

	@Override
	public boolean saveLog(SubmitLog submitLog) {
		boolean result = false;
		
		try {
			logDao.save( submitLog );
			result = true;
		} catch (Exception e) {
			throw new DTPException(e.getMessage(),e.getCause());
		}
		return result;
	}

	@Override
	public boolean updateLogStatus(String logId, LogStatus logStatus) {
		boolean result = false;
		
		try {
			logDao.updateStatus(logId, logStatus);
			result = true;
		} catch (Exception e) {
			throw new DTPException(e.getMessage(),e.getCause());
		}
		
		return result;
	}

	@Override
	public List<SubmitLog> getUndoLogsByBxid(String bxid) {
		List<SubmitLog> logs = null;
		
		try {
			logs = logDao.getLogsByBxid(bxid, LogStatus.undo);
		} catch (Exception e) {
			throw new DTPException(e.getMessage(),e.getCause());
		}
		
		return logs;
	}


	@Override
	public SubmitLog getSubmitLog(String logId) {
		SubmitLog result = null;
		
		try {
			result = logDao.getSubmitInfo(logId);
		} catch (Exception e) {
			throw new DTPException(e.getMessage(),e.getCause());
		}
		
		return result;
	}

	@Override
	public List<SubmitLog> getUndoLogsByAppName(String appName) {
		return null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		SubmitLogServiceFactory.registerBean(this);
	}


}
