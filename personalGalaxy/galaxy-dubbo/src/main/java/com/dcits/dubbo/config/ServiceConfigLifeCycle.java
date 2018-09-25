package com.dcits.dubbo.config;

import com.alibaba.dubbo.config.ServiceConfig;
import com.dcits.dubbo.LifeCycle;

public class ServiceConfigLifeCycle implements LifeCycle {

	public void stop() {
		ServiceConfig.reset();
	}

}
