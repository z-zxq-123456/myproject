/**   
 * <p>Title: ThreeTuple.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2015</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2015年2月12日 上午10:46:51
 * @version V1.0
 */
package com.dcits.galaxy.base.tuple;

/**
 * 三元组
 * 
 * @description
 * @version V1.0
 * @author Tim
 * @update 2015年2月12日 上午10:46:51
 */

public class ThreeTuple<A, B, C> extends TwoTuple<A, B> {

	public final C three;

	public ThreeTuple(A first, B second, C three) {
		super(first, second);
		this.three = three;
	}
}
