package com.dcits.galaxy.sequences.model;

import java.util.Queue;

public class SeqQueue {
	
	private String sequenceName;
	
	private long cacheCount;
	
	private Queue<Long> queue;

	public SeqQueue() {
	}

	public SeqQueue(String sequenceName, long cacheCount, Queue<Long> queue) {
		this.sequenceName = sequenceName;
		this.cacheCount = cacheCount;
		this.queue = queue;
	}


	public String getSequenceName() {
		return sequenceName;
	}

	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}

	public long getCacheCount() {
		return cacheCount;
	}

	public void setCacheCount(long cacheCount) {
		this.cacheCount = cacheCount;
	}

	public Queue<Long> getQueue() {
		return queue;
	}

	public void setQueue(Queue<Long> queue) {
		this.queue = queue;
	}
	
	public boolean hasQueue(){
		boolean flag = false;
		if(null!=queue&&queue.size()>0){
			flag = true;
		}
		return flag;
	}
	
	

}
