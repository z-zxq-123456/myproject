package com.dcits.galaxy.base;

import com.dcits.galaxy.base.tuple.TwoTuple;

/**
 * 生成器负责创建对象
 * 
 * @description
 * @version V1.0
 * @author Tim
 * @update 2015年1月7日 上午10:17:07
 */

public interface Generator<T> {

	T next();

	/**
	 * @param parameter
	 * @return
	 * @description
	 * @version 1.0
	 * @author Liang
	 * @update 2015年2月12日 下午7:08:36
	 */
	T next(TwoTuple<Class<?>[], Object[]> parameter);

}
