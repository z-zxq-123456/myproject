package com.dcits.dubbo;

import com.alibaba.dubbo.remoting.transport.netty.NettyClient;
import com.dcits.dubbo.common.extension.ExtensionLoaderLifeCycle;
import com.dcits.dubbo.config.ReferenceConfigLifeCycle;
import com.dcits.dubbo.config.ServiceConfigLifeCycle;
import com.dcits.dubbo.config.spring.extensio.SpringExtensionFactoryLifeCycle;
import com.dcits.dubbo.registry.integration.RegistryDirectoryLifeCycle;
import com.dcits.dubbo.remoting.exchange.support.DefaultFutureLifeCycle;
import com.dcits.dubbo.remoting.exchange.support.header.HeaderExchangeClientLifeCycle;
import com.dcits.dubbo.remoting.transport.AbstractClientLifeCycle;
import com.dcits.dubbo.remoting.transport.dispather.WrappedChannelHandlerLifeCycle;

/**
 * 在容器中部署dubbo时, 应用停止时释放dubbo资源的工具类
 * @author yin.weicai
 */
public class LifeCycleUtils {

	/**
	 * 调用LifetCyle接口的所有 实现类，完成资源释放
	 */
	public static void release(){
		
		LifeCycle lifeCycle = new HeaderExchangeClientLifeCycle();
		lifeCycle.stop();
		
		lifeCycle = new ReferenceConfigLifeCycle();
		lifeCycle.stop();
		
		lifeCycle = new ServiceConfigLifeCycle();
		lifeCycle.stop();
		
		lifeCycle = new ExtensionLoaderLifeCycle();
		lifeCycle.stop();
		
		lifeCycle = new DefaultFutureLifeCycle();
		lifeCycle.stop();
		
		lifeCycle = new AbstractClientLifeCycle();
		lifeCycle.stop();
		
		lifeCycle = new RegistryDirectoryLifeCycle();
		lifeCycle.stop();
		
		lifeCycle = new WrappedChannelHandlerLifeCycle();
		lifeCycle.stop();
		
		lifeCycle = new SpringExtensionFactoryLifeCycle();
		lifeCycle.stop();
		
		NettyClient.realse();
		
	}
}
