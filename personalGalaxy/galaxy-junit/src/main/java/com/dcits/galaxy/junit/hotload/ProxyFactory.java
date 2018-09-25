package com.dcits.galaxy.junit.hotload;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.target.HotSwappableTargetSource;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import com.dcits.galaxy.junit.hotload.fileload.ConfigFileLoad;


public class ProxyFactory<T> implements FactoryBean<T>, InitializingBean {

	private volatile T proxyObject;
	
	private T target;
	
	private HotSwappableTargetSource targetSorce =null;

	private String loadInterface;

	private Resource[] resources;

	private int initTime = 10;

	private int reflushTime = 20;

	private Object objbean;

	private ConfigFileLoad updateOption;

	private Map<String, Long> map = new HashMap<>();

	ScheduledExecutorService executorService = null;
	
	protected static final Logger logger =  LoggerFactory.getLogger(ProxyFactory.class); 

	public void setInitTime(int initTime) {
		this.initTime = initTime;
	}

	public void setReflushTime(int reflushTime) {
		this.reflushTime = reflushTime;
	}

	public void setProxyObject(T proxyObject) {
		this.proxyObject = proxyObject;
	}

	public void setLoadInterface(String loadInterface) {
		this.loadInterface = loadInterface;
	}

	public void setResources(Resource[] resources) {
		this.resources = resources;
	}

	public void setObjbean(Object objbean) {
		this.objbean = objbean;
	}

	public void setUpdateOption(ConfigFileLoad updateOption) {
		this.updateOption = updateOption;
	}


	@Override
	public T getObject() throws Exception {
		return target;
	}

	@Override
	public Class<?> getObjectType() {
		return this.proxyObject == null ? null : this.proxyObject.getClass();
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	private void initThread() {
		executorService = Executors.newSingleThreadScheduledExecutor();
		logger.debug("File Scanner was began! ");
		start();
	}

	private void start() {
		executorService.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				if (isReLoad()) {
					logger.debug("Some files has been update ! Those will be hot loadding!");
					Object obj = fileLoadding();
					targetSorce.swap(obj);
				}
			}
		}, initTime, reflushTime, TimeUnit.SECONDS);
	}

	private boolean isReLoad() {
		String fileName = null;
		long beforeTime = 0L;
		for (Resource resource : resources) {
			try {
				fileName = resource.getFile().getName();
				beforeTime = resource.getFile().lastModified();
				System.out.println(resource.getFile().getPath());
				if (map.get(fileName) == null) {
					map.put(fileName, beforeTime);
					return true;
				} else if (map.get(fileName) != beforeTime) {
					map.put(fileName, beforeTime);
					return true;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	private Object fileLoadding() {
		Object object = updateOption.updateXmlBuilder(objbean);
		return object;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.debug("loadding update files begin!");
		targetSorce = new HotSwappableTargetSource(proxyObject);
		Class<T> clazz = (Class<T>) Class.forName(loadInterface);
		initThread();
		target = org.springframework.aop.framework.ProxyFactory.getProxy(clazz, targetSorce);
		if(logger.isDebugEnabled()){
			logger.debug("loadding update files end! The target is:["+target.toString()+"]");
		}
		
	}

}
