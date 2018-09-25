/**
 * Title: Galaxy(Distributed service platform) 
 * Copyright:Copyright (c) 2014-2016
 * Company: 神州数码融信软件有限公司
 */
package com.dcits.galaxy.cache.global;

import org.springframework.cache.Cache.ValueWrapper;

import com.dcits.galaxy.base.tuple.TwoTuple;

/**
 * 对从cache返回的数据进行TwoTuple相关的封装 主要应对update接口返回的参数，参数的约定为：
 * TwoTuple中的第一个参数是更新条数，第二个参数是新对象。
 * 
 * @author xuecy2
 *
 */
public class TwoTupleWrapper implements ValueWrapper {

	private final Object value;

	/**
	 * Create a new SimpleValueWrapper instance for exposing the given value.
	 * 
	 * @param value
	 *            the value to expose (may be {@code null})
	 */
	public TwoTupleWrapper(Object value) {
		this.value = value;
	}

	/**
	 * Simply returns the value as given at construction time.
	 */
	@SuppressWarnings("unchecked")
	public Object get() {
		if (this.value instanceof TwoTuple<?, ?>) {
			TwoTuple<Integer, Object> temp = (TwoTuple<Integer, Object>) this.value;
			return temp.second;
		}
		return this.value;
	}

}
