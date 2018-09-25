package com.dcits.galaxy.logclient.help;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dcits.galaxy.logcover.model.EcmTcecinBus;



/**
 * 业务字段辅助类* 
 * @author tangxlf
 * @date 2016-10-28 
 */
public class BusColHelper {
	
	private  static final Logger log = LoggerFactory.getLogger(BusColHelper.class);
	/**
	 * 调用链开始写日志
	 * @param EcmTcecinBus chainBus 调用链业务字段对象 
	 * @param BaseRequest  request  解包后的报文对象	 
	 */
	public static void writeBusCol(Map<String,Object> bodyMsg,EcmTcecinBus chainBus){
		log.info("bodyMsg="+bodyMsg);
		if(bodyMsg.containsKey("ACCT_NO")){
			chainBus.setAcctNo((String)bodyMsg.get("ACCT_NO"));
		}
		if(bodyMsg.containsKey("CARD_NO")){
			chainBus.setCardNo((String)bodyMsg.get("CARD_NO"));
		}
	}

}
