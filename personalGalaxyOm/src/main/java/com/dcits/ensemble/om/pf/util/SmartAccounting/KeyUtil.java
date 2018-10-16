package com.dcits.ensemble.om.pf.util.SmartAccounting;

/**
 * 缓存key工具类
 * @author wangyqm
 *
 */
public class KeyUtil {

	// 公共级缓存前缀
	private static String PREFIX_GLOBAL = "G_";
	// 业务级缓存前缀
	private static String PREFIX_BUSINESS = "B_";
	// 表级缓存前缀
	private static String PREFIX_TABLE = "T_";

	/**
	 * 公共级缓存key构建
	 * @param name
	 * @param keys
	 * @return String
	 */
	public static String buildGlobalKey(String name, String... keys) {
		StringBuilder builder = new StringBuilder();
		builder.append(PREFIX_GLOBAL).append(name);
		for (String key : keys) {
			builder.append("_");
			builder.append(key);
		}
		return builder.toString();
	}

	/**
	 * 业务级缓存key构建
	 * @param keys
	 * @return String
	 */
	public static String buildBusinessKey(String name, String... keys) {
		StringBuilder builder = new StringBuilder();
		builder.append(PREFIX_BUSINESS).append(name);
		for (String key : keys) {
			builder.append("_");
			builder.append(key);
		}
		return builder.toString();
	}

	/**
	 * 表级缓存key构建
	 * @param keys
	 * @return String
	 */
	public static String buildTableKey(String name, String... keys) {
		StringBuilder builder = new StringBuilder();
		builder.append(PREFIX_TABLE).append(name);
		for (String key : keys) {
			builder.append("_");
			builder.append(key);
		}
		return builder.toString();
	}
}
