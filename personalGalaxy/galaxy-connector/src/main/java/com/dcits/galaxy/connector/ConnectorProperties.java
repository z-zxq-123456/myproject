package com.dcits.galaxy.connector;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Connector属性加载器.
 * 
 * @author xuecy
 * 
 */
public class ConnectorProperties {

	private static Properties ps = new Properties();

	static {
		ps = loadProperties();
	}

	/**
	 * 获取Connector相关属性
	 * 
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		return getProperty(key, null);
	}

	/**
	 * 获取Connector相关属性
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getProperty(String key, String defaultValue) {
		String value = ps.getProperty(key);
		if (value == null) {
			return defaultValue;
		} else {
			return value;
		}
	}

	private static Properties loadProperties() {
		InputStream inputStream = null;
		try {
			inputStream = ConnectorProperties.class
					.getResourceAsStream("/galaxy.connector.properties");
			if (inputStream == null) {
				throw new IllegalStateException(
						"[galaxy.connector.properties] not found, please put this file under your classpath root.");
			}

			Properties properties = new Properties();
			properties.load(inputStream);

			return properties;
		} catch (IOException e) {
			throw new IllegalStateException(
					"Error occured while reading [/galaxy.connector.properties].",
					e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ignore) {
				}
			}
		}
	}
}
