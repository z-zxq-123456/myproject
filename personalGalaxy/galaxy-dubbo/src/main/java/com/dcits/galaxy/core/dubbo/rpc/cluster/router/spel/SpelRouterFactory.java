package com.dcits.galaxy.core.dubbo.rpc.cluster.router.spel;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.cluster.Router;
import com.alibaba.dubbo.rpc.cluster.RouterFactory;

public class SpelRouterFactory implements RouterFactory {
	
	public static final String NAME = "spel";

	@Override
	public Router getRouter(URL url) {
		// TODO Auto-generated method stub
		return new SpelRouter(url);
	}

}
