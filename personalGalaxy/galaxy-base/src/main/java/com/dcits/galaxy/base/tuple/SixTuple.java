/**   
 * <p>Title: TwoTuple.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2015</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2015年2月12日 上午10:46:51
 * @version V1.0
 */
package com.dcits.galaxy.base.tuple;

/**
 * 六元组
 * 
 * @description
 * @version V1.0
 * @author Tim
 * @update 2015年2月12日 上午10:46:51
 */

public class SixTuple<A, B, C, D, E, F> extends FiveTuple<A, B, C, D, E> {

	public final F six;

	public SixTuple(A first, B second, C three, D four, E five, F six) {
		super(first, second, three, four, five);
		this.six = six;
	}
}
