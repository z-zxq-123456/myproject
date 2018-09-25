package com.dcits.galaxy.dtp.sequence;

import org.springframework.beans.factory.InitializingBean;

import com.dcits.galaxy.dtp.demo.util.SequenceHelper;

public class Sequences implements TxidSequence, BxidSequence, LogIdSequence,InitializingBean {

	private String serverId = null;
	private Tuple trunkTuple = new Tuple();
	private Tuple branchTuple = new Tuple();
	private Tuple logTuple = new Tuple();
	private long startTime = 1261440000L;

	@Override
	public synchronized String nextLogid() {
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

	@Override
	public synchronized int nextLogIndex(String bxid) {
		return -1;
	}

	@Override
	public synchronized String nextBxid() {
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

	@Override
	public synchronized int nextIndexInBranch(String bxid) {
		return -1;
	}

	@Override
	public synchronized int nextIndexInTrunk(String txid) {
		return -1;
	}

	@Override
	public synchronized String nextTxid() {
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

	private String joinId(String ... strings) {
		StringBuilder sb = new StringBuilder();
		sb.append(strings[0]);
		for(int i = 1;i<strings.length;i++){
			sb.append("_");
			sb.append(strings[i]);
		}
		return sb.toString();
	}
	
	public void setServerId(String serverId){
		this.serverId = serverId;
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

	@Override
	public void afterPropertiesSet() throws Exception {
		SequenceHelper.registerSequences(this);
		
	}
}
