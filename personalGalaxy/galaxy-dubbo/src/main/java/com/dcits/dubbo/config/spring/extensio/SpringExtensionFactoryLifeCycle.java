package com.dcits.dubbo.config.spring.extensio;

import com.alibaba.dubbo.config.spring.extension.SpringExtensionFactory;
import com.dcits.dubbo.LifeCycle;

/**
 * 在容器中部署dubbo时, 方便应用释放SpringExtensionFactory.contexts
 * @author yin.weicai
 */
public class SpringExtensionFactoryLifeCycle implements LifeCycle {

	@Override
	public void stop() {
		SpringExtensionFactory.reset();
	}

}
