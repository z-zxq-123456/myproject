package com.dcits.dubbo.remoting.exchange.support.header;

import com.alibaba.dubbo.remoting.exchange.support.header.HeaderExchangeClient;
import com.dcits.dubbo.LifeCycle;

/**
 * 在容器中部署dubbo时, 方便应用释放HeaderExchangeClient.scheduled
 * @author yin.weicai
 */
public class HeaderExchangeClientLifeCycle implements LifeCycle {

	public void stop() {
		HeaderExchangeClient.reset();
	}

}
