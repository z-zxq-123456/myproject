package com.dcits.galaxy.dtp.demo.service.transfer;

import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dcits.galaxy.dtp.DtpContext;
import com.dcits.galaxy.dtp.demo.service.money.DrawService;
import com.dcits.galaxy.dtp.demo.service.money.SaveService;
import com.dcits.galaxy.dtp.demo.service.user.UserService;
import com.dcits.galaxy.dtp.demo.util.SequenceHelper;
import com.dcits.galaxy.dtp.trunk.TrunkManager;
import com.dcits.galaxy.dtp.trunk.TrunkManagerFactory;
import com.dcits.galaxy.dtp.trunk.TrunkServiceFactory;
import com.dcits.galaxy.dtp.trunk.TrunkTransaction;

/**
 * 转账
 * @author Yin.Weicai
 */

@Service
public class TransferService {
	
	private static Logger logger = LoggerFactory.getLogger(TransferService.class);
	
	@Resource
	private UserService iUserService;
	
	@Resource
	private SaveService iSaveService;
	
	@Resource
	private DrawService iDrawService;

	public void transferSuccess(String from,String to, int amount) throws IOException {
		TrunkManager trunkTM = TrunkManagerFactory.getBean();
		String txid = doTransfer(from,to,-amount);
		trunkTM.confirm(txid);
	}
	
	public void transferCancle(String from,String to, int amount) throws IOException {
		TrunkManager trunkTM = TrunkManagerFactory.getBean();
		
		String txid = doTransfer(from,to,-amount);
		trunkTM.cancel(txid);
	}
	
	public void transferTimerConfirm(String from,String to, int amount) throws IOException {
		String txid = doTransfer(from,to,-amount);
		TrunkServiceFactory.getBean().setConfirm(txid);
		DtpContext.clean();
	}
	
	public void transferTimerCancle(String from,String to, int amount) throws IOException {
		String txid = doTransfer(from,to,-amount);
		TrunkServiceFactory.getBean().setCancel(txid);
	}
	
	private String doTransfer(String from,String to, int amount) throws IOException {
		/*
		 *完成账户间的跨服务转帐
		 *  从 账户 A(余额:500) 转  200  到  账户 B 
		 */
		String txid = SequenceHelper.newTxid();
		//开启主事务
		TrunkManager trunkTM = TrunkManagerFactory.getBean();
		TrunkTransaction trunk = trunkTM.begin(txid);
		if( trunk == null){
			return null;
		}
		
		try{
			iUserService.lockUser(from, txid);
			iUserService.lockUser(to, txid);
			logger.info("加锁成功");
			
			iDrawService.check(from, -amount);
			iSaveService.check(from, -amount);
			iSaveService.check(to, -amount);
			logger.info("检查成功");
			
			//调用取款服务 ------------------ start ------------------
			iDrawService.drawMoney(from, amount);
			logger.info("账户："+ from + " 支取：" + (-amount) );
			iSaveService.saveMoney(to, -amount);
			
			logger.info("账户："+ to + " 存入：" + (-amount) );
			
		}catch(Exception e) {
			logger.info("从账户："+ from + "转账到："+ to + "失败！" );
			trunkTM.cancel(txid);
			throw e;
		}
		
		return txid;
	}
}
