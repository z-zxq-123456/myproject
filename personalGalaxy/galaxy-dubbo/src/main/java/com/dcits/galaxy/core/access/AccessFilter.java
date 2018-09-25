package com.dcits.galaxy.core.access;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.RpcInvocation;
import com.dcits.galaxy.base.util.SeqUtils;

/**
 * 交易唯一标识过滤器，支持分布式。
 * 
 * @author xuecy
 *
 */
@Activate(group = { Constants.PROVIDER, Constants.CONSUMER }, order = Integer.MIN_VALUE + 1)
public class AccessFilter implements Filter {

	private static final Logger logger = LoggerFactory
			.getLogger(AccessFilter.class);

	public static final String KEY_NAME = "GalaxyTranUID";

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation)
			throws RpcException {
		// 忽略injvm协议
		if (Constants.LOCAL_PROTOCOL.equals(invoker.getUrl().getProtocol())) {
			return invoker.invoke(invocation);
		}
		// 忽略日志分析内部使用的服务
		if (invoker.getInterface().getName().indexOf("com.dcits.galaxy.trace") != -1) {
			return invoker.invoke(invocation);
		}
		
		String uid = null;
		String side = invoker.getUrl().getParameter(Constants.SIDE_KEY);
		if (Constants.PROVIDER_SIDE.equals(side)) {
			// provider
			uid = getAttachment(invocation);
			uid = getUID(uid);
			ThreadLocalManager.setUID(uid);
			setAttachment(invocation, uid);
		} else if (Constants.CONSUMER_SIDE.equals(side)) {
			// consumer
			uid = ThreadLocalManager.getUID();
			uid = getUID(uid);
			setAttachment(invocation, uid);
		}

		try {
			return invoker.invoke(invocation);
		} catch (Exception e) {
			throw e;
		} finally {
			// 提供者清除线程信息，防止线程重用时的误操作
			if (Constants.PROVIDER_SIDE.equals(side)) {
				ThreadLocalManager.remove();
			}
		}
	}

	/**
	 * 获取UID，如果传入的为空则生成。
	 * 
	 * @param uid
	 * @return
	 */
	private String getUID(String uid) {
		if (uid == null) {
			uid = SeqUtils.getStringSeq();
			logger.info("Create a unique transaction identifier[" + uid	+ "]...");
		}
		return uid;
	}

	/**
	 * 设置RPC传递的参数
	 * 
	 * @param invocation
	 * @param uid
	 */
	private void setAttachment(Invocation invocation, String uid) {
		((RpcInvocation) invocation).setAttachment(KEY_NAME, uid);
	}

	/**
	 * 获取PRC传递的参数
	 * 
	 * @param invocation
	 * @return
	 */
	private String getAttachment(Invocation invocation) {
		return invocation.getAttachment(KEY_NAME);
	}

}
