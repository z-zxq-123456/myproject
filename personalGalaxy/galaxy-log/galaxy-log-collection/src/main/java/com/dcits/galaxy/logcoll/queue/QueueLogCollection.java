package com.dcits.galaxy.logcoll.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dcits.galaxy.logcover.api.ILogCollection;
import com.dcits.galaxy.logcover.config.LogConfCache;
import com.dcits.galaxy.logcover.model.EcmTcechainStart;
import com.dcits.galaxy.logcover.model.EcmTcecinBus;
import com.dcits.galaxy.logcover.model.EcmTcecinEnd;
import com.dcits.galaxy.logcover.model.EcmTcecirCr;
import com.dcits.galaxy.logcover.model.EcmTcecirCs;
import com.dcits.galaxy.logcover.model.EcmTcecirSr;
import com.dcits.galaxy.logcover.model.EcmTcecirSs;
import com.dcits.galaxy.logcover.model.EcmTraceAnnot;
import com.dcits.galaxy.logcover.queue.PlatLogQueue;

/**
 * 队列日志收集* 
 * @author tangxlf
 * @date 2016-10-12 
 */
public class QueueLogCollection implements ILogCollection{
	
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	/**
	 * 调用链开始写日志
	 * @param EcmTcechainStart  调用链开始对象	 
	 */
	@Override
	public void writeStartTrace(EcmTcechainStart chainStart) {
		if(PlatLogQueue.getQueueSize()<LogConfCache.getConfigInfo().getQueueLen()){
			PlatLogQueue.writeStartTrace(chainStart);
		}else{
			writeLogFile(chainStart);
		}
	}
	/**
	 * 调用链结束写日志	 
	 * @param EcmTcecinEnd   调用链结束对象	 
	 */
	@Override
	public void writeEndTrace(EcmTcecinEnd traceChain) {
		if(PlatLogQueue.getQueueSize()<LogConfCache.getConfigInfo().getQueueLen()){
			PlatLogQueue.writeEndTrace(traceChain);
		}else{
			writeLogFile(traceChain);
		}
	}
	/**
	 * 调用链BusinessHandler解包后调用此方法,写业务字段[一个调用链只写一次,在第一个调用环时写]	
	 * @param EcmTcecinBus  调用链业务对象	 
	 */
	@Override
	public void writeBusColTrace(EcmTcecinBus chainBus) {
		if(PlatLogQueue.getQueueSize()<LogConfCache.getConfigInfo().getQueueLen()){
			PlatLogQueue.writeBusColTrace(chainBus);
		}else{
			writeLogFile(chainBus);
		}
	}
	/**
	 * 调用环开始客户端cs-Client Send	
	 * @param EcmTcecirCs circle 调用环cs对象	 
	 */
	@Override
	public void writeCircleCS(EcmTcecirCs circs) {
		if(PlatLogQueue.getQueueSize()<LogConfCache.getConfigInfo().getQueueLen()){
			PlatLogQueue.writeCircleCS(circs);
		}else{
			writeLogFile(circs);
		}
	}
	/**
	 * 调用环开始服务端sr-Server Receive	 
	 * @param EcmTcecirSr  调用环sr对象	 
	 */
	@Override
	public void writeCircleSR(EcmTcecirSr cirsr) {
		if(PlatLogQueue.getQueueSize()<LogConfCache.getConfigInfo().getQueueLen()){
			PlatLogQueue.writeCircleSR(cirsr);
		}else{
			writeLogFile(cirsr);
		}
	}
	/**
	 * 调用环开始服务端ss-Server Send	 
	 * @param EcmTcecirSs   调用环ss对象	 
	 */
	@Override
	public void writeCircleSS(EcmTcecirSs cirss) {
		if(PlatLogQueue.getQueueSize()<LogConfCache.getConfigInfo().getQueueLen()){
			PlatLogQueue.writeCircleSS(cirss);
		}else{
			writeLogFile(cirss);
		}
	}
	/**
	 * 调用环开始客户端cr-Client Receive	
	 * @param EcmTcecirCr  调用环cr对象	 
	 */
	@Override
	public void writeCircleCr(EcmTcecirCr circr) {
		if(PlatLogQueue.getQueueSize()<LogConfCache.getConfigInfo().getQueueLen()){
			PlatLogQueue.writeCircleCR(circr);
		}else{
			writeLogFile(circr);
		}
	}
	/**
	 * 写日志标注	 
	 * @param EcmTraceAnnot  日志标注对象	 
	 */
	@Override
	public void writeAnnot(EcmTraceAnnot annot) {
		if(PlatLogQueue.getQueueSize()<LogConfCache.getConfigInfo().getQueueLen()){
			PlatLogQueue.writeAnnot(annot);
		}else{
			writeLogFile(annot);
		}
	}

	private void writeLogFile(Object obj){
		log.error("queue exceed max size,classtype:"+obj.getClass().getSimpleName()+"data:"+obj);
	}
}
