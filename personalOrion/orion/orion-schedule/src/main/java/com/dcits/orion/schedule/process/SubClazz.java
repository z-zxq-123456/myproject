/**   
 * <p>Title: SubClazz.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2015</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2015年3月12日 下午5:45:30
 * @version V1.0
 */
package com.dcits.orion.schedule.process;

import com.dcits.galaxy.base.util.ClassLoaderUtils;

/**
 * 获取子类的Clazz
 * 
 * @description
 * @version V1.0
 * @author Tim
 * @update 2015年3月12日 下午5:45:30
 */

public class SubClazz<T> {

	@SuppressWarnings("unchecked")
	public Class<? extends T> getClazz(String clazz)
			throws ClassNotFoundException {
		if (null == clazz || "".equals(clazz))
			return null;
		return (Class<? extends T>) ClassLoaderUtils.loadClass(clazz);
	}
}
