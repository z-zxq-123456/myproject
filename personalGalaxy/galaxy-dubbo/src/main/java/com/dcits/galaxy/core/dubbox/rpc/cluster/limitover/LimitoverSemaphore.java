package com.dcits.galaxy.core.dubbox.rpc.cluster.limitover;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Semaphore;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.utils.ConfigUtils;

public class LimitoverSemaphore {
	
	private static final ConcurrentMap<String, Semaphore> SERVICE_STATISTICS = new ConcurrentHashMap<>();
	
	public final static String LimitoverSize = "galaxy.cluster.limitover.size";
	
	/**
     * 
     * @param url
     * @return status
     */
    public static Semaphore getSemaphore(URL url) {
        String uri = url.toIdentityString();
        Semaphore status = SERVICE_STATISTICS.get(uri);
        if (status == null) {
        	newSemaphore(uri);
            status = SERVICE_STATISTICS.get(uri);
        }
        return status;
    }
    
    private static synchronized void newSemaphore(String uri){
    	Semaphore status = SERVICE_STATISTICS.get(uri);
        if (status == null) {
        	String limit = ConfigUtils.getProperty( LimitoverSize );
            int size = 0;
    		try {
    			size = Integer.parseInt( limit );
    		} catch (NumberFormatException e) {
    			size = 1;
    		}
    		Semaphore semaphore = new Semaphore(size,true);
        	SERVICE_STATISTICS.putIfAbsent(uri, semaphore );
        }
    }
    
    /**
     * 
     * @param url
     */
    public static void removeSemaphore(URL url) {
        String uri = url.toIdentityString();
        SERVICE_STATISTICS.remove(uri);
    }
    
    /**
     * 
     * @param url
     */
    public static boolean tryAcquire(URL url) {
        Semaphore semaphore = getSemaphore(url);
        return semaphore.tryAcquire();
    }

    /**
     * 
     * @param url
     * @param elapsed
     * @param succeeded
     */
    public static void release(URL url) {
    	 Semaphore semaphore = getSemaphore(url);
         semaphore.release();
    }
}
