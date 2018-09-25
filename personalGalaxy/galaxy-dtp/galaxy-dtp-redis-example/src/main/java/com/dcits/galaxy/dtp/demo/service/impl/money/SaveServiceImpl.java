package com.dcits.galaxy.dtp.demo.service.impl.money;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.rpc.RpcContext;
import com.dcits.galaxy.dtp.branch.BranchManagerHelper;
import com.dcits.galaxy.dtp.branch.BranchTransaction;
import com.dcits.galaxy.dtp.defaults.log.DefaultSubmitLog;
import com.dcits.galaxy.dtp.defaults.log.LogManagerHelper;
import com.dcits.galaxy.dtp.defaults.log.ServiceInfo;
import com.dcits.galaxy.dtp.defaults.log.ServiceType;
import com.dcits.galaxy.dtp.demo.dao.MoneyDao;
import com.dcits.galaxy.dtp.demo.entities.Money;
import com.dcits.galaxy.dtp.demo.service.money.SaveService;
import com.dcits.galaxy.dtp.demo.util.SequenceHelper;
import com.dcits.galaxy.dtp.log.SubmitLog;

public class SaveServiceImpl implements SaveService {
	
	private static final long serialVersionUID = -6961954047825335112L;
	
	private static Logger logger = LoggerFactory.getLogger(SaveServiceImpl.class);
	
	@Resource
	private MoneyDao moneyDao;

	@Override
	public boolean saveMoney(String userId,int amount) {
		//开启分支事务
		RpcContext rpcContext = RpcContext.getContext();
		String txid = rpcContext.getAttachment( "txid" );
		String bxid = SequenceHelper.newBxid();
		int indexInTrunk = SequenceHelper.nextIndexInTrunk(txid);
		BranchTransaction branch = BranchManagerHelper.begin(bxid,indexInTrunk, txid);
		if( branch == null ){
			throw new RuntimeException("账户："+ userId + " 存入：" + amount + " 失败");
		}
		
		//业务计算 
		
		// 执行提交逻辑 
		// doSubmit(userId, amount);
		
		// 不再直接调用 doSubmit方法，而是是将调用信息写成一条事务提交日志
		ServiceInfo serviceInfo = new ServiceInfo();
		serviceInfo.setMethodName( "doSubmit" );
		Class<?>[] argsType = new Class<?>[]{ String.class, int.class };
		serviceInfo.setArgsType( argsType );
		Object[] argsValue = new Object[]{ userId, amount};
		serviceInfo.setArgs( argsValue );
		serviceInfo.setServiceClass( SaveServiceImpl.class.getName() );
		serviceInfo.setServiceType( ServiceType.SPRING_BEAN );
		
		SubmitLog submitLog = new DefaultSubmitLog();
		submitLog.setLogId( SequenceHelper.newLogId() );
		submitLog.setTxid(txid);
		submitLog.setBxid(bxid);
		int logIndex = SequenceHelper.nextLogIndex(bxid);
		submitLog.setLogIndex(logIndex);
		submitLog.setContent(serviceInfo);
		boolean writeResult = LogManagerHelper.writeLog(submitLog);
		
		if(!writeResult) {
			throw new RuntimeException("账户："+ userId + " 存入：" + amount + " 失败");
		}
		return writeResult;
	}
	
	@Override
	@Transactional
	public void doSubmit(String userId,int amount) {
		try {
			//通过上下文获取日志标识
			//String logId = ServiceInfoContext.getLogId();
			
			//业务提交逻辑
			Money money = new Money();
			money.setUserId(userId);
			money.setAmount(amount);
			moneyDao.updateMoney(money);
			
		} catch (Exception e) {
			logger.error("SaveService doSubmit faild!",e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean check(String userId, int amount) {
		boolean result = true;
		
		if(!result){
			throw new RuntimeException("账户："+ userId + " 存入条件不满足");
		}
		
		return true;
	}

}
