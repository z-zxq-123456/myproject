package com.dcits.dubbo.common.extension;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.dcits.dubbo.LifeCycle;

/**
 * 在容器中部署dubbo时, 方便应用释放ExtensionLoader.cachedInstances
 * @author yin.weicai
 */
public class ExtensionLoaderLifeCycle implements LifeCycle {

	public void stop() {
		ExtensionLoader.reset();
	}

}
