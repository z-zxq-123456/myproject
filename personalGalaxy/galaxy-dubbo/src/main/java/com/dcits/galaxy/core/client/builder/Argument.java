package com.dcits.galaxy.core.client.builder;

import java.io.Serializable;

/**
 * 方法参数
 */
public final class Argument implements Serializable {

	private static final long serialVersionUID = -4191137912358499578L;

	private int index = -1;
	private String type;
	private boolean callback;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isCallback() {
		return callback;
	}

	public void setCallback(boolean callback) {
		this.callback = callback;
	}
}
