package com.dcits.galaxy.dal.mybatis.table.router;

import com.dcits.galaxy.dal.mybatis.table.tableshard.TableShard;
/**
 * 用于存放tableShard信息的线程上下文容器
 * @author huang.zhe
 *
 */
public class TableShardContext {

	private final static ThreadLocal<TableShard> threadLocal = new ThreadLocal<TableShard>();

	public static boolean isEmpty() {
		TableShard ts = threadLocal.get();
		if (ts == null) {
			return false;
		}
		return true;
	}

	public static void put(TableShard ts) {
		threadLocal.set(ts);
	}

	public static TableShard get() {
		return threadLocal.get();
	}

}
