package com.dcits.galaxy.dtp.defaults.log;

import java.io.Serializable;

import com.dcits.galaxy.core.serializer.SerializationUtils;
import com.dcits.galaxy.dtp.log.SubmitLog;

/**
 * 提交事务日志实现
 * @author Yin.Weicai
 *
 */
public final class DefaultSubmitLog extends SubmitLog {
	
	public static final String LogTypeName = "SubmitLog_ServiceInfo";
	
	@Override
	public String getLogTypeName() {
		return LogTypeName;
	}
	
	public ServiceInfo getServiceInfo() {
		return (ServiceInfo)getContent();
	}

	public void setServiceInfo(ServiceInfo serviceInfo) {
		setContent(serviceInfo);
	}
	
	public void setContentByte(byte[] bytes) {
		Serializable object = (Serializable) SerializationUtils.deserializeObj(bytes);
		this.setContent(object);
	}

	public byte[] getContentByte() {
		return SerializationUtils.serializeObj(this.getContent());
	}
}
