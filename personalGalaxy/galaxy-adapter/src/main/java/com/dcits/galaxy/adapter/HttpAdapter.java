/**
 * Title: Galaxy(Distributed service platform)
 * File: HttpAdapter.java
 * Copyright: Copyright (c) 2014-2016
 * Company: 神州数码融信软件有限公司
 */
package com.dcits.galaxy.adapter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.adapter.exception.AfterSendException;
import com.dcits.galaxy.adapter.exception.BeforeSendException;
import com.dcits.galaxy.base.exception.GalaxyException;

/**
 * 基于Http协议的通讯适配器实现
 * <p>
 * Created on 2015/10/21.
 * </p>
 * @author huangzhec
 * @see com.dcits.galaxy.adapter.Adapter
 * @since 1.7
 */
public class HttpAdapter implements Adapter<String> {

	private static final Logger log = LoggerFactory.getLogger(HttpAdapter.class);

	/**
	 * 服务器Url
	 */
	protected String url;

	/**
	 * 读取超时事件，默认30秒
	 */
	protected int readTimeout = 30 * 1000;

	/**
	 * 字符集，默认UTF-8
	 */
	protected String encoding = "UTF-8";

	/**
	 * 
	 */
	protected ContentType contentType = ContentType.APPLICATION_JSON;

	/**
	 * 请求转换
	 * @param req 请求对象
	 * @return 转换后的字节流
	 */
	protected byte[] requestConvert(String req) {
		try {
			return req.getBytes(encoding);
		} catch (UnsupportedEncodingException e) {
			throw new BeforeSendException("无效的字符集");
		}
	}

	/**
	 * @param req 请求字符串
	 * @return 响应字符串
	 * @exception <p>
	 * BeforeSendException 发送前异常
	 * </p>
	 * <p>
	 * AfterSendException 发送后异常
	 * </p>
	 * @see com.dcits.galaxy.adapter.exception.BeforeSendException
	 * @see com.dcits.galaxy.adapter.exception.AfterSendException
	 */
	@Override
	public String execute(String req) {
		String out = null;
		ByteArrayInputStream bais = null;
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse rs = null;
		if (log.isDebugEnabled())
			log.debug("通讯接出：" + url);
		try {
			httpClient = HttpClients.createMinimal(new BasicHttpClientConnectionManager());
			HttpPost pm = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(readTimeout).build();
			pm.setConfig(requestConfig);
			InputStreamEntity entity = null;
			if (req != null) {
				bais = new ByteArrayInputStream(requestConvert(req));
				entity = new InputStreamEntity(bais, contentType);
			}
			pm.setEntity(entity);
		    rs = httpClient.execute(pm);
			int status = rs.getStatusLine().getStatusCode();
			if (status != 200) {
				throw new AfterSendException("服务端执行出错：" + status);
			} else {
				HttpEntity httpEntity = rs.getEntity();
				if (httpEntity != null) {
					out = EntityUtils.toString(httpEntity, encoding);
				}
			}
		} catch (ConnectException e) {
			throw new BeforeSendException("[" + url + "]建立连接出错，服务未启动");
		} catch (SocketTimeoutException e) {
			throw new AfterSendException("获取响应超时");
		} catch (GalaxyException e) {
			throw e;
		} catch (Throwable t) {
			throw new RuntimeException(t);
		} finally {
			if (null != bais){
				try {
					bais.close();
				} catch (IOException e) {
					if (log.isWarnEnabled()) {
						log.warn("输入流关闭失败： {}", e.getMessage());
					}
				}
			}
			if (null != rs){
				try {
					rs.close();
				} catch (IOException e) {
					if (log.isWarnEnabled()) {
						log.warn("HttpResponse关闭失败： {}", e.getMessage());
					}
				}
			}
			if (null != httpClient) {
				try {
					httpClient.close();
				} catch (IOException e) {
					if (log.isWarnEnabled()) {
						log.warn("HttpClient关闭失败： {}", e.getMessage());
					}
				}
			}
		}
		return out;
	}

	/**
	 * 获取访问地址
	 * @return 访问的Http服务器请求地址
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置访问地址
	 * @param url Http服务器请求地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取读取超时时间
	 * @return 读取超时时间
	 */
	public int getReadTimeout() {
		return readTimeout;
	}

	/**
	 * 设置读取超时时间
	 * @param readTimeout 读取超时时间
	 */
	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

	/**
	 * 获取字符集
	 * @return 字符集
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * 设置字符集
	 * @param encoding 字符集
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public ContentType getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = ContentType.parse(contentType);
	}

	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}

}
