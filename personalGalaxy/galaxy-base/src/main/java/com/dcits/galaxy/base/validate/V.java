/**
 * <p>Title: V.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2015年1月15日 下午1:17:39
 * @version V1.0
 */
package com.dcits.galaxy.base.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description
 * @version V1.0
 * @author Tim
 * @update 2015年1月15日 下午1:17:39
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface V {

	/**
	 * 字段说明
	 *
	 * @return
	 * @description 字段说明
	 * @version 1.0
	 * @author Tim
	 * @update 2015年1月15日 下午1:20:05
	 */
	String desc();

	/**
	 * @return
	 * @description 检查条件，输入格式key="value1,value2",当前值存在于key的value时，检查生效
	 * @version 1.0
	 * @author Tim
	 * @update 2015年1月15日 下午2:09:50
	 */
	String restraint() default "";

	/**
	 * @return
	 * @description 是否必须输入，默认false
	 * @version 1.0
	 * @author Tim
	 * @update 2015年1月15日 下午1:27:11
	 */
	boolean notNull() default false;

	/**
	 * @return
	 * @description 是否为空，用于String类型和List类型检查，默认true
	 * @version 1.0
	 * @author Tim
	 * @update 2015年1月15日 下午1:23:09
	 */
	boolean notEmpty() default true;

	/**
	 * @return
	 * @description 用于String类型检查全数字，默认false
	 * @version 1.0
	 * @author Tim
	 * @update 2015年1月15日 下午1:20:25
	 */
	boolean digit() default false;

	/**
	 * @return
	 * @description 最小整数，默认Integer.MIN_VALUE
	 * @version 1.0
	 * @author Tim
	 * @update 2015年1月15日 下午1:20:47
	 */
	int min() default Integer.MIN_VALUE;

	/**
	 * @return
	 * @description 最大整数，默认Integer.MAX_VALUE
	 * @version 1.0
	 * @author Tim
	 * @update 2015年1月15日 下午1:20:50
	 */
	int max() default Integer.MAX_VALUE;

	/**
	 * @return
	 * @description 正则表达式，用于String类型检查，默认""
	 * @version 1.0
	 * @author Tim
	 * @update 2015年1月15日 下午1:28:30
	 */
	String pattern() default "";

	/**
	 * @return
	 * @description 最小长度，用于String类型检查，默认-1
	 * @version 1.0
	 * @author Tim
	 * @update 2015年1月15日 下午1:29:37
	 */
	int minSize() default -1;

	/**
	 * @return
	 * @description 最大长度，用于String类型检查，默认-1
	 * @version 1.0
	 * @author Tim
	 * @update 2015年1月15日 下午1:29:48
	 */
	int maxSize() default -1;

	/**
	 * @return
	 * @description 长度，默认""
	 * @version 1.0
	 * @author Tim
	 * @update 2015年1月15日 下午3:50:52
	 */
	String length() default "";

	/**
	 * @return
	 * @description 检查是否在范围之内，用于String类型检查，默认""
	 * @version 1.0
	 * @author Tim
	 * @update 2015年1月15日 下午1:30:55
	 */
	String in() default "";

	/**
	 * @return
	 * @description 截止日期的字段名，默认""
	 * @version 1.0
	 * @author Tim
	 * @update 2015年1月15日 下午3:09:31
	 */
	String laterTime() default "";
	
	/**
	 * @return
	 * @description 小数位校验，用于String，Number类型检查，默认-1
	 * <p>基础类型以及由基础类型构造的BigDecimal对象，其精度可能与预计不符，请慎重使用。
	 * @author fankj
	 */
	int decimalLength() default -1;
	
	/**
	 * @return
	 * @description 整数位校验，用于String，Number类型检查，默认-1
	 * @author fankj
	 */
	int integerLength() default -1;
	
	/**
	 * @return
	 * @description string表示的日期格式
	 * @version 1.0
	 * @author Tim
	 * @update 2015年1月15日 下午3:09:31
	 */
	String dateFormat() default "";
}
