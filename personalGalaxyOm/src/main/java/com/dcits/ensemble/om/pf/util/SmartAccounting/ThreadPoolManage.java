package com.dcits.ensemble.om.pf.util.SmartAccounting;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池管理类
 * @author wang.yq
 */
public class ThreadPoolManage {
	/**
	 * 获取线程池实例(线程数根据任务数自动扩展)
	 * @return ExecutorService
	 */
	public static ExecutorService getPool() {
		return Executors.newCachedThreadPool();
	}

	/**
	 * 获取线程池实例(线程数固定)
	 * @param threadNum	线程数
	 * @return ExecutorService
	 */
	public static ExecutorService getPool(int threadNum) {
		return Executors.newFixedThreadPool(threadNum);
	}
	/**
	 * 关闭线程池
	 */
	public static void shutdownPool(ExecutorService pool) {
		pool.shutdown();
	}
}