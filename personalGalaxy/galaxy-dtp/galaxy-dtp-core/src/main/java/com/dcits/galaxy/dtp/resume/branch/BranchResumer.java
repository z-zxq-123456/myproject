package com.dcits.galaxy.dtp.resume.branch;

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

import com.dcits.galaxy.dtp.branch.BranchService;
import com.dcits.galaxy.dtp.branch.BranchServiceFactory;
import com.dcits.galaxy.dtp.branch.BranchTransaction;
import com.dcits.galaxy.dtp.resume.NamedThreadFactory;

/**
 * 分支事务恢复调度
 * @author Yin.Weicai
 *
 */
public class BranchResumer {
	
	private static Logger logger = LoggerFactory.getLogger(BranchResumer.class);
	
	/**
	 * 恢复周期，默认：90，单位：秒
	 */
	private int internal = 90;
	
	/**
	 * 应用名
	 */
	private String appName = null;
	
	private ScheduledExecutorService scheduledService = null;
	
	private ExecutorService executorService = null;
	
	private ConcurrentMap<String,RecoveringBranch> recoveringCache = new ConcurrentHashMap<String,RecoveringBranch>();
	
	private ScheduledExecutorService recoveringCacheSchedule = null;
	
	public BranchResumer(){
		scheduledService = Executors.newSingleThreadScheduledExecutor( new NamedThreadFactory("BranchResumer-Scheduled-") );
		executorService = Executors.newFixedThreadPool(10, new NamedThreadFactory("BranchResumer-pool-") );
		recoveringCacheSchedule = Executors.newSingleThreadScheduledExecutor( new NamedThreadFactory("BranchResumer-CacheCheck-") );
	}
	
	/**
	 * 启动定时恢复调度
	 */
	public void start(){
		scheduledService.scheduleAtFixedRate( new Runnable(){
			@Override
			public void run() {
				try {
					execute();
				} catch (Exception e) {
					logger.info("occur exception when resume branch: ", e);
				}
			}
		}, internal, internal, TimeUnit.SECONDS);
		
		// 定期检查缓存，清除缓存中存在时间过长的Branch信息
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
		logger.debug(" New BranchResumer period start again ");
		
		BranchService branchService = BranchServiceFactory.getBean();
		List<BranchTransaction> branches = branchService.getUnCompletedDisorderByAppGroup(appName);
		if( branches != null && branches.size() > 0 ){
			logger.debug(" New BranchResumer period has ["+ branches.size() +"] BranchTransaction need resume! ");
			for (BranchTransaction branch : branches) {
				BranchResumerTask task = new BranchResumerTask(branch);
				executorService.execute( task );
			}
		}else{
			logger.debug(" New BranchResumer period has [0] BranchTransaction need resume! ");
		}
	}
	
	/**
	 * 定期检测 recoveringCache，清除存在时长过长的Branch信息。
	 */
	private void recoveringCacheCheck(){
		Iterator<String> iterator = recoveringCache.keySet().iterator();
		while( iterator.hasNext() ){
			String txid = iterator.next();
			RecoveringBranch recovering = recoveringCache.get( txid );
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
		if( !scheduledService.isShutdown() ){
			scheduledService.shutdown();
		}
		if( !executorService.isShutdown() ){			
			executorService.shutdown();
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
	
	static class RecoveringBranch{
		String bxid = null;
		long startTime = 0;
		
		public RecoveringBranch(String txid){
			this.bxid = txid;
			this.startTime = System.currentTimeMillis();
		}
		
		@Override
		public boolean equals(Object obj) {
			boolean result = false;
			if( obj == null ){
				return result;
			}
			RecoveringBranch target = (RecoveringBranch)obj;
			if( (target.bxid != null && bxid != null && bxid.equals( target.bxid )) 
					|| ( target.bxid == null && bxid == null ) ){
				result = true;
			}
			if( result ){
				if( target.startTime == startTime ){
					result = true;
				}else{
					result = false;
				}
			}
			return result;
		}
	}
}
