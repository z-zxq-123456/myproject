package com.dcits.galaxy.dtp.redis.log.service;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;

import com.dcits.galaxy.dtp.exception.DTPException;
import com.dcits.galaxy.dtp.log.LogStatus;
import com.dcits.galaxy.dtp.log.PrepareLog;
import com.dcits.galaxy.dtp.log.service.PrepareLogService;
import com.dcits.galaxy.dtp.log.service.PrepareLogServiceFactory;
import com.dcits.galaxy.dtp.redis.dao.PrepareLogDao;

/**
 * 准备日志服务的基于redis的实现
 * @author fan.kaijie
 *
 */
public class PrepareLogServiceImplRedis implements PrepareLogService,InitializingBean {
	
	private static final long serialVersionUID = 6109020908846022519L;
	
	public static final String ServiceName = "PrepareLogService_Redis";
	
	@Override
	public String getServiceName() {
		return ServiceName;
	}
	
	private PrepareLogDao logDao = PrepareLogDao.getInstance();
	@Override
	public boolean saveLog(PrepareLog preparedLog) {
		boolean result = false;
		
		try {
			logDao.save( preparedLog );
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
	public List<PrepareLog> getUndoLogsByTxid(String txid) {
		List<PrepareLog> logs = null;
		
		try {
			logs = logDao.getLogsByTxid(txid, LogStatus.undo);
		} catch (Exception e) {
			throw new DTPException(e.getMessage(),e.getCause());
		}
		
		return logs;
	}


	@Override
	public PrepareLog getPrepareLog(String logId) {
		PrepareLog result = null;
		
		try {
			result = logDao.getPrepareLog(logId);
		} catch (Exception e) {
			throw new DTPException(e.getMessage(),e.getCause());
		}
		
		return result;
	}

	@Override
	public List<PrepareLog> getUndoLogsByAppName(String appName) {
		return null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		PrepareLogServiceFactory.registerBean(this);
	}

}
