package com.dcits.galaxy.core.serializer;

/**
 * 序列化处理工具类
 * 
 * @author xuecy
 * 
 */
public class SerializationUtils {

	private static final Serializer<String> stringSerializer = new StringSerializer();

	private static final Serializer<Object> serializer = new HessianSerializer();

	/**
	 * String序列化
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] serialize(final String str) {
		if (str == null) {
			throw new SerializationException(
					"value sent to redis cannot be null");
		}
		return stringSerializer.serialize(str);
	}

	/**
	 * String反序列化
	 * 
	 * @param data
	 * @return
	 */
	public static String deserialize(final byte[] data) {
		return stringSerializer.deserialize(data);
	}

	/**
	 * 对象序列化
	 * 
	 * @param obj
	 * @return
	 */
	public static byte[] serializeObj(final Object obj) {
		return serializer.serialize(obj);
	}

	/**
	 * 对象反序列化
	 * 
	 * @param data
	 * @return
	 */
	public static Object deserializeObj(final byte[] data) {
		return serializer.deserialize(data);
	}
}
