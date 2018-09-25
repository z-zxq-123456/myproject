package com.dcits.galaxy.logcoll.trace;

import com.dcits.galaxy.logcover.api.ILogCollection;
import com.dcits.galaxy.logcover.model.EcmTcechainStart;
import com.dcits.galaxy.logcover.model.EcmTcecinBus;
import com.dcits.galaxy.logcover.model.EcmTcecinEnd;
import com.dcits.galaxy.logcover.model.EcmTcecirCr;
import com.dcits.galaxy.logcover.model.EcmTcecirCs;
import com.dcits.galaxy.logcover.model.EcmTcecirSr;
import com.dcits.galaxy.logcover.model.EcmTcecirSs;
import com.dcits.galaxy.logcover.model.EcmTraceAnnot;

/**
 * 日志收集dubbo服务* 
 * @author tangxlf
 * @date 2017-02-20
 */
public class LogCollectService implements ILogCollection{
	
	
	/**
	 * 调用链开始写日志
	 * @param EcmTcechainStart  调用链开始对象	 
	 */
	@Override
	public void writeStartTrace(EcmTcechainStart chainStart) {		
		LogCollectionFactory.getInstance().writeStartTrace(chainStart);
	}
	/**
	 * 调用链结束写日志	 
	 * @param EcmTcecinEnd   调用链结束对象	 
	 */
	@Override
	public void writeEndTrace(EcmTcecinEnd traceChain) {		
		LogCollectionFactory.getInstance().writeEndTrace(traceChain);
	}
	/**
	 * 调用链BusinessHandler解包后调用此方法,写业务字段[一个调用链只写一次,在第一个调用环时写]	
	 * @param EcmTcecinBus  调用链业务对象	 
	 */
	@Override
	public void writeBusColTrace(EcmTcecinBus chainBus) {		
		LogCollectionFactory.getInstance().writeBusColTrace(chainBus);
	}
	/**
	 * 调用环开始客户端cs-Client Send	
	 * @param EcmTcecirCs circle 调用环cs对象	 
	 */
	@Override
	public void writeCircleCS(EcmTcecirCs circs) {		
		LogCollectionFactory.getInstance().writeCircleCS(circs);
	}
	/**
	 * 调用环开始服务端sr-Server Receive	 
	 * @param EcmTcecirSr  调用环sr对象	 
	 */
	@Override
	public void writeCircleSR(EcmTcecirSr cirsr) {		
		LogCollectionFactory.getInstance().writeCircleSR(cirsr);
	}
	/**
	 * 调用环开始服务端ss-Server Send	 
	 * @param EcmTcecirSs   调用环ss对象	 
	 */
	@Override
	public void writeCircleSS(EcmTcecirSs cirss) {		
		LogCollectionFactory.getInstance().writeCircleSS(cirss);
	}
	/**
	 * 调用环开始客户端cr-Client Receive	
	 * @param EcmTcecirCr  调用环cr对象	 
	 */
	@Override
	public void writeCircleCr(EcmTcecirCr circr) {		
		LogCollectionFactory.getInstance().writeCircleCr(circr);
	}
	/**
	 * 写日志标注	 
	 * @param EcmTraceAnnot  日志标注对象	 
	 */
	@Override
	public void writeAnnot(EcmTraceAnnot annot) {		
		LogCollectionFactory.getInstance().writeAnnot(annot);
	}

}
