package com.dcits.galaxy.dtp.defaults.log;


import java.io.Serializable;

import com.dcits.galaxy.core.serializer.SerializationUtils;
import com.dcits.galaxy.dtp.log.PrepareLog;

/**
 * 事务准备日志实现
 * @author Yin.Weicai
 */
public class DefaultPrepareLog extends PrepareLog  {
	public static final String LogTypeName = "PrapareLog_Default";
	
	@Override
	public String getLogTypeName() {
		return LogTypeName;
	}
		
	public void setContentByte(byte[] bytes) {
		Serializable object = (Serializable) SerializationUtils.deserializeObj(bytes);
		this.setContent(object);
	}

	public byte[] getContentByte() {
		return SerializationUtils.serializeObj(this.getContent());
	}
}
