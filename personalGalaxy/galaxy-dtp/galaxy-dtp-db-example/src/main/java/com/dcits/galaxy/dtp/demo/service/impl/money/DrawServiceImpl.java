package com.dcits.galaxy.dtp.demo.service.impl.money;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.dtp.DtpContext;
import com.dcits.galaxy.dtp.annotation.SubmitLog;
import com.dcits.galaxy.dtp.branch.BranchManagerHelper;
import com.dcits.galaxy.dtp.branch.BranchTransaction;
import com.dcits.galaxy.dtp.demo.dao.MoneyDao;
import com.dcits.galaxy.dtp.demo.entities.Money;
import com.dcits.galaxy.dtp.demo.service.money.DrawService;
import com.dcits.galaxy.dtp.demo.util.SequenceHelper;

public class DrawServiceImpl implements DrawService {

	private static final long serialVersionUID = -430136764464908161L;

	private static Logger logger = LoggerFactory
			.getLogger(DrawServiceImpl.class);

	@Resource
	private MoneyDao moneyDao;
	
	@Override
	public boolean drawMoney(String userId, int amount) {

		// 开启分支事务
		String txid = DtpContext.getTxid();
		String bxid = DtpContext.getBxid();
		boolean inBranch = bxid != null;
		if (!inBranch) {
			// 开启分支事务
			bxid = SequenceHelper.newBxid();
			int indexInTrunk = SequenceHelper.nextIndexInTrunk(txid);
			BranchTransaction branch = BranchManagerHelper.begin(bxid, indexInTrunk, txid);
			if (branch == null) {
				throw new RuntimeException("账户：" + userId + " 存入：" + amount + " 失败");
			}
		}
		
		// 业务计算

		// 执行提交逻辑
		try {
			doSubmit(userId, amount);
		} catch (Exception e) {
			throw new RuntimeException("账户：" + userId + " 支取：" + amount + " 失败", e);
		} finally {
			if(!inBranch){
				DtpContext.setBxid(null);
			}
		}
		
		// 不再直接调用 doSubmit方法，而是是将调用信息写成一条事务提交日志
//		ServiceInfo serviceInfo = new ServiceInfo();
//		serviceInfo.setMethodName("doSubmit");
//		Class<?>[] argsType = new Class<?>[] { String.class, int.class };
//		serviceInfo.setArgsType(argsType);
//		Object[] argsValue = new Object[] { userId, amount };
//		serviceInfo.setArgs(argsValue);
//		serviceInfo.setServiceClass(DrawServiceImpl.class.getName());
//		serviceInfo.setServiceType(ServiceType.SPRING_BEAN);
//
//		SubmitLog submitLog = new DefaultSubmitLog();
//		submitLog.setLogId(SequenceHelper.newLogId());
//		submitLog.setTxid(txid);
//		submitLog.setBxid(bxid);
//		int logIndex = SequenceHelper.nextLogIndex(bxid);
//		submitLog.setLogIndex(logIndex);
//		submitLog.setContent(serviceInfo);
//		boolean writeResult = LogManagerHelper.writeLog(submitLog);
		
//		if (!writeResult) {
//			throw new RuntimeException("账户：" + userId + " 支取：" + amount + " 失败");
//		}
		return true;
	}

	/**
	 * 提交逻辑
	 * 
	 * @param userId
	 * @param amount
	 */

	@SubmitLog
	public void doSubmit(String userId, int amount) {
		try {
			// 通过上下文获取日志标识
			// String logId = ServiceInfoContext.getLogId();

			// 业务提交逻辑
			Money money = new Money();
			money.setUserId(userId);
			money.setAmount(amount);
			moneyDao.updateMoney(money);

		} catch (Exception e) {
			logger.error("DrawService doSubmit faild!", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean check(String userId, int amount) {
		boolean result = false;
		try {
			Money money = moneyDao.getMoney(userId);
			if (money.getAmount() >= amount) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (!result) {
			throw new RuntimeException("账户：" + userId + " 余额不足");
		}
		return result;
	}

}
