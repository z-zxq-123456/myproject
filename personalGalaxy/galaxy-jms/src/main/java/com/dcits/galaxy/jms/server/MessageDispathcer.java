package com.dcits.galaxy.jms.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.base.MessageDistributor;
import com.dcits.galaxy.core.spring.SpringApplicationContext;

public class MessageDispathcer extends AbstractDispathcer {

	private static final Logger logger = LoggerFactory.getLogger(MessageDispathcer.class);

	/**
	 * 应用组
	 */
	private String appGroup;

	/**
	 * 报文格式
	 */
	private String msgFormat;

	/**
	 * 报文处理解析器
	 */
	private String msgParser;

	public String getAppGroup() {
		return appGroup;
	}

	public void setAppGroup(String appGroup) {
		this.appGroup = appGroup;
	}

	public String getMsgFormat() {
		return msgFormat;
	}

	public void setMsgFormat(String msgFormat) {
		this.msgFormat = msgFormat;
	}

	public String getMsgParser() {
		return msgParser;
	}

	public void setMsgParser(String msgParser) {
		this.msgParser = msgParser;
	}

	@Override
	protected Object doService(Object message) {
		String inMsg = (String) message;
		long start = System.currentTimeMillis();
		// 获取报文
		String outMsg = null;

		if (logger.isDebugEnabled()) {
			logger.debug("Request message is...\n" + inMsg);
		}

		MessageDistributor distributor = SpringApplicationContext.getContext().getBean(MessageDistributor.class);
		try {
			// 设置PlatFormId
			distributor.setPlatFormId();
			outMsg = distributor.execute(inMsg, appGroup, msgFormat, msgParser);

			if (outMsg == null) {
				String errMsg = "Response message is null.";
				if (logger.isErrorEnabled()) {
					logger.error(errMsg);
				}
			}
			if (logger.isDebugEnabled()) {
				logger.debug("Response message is...\n" + outMsg);
			}
		} finally {
			if (logger.isInfoEnabled()) {
				logger.info("Service execute time is : " + (System.currentTimeMillis() - start));
			}
			distributor.removePlatFormId();
		}
		return outMsg;
	}
}
