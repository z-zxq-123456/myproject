package com.dcits.galaxy.dtp.demo.util;

import com.dcits.galaxy.dtp.sequence.Sequences;



public class SequenceHelper {
	
	private static Sequences sequence = null;
	
	/**
	 * 获取一个唯一的主事务标识
	 * @return
	 */
	public  static String newTxid() {
		return sequence.nextTxid();
	}
	
	/**
	 * 获取一个唯一的分支事务标识
	 * @return
	 */
	public static String newBxid() {
		return sequence.nextBxid();
	}
	
	/**
	 * 获取指定的主事务中下一个分支事务的顺序号
	 * @param txid
	 * @return
	 */
	public static int nextIndexInTrunk( String txid){
		return sequence.nextIndexInTrunk(txid);
	}
	
	/**
	 * 获取指定的分支事务中下一个内部嵌套的分支事务的顺序号
	 * @param bxid
	 * @return
	 */
	public static int nextIndexInBranch( String bxid){
		return sequence.nextIndexInBranch(bxid);
	}
	
	/**
	 * 获取分支事务中下一条事务日志的顺序号
	 * @param bxid
	 * @return
	 */
	public static int nextLogIndex( String bxid ){
		return sequence.nextLogIndex(bxid);
	}
	
	public static String newLogId() {
		return sequence.nextLogid();
	}
	
	public static void registerSequences(Sequences sequence){
		SequenceHelper.sequence = sequence;
	}
}
