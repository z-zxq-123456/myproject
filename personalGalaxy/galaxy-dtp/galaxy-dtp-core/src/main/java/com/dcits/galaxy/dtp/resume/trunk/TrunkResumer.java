package com.dcits.galaxy.dtp.resume.trunk;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.dtp.resume.NamedThreadFactory;
import com.dcits.galaxy.dtp.trunk.TrunkService;
import com.dcits.galaxy.dtp.trunk.TrunkServiceFactory;
import com.dcits.galaxy.dtp.trunk.TrunkTransaction;

/**
 * 主事务恢复调度
 * @author Yin.Weicai
 *
 */
public class TrunkResumer {

	private static Logger logger = LoggerFactory.getLogger(TrunkResumer.class);

	/**
	 * 恢复周期，默认：60，单位：秒
	 */
	private int internal = 60;

	/**
	 * 超时时间
	 * 默认：180
	 * 单位：秒
	 */
	private int timeout = 180;


	/**
	 * 应用名
	 */
	private String appName = null;

	/**
	 * 应用组
	 */
	private String appGroup = null;

	private ScheduledExecutorService recoverySchedule = null;

	private ExecutorService recoveryExecutor = null;

	private ConcurrentMap<String,RecoveringTrunk> recoveringCache = new ConcurrentHashMap<String,RecoveringTrunk>();

	private ScheduledExecutorService recoveringCacheSchedule = null;

	public TrunkResumer(){
		this(60, 180);
	}

	public TrunkResumer(int internal,int timeout){
		this.internal = internal;
		this.timeout = timeout;
	}

	private void init(){
		if(recoverySchedule == null || recoverySchedule.isShutdown()){
			recoverySchedule = Executors.newSingleThreadScheduledExecutor( new NamedThreadFactory("TrunkResumer-Scheduled-") );
		}

		if(recoveryExecutor == null || recoveryExecutor.isShutdown()){
			recoveryExecutor = Executors.newFixedThreadPool(10, new NamedThreadFactory("TrunkResumer-pool-") );
		}

		if(recoveringCacheSchedule == null || recoveringCacheSchedule.isShutdown()){
			recoveringCacheSchedule = Executors.newSingleThreadScheduledExecutor( new NamedThreadFactory("TrunkResumer-CacheCheck-") );
		}
	}

	/**
	 * 启动定时恢复调度
	 */
	public void start(){
		init();
		//定期检查是否存在异常的未完成事务,如果存在尝试恢复。
		recoverySchedule.scheduleAtFixedRate( new Runnable(){
			@Override
			public void run() {
				try {
					execute();
				} catch (Exception e) {
					logger.info("",e);
				}
			}
		}, internal, internal, TimeUnit.SECONDS);

		// 定期检查缓存，清除缓存中存在时间过长的Trunk信息
		recoveringCacheSchedule.scheduleAtFixedRate(new Runnable(){
			@Override
			public void run() {
				try {
					recoveringCacheCheck();
				} catch (Exception e) {
					// no-do
				}
			}
		}, internal*3/2, internal*3/2, TimeUnit.SECONDS);

		logger.info(" TrunkResumer is started......");
	}

	private void execute(){
		logger.debug(" New TrunkResumer period start again ");

		TrunkService trunkService = TrunkServiceFactory.getBean();
		List<TrunkTransaction> trunks = trunkService.getUnCompletedTrunks(appGroup, timeout);
		if( trunks != null && trunks.size() > 0 ){

			logger.debug(" New TrunkResumer period has "+ trunks.size() +" TrunkTransaction need resume! ");

			for (TrunkTransaction trunk : trunks) {
//				TrunkStatus status = trunk.getStatus();
//				if( TrunkStatus.prepare == status ){
//					break;
//				}

				String txid = trunk.getTxid();
				boolean isRecovering = recoveringCache.containsKey(txid);
				if( isRecovering ){
					continue;
				}

				boolean needOrder = trunk.isNeedOrder();
				//区分事务是否需要执行顺序
				if( needOrder ){
					TrunkResumeTask task = new TrunkResumeTask(trunk);
					recoveryExecutor.execute( task );
				}else{
					TrunkResumeDisorderTask task = new TrunkResumeDisorderTask(trunk);
					recoveryExecutor.execute( task );
				}

				recoveringCache.put(txid, new RecoveringTrunk(txid));
			}
		}else{
			logger.debug(" New TrunkResumer period has no TrunkTransaction need resume! ");
		}
	}

	/**
	 * 定期检测 recoveringCache，清除存在时长过长的Trunk信息。
	 */
	private void recoveringCacheCheck(){
		Iterator<String> iterator = recoveringCache.keySet().iterator();
		while( iterator.hasNext() ){
			String txid = iterator.next();
			RecoveringTrunk recovering = recoveringCache.get( txid );
			long recoveryTime = System.currentTimeMillis() - recovering.startTime;
			if( recoveryTime >= 180 * 1000 ){
				recoveringCache.remove( txid );
			}
		}
	}

	/**
	 * 关闭定时恢复调度
	 */
	public void shutdown(){
		if(recoverySchedule != null && !recoverySchedule.isShutdown() ){
			recoverySchedule.shutdown();
			recoverySchedule = null;
		}
		if(recoveryExecutor != null && !recoveryExecutor.isShutdown() ){
			recoveryExecutor.shutdown();
			recoveryExecutor = null;
		}
		if(recoveringCacheSchedule != null && !recoveringCacheSchedule.isShutdown()){
			recoveringCacheSchedule.shutdown();
			recoveringCacheSchedule = null;
			recoveringCache.clear();
		}
	}

	public int getInternal() {
		return internal;
	}

	public void setInternal(int internal) {
		this.internal = internal;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppGroup() {
		return appGroup;
	}

	public void setAppGroup(String appGroup) {
		this.appGroup = appGroup;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	static class RecoveringTrunk{
		String txid = null;
		long startTime = 0;

		public RecoveringTrunk(String txid){
			this.txid = txid;
			this.startTime = System.currentTimeMillis();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (startTime ^ (startTime >>> 32));
			result = prime * result + ((txid == null) ? 0 : txid.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			RecoveringTrunk other = (RecoveringTrunk) obj;
			if (startTime != other.startTime)
				return false;
			if (txid == null) {
				if (other.txid != null)
					return false;
			} else if (!txid.equals(other.txid))
				return false;
			return true;
		}
	}

}
