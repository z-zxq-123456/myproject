package com.dcits.dubbo.remoting.exchange.support;

import com.alibaba.dubbo.remoting.exchange.support.DefaultFuture;
import com.dcits.dubbo.LifeCycle;

/**
 * 在容器中部署dubbo时, 方便应用释放DefaultFuture在静态块中开启的线程
 * @author yin.weicai
 */
public class DefaultFutureLifeCycle implements LifeCycle {

	@Override
	public void stop() {
		DefaultFuture.reset();
	}

}
