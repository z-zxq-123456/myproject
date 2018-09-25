package com.dcits.galaxy.logcover.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 调用链开始批量插入线程* 
 * @author tangxlf
 * @date 2016-10-21 
 */
public class LogLock {
	 public static ReentrantLock chainStartLock = new ReentrantLock();//调用链开始批量插入线程锁
     public static Condition chainStartCon = chainStartLock.newCondition();//调用链开始批量插入线程锁条件
     public static ReentrantLock chainBusLock = new ReentrantLock();//调用链业务字段批量插入线程锁
     public static Condition chainBusCon = chainBusLock.newCondition();//调用链业务字段批量插入线程锁条件
     public static ReentrantLock chainEndLock = new ReentrantLock();//调用链结束批量插入线程锁
     public static Condition chainEndCon = chainEndLock.newCondition();//调用链结束批量插入线程锁条件
     public static ReentrantLock tceCsLock = new ReentrantLock();//调用环客户端开始批量插入线程锁
     public static Condition tceCsCon = tceCsLock.newCondition();//调用环客户端开始批量插入线程锁条件
     public static ReentrantLock tceSrLock = new ReentrantLock();//调用环服务端开始批量插入线程锁
     public static Condition tceSrCon = tceSrLock.newCondition();//调用环服务端开始批量插入线程锁条件
     public static ReentrantLock tceSsLock = new ReentrantLock();//调用环服务端结束批量插入线程锁
     public static Condition tceSsCon = tceSsLock.newCondition();//调用环服务端结束批量插入线程锁条件
     public static ReentrantLock tceCrLock = new ReentrantLock();//调用环客户端结束批量插入线程锁
     public static Condition tceCrCon = tceCrLock.newCondition();//调用环客户端结束批量插入线程锁条件
     public static ReentrantLock annotLock = new ReentrantLock();//日志标注批量插入线程锁
     public static Condition annotCon = annotLock.newCondition();//日志标注批量插入线程锁条件
}
