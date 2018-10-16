package com.dcits.galaxy.core.dubbox.rpc.cluster.limitover;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.Version;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.utils.NetUtils;
import com.alibaba.dubbo.rpc.*;
import com.alibaba.dubbo.rpc.cluster.Directory;
import com.alibaba.dubbo.rpc.cluster.LoadBalance;
import com.alibaba.dubbo.rpc.cluster.support.AbstractClusterInvoker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LimitoverClusterInvoker<T> extends AbstractClusterInvoker<T> {

	private static final Logger logger = LoggerFactory.getLogger(LimitoverClusterInvoker.class);

    public LimitoverClusterInvoker(Directory<T> directory) {
        super(directory);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Result doInvoke(Invocation invocation, final List<Invoker<T>> invokers, LoadBalance loadbalance) throws RpcException {
    	List<Invoker<T>> copyinvokers = invokers;
    	checkInvokers(copyinvokers, invocation);
    	
        int len = copyinvokers.size();
        
        // retry loop.
        RpcException le = null; // last exception.
        List<Invoker<T>> invoked = new ArrayList<Invoker<T>>(copyinvokers.size()); // invoked invokers.
        Set<String> providers = new HashSet<String>(len);
        for (int i = 0; i < len; i++) {
        	//重试时，进行重新选择，避免重试时invoker列表已发生变化.
        	//注意：如果列表发生了变化，那么invoked判断会失效，因为invoker示例已经改变
        	if (i > 0) {
        		checkWheatherDestoried();
        		copyinvokers = list(invocation);
        		//重新检查一下
        		checkInvokers(copyinvokers, invocation);
        	}
        	Invoker<T> invoker = select(loadbalance, invocation, copyinvokers, invoked);
        	invoked.add(invoker);
            RpcContext.getContext().setInvokers((List)invoked);
            
            try {
            	URL url = invoker.getUrl();
        		boolean limited = LimitoverSemaphore.tryAcquire(url);
                if ( !limited ) {
                    continue;
                }
                try {
                	Result result = invoker.invoke(invocation);
                	if (le != null && logger.isWarnEnabled()) {
                		logger.warn("Although retry the method " + invocation.getMethodName()
                				+ " in the service " + getInterface().getName()
                				+ " was successful by the provider " + invoker.getUrl().getAddress()
                				+ ", but there have been failed providers " + providers 
                				+ " (" + providers.size() + "/" + copyinvokers.size()
                				+ ") from the registry " + directory.getUrl().getAddress()
                				+ " on the consumer " + NetUtils.getLocalHost()
                				+ " using the dubbo version " + Version.getVersion() + ". Last error is: "
                				+ le.getMessage(), le);
                	}
                	return result;
                } finally {
                	LimitoverSemaphore.release(url);
                }
        		
            } catch (RpcException e) {
                if (e.isBiz()) { // biz exception.
                    throw e;
                }
                le = e;
            } catch (Throwable e) {
                le = new RpcException(e.getMessage(), e);
            } finally {
                providers.add(invoker.getUrl().getAddress());
            }
        }
        throw new RpcException(le != null ? le.getCode() : 20, "Failed to invoke the method "
                + invocation.getMethodName() + " in the service " + getInterface().getName() 
                + ". Tried " + len + " times of the providers " + providers 
                + " (" + providers.size() + "/" + copyinvokers.size() 
                + " ) from the registry " + directory.getUrl().getAddress()
                + " on the consumer " + NetUtils.getLocalHost() + " using the dubbo version "
                + Version.getVersion() + ". Last error is: "
                + (le != null ? le.getMessage() : ""), le != null && le.getCause() != null ? le.getCause() : le);
    }
}