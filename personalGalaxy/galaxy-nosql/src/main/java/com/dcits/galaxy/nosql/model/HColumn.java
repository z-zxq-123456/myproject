/**   
 * <p>Title: HColumn.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Liang
 * @update 2015年3月6日 下午1:39:50
 * @version V1.0
 */
package com.dcits.galaxy.nosql.model;

import com.dcits.galaxy.base.bean.AbstractBean;

/**
 * @description
 * @version V1.0
 * @author Liang
 * @update 2015年3月6日 下午1:39:50
 */

public class HColumn extends AbstractBean {
	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = -3143026006183983022L;
	private String rowkey;
	private String familyName;
	private String[] column;
	private String[] value;

	public String getRowkey() {
		return rowkey;
	}

	public void setRowkey(String rowkey) {
		this.rowkey = rowkey;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String[] getColumn() {
		return column;
	}

	public void setColumn(String[] column) {
		this.column = column;
	}

	public String[] getValue() {
		return value;
	}

	public void setValue(String[] value) {
		this.value = value;
	}

}
