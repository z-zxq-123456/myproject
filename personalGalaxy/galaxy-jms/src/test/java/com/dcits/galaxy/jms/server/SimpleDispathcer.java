package com.dcits.galaxy.jms.server;

import java.util.concurrent.atomic.AtomicLong;

import javax.jms.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.dcits.galaxy.jms.JMSContext;

public class SimpleDispathcer extends AbstractDispathcer {
	
	private static final Logger logger = LoggerFactory.getLogger(SimpleDispathcer.class);

	public final AtomicLong counter = new AtomicLong(0);
	
	@Override
	protected Object doService(Object message) {
		logger.debug("receive properties: " + JSON.toJSONString(JMSContext.getContext().getProperties()));
		logger.debug("receive message " + JSON.toJSONString(message));
		return "第" + counter.incrementAndGet() + "次调用!";
	}
	
	@Override
	protected void onServiceException(Message message, boolean needReply, Exception e) {
		super.onServiceException(message, needReply, e);
		throw (RuntimeException)e;
	}

}
