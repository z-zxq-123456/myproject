package com.dcits.galaxy.logcover.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
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
 * 当日志平台模式 为队列+数据库时的日志队列实现，目的为平抑日志峰涌* 
 * @author tangxlf
 * @date 2016-10-09 
 */
public class PlatLogQueue {
	
	public static AtomicInteger queueSize = new AtomicInteger();//平台队列长度
	
	public static AtomicInteger tceinStartSize = new AtomicInteger();//调用链开始队列长度
	
	public static AtomicInteger tceinEndSize = new AtomicInteger();//调用链结束队列长度
	
	public static AtomicInteger tceinBusSize = new AtomicInteger();//调用链业务字段队列长度	
	
	public static AtomicInteger circsSize = new AtomicInteger();//调用环客户端发起队列长度
	
	public static AtomicInteger cirsrSize = new AtomicInteger();//调用环服务端接收队列长度
	
	public static AtomicInteger cirssSize = new AtomicInteger();//调用环服务端发起长度
	
	public static AtomicInteger circrSize = new AtomicInteger();//调用环客户端接收队列长度
	
	public static AtomicInteger annotSize = new AtomicInteger();//日志标注队列长度

	private static Queue<EcmTcechainStart> tcecinStartQueue = new ConcurrentLinkedQueue<EcmTcechainStart>(); //调用链开始队列
	
	private static Queue<EcmTcecinEnd> tcecinEndQueue = new ConcurrentLinkedQueue<EcmTcecinEnd>();   //调用链结束队列
	
	private static Queue<EcmTcecinBus> tcecinBusQueue = new ConcurrentLinkedQueue<EcmTcecinBus>();   //调用链业务字段队列
	
	private static Queue<EcmTcecirCs> circsQueue = new ConcurrentLinkedQueue<EcmTcecirCs>();   //调用环客户端发起队列
	
	private static Queue<EcmTcecirSr> cirsrQueue = new ConcurrentLinkedQueue<EcmTcecirSr>();   //调用环服务端接收队列
	
	private static Queue<EcmTcecirSs> cirssQueue = new ConcurrentLinkedQueue<EcmTcecirSs>();   //调用环服务端发起队列
	
	private static Queue<EcmTcecirCr> circrQueue = new ConcurrentLinkedQueue<EcmTcecirCr>();   //调用环客户端接收队列
	
	private static Queue<EcmTraceAnnot> annotQueue = new ConcurrentLinkedQueue<EcmTraceAnnot>();   //日志标注队队列	
	
	public static long tceinStartInitTime = 0l;//调用链开始队列为零时的初始时间
	
	public static Long tceinEndInitTime = null;//调用链结束队列为零时的初始时间
	
	public static Long tceinBusInitTime = null;//调用链业务字段队列为零时的初始时间	
	
	public static Long circsInitTime = null;//调用环客户端发起队列为零时的初始时间
	
	public static Long cirsrInitTime = null;//调用环服务端接收队列为零时的初始时间
	
	public static Long cirssInitTime = null;//调用环服务端发起队列为零时的初始时间
	
	public static Long circrInitTime = null;//调用环客户端接收队列为零时的初始时间
	
	public static Long annotInitTime = null;//日志标注队列为零时的初始时间
	
	static{
		tceinStartInitTime = initExpiredTime();
		tceinEndInitTime = initExpiredTime();
		tceinBusInitTime = initExpiredTime();
		circsInitTime = initExpiredTime();
		cirsrInitTime = initExpiredTime();
		cirssInitTime = initExpiredTime();
		circrInitTime = initExpiredTime();
		annotInitTime = initExpiredTime();		
	}
	/**
	 * 调用链开始写日志
	 * @param EcmTcechainStart  调用链开始对象	 
	 */
	public static void writeStartTrace(EcmTcechainStart chainStart){
		incrQueueSize();
		incrTceinStartQueueSize();
		tcecinStartQueue.add(chainStart);
		signalThread(LogLock.chainStartLock,LogLock.chainStartCon,getTceinStartQueueSize(),LogConfCache.getConfigInfo().getTceinStartLen());
	}
	/**
	 * 从调用链开始队列中读取指定数量的对象
	 * @param List<EcmTcechainStart>  调用链开始对象列表	 
	 */
	public static List<EcmTcechainStart> readStartTrace(int batchSize){		
		List<EcmTcechainStart> list = new ArrayList<EcmTcechainStart>();
		for(int i=0;i<batchSize;i++){
			EcmTcechainStart obj= tcecinStartQueue.poll();
			if(obj!=null){
				decrQueueSize();
				decrTceinStartQueueSize();
				list.add(obj);
			}else{
				tceinStartInitTime = initExpiredTime();
				break;
			}
		}
		return list;
	}	
	/**
	 * 调用链结束写日志	 
	 * @param EcmTcecinEnd   调用链结束对象	 
	 */
	public static void writeEndTrace(EcmTcecinEnd traceChain){
		incrQueueSize();
		incrTceinEndQueueSize();
		tcecinEndQueue.add(traceChain);
		signalThread(LogLock.chainEndLock,LogLock.chainEndCon,getTceinEndQueueSize(),LogConfCache.getConfigInfo().getTceinStartLen());
	}
	/**
	 * 从调用链结束队列中读取指定数量的对象
	 * @param List<EcmTcecinEnd>  调用链结束对象列表	 
	 */
	public static List<EcmTcecinEnd> readEndTrace(int batchSize){		
		List<EcmTcecinEnd> list = new ArrayList<EcmTcecinEnd>();
		for(int i=0;i<batchSize;i++){
			EcmTcecinEnd obj= tcecinEndQueue.poll();
			if(obj!=null){
				decrQueueSize();
				decrTceinEndQueueSize();
				list.add(obj);
			}else{
				tceinEndInitTime = initExpiredTime();
				break;
			}
		}
		return list;
	}	
	/**
	 * 调用链BusinessHandler解包后调用此方法,写业务字段[一个调用链只写一次,在第一个调用环时写]	
	 * @param EcmTcecinBus  调用链业务对象	 
	 */
	public static void writeBusColTrace(EcmTcecinBus chainBus){
		incrQueueSize();
		incrTceinBusQueueSize();
		tcecinBusQueue.add(chainBus);
		signalThread(LogLock.chainBusLock,LogLock.chainBusCon,getTceinBusQueueSize(),LogConfCache.getConfigInfo().getTceinStartLen());
	}
	/**
	 * 从调用链业务对象队列中读取指定数量的对象
	 * @param List<EcmTcecinBus>  调用链业务对象列表	 
	 */
	public static List<EcmTcecinBus> readBusColTrace(int batchSize){		
		List<EcmTcecinBus> list = new ArrayList<EcmTcecinBus>();
		for(int i=0;i<batchSize;i++){
			EcmTcecinBus obj= tcecinBusQueue.poll();
			if(obj!=null){
				decrQueueSize();
				decrTceinBusQueueSize();
				list.add(obj);
			}else{
				tceinBusInitTime = initExpiredTime();
				break;
			}
		}
		return list;
	}	
	/**
	 * 调用环开始客户端cs-Client Send	
	 * @param EcmTcecirCs circle 调用环cs对象	 
	 */
	public static void writeCircleCS(EcmTcecirCs circs){
		incrQueueSize();
		incrCircsQueueSize();
		circsQueue.add(circs);
		signalThread(LogLock.tceCsLock,LogLock.tceCsCon,getCircsQueueSize(),LogConfCache.getConfigInfo().getCirStartLen());
	}
	/**
	 * 从调用环cs对象队列中读取指定数量的对象
	 * @param List<EcmTcecirCs>  调用环cs对象列表	 
	 */
	public static List<EcmTcecirCs> readCircleCS(int batchSize){		
		List<EcmTcecirCs> list = new ArrayList<EcmTcecirCs>();
		for(int i=0;i<batchSize;i++){
			EcmTcecirCs obj= circsQueue.poll();
			if(obj!=null){
				decrQueueSize();
				decrCircsQueueSize();
				list.add(obj);
			}else{
				circsInitTime = initExpiredTime();
				break;
			}
		}
		return list;
	}	
	/**
	 * 调用环开始服务端sr-Server Receive	 
	 * @param EcmTcecirSr  调用环sr对象	 
	 */
	public static void writeCircleSR(EcmTcecirSr cirsr){
		incrQueueSize();
		incrCirsrQueueSize();
		cirsrQueue.add(cirsr);
		signalThread(LogLock.tceSrLock,LogLock.tceSrCon,getCirsrQueueSize(),LogConfCache.getConfigInfo().getCirStartLen());
	}
	/**
	 * 从调用环sr对象队列中读取指定数量的对象
	 * @param List<EcmTcecirSr>  调用环sr对象表	 
	 */
	public static List<EcmTcecirSr> readCircleSR(int batchSize){		
		List<EcmTcecirSr> list = new ArrayList<EcmTcecirSr>();
		for(int i=0;i<batchSize;i++){
			EcmTcecirSr obj= cirsrQueue.poll();
			if(obj!=null){
				decrQueueSize();
				decrCirsrQueueSize();
				list.add(obj);
			}else{
				cirsrInitTime = initExpiredTime();
				break;
			}
		}
		return list;
	}	
	/**
	 * 调用环开始服务端ss-Server Send	 
	 * @param EcmTcecirSs   调用环ss对象	 
	 */
	public static void writeCircleSS(EcmTcecirSs cirss){
		incrQueueSize();
		incrCirssQueueSize();
		cirssQueue.add(cirss);
		signalThread(LogLock.tceSsLock,LogLock.tceSsCon,getCirssQueueSize(),LogConfCache.getConfigInfo().getCirStartLen());
	}
	/**
	 * 从调用环ss对象队列中读取指定数量的对象
	 * @param List<EcmTcecirSs>  调用环ss对象列表	 
	 */
	public static List<EcmTcecirSs> readCircleSS(int batchSize){		
		List<EcmTcecirSs> list = new ArrayList<EcmTcecirSs>();
		for(int i=0;i<batchSize;i++){
			EcmTcecirSs obj= cirssQueue.poll();
			if(obj!=null){
				decrQueueSize();
				decrCirssQueueSize();
				list.add(obj);
			}else{
				cirssInitTime = initExpiredTime();
				break;
			}
		}
		return list;
	}	
	/**
	 * 调用环开始客户端cr-Client Receive	
	 * @param EcmTcecirCr  调用环cr对象	 
	 */
	public static void writeCircleCR(EcmTcecirCr circr){
		incrQueueSize();
		incrCircrQueueSize();
		circrQueue.add(circr);
		signalThread(LogLock.tceCrLock,LogLock.tceCrCon,getCircrQueueSize(),LogConfCache.getConfigInfo().getCirStartLen());
	}
	/**
	 * 从调用环cr对象队列中读取指定数量的对象
	 * @param List<EcmTcecirCr>  调用环cr对象列表	 
	 */
	public static List<EcmTcecirCr> readCircleCR(int batchSize){		
		List<EcmTcecirCr> list = new ArrayList<EcmTcecirCr>();
		for(int i=0;i<batchSize;i++){
			EcmTcecirCr obj= circrQueue.poll();
			if(obj!=null){
				decrQueueSize();
				decrCircrQueueSize();
				list.add(obj);
			}else{
				circrInitTime = initExpiredTime();
				break;
			}
		}
		return list;
	}	
	/**
	 * 写日志标注	 
	 * @param EcmTraceAnnot  日志标注对象	 
	 */
	public static void writeAnnot(EcmTraceAnnot annot){
		incrQueueSize();
		incrAnnotQueueSize();
		annotQueue.add(annot);
		signalThread(LogLock.annotLock,LogLock.annotCon,getAnnotQueueSize(),LogConfCache.getConfigInfo().getAnnotStartLen());
	}
	/**
	 * 从日志标注队列中读取指定数量的对象
	 * @param List<EcmTraceAnnot>  日志标注对象列表	 
	 */
	public static List<EcmTraceAnnot> readAnnot(int batchSize){		
		List<EcmTraceAnnot> list = new ArrayList<EcmTraceAnnot>();
		for(int i=0;i<batchSize;i++){
			EcmTraceAnnot obj= annotQueue.poll();
			if(obj!=null){
				decrQueueSize();
				decrAnnotQueueSize();
				list.add(obj);
			}else{
				annotInitTime = initExpiredTime();
				break;
			}
		}
		return list;
	}	
	//增加队列长度
	private  static void incrQueueSize(){
		queueSize.incrementAndGet();
	}
	//减少队列长度
	private  static void decrQueueSize(){
		queueSize.decrementAndGet();
	}
	
	//增加调用链队列长度
	private  static void incrTceinStartQueueSize(){
		tceinStartSize.incrementAndGet();
	}
	//减少调用链队列长度
	private  static void decrTceinStartQueueSize(){
		tceinStartSize.decrementAndGet();
	}	
	//增加调用链结束队列长度
	private  static void incrTceinEndQueueSize(){
		tceinEndSize.incrementAndGet();
	}
	//减少调用链业务字段队列长度
	private  static void decrTceinEndQueueSize(){
		tceinEndSize.decrementAndGet();
	}	
	//增加调用链队列长度
	private  static void incrTceinBusQueueSize(){
		tceinBusSize.incrementAndGet();
	}
	//减少调用链业务字段长度
	private  static void decrTceinBusQueueSize(){
		tceinBusSize.decrementAndGet();
	}	
	//增加调用环客户端发起队列长度
	private  static void incrCircsQueueSize(){
		circsSize.incrementAndGet();
	}
	//减少调用环客户端发起队列长度
	private  static void decrCircsQueueSize(){
		circsSize.decrementAndGet();
	}
	//增加调用环服务端接收队列长度
	private  static void incrCirsrQueueSize(){
		cirsrSize.incrementAndGet();
	}
	//减少调用环服务端接收队列长度
	private  static void decrCirsrQueueSize(){
		cirsrSize.decrementAndGet();
	}
	//增加调用环服务端发起队列长度
	private  static void incrCirssQueueSize(){
		cirssSize.incrementAndGet();
	}
	//减少调用环服务端发起队列长度
	private  static void decrCirssQueueSize(){
		cirssSize.decrementAndGet();
	}
	//增加调用环客户端接收队列长度
	private  static void incrCircrQueueSize(){
		circrSize.incrementAndGet();
	}
	//减少调用环客户端接收队列长度
	private  static void decrCircrQueueSize(){
		circrSize.decrementAndGet();
	}
	//增加日志标注队列长度
	private  static void incrAnnotQueueSize(){
		annotSize.incrementAndGet();
	}
	//减少日志标注队列长度
	private  static void decrAnnotQueueSize(){
		annotSize.decrementAndGet();
	}
	
	/**
	 * 通知线程  是否开启线程
	 * @param  ReentrantLock lock     线程的锁
	 * @param  Condition     cond     线程启动停止的条件
	 * @param  int currentQueueSize   当前队列的长度
	 * @param  int startSize          队列启动的长度
	 */
	private static void signalThread(ReentrantLock lock,Condition cond,int currentQueueSize,int startSize){
		if(currentQueueSize>=startSize){			
			 if(lock.tryLock()){
				 try{
					 cond.signal();					
				 }finally{
					 lock.unlock();
				 }
			 }
		}
	}	
	
	/**
	 * 获取队列长度	 
	 * @return int  队列长度	 
	 */
	public static int getQueueSize(){
		return queueSize.get();
	}
	/**
	 * 获取调用链开始队列长度	 
	 * @return int  调用链队列长度	 
	 */
	public static int getTceinStartQueueSize(){
		return tceinStartSize.get();
	}
	/**
	 * 获取调用链结束队列长度	 
	 * @return int  调用链结束队列长度	 
	 */
	public static int getTceinEndQueueSize(){
		return tceinEndSize.get();
	}
	/**
	 * 获取调用链业务字段队列长度	 
	 * @return int  调用链业务字段长度	 
	 */
	public static int getTceinBusQueueSize(){
		return tceinBusSize.get();
	}
	/**
	 * 获取客户端发起队列长度	 
	 * @return int  客户端发起队列长度	 
	 */
	public static int getCircsQueueSize(){
		return circsSize.get();
	}
	/**
	 * 获取调用环服务端接收长度	 
	 * @return int  调用环服务端接收队列长度	 
	 */
	public static int getCirsrQueueSize(){
		return cirsrSize.get();
	}
	/**
	 * 获取调用环服务端发起队列长度	 
	 * @return int  调用环服务端发起队列长度	 
	 */
	public static int getCirssQueueSize(){
		return cirssSize.get();
	}
	/**
	 * 获取调用环客户端接收队列长度	 
	 * @return int  调用环客户端接收队列长度	 
	 */
	public static int getCircrQueueSize(){
		return circrSize.get();
	}
	/**
	 * 获取日志标注队列长度	 
	 * @return int  日志标注队列长度	 
	 */
	public static int getAnnotQueueSize(){
		return annotSize.get();
	}
	
	/**
	 * 赋值当前时间	 
	 * @return 
	 *  
	 */
	private static long initExpiredTime(){
		return System.currentTimeMillis();
	}	

}
