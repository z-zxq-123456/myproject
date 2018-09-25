package com.dcits.galaxy.cache.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.ibatis.cache.CacheException;

import com.dcits.galaxy.core.serializer.SerializationUtils;

public abstract class RedisUtils {
	
	private RedisUtils(){}
	
	public static byte[] getByteKey(String str) {
		return SerializationUtils.serialize(str);
	}

	public static byte[][] getByteKeys(String... str) {
		byte[][] byteskeys = new byte[str.length][];
		for (int i = 0; i < str.length; i++) {
			byteskeys[i] = getByteKey(str[i]);
		}
		return byteskeys;
	}

	public static byte[] getByteValue(Object obj) {
		return SerializationUtils.serializeObj(obj);
	}

	public static byte[][] getByteValues(Object... obj) {
		byte[][] bytesValues = new byte[obj.length][];
		for (int i = 0; i < obj.length; i++) {
			bytesValues[i] = getByteValue(obj[i]);
		}
		return bytesValues;
	}

	public static Map<byte[], byte[]> getByteValueMap(Map<String, ?> map) {
		Map<byte[], byte[]> byteValueMap = new HashMap<>();
		for (Entry<String, ?> entry : map.entrySet()) {
			byte[] key = getByteKey(entry.getKey());
			byte[] value = getByteValue(entry.getValue());
			byteValueMap.put(key, value);
		}
		return byteValueMap;
	}

	public static Object handleResult(byte[] binaryResult) {
		if(binaryResult == null)
			return null;
		try {
			return SerializationUtils.deserializeObj(binaryResult);
		} catch (Exception e) {
			try {
				return SerializationUtils.deserialize(binaryResult);
			} catch (Exception t) {
				throw new CacheException("can't deserialize bytes for:" + binaryResult);
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List handleResult(List<byte[]> binaryResult) {
		if(binaryResult == null)
			return null;
		List results = new ArrayList();
		for (byte[] bytes : binaryResult) {
			results.add(handleResult(bytes));
		}
		return results;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Collection handleResult(Collection<byte[]> binaryResult) {
		if(binaryResult == null)
			return null;
		List results = new ArrayList();
		for (byte[] bytes : binaryResult) {
			results.add(handleResult(bytes));
		}
		return results;
	}

	public static Set<String> handleResultString(Set<byte[]> binaryResult) {
		if(binaryResult == null)
			return null;
		Set<String> results = new HashSet<>();
		for (byte[] bytes : binaryResult) {
			results.add(SerializationUtils.deserialize(bytes));
		}
		return results;
	}
	
	@SuppressWarnings("rawtypes")
	public static Set handleResult(Set<byte[]> binaryResult) {
		if(binaryResult == null)
			return null;
		Set<Object> results = new HashSet<>();
		for (byte[] bytes : binaryResult) {
			results.add(handleResult(bytes));
		}
		return results;
	}

	public static <T> T handleResult(byte[] binaryResult, Class<T> clazz) {
		if(binaryResult == null)
			return null;
		Object obj = handleResult(binaryResult);
		return safeConvert(obj, clazz);
	}

	public static<K,V> Map<K, V> handleResult(Map<byte[], byte[]> binaryResult,Class<K> keyType,Class<V> valueType) {
		if(binaryResult == null)
			return null;
		Map<K, V> map = new HashMap<>();
		for (Map.Entry<byte[], byte[]> entry : binaryResult.entrySet()) {
			K key = null;
			if(keyType.equals(String.class))
				key = safeConvert(SerializationUtils.deserialize(entry.getKey()),keyType);
			else
				key = safeConvert(handleResult(entry.getKey()),keyType);
			
			if(key == null)
				continue;
			
			V value = safeConvert(handleResult(entry.getValue()),valueType);
			map.put(key, value);
		}
		return map;
	}
	
	public static<K,V> Map<K, V> handleResult(Map<byte[], V> binaryResult,Class<K> keyType) {
		if(binaryResult == null)
			return null;
		Map<K, V> map = new HashMap<>();
		for (Map.Entry<byte[], V> entry : binaryResult.entrySet()) {
			K key = null;
			if(keyType.equals(String.class))
				key = safeConvert(SerializationUtils.deserialize(entry.getKey()),keyType);
			else
				key = safeConvert(handleResult(entry.getKey()),keyType);
			
			if(key == null)
				continue;
			
			V value = entry.getValue();
			map.put(key, value);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public static <T> T safeConvert(Object obj, Class<T> clazz) {
		if (clazz.isInstance(obj))
			return (T) obj;
		return null;
	}
}
