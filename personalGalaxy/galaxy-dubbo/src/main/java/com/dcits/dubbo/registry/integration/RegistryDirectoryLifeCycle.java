package com.dcits.dubbo.registry.integration;

import com.alibaba.dubbo.registry.integration.RegistryDirectory;
import com.dcits.dubbo.LifeCycle;

public class RegistryDirectoryLifeCycle implements LifeCycle {

	@Override
	public void stop() {
		RegistryDirectory.reset();
	}

}
