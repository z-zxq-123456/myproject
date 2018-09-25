package com.dcits.galaxy.dtp.demo.util;


public class SequenceHelper {
	
//	private static Preferences userPrefs = Preferences.userRoot();
//	private static Logger logger = LoggerFactory.getLogger(SequenceHelper.class);
	
	private static String serverId = null;
	
	private static Tuple trunkTuple = new Tuple();
	
	private static Tuple branchTuple = new Tuple();
	
	private static Tuple logTuple = new Tuple();
	
	private static long startTime = 1261440000L;
	
	public static void setServerId(String serverId){
		SequenceHelper.serverId = serverId;
	}
	
	private static String joinId(String ... strings) {
		StringBuilder sb = new StringBuilder();
		sb.append(strings[0]);
		for(int i = 1;i<strings.length;i++){
			sb.append("_");
			sb.append(strings[i]);
		}
		return sb.toString();
	}
	
	/**
	 * 获取一个唯一的主事务标识
	 * @return
	 */
	public synchronized static String newTxid() {
		long time = System.currentTimeMillis() / 1000 - startTime;
		int count = 0;
		if(time != trunkTuple.getTime()){
			trunkTuple.setTime(time);
		} else {
			count = trunkTuple.getCount() + 1;
		}
		trunkTuple.setCount(count);
		return joinId("txid",serverId,Long.toString(time),Integer.toString(count));
	}
	
	/**
	 * 获取一个唯一的分支事务标识
	 * @return
	 */
	public synchronized static String newBxid() {
		long time = System.currentTimeMillis() / 1000 - startTime;
		int count = 0;
		if(time != branchTuple.getTime()){
			branchTuple.setTime(time);
		} else {
			count = branchTuple.getCount() + 1;
		}
		branchTuple.setCount(count);
		return joinId("bxid",serverId,Long.toString(time),Integer.toString(count));
	}
	
	/**
	 * 获取指定的主事务中下一个分支事务的顺序号
	 * @param txid
	 * @return
	 */
	public static int nextIndexInTrunk( String txid){
//		try {
//			int indexInTrunk = userPrefs.getInt(txid, -1);
//			indexInTrunk = indexInTrunk + 1;
//			userPrefs.putInt(txid, indexInTrunk);
//			userPrefs.flush();
//			return indexInTrunk;
//		} catch (Exception e) {
//			logger.error("get nextIndexInTrunk faild!", e);
//		}
		return -1;
	}
	
	/**
	 * 获取指定的分支事务中下一个内部嵌套的分支事务的顺序号
	 * @param bxid
	 * @return
	 */
	public static int nextIndexInBranch( String bxid){
//		try {
//			int indexInBranch = userPrefs.getInt(bxid, -1);
//			indexInBranch = indexInBranch + 1;
//			userPrefs.putInt(bxid, indexInBranch);
//			userPrefs.flush();
//			return indexInBranch;
//		} catch (Exception e) {
//			logger.error("get nextIndexInBranch faild!", e);
//		}
		return -1;
	}
	
	/**
	 * 获取分支事务中下一条事务日志的顺序号
	 * @param bxid
	 * @return
	 */
	public static int nextLogIndex( String bxid ){
//		try {
//			String key = "LogIndex_" + bxid;
//			int logIndex = userPrefs.getInt(key, -1);
//			logIndex = logIndex + 1;
//			userPrefs.putInt(key, logIndex);
//			userPrefs.flush();
//			return logIndex;
//		} catch (Exception e) {
//			logger.error("get nextLogIndex faild!", e);
//		}
		return -1;
	}
	
	public synchronized static String newLogId() {
		long time = System.currentTimeMillis() / 1000 - startTime;
		int count = 0;
		if(time != logTuple.getTime()){
			logTuple.setTime(time);
		} else {
			count = logTuple.getCount() + 1;
		}
		logTuple.setCount(count);
		return joinId("logId",serverId,Long.toString(time),Integer.toString(count));
	}
	
	private static class Tuple {
		private long time;
		private int count;
		public long getTime() {
			return time;
		}
		public void setTime(long time) {
			this.time = time;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
	}
}
