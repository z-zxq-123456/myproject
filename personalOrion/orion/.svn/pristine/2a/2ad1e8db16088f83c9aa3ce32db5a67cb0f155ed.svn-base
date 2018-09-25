/**   
 * <p>Title: Configuration.java</p>
 * <p>Description: 系统参数实例
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年9月29日 下午2:25:37 
 * @version V1.0   
 */
package com.dcits.orion.schedule.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import com.dcits.galaxy.base.util.BeanUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @description 系统参数实例
 * @version V1.0
 * @author Tim
 * @update 2014年9月29日 下午2:25:37
 */

@XStreamAlias("configuration")
public class Configuration {

	@XStreamImplicit(itemFieldName = "property")
	List<Property> property;

	class Property {
		Property(String name, String value) {
			this.name = name;
			this.value = value;
		}

		String name;
		String value;
		String description;

		@Override
		public String toString() {
			return BeanUtils.getString(this);
		}
	}

	/**
	 * @param name
	 * @return
	 * @description 倒序检查，返回最近匹配的value
	 * @version 1.0
	 * @author Tim
	 * @update 2014年9月29日 下午2:26:43
	 */
	public String get(String name) {
		String value = null;
		Property p = null;
		for (int i = property.size() - 1; i >= 0; i--) {
			p = property.get(i);
			if (name.equals(p.name)) {
				value = p.value;
				break;
			}
		}
		return value;
	}

	public void set(String name, String value) {
		if (null == property)
			property = new ArrayList<Property>();
		property.add(new Property(name, value));
	}

	public Properties getProperties() {
		Properties pro = new Properties();
		if (null == property)
			return pro;
		for (Property p : property) {
			pro.put(p.name, p.value);
		}
		return pro;
	}

	public void setProperties(Properties pro) {
		Iterator<Entry<Object, Object>> it = pro.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Object, Object> entry = it.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			if (key instanceof String && value instanceof String) {
				if (null == property)
					property = new ArrayList<Property>();
				property.add(new Property((String) key, (String) value));
			}
		}
	}

	@Override
	public String toString() {
		return BeanUtils.getString(this);
	}
}
