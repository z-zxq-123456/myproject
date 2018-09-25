package com.dcits.galaxy.dtp.resource;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.galaxy.dtp.DtpContext;
import com.dcits.galaxy.dtp.defaults.log.DefaultPrepareLog;
import com.dcits.galaxy.dtp.defaults.log.LogManagerHelper;
import com.dcits.galaxy.dtp.exception.DTPException;
import com.dcits.galaxy.dtp.log.PrepareLog;
import com.dcits.galaxy.dtp.sequence.LogIdSequence;

public class DtpResourceService implements ApplicationContextAware {

	@Resource
	private DtpResourceDao dao;

	@Resource
	private LogIdSequence sequence;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean lockResource(DtpResource r) {
		boolean result = false;
		String txid = r.getTxid();
		try {
			DtpResource tableResource = dao.getResource(r);
			if (tableResource != null) {
				if (txid != null && txid.equals(tableResource.getTxid())) {
					result = true;
				}
			} else {
				boolean resultOfLog = false;
				if (DtpContext.isInDtp()) {
					if (!txid.equals(DtpContext.getTxid())) {
						throw new DTPException("fail unlock resource : txid disaccord");
					}

					String logid = sequence.nextLogid();
					PrepareLog prepareLog = new DefaultPrepareLog();
					prepareLog.setLogId(logid);
					prepareLog.setTxid(txid);
					prepareLog.setBxid("-");
					prepareLog.setContent(r);
					resultOfLog = LogManagerHelper.writeLog(prepareLog);
				}

				boolean updateResult = dao.lockResource(r);

				if (updateResult && resultOfLog) {
					result = true;
				}
			}
		} catch (Exception e) {
			throw new DTPException("fail lock resource:" + r, e);
		}

		if (!result)
			throw new DTPException("fail lock resource:" + r);

		return result;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean unLockResource(DtpResource r) {
		boolean result = false;
		try {
			DtpResource tableResource = dao.getResource(r);
			if (tableResource == null) {
				result = true;
			} else {
				boolean updateResult = dao.unLockResource(r);
				if (updateResult) {
					result = true;
				}
			}
		} catch (SQLException e) {
			throw new DTPException("fail unlock resource:" + r, e);
		}

		if (!result)
			throw new DTPException("fail unlock resource:" + r);

		return result;
	}

	public DtpResource getResource(DtpResource r) {
		DtpResource resource = null;
		try {
			resource = dao.getResource(r);
		} catch (SQLException e) {
			throw new DTPException("fail get resource:" + r);
		}
		return resource;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		DtpResourceService service = applicationContext.getBean(this.getClass());
		DtpResource.setDtpResourceService(service);
	}
	
}
