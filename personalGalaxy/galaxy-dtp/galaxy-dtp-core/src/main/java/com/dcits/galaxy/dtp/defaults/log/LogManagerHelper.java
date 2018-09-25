package com.dcits.galaxy.dtp.defaults.log;

import com.dcits.galaxy.dtp.log.LogManager;
import com.dcits.galaxy.dtp.log.TransactionLog;

/**
 * 默认的事务日志管理器
 * @author Yin.Weicai
 *
 */
public class LogManagerHelper {
	
	private static LogManager instance = null;
	
	public static void registLogManager(LogManager logManager){
		LogManagerHelper.instance = logManager;
	}
	
	public static boolean writeLog(TransactionLog transactionLog){
		boolean result = false;
		String logTypeName = transactionLog.getLogTypeName();
		if(DefaultPrepareLog.LogTypeName.equals(logTypeName)){
			result = instance.writePrapareLog( (DefaultPrepareLog)transactionLog);
		}else if( DefaultSubmitLog.LogTypeName.equals(logTypeName)){
			result =  instance.writeSubmitLog( (DefaultSubmitLog)transactionLog);
		}
		return result;
	}
}
