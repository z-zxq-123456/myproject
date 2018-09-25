package com.dcits.dubbo.config;

import com.alibaba.dubbo.config.ReferenceConfig;
import com.dcits.dubbo.LifeCycle;

public class ReferenceConfigLifeCycle implements LifeCycle {

	public void stop() {
		ReferenceConfig.reset();
	}

}
