package com.dcits.galaxy.dtp.db.trunk;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.galaxy.dtp.db.dao.TrunkDao;
import com.dcits.galaxy.dtp.exception.DTPException;
import com.dcits.galaxy.dtp.trunk.TrunkService;
import com.dcits.galaxy.dtp.trunk.TrunkServiceFactory;
import com.dcits.galaxy.dtp.trunk.TrunkStatus;
import com.dcits.galaxy.dtp.trunk.TrunkTransaction;

/**
 * 基于数据库的主事务信息存储服务实现
 * @author Yin.Weicai
 */
public class TrunkServiceImplDB implements TrunkService, ApplicationContextAware {

	private static final long serialVersionUID = 9120796004768760265L;
	
	private static Logger logger = LoggerFactory.getLogger(TrunkServiceImplDB.class);

	@Resource
	private TrunkDao trunkDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean saveTransaction(TrunkTransaction trunk) {
		boolean result = false;
		try {
			trunkDao.save(trunk);
			result = true;
		} catch (Exception e) {
			throw new DTPException(e.getMessage(), e.getCause());
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean saveTransaction(String txid) {
		boolean result = false;
		try {
			TrunkTransaction trunk = new TrunkTransaction();
			trunk.setTxid(txid);
			trunkDao.save(trunk);
			result = true;
		} catch (Exception e) {
			throw new DTPException(e.getMessage(), e.getCause());
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean updateStatus(String txid, TrunkStatus status) {
		boolean result = false;
		try {
			trunkDao.updateStatus(txid, status);
			result = true;
		} catch (Exception e) {
			throw new DTPException(e.getMessage(), e.getCause());
		}
		return result;
	}

	@Override
	public TrunkTransaction getTransaction(String txid) {
		TrunkTransaction result = null;
		try {
			result = trunkDao.getTrunk(txid);
		} catch (Exception e) {
			throw new DTPException(e.getMessage(), e.getCause());
		}
		return result;
	}

	@Override
	public List<TrunkTransaction> getUnCompletedTrunks(String appGroup, long timeout) {
		List<TrunkTransaction> trunks = null;
		try {
			trunks = trunkDao.getUnCompletedTrunks(appGroup, timeout);
		} catch (Exception e) {
			throw new DTPException(e.getMessage(), e.getCause());
		}
		return trunks;
	}

	@Override
	public boolean hasUnCompletedBranches(String txid) {
		boolean result = false;
		try {
			result = trunkDao.hasUnCompletedBranches(txid);
		} catch (Exception e) {
			throw new DTPException(e.getMessage(), e.getCause());
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean setConfirm(String txid) {
		boolean result = false;
		try {
			result = trunkDao.setConfirm(txid);
		} catch (Exception e) {
			throw new DTPException(e.getMessage(), e.getCause());
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean setConfirmCompleted(String txid) {
		boolean result = false;
		try {
			result = trunkDao.setConfirmCompleted(txid);
		} catch (Exception e) {
			throw new DTPException(e.getMessage(), e.getCause());
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean setCancel(String txid) {
		boolean result = false;
		try {
			result = trunkDao.setCancel(txid);
		} catch (Exception e) {
			throw new DTPException(e.getMessage(), e.getCause());
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean setCancelCompleted(String txid) {
		boolean result = false;
		try {
			result = trunkDao.setCancelCompleted(txid);
		} catch (Exception e) {
			throw new DTPException(e.getMessage(), e.getCause());
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void clean(String appGroup, long time) {
		try {
			trunkDao.clean(appGroup, time);
		} catch (Exception e) {
			logger.warn("failed clean trunk. ", e);
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		TrunkService service = applicationContext.getBean(this.getClass());
		TrunkServiceFactory.registerBean(service);
	}
}
