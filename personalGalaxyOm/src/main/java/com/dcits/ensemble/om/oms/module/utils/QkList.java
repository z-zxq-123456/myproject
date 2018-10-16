
package com.dcits.ensemble.om.oms.module.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * shell执行结果*
 * @author tangxlf
 * @date   2015-8-6
 */
public class QkList{
		private String qkValue;
		private String qkDesc;
		private boolean selected = false;
		public QkList() {
		}

		public String getQkDesc() {
			return this.qkDesc;
		}

		public void setQkDesc(String qkDesc) {
			this.qkDesc = qkDesc;
		}

		public String getQkValue() {
			return this.qkValue;
		}

		public void setQkValue(String qkValue) {
			this.qkValue = qkValue;
		}
		public boolean isSelected() {
			return selected;
		}

		public void setSelected(boolean selected) {
			this.selected = selected;
		}
	}

