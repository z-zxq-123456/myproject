package com.dcits.galaxy.connector.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 默认解析器实现
 * 
 * @author xuecy
 * 
 */
public class DefaultMessageParser implements MessageParser {

	public String read(InputStream in) throws IOException {
		int lenTotal = 10;
		byte[] bufferSum = new byte[lenTotal];
		in.read(bufferSum, 0, lenTotal);

		int len = Integer.parseInt(new String(bufferSum));
		byte[] buffer = new byte[len];
		in.read(buffer, 0, len);

		return new String(buffer);
	}

	public void write(OutputStream out, String msg) throws IOException {
		byte[] b = msg.getBytes();
		String ret = String.format("%010d",
				new Object[] { Integer.valueOf(b.length) })
				+ msg;
		out.write(ret.getBytes());
	}
}
