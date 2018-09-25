package com.dcits.galaxy.logclient.trace;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.logclient.help.BusColHelper;
import com.dcits.galaxy.logclient.help.DateTimeHelper;
import com.dcits.galaxy.logclient.help.TraceLoggerHelper;
import com.dcits.galaxy.logcover.api.ILogCollection;
import com.dcits.galaxy.logcover.common.DataUtil;
import com.dcits.galaxy.logcover.model.EcmTcechainStart;
import com.dcits.galaxy.logcover.model.EcmTcecinBus;
import com.dcits.galaxy.logcover.model.EcmTcecinEnd;
import com.dcits.galaxy.logcover.model.EcmTcecirCr;
import com.dcits.galaxy.logcover.model.EcmTcecirCs;
import com.dcits.galaxy.logcover.model.EcmTcecirSr;
import com.dcits.galaxy.logcover.model.EcmTcecirSs;
import com.dcits.galaxy.logcover.model.EcmTraceAnnot;



/**
 * 用于架构层写调用链和调用环信息* 
 * @author tangxlf
 * @date 2016-09-23 
 */
public class TraceWriter {
	private static final Logger log = LoggerFactory.getLogger(TraceWriter.class);	
	
	private static final ILogCollection logCollService;
	
	static{
		logCollService = (ILogCollection)SpringApplicationContext.getContext().getBean("logCollService");
	}
	/**
	 * 调用链开始写日志
	 * @param EcmTcechainStart  调用链开始对象	 
	 */
	public static void writeStartTrace(EcmTcechainStart chainStart){
			chainStart.setTraceSttime(DateTimeHelper.getCurrentTime());
			log.info(chainStart.toString());
			logCollService.writeStartTrace(chainStart);		
	}
	/**
	 * 调用链结束写日志
	 * 调用状态[traceStatus]{0015001 成功,0015002 失败}
	 * @param EcmTcecinEnd   调用链结束对象	 
	 */
	public static void writeEndTrace(EcmTcecinEnd traceChain){	
			traceChain.setTraceEntime(DateTimeHelper.getCurrentTime());
			log.info(traceChain.toString());
			logCollService.writeEndTrace(traceChain);	
	}
	/**
	 * 调用链BusinessHandler解包后调用此方法,写业务字段[一个调用链只写一次,在第一个调用环时写]		 
	 * @param Map msg  输入报文	?
	 */
	public static void writeBusColTrace(Map<String,Map<String,Object>> msg){	
			String traceId = TraceLoggerHelper.getTraceId();
			if(DataUtil.isNull(traceId)){
				return;
			}
			EcmTcecinBus chainBus = new EcmTcecinBus();
			Map<String,Object> sysHead = (Map<String,Object>) msg.get("SYS_HEAD");           			
			chainBus.setTraceId(traceId);
			chainBus.setTellerId((String) sysHead.get("USER_ID"));
			chainBus.setMessageCode((String) sysHead.get("MESSAGE_CODE"));
			chainBus.setMessageType((String) sysHead.get("MESSAGE_TYPE"));
			chainBus.setServiceCode((String) sysHead.get("SERVICE_CODE"));
			chainBus.setBranchId((String) sysHead.get("BRANCH_ID"));
			BusColHelper.writeBusCol((Map<String,Object>) msg.get("BODY"), chainBus);
			chainBus.setOpetTime(DateTimeHelper.getCurrentTime());
			log.info(chainBus.toString());
			logCollService.writeBusColTrace(chainBus);		
	}
	/**
	 * 调用环开始客户端cs-Client Send
	 * 调用方式[cirType]{0070001 rpc调用,0070002 injvm调用}
	 * @param EcmTcecirCs circle 调用环cs对象	 
	 */
	public static void writeCircleCS(EcmTcecirCs circs){		
			circs.setCirClientSttm(DateTimeHelper.getCurrentTime());
			log.info(circs.toString());
			logCollService.writeCircleCS(circs);		
	}
	/**
	 * 调用环开始服务端sr-Server Receive	 
	 * @param EcmTcecirSr  调用环sr对象	 
	 */
	public static  void writeCircleSR(EcmTcecirSr cirsr){		
			cirsr.setCirServerSttm(DateTimeHelper.getCurrentTime());
			log.info(cirsr.toString());
			logCollService.writeCircleSR(cirsr);
	}
	/**
	 * 调用环开始服务端ss-Server Send
	 * 服务端调用状态[cirServerStatus]{0015001 成功,0015002 失败}
	 * @param EcmTcecirSs   调用环ss对象	 
	 */
	public static void writeCircleSS(EcmTcecirSs cirss){		
			cirss.setCirServerEntm(DateTimeHelper.getCurrentTime());
			log.info(cirss.toString());
			logCollService.writeCircleSS(cirss);		
	}
	
	/**
	 * 调用环开始客户端cr-Client Receive
	 * 客户端调用状态[cirClientStatus]{0015001 成功,0015002 失败}
	 * @param EcmTcecirCr  调用环cr对象	 
	 */
	public static void writeCircleCr(EcmTcecirCr circr){		
			circr.setCirClientEntm(DateTimeHelper.getCurrentTime());	
			log.info(circr.toString());
			logCollService.writeCircleCr(circr);	
	}
	/**
	 * 写日志标注
	 * 标注类型[annotType]{0074001 普通标注,0074002 异常标注}
	 * @param EcmTraceAnnot  日志标注对象	 
	 */
	public static void writeAnnot(EcmTraceAnnot annot){		
			annot.setAnnotTime(DateTimeHelper.getCurrentTime());
			log.info(annot.toString());
			logCollService.writeAnnot(annot);
	}
}
