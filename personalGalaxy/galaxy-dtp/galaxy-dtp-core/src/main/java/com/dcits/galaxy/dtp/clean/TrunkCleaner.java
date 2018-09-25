package com.dcits.galaxy.dtp.clean;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.dtp.resume.NamedThreadFactory;
import com.dcits.galaxy.dtp.trunk.TrunkServiceFactory;

/**
 * 过期事务清理
 * @author fan.kaijie
 *
 */
public class TrunkCleaner {
	
	private static Logger logger = LoggerFactory.getLogger(TrunkCleaner.class);

	/**
	 * 过期时间
	 * 单位：秒
	 */
	private long cleanCycleTime = 30 * 60;
	
	/**
	 * 清理频率
	 * 单位：秒
	 */
	private long frequency = 10 * 60;

	private String appGroup = null;

	private ScheduledExecutorService scheduledService = null;
	
	public void start(){
		scheduledService.scheduleAtFixedRate( new Runnable(){
			@Override
			public void run() {
				logger.debug("Begin clean trunks......");
				long time = System.currentTimeMillis() - cleanCycleTime * 1000;
				try {
					TrunkServiceFactory.getBean().clean(appGroup, time);
				} catch (Exception e) {
					logger.debug("can't clean trunk. cause: ", e);
				}
				logger.debug("Clean trunks is finished......");
			}
		}, frequency, frequency, TimeUnit.SECONDS);
		
		logger.debug(" TrunkCleaner is started......");
	}
	
	public void shutdown(){
		if(!scheduledService.isShutdown())
			scheduledService.shutdown();
	}

	public TrunkCleaner(){
		scheduledService = Executors.newSingleThreadScheduledExecutor( new NamedThreadFactory("TrunkCleaner-Scheduled-") );
	}
	public long getCleanCycleTime() {
		return cleanCycleTime;
	}

	/**
	 * 设置过期时间
	 * 单位：秒
	 * @param cleanCycleTime
	 */
	public void setCleanCycleTime(long cleanCycleTime) {
		this.cleanCycleTime = cleanCycleTime;
	}

	public long getFrequency() {
		return frequency;
	}

	/**
	 * 设置清理频率
	 * 单位：秒
	 * @param frequency
	 */
	public void setFrequency(long frequency) {
		this.frequency = frequency;
	}

	public String getAppGroup() {
		return appGroup;
	}

	public void setAppGroup(String appGroup) {
		this.appGroup = appGroup;
	}
}
