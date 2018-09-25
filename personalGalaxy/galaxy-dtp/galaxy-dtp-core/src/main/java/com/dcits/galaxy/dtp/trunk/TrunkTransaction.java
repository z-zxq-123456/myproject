package com.dcits.galaxy.dtp.trunk;


/**
 * 主事务
 * @author Yin.Weicai
 */
public class TrunkTransaction {
	
	/**
	 * 主事务标识
	 */
	private String txid;
	
	/**
	 * 主事务状态
	 */
	private TrunkStatus status = TrunkStatus.prepare;
	
	/**
	 * 是否需要顺序
	 */
	private boolean needOrder = true;
	
	/**
	 * 应用组
	 * @return
	 */
	private String appGroup = null;
	
	/**
	 * 事务开启时间
	 */
	private long startTime = 0;
	
	
	public String getTxid() {
		return txid;
	}

	public void setTxid(String txid) {
		this.txid = txid;
	}

	public TrunkStatus getStatus() {
		return status;
	}

	public void setStatus(TrunkStatus status) {
		this.status = status;
	}
	
	public void setStatus(String status) {
		this.status = TrunkStatus.valueOf(status);
	}

	public String getAppGroup() {
		return appGroup;
	}

	public void setAppGroup(String appGroup) {
		this.appGroup = appGroup;
	}

	public boolean isNeedOrder() {
		return needOrder;
	}

	public void setNeedOrder(boolean needOrder) {
		this.needOrder = needOrder;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
}
