package com.dcits.galaxy.core.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.dcits.galaxy.core.threadpool.support.AbortPolicyWithReport;
import com.dcits.galaxy.core.threadpool.support.NamedThreadFactory;

/**
 * 线程池封装
 * 
 * @author xuecy
 *
 */
public class Executors {

	/**
	 * 固定大小的线程池
	 * 
	 * @param name
	 * @param nThreads
	 * @return
	 */
	public static ExecutorService newFixedThreadPool(String name, int nThreads) {
		return new ThreadPoolExecutor(nThreads, nThreads, 0L,
				TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(),
				new NamedThreadFactory(name, true), new AbortPolicyWithReport(
						name));
	}

	/**
	 * 可以伸缩的线程池，3分钟不用自动回收
	 * 
	 * @param name
	 * @param cores
	 * @param max
	 * @return
	 */
	public static ExecutorService newCachedThreadPool(String name, int cores,
			int max) {
		return new ThreadPoolExecutor(cores, max, 3 * 60L, TimeUnit.SECONDS,
				new SynchronousQueue<Runnable>(), new NamedThreadFactory(name,
						true), new AbortPolicyWithReport(name));
	}

	/**
	 * 此线程池一直增长，直到上限，增长后不收缩。
	 * 
	 * @param name
	 * @param cores
	 * @param max
	 * @return
	 */
	public static ExecutorService newLimitedThreadPool(String name, int cores,
			int max) {
		return new ThreadPoolExecutor(cores, max, Integer.MAX_VALUE,
				TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),
				new NamedThreadFactory(name, true), new AbortPolicyWithReport(
						name));
	}

	/**
	 * 单个线程
	 * 
	 * @param name
	 * @return
	 */
	public static ExecutorService newSingleThreadExecutor(String name) {
		return java.util.concurrent.Executors
				.newSingleThreadExecutor(new NamedThreadFactory(name, true));
	}

}
