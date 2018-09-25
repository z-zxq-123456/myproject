package com.dcits.galaxy.dal.mybatis.exception;

import org.springframework.core.io.Resource;

/**
 * 解析文件异常
 * 
 * @author huang.zhe
 *
 */
public class ParseFileException extends DALException {

	private static final long serialVersionUID = -5189708592165668089L;

	public ParseFileException(Resource configLocation, Throwable e) {
		super("Parse " + configLocation.getDescription() + " has error!", e);
	}

	public ParseFileException(Resource configLocation) {
		super("Parse " + configLocation.getDescription() + " has error!");
	}

	public ParseFileException(String info) {
		super(info);
	}

	public ParseFileException() {
		super("Parse file has error!");
	}
}
