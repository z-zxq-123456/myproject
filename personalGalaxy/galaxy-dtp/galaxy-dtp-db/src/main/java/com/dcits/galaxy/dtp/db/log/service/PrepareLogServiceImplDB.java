package com.dcits.galaxy.dtp.db.log.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.galaxy.dtp.db.dao.PrepareLogDao;
import com.dcits.galaxy.dtp.exception.DTPException;
import com.dcits.galaxy.dtp.log.LogStatus;
import com.dcits.galaxy.dtp.log.PrepareLog;
import com.dcits.galaxy.dtp.log.service.PrepareLogService;
import com.dcits.galaxy.dtp.log.service.PrepareLogServiceFactory;

/**
 * 准备日志服务的基于数据库的实现
 * 
 * @author Yin.Weicai
 *
 */
public class PrepareLogServiceImplDB implements PrepareLogService, ApplicationContextAware {

	private static final long serialVersionUID = -3170825457514423228L;

	public static final String ServiceName = "PrepareLogService_DB";

	@Override
	public String getServiceName() {
		return ServiceName;
	}

	@Resource
	private PrepareLogDao prepareLogDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean saveLog(PrepareLog preparedLog) {
		boolean result = false;
		try {
			prepareLogDao.save(preparedLog);
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
			prepareLogDao.updateStatus(logId, logStatus);
			result = true;
		} catch (Exception e) {
			throw new DTPException(e.getMessage(), e.getCause());
		}
		return result;
	}

	@Override
	public List<PrepareLog> getUndoLogsByTxid(String txid) {
		List<PrepareLog> logs = null;
		try {
			logs = prepareLogDao.getLogsByTxid(txid, LogStatus.undo);
		} catch (Exception e) {
			throw new DTPException(e.getMessage(), e.getCause());
		}
		return logs;
	}

	@Override
	public PrepareLog getPrepareLog(String logId) {
		PrepareLog result = null;
		try {
			result = prepareLogDao.getPrepareLog(logId);
		} catch (Exception e) {
			throw new DTPException(e.getMessage(), e.getCause());
		}
		return result;
	}

	@Override
	public List<PrepareLog> getUndoLogsByAppName(String appName) {
		return null;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		PrepareLogService service = applicationContext.getBean(this.getClass());
		PrepareLogServiceFactory.registerBean(service);
		
	}

}
