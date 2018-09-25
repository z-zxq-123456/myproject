/**   
 * <p>Title: TablePk.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2015年2月10日 上午11:24:46
 * @version V1.0
 */
package com.dcits.galaxy.dal.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用户配置mapper bean的主键关系
 * 
 * @description 
 * @version V1.0
 * @author Tim
 * @update 2015年2月10日 上午11:24:46 
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TablePk {
	int index();
}
