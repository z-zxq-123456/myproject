package com.dcits.galaxy.core.loggerx;

import java.util.Map;

import org.slf4j.MDC;

public class Slf4jRunnableWrapper implements Runnable {

    final private Map<String, String> mdc;

    private Runnable target = null;

    public Slf4jRunnableWrapper() {
    	mdc = MDC.getCopyOfContextMap();
    }

    @Override
    public void run() {
    	if(mdc != null){
    		MDC.setContextMap(mdc);
    	}
    	try {
    		target.run();
		} finally {
			if (mdc != null) {
				MDC.clear();
			}
		}
    }

    public void setTarget(Runnable target) {
        this.target = target;
    }

}
