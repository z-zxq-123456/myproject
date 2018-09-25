package com.dcits.galaxy.dal.mybatis.plugins;

/**
 * 行相关的参数
 * @author yin.weicai
 *
 */
public class RowArgs {

	/**
	 * 偏移量
	 */
	private int offset = 0;
	
	/**
	 * 查询条数
	 */
	private int limit = 10;
	
	public RowArgs() {
		
	}
	
	public RowArgs(int offset, int limit) {
		this.offset = offset;
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}
