package com.dcits.galaxy.dtp.redis.trunk;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.dcits.galaxy.dtp.exception.DTPException;
import com.dcits.galaxy.dtp.redis.dao.BranchDao;
import com.dcits.galaxy.dtp.redis.dao.PrepareLogDao;
import com.dcits.galaxy.dtp.redis.dao.SubmitLogDao;
import com.dcits.galaxy.dtp.redis.dao.TrunkDao;
import com.dcits.galaxy.dtp.trunk.TrunkService;
import com.dcits.galaxy.dtp.trunk.TrunkServiceFactory;
import com.dcits.galaxy.dtp.trunk.TrunkStatus;
import com.dcits.galaxy.dtp.trunk.TrunkTransaction;

/**
 * 基于redis的主事务信息存储服务实现
 * @author fan.kaijie
 * 
 */
public class TrunkServiceImplRedis implements TrunkService,InitializingBean {

	private static final long serialVersionUID = 9120796004768760265L;
	private static Logger logger = LoggerFactory.getLogger(TrunkServiceImplRedis.class);
	
	private TrunkDao trunkDao = null;
	
	public TrunkServiceImplRedis(long partition) {
		trunkDao = TrunkDao.getInstance();
		trunkDao.setPartition(partition);
	}
	
	public TrunkServiceImplRedis() {
		trunkDao = TrunkDao.getInstance();
	}

	@Override
	public boolean saveTransaction(TrunkTransaction trunk) {
		boolean result = false;
		
		try {
			trunkDao.save(trunk);
			result = true;
		} catch (Exception e) {
			throw new DTPException(e.getMessage(),e.getCause());
		}
		
		return result;
	}

	@Override
	public boolean saveTransaction(String txid) {
		boolean result = false;
		
		try {
			TrunkTransaction trunk = new TrunkTransaction();
			trunk.setTxid(txid);
			trunkDao.save( trunk );
			result = true;
		} catch (Exception e) {
			throw new DTPException(e.getMessage(),e.getCause());
		}
		return result;
	}

	
	public boolean updateStatus(String txid, TrunkStatus status) {
		boolean result = false;
		
		try {
			trunkDao.updateStatus(txid, status);
			result = true;
		} catch (Exception e) {
			throw new DTPException(e.getMessage(),e.getCause());
		}
		return result;
	}

	@Override
	public TrunkTransaction getTransaction(String txid) {
		TrunkTransaction result = null;
		
		try {
			result = trunkDao.getTrunk(txid);
		} catch (Exception e) {
			throw new DTPException(e.getMessage(),e.getCause());
		}
		return result;
	}
	
	@Override
	public List<TrunkTransaction> getUnCompletedTrunks(String appGroup, long timeout) {
		List<TrunkTransaction> trunks = null;
		try {
			trunks = trunkDao.getUnCompletedTrunks(appGroup, timeout);
		} catch (Exception e) {
			throw new DTPException(e.getMessage(),e.getCause());
		}
		return trunks;
	}

	@Override
	public boolean hasUnCompletedBranches(String txid) {
		boolean result = false;
		try {
			result = trunkDao.hasUnCompletedBranches(txid);
		}catch( Exception e){
			throw new DTPException(e.getMessage(),e.getCause());
		}
		return result;
	}

	@Override
	public boolean setConfirm(String txid) {
		boolean result = false;
		try {
			result = trunkDao.setConfirm(txid);
		}catch( Exception e){
			throw new DTPException(e.getMessage(),e.getCause());
		}
		return result;
	}

	@Override
	public boolean setConfirmCompleted(String txid) {
		boolean result = false;
		try {
			result = trunkDao.setConfirmCompleted(txid);
		}catch( Exception e){
			throw new DTPException(e.getMessage(),e.getCause());
		}
		return result;
	}

	@Override
	public boolean setCancel(String txid) {
		boolean result = false;
		try {
			result = trunkDao.setCancel(txid);
		}catch( Exception e){
			throw new DTPException(e.getMessage(),e.getCause());
		}
		return result;
	}

	@Override
	public boolean setCancelCompleted(String txid) {
		boolean result = false;
		try {
			result = trunkDao.setCancelCompleted(txid);
		}catch( Exception e){
			throw new DTPException(e.getMessage(),e.getCause());
		}
		return result;
	}

	@Override
	public void clean(String appGroup, long time) {
		
		try {
			Set<TrunkTransaction> trunks = trunkDao.getComplete(appGroup, time);
			for(TrunkTransaction trunk : trunks){
				this.cleanTrunk(trunk);
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	private boolean cleanTrunk(TrunkTransaction trunk){
		boolean result = true;
		
		BranchDao branchDao = BranchDao.getInstance();
		SubmitLogDao submitLogDao = SubmitLogDao.getInstance();
		PrepareLogDao prepareLogDao = PrepareLogDao.getInstance();
		try {
			Set<String> bxidSet = branchDao.getBxids(trunk.getTxid());
			for(String bxid : bxidSet){
				submitLogDao.cleanByBxid(bxid);
				branchDao.delete(bxid);
			}
			prepareLogDao.cleanByTxid(trunk.getTxid());
			branchDao.deleteIndexTxid(trunk.getTxid());
			trunkDao.delete(trunk);
		} catch (Exception e) {
			logger.info("Delete Trunk:" + trunk.getTxid()+" occur error. " + e.getMessage());
		}
		
		return result;
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		TrunkServiceFactory.registerBean(this);
	}
	
	public void setPartition(long partition){
		trunkDao.setPartition(partition);
	}

}
