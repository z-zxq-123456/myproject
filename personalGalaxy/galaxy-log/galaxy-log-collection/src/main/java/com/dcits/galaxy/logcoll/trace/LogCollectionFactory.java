package com.dcits.galaxy.logcoll.trace;


import java.util.HashMap;
import java.util.Map;
import com.dcits.galaxy.logcoll.kafka.KafakaLogCollection;
import com.dcits.galaxy.logcoll.queue.QueueLogCollection;
import com.dcits.galaxy.logcover.api.ILogCollection;
import com.dcits.galaxy.logcover.config.ConfigConstants;
import com.dcits.galaxy.logcover.config.LogConfCache;
import com.dcits.galaxy.logcover.model.EcmTcechainStart;
import com.dcits.galaxy.logcover.model.EcmTcecinBus;
import com.dcits.galaxy.logcover.model.EcmTcecinEnd;
import com.dcits.galaxy.logcover.model.EcmTcecirCr;
import com.dcits.galaxy.logcover.model.EcmTcecirCs;
import com.dcits.galaxy.logcover.model.EcmTcecirSr;
import com.dcits.galaxy.logcover.model.EcmTcecirSs;
import com.dcits.galaxy.logcover.model.EcmTraceAnnot;

/**
 * 日志收集工厂类* 
 * @author tangxlf
 * @date 2016-10-12 
 */
public class LogCollectionFactory implements ILogCollection{
	
	private  static Map<String,ILogCollection> LOG_MAP = new HashMap<String,ILogCollection>();
	
	private  static LogCollectionFactory factory = new LogCollectionFactory();
	
	
	static{
		LOG_MAP.put(ConfigConstants.PLAT_MODE_QUEDB,new QueueLogCollection());
		LOG_MAP.put(ConfigConstants.PLAT_MODE_KAFKADB,new KafakaLogCollection());
		LOG_MAP.put(ConfigConstants.PLAT_MODE_KAFKAHBASE,new KafakaLogCollection());
	}
	
    private LogCollectionFactory(){}
    //单实例调用
	public static LogCollectionFactory getInstance(){
		return  factory;
	}
	/**
	 * 调用链开始写日志
	 * @param EcmTcechainStart  调用链开始对象	 
	 */
	@Override
	public void writeStartTrace(EcmTcechainStart chainStart) {		
		LOG_MAP.get(LogConfCache.getConfigInfo().getLogPlatMode()).writeStartTrace(chainStart);
	}
	/**
	 * 调用链结束写日志	 
	 * @param EcmTcecinEnd   调用链结束对象	 
	 */
	@Override
	public void writeEndTrace(EcmTcecinEnd traceChain) {		
		LOG_MAP.get(LogConfCache.getConfigInfo().getLogPlatMode()).writeEndTrace(traceChain);
	}
	/**
	 * 调用链BusinessHandler解包后调用此方法,写业务字段[一个调用链只写一次,在第一个调用环时写]	
	 * @param EcmTcecinBus  调用链业务对象	 
	 */
	@Override
	public void writeBusColTrace(EcmTcecinBus chainBus) {		
		LOG_MAP.get(LogConfCache.getConfigInfo().getLogPlatMode()).writeBusColTrace(chainBus);
	}
	/**
	 * 调用环开始客户端cs-Client Send	
	 * @param EcmTcecirCs circle 调用环cs对象	 
	 */
	@Override
	public void writeCircleCS(EcmTcecirCs circs) {		
		LOG_MAP.get(LogConfCache.getConfigInfo().getLogPlatMode()).writeCircleCS(circs);
	}
	/**
	 * 调用环开始服务端sr-Server Receive	 
	 * @param EcmTcecirSr  调用环sr对象	 
	 */
	@Override
	public void writeCircleSR(EcmTcecirSr cirsr) {		
		LOG_MAP.get(LogConfCache.getConfigInfo().getLogPlatMode()).writeCircleSR(cirsr);
	}
	/**
	 * 调用环开始服务端ss-Server Send	 
	 * @param EcmTcecirSs   调用环ss对象	 
	 */
	@Override
	public void writeCircleSS(EcmTcecirSs cirss) {		
		LOG_MAP.get(LogConfCache.getConfigInfo().getLogPlatMode()).writeCircleSS(cirss);
	}
	/**
	 * 调用环开始客户端cr-Client Receive	
	 * @param EcmTcecirCr  调用环cr对象	 
	 */
	@Override
	public void writeCircleCr(EcmTcecirCr circr) {		
		LOG_MAP.get(LogConfCache.getConfigInfo().getLogPlatMode()).writeCircleCr(circr);
	}
	/**
	 * 写日志标注	 
	 * @param EcmTraceAnnot  日志标注对象	 
	 */
	@Override
	public void writeAnnot(EcmTraceAnnot annot) {		
		LOG_MAP.get(LogConfCache.getConfigInfo().getLogPlatMode()).writeAnnot(annot);
	}

}
