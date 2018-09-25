/**   
 * <p>Title: BasicGenerator.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2015年1月7日 上午10:28:21
 * @version V1.0
 */
package com.dcits.galaxy.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.tuple.TwoTuple;

/**
 * @description
 * @version V1.0
 * @author Tim
 * @update 2015年1月7日 上午10:28:21
 */

public class BaseGenerator<T> implements Generator<T> {

	private Class<T> type;

	public BaseGenerator(Class<T> type) {
		this.type = type;
	}

	@Override
	public T next() {
		try {
			return type.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new GalaxyException(e);
		}
	}

	@Override
	public T next(TwoTuple<Class<?>[], Object[]> parameter) {
		try {
			Constructor<T> constructor = type.getConstructor(parameter.first);
			return constructor.newInstance(parameter.second);
		} catch (NoSuchMethodException | SecurityException
				| InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			throw new GalaxyException(e);
		}

	}

	public static <T> Generator<T> create(Class<T> type) {
		return new BaseGenerator<T>(type);
	}

}
