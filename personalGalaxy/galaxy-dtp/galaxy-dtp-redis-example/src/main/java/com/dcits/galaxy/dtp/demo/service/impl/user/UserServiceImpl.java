package com.dcits.galaxy.dtp.demo.service.impl.user;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.dcits.galaxy.dtp.branch.BranchManagerHelper;
import com.dcits.galaxy.dtp.branch.BranchTransaction;
import com.dcits.galaxy.dtp.defaults.log.DefaultPrepareLog;
import com.dcits.galaxy.dtp.defaults.log.LogManagerHelper;
import com.dcits.galaxy.dtp.demo.dao.UserDao;
import com.dcits.galaxy.dtp.demo.entities.LockStatus;
import com.dcits.galaxy.dtp.demo.entities.User;
import com.dcits.galaxy.dtp.demo.service.user.UserService;
import com.dcits.galaxy.dtp.demo.util.SequenceHelper;
import com.dcits.galaxy.dtp.log.PrepareLog;

public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao dao;
	
	@Override
	@Transactional
	public boolean lockUser(String userId ,String txid){
		boolean result = false;
		try {
			User user = dao.getUser(userId);
			if( LockStatus.Lock == user.getStatus()){
				if( txid != null && txid.equals( user.getTxid() ) ){
					result = true;
				}
			}else{
				//开启分支事务
				String bxid = SequenceHelper.newBxid();
				int indexInTrunk = SequenceHelper.nextIndexInTrunk(txid);
				BranchTransaction branch = BranchManagerHelper.begin(bxid,indexInTrunk, txid);
				if( branch == null ){
					throw new RuntimeException("begin branch for " + txid + " faild!Branch is null!");
				}
				
				user.setStatus( LockStatus.Lock );
				user.setTxid( txid );
				boolean updateResult = dao.updateUser(user);
				
				boolean resultOfLog = false; 
				if( updateResult ){
					PrepareLog prepareLog = new DefaultPrepareLog();
					prepareLog.setLogId( SequenceHelper.newLogId());
					prepareLog.setTxid( txid );
					prepareLog.setBxid(bxid);
					int logIndex = SequenceHelper.nextLogIndex(bxid);
					prepareLog.setLogIndex(logIndex);
					prepareLog.setContent( userId );
					resultOfLog = LogManagerHelper.writeLog(prepareLog);
				}
				if( updateResult && resultOfLog ){
					result = true;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("fail lock user:" + userId ,e);
		}
		
		if(!result)
			throw new RuntimeException("fail lock user:" + userId);
		
		return result ;
	}

	@Override
	@Transactional
	public boolean unLockUser(String userId, String txid) {
		boolean result = false;
		try {
			User user = dao.getUser(userId);
			if( LockStatus.UnLock == user.getStatus()){
				result = true;
			}else{
				user.setStatus( LockStatus.UnLock );
				user.setTxid( "--" );
				boolean updateResult = dao.updateUser(user);
				if( updateResult ){
					result = true;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("fail unlock user:" + userId ,e);
		}
		
		if(!result)
			throw new RuntimeException("fail unlock user:" + userId);
		
		return result ;
	}

}
