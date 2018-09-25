package com.dcits.galaxy.dal.mybatis.proactor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.dal.mybatis.utils.MetaUtils;

public class ProactorUtil {

	private final static Logger logger = LoggerFactory.getLogger(ProactorUtil.class);
	
	public static Class<?> getReturnType(String statement, SqlSessionFactory sqlSessionFactory) {
		Configuration configuration = sqlSessionFactory.getConfiguration();
		MappedStatement ms = configuration.getMappedStatement(statement);
		List<ResultMap> resultMaps = ms.getResultMaps();
		ResultMap resultMap = resultMaps.get(0);
		return resultMap.getType();
	}

	public static boolean isWrapper(Class<?> cls) {
		boolean result = false;
		if (Byte.class.isAssignableFrom(cls) || Short.class.isAssignableFrom(cls) || Integer.class.isAssignableFrom(cls)
				|| Long.class.isAssignableFrom(cls) || Float.class.isAssignableFrom(cls) || Double.class.isAssignableFrom(cls)
				|| BigInteger.class.isAssignableFrom(cls) || BigDecimal.class.isAssignableFrom(cls) || String.class.isAssignableFrom(cls)
				|| Character.class.isAssignableFrom(cls)) {
			result = true;
		}
		return result;
	}

	public static boolean isMap(Class<?> cls) {
		return Map.class.isAssignableFrom(cls);
	}

	public static Object getValue(Object target, String attribute) {
		MetaObject metaObject = MetaUtils.forObject(target);
		Object value = metaObject.getValue(attribute);
		return value;
	}

	public static MetaObject newMetaObject(Object object) {
		return MetaUtils.forObject(object);
	}

	public static String getKey(Map<String, Object> map, String key) {
		String result = key;
		if (!map.containsKey(key)) {
			result = key.toUpperCase();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public static <E> E cloneObject(E object) {
		E copy = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(baos);
			out.writeObject(object);
			byte[] bytes = baos.toByteArray();
			ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes));
			copy = (E) in.readObject();
		} catch (NotSerializableException e) {
			logger.info("can't serializable object: " + object, e);
		} catch (Exception e) {
			logger.info("can't clone object: " + object, e);
		}
		return copy;
	}

	public static Object getDefaultValue(Object value) {
		Object initValue = null;
		if (value instanceof Long) {
			initValue = 0L;
		} else if (value instanceof Integer) {
			initValue = 0;
		} else if (value instanceof Short) {
			initValue = 0;
		} else if (value instanceof Byte) {
			initValue = 0;
		} else if (value instanceof BigInteger) {
			initValue = new BigInteger("0");
		} else if (value instanceof Double) {
			initValue = 0D;
		} else if (value instanceof Float) {
			initValue = 0F;
		} else if (value instanceof BigDecimal) {
			initValue = new BigDecimal("0");
		} else {
			// TODO
		}
		return initValue;
	}

	public static Object getDefaultValue(Class<?> cls) {
		Object initValue = null;

		if (Long.class.isAssignableFrom(cls) || long.class.isAssignableFrom(cls)) {
			initValue = 0L;
		} else if (Integer.class.isAssignableFrom(cls) || int.class.isAssignableFrom(cls)) {
			initValue = 0;
		} else if (Short.class.isAssignableFrom(cls) || short.class.isAssignableFrom(cls)) {
			initValue = 0;
		} else if (Byte.class.isAssignableFrom(cls) || byte.class.isAssignableFrom(cls)) {
			initValue = 0;
		} else if (BigInteger.class.isAssignableFrom(cls)) {
			initValue = new BigInteger("0");
		} else if (Double.class.isAssignableFrom(cls) || double.class.isAssignableFrom(cls)) {
			initValue = 0D;
		} else if (Float.class.isAssignableFrom(cls) || float.class.isAssignableFrom(cls)) {
			initValue = 0F;
		} else if (BigDecimal.class.isAssignableFrom(cls)) {
			initValue = new BigDecimal("0");
		} else {
			// TODO
		}
		return initValue;
	}
}
