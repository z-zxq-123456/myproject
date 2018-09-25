package com.dcits.galaxy.dtp.resume;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 可配置线程名的线程工厂
 * @author Yin.Weicai
 *
 */
public class NamedThreadFactory implements ThreadFactory {
	
	private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;
    
    /**
     * 线程名前缀
     */
    private String prefix = "pool-";
    
    /**
     * 线程名后缀
     */
    private String subfix = "-thread-";
    
    public NamedThreadFactory() {
    	this(null, null);
    }
    
    public NamedThreadFactory(String prefix) {
    	this(prefix, null);
    }

    public NamedThreadFactory(String prefix, String subfix) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
                              Thread.currentThread().getThreadGroup();
        
        if( prefix != null && !"".equals(prefix)){
        	this.prefix = prefix;
        }
        if( subfix != null && !"".equals(subfix)){
        	this.subfix = subfix;
        }
        
        namePrefix = this.prefix + poolNumber.getAndIncrement() + this.subfix;
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r,
                              namePrefix + threadNumber.getAndIncrement(),
                              0);
        if (t.isDaemon())
            t.setDaemon(false);
        if (t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }
    
    

}
