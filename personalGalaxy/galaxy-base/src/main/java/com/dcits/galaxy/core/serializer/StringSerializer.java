package com.dcits.galaxy.core.serializer;

import java.nio.charset.Charset;

/**
 * 对String的简单实现
 * 
 * @author xuecy
 * 
 */
public class StringSerializer implements Serializer<String> {
	
	public static final String CHARSET = "UTF-8";

	private final Charset charset;

	public StringSerializer() {
		this(Charset.forName(CHARSET));
	}

	public StringSerializer(Charset charset) {
		this.charset = charset;
	}

	@Override
	public String deserialize(byte[] bytes) {
		return (bytes == null ? null : new String(bytes, charset));
	}

	@Override
	public byte[] serialize(String string) {
		return (string == null ? null : string.getBytes(charset));
	}
}
