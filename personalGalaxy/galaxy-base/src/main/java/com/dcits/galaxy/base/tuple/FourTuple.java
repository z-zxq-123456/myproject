/**   
 * <p>Title: FourTuple.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2015</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2015年2月12日 上午10:46:51
 * @version V1.0
 */
package com.dcits.galaxy.base.tuple;

/**
 * 四元组
 * 
 * @description
 * @version V1.0
 * @author Tim
 * @update 2015年2月12日 上午10:46:51
 */

public class FourTuple<A, B, C, D> extends ThreeTuple<A, B, C> {

	public final D four;

	public FourTuple(A first, B second, C three, D four) {
		super(first, second, three);
		this.four = four;
	}
}
