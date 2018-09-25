package com.dcits.dubbo.remoting.transport;

import com.alibaba.dubbo.remoting.transport.AbstractClient;
import com.dcits.dubbo.LifeCycle;

public class AbstractClientLifeCycle implements LifeCycle {

	@Override
	public void stop() {
		AbstractClient.reset();
	}
}
