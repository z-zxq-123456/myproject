/**   
 * <p>Title: Tuple.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2015</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2015年2月12日 上午10:46:51
 * @version V1.0
 */
package com.zxq.learn.parseDb;


import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * 元组
 * 
 * @description
 * @version V1.0
 * @author Tim
 * @update 2015年2月12日 上午10:46:51
 */

public class Tuple implements Serializable {
	private static final long serialVersionUID = 1085178677050530148L;

	@SuppressWarnings("unchecked")
	public <T> T getField(TupleField field) {
		T t;
		try {
			Field f = this.getClass().getField(field.toString());
			t = (T) f.get(this);
		} catch (NoSuchFieldException | SecurityException
				| IllegalArgumentException | IllegalAccessException e) {
			t = null;
		}
		return t;

	}
}


