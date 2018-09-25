package com.dcits.galaxy.core.client.builder;

import java.util.Set;

/**
 * 远程调用属性<br/>
 * 注意，实现类必须实现{@link #toString()}方法，并且{@code toString()}的返回值必须可以唯一确定一个{@code Attributes}对象
 */
public interface Attributes {

	/**
	 * 根据属性名获取属性值
	 * 
	 * @param attributeName 属性名
	 * @return 属性值
	 */
	Object getAttribute(String attributeName);

	/**
	 * 判断是否存在某个属性
	 * 
	 * @param attributeName 属性名
	 * @return 存在某个属性时返回{@code true}，否则返回{@code false}
	 */
	boolean hasAttribute(String attributeName);

	/**
	 * 获取所有的属性名
	 * 
	 * @return 属性名集合
	 */
	Set<String> getAttributeNames();
}
