package com.dcits.dubbo.remoting.transport.dispather;

import com.alibaba.dubbo.remoting.transport.dispather.WrappedChannelHandler;
import com.dcits.dubbo.LifeCycle;

public class WrappedChannelHandlerLifeCycle implements LifeCycle {

	@Override
	public void stop() {
		WrappedChannelHandler.reset();
	}
}
