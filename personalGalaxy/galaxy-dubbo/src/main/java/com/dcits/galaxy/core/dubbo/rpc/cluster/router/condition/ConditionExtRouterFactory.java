package com.dcits.galaxy.core.dubbo.rpc.cluster.router.condition;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.cluster.Router;
import com.alibaba.dubbo.rpc.cluster.RouterFactory;

public class ConditionExtRouterFactory implements RouterFactory {
	
	public static final String NAME = "conditionext";

	@Override
	public Router getRouter(URL url) {
		// TODO Auto-generated method stub
		return new ConditionExtRouter(url);
	}

}
