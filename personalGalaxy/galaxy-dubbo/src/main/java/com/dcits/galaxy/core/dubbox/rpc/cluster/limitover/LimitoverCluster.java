package com.dcits.galaxy.core.dubbox.rpc.cluster.limitover;

import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.cluster.Cluster;
import com.alibaba.dubbo.rpc.cluster.Directory;

public class LimitoverCluster implements Cluster {

	@Override
	public <T> Invoker<T> join(Directory<T> directory) throws RpcException {
		return new LimitoverClusterInvoker<T>(directory);
	}

}
