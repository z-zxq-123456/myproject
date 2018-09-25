package com.dcits.galaxy.dtp;

import com.dcits.galaxy.dtp.exception.DTPException;
import com.dcits.galaxy.dtp.trunk.TrunkStatus;

public class DtpContext {

	private static ThreadLocal<TransactionObject> transactionLocal = new ThreadLocal<>();

	public static String getTxid() {
		TransactionObject object = transactionLocal.get();

		if (object == null)
			return null;

		return object.getTxid();
	}

	public static String getBxid() {

		TransactionObject object = transactionLocal.get();

		if (object == null)
			return null;

		return object.getBxid();
	}

	public static void setTxid(String txid) {
		TransactionObject object = transactionLocal.get();

		if (object == null) {
			object = new TransactionObject();
			transactionLocal.set(object);
		}
		
		object.setTxid(txid);
	}

	public static void setBxid(String bxid) {
		TransactionObject object = transactionLocal.get();

		if (object == null) {
			object = new TransactionObject();
			transactionLocal.set(object);
		}
		
		object.setBxid(bxid);
	}
	
	public static String getLogid() {
		TransactionObject object = transactionLocal.get();

		if (object == null)
			return null;
		
		return object.getLogid();
	}

	public static void setLogid(String logid) {
		TransactionObject object = transactionLocal.get();

		if (object == null) {
			object = new TransactionObject();
			transactionLocal.set(object);
		}
		
		object.setLogid(logid);
	}
	
	public static TrunkStatus getStatus() {

		TransactionObject object = transactionLocal.get();

		if (object == null)
			return null;
		
		return object.getStatus();
	}

	public static void setStatus(TrunkStatus status) {
		TransactionObject object = transactionLocal.get();

		if (object == null) {
			object = new TransactionObject();
			transactionLocal.set(object);
		}
		object.setStatus(status);
	}
	
	public static void setStatus(String status) {
		
		if(status == null)
			return;
		
		TransactionObject object = transactionLocal.get();

		if (object == null) {
			object = new TransactionObject();
			transactionLocal.set(object);
		}
		
		object.setStatus(TrunkStatus.valueOf(status));
	}
	
	public static int nextBranchIndex() {
		TransactionObject object = transactionLocal.get();
		if (object == null || object.getBxid() == null) {
			throw new DTPException("bxid must be not null when get branch index.");
		}
		return object.nextBranchIndex();
	}

	public static int nextLogIndex() {
		TransactionObject object = transactionLocal.get();
		if (object == null || object.getBxid() == null) {
			throw new DTPException("bxid must be not null when get log index.");
		}
		return object.nextLogIndex();
	}
	
	public static int nextTrunkIndex() {
		TransactionObject object = transactionLocal.get();

		if (object == null || object.getTxid() == null) {
			throw new DTPException("txid must be not null when get trunk index.");
		}
		return object.nextTrunkIndex();
	}

	public static boolean isInDtp() {
		return transactionLocal.get() != null;
	}

	public static void clean() {
		transactionLocal.remove();
	}
	
	public static TransactionObject getTransactionObject(){
		return transactionLocal.get();
	}
	
	public static void setTransactionObject(TransactionObject object){
		if(transactionLocal.get() != null){
			throw new DTPException("can't set TransactionObject, it's have a instance. txid: " + transactionLocal.get().getTxid());
		}
		transactionLocal.set(object);
	}
	
	public static class TransactionObject {
		
		private String txid;
		private String bxid;
		private String logid;
		
		private int trunkIndex = 0;
		private int branchIndex = 0;
		private int logIndex = 0;
		
		private TrunkStatus status = TrunkStatus.prepare;

		public String getTxid() {
			return txid;
		}

		public void setTxid(String txid) {
			this.txid = txid;
		}

		public String getBxid() {
			return bxid;
		}

		public void setBxid(String bxid) {
			if(bxid == null || !bxid.equals(this.bxid)){
				this.bxid = bxid;
				this.logIndex = 0;
				this.branchIndex = 0;
			}
		}

		public String getLogid() {
			return logid;
		}

		public void setLogid(String logid) {
			this.logid = logid;
		}

		public TrunkStatus getStatus() {
			return status;
		}

		public void setStatus(TrunkStatus status) {
			this.status = status;
		}

		public int nextBranchIndex() {
			return branchIndex++;
		}

		public int nextLogIndex() {
			return logIndex++;
		}
		
		public int nextTrunkIndex() {
			return trunkIndex++;
		}

		public void setTrunkIndex(int trunkIndex) {
			this.trunkIndex = trunkIndex;
		}

		public void setBranchIndex(int branchIndex) {
			this.branchIndex = branchIndex;
		}
	}
}
