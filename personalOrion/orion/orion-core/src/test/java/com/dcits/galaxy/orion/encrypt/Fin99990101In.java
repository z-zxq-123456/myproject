/**   
 * <p>Title: Fin99990101In.java</p>
 * <p>Description: 存款账户查询（demo）</p>
 * <p>Copyright: Copyright (c) 2014-2015</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 20150926
 * @version V1.0
 */
package com.dcits.galaxy.orion.encrypt;

import com.dcits.galaxy.base.data.Request;
import com.dcits.galaxy.base.validate.V;

import java.io.Serializable;
import java.util.List;

/***
 * @description 存款账户查询（demo）
 * @version V1.0
 * @author Tim
 * @update 20150926
 */
public class Fin99990101In extends Request {

	/***
	 * @fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private Body body;

	/**
	 * @return body : return the property body.
	 */
	public Body getBody() {
		return body;
	}

	/**
	 * @param body
	 *            : set the property body.
	 */
	public void setBody(Body body) {
		this.body = body;
	}

	public static class Body implements Serializable {
	
		private static final long serialVersionUID = 1L;

		/**
		 * 账号<br>
		 * ACCT_NO<br>
		 * seqNo:1<br>
		 * dataType:String<br>
		 * length:50<br>
		 * cons:
		 */
		@V(desc = "账号", notNull = true, notEmpty = true)
		private String acctNo;

		public List<Item> getItems() {
			return items;
		}

		public void setItems(List<Item> items) {
			this.items = items;
		}

		private List<Item> items;

		public Item getItem() {
			return item;
		}

		public void setItem(Item item) {
			this.item = item;
		}

		private Item item;

		public static long getSerialVersionUID() {
			return serialVersionUID;
		}

		/**
		 * 账号<br>
		 * ACCT_NO
		 */
		public String getAcctNo() {
			return acctNo;
		}
	
		/**
		 * 账号<br>
		 * ACCT_NO
		 */
		public void setAcctNo(String acctNo) {
			this.acctNo = acctNo;
		}


	}


	public static class Acct implements Serializable {
		private static final long serialVersionUID = 1L;

		public String getAcctNo() {
			return acctNo;
		}

		public void setAcctNo(String acctNo) {
			this.acctNo = acctNo;
		}

		private String acctNo;
	}

	public static class Item implements Serializable{
		private static final long serialVersionUID = 1L;

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		private String password;

		public String getAcctNo() {
			return acctNo;
		}

		public void setAcctNo(String acctNo) {
			this.acctNo = acctNo;
		}

		private String acctNo;

		public Acct getAcct() {
			return acct;
		}

		public void setAcct(Acct acct) {
			this.acct = acct;
		}

		private Acct acct;

		public static long getSerialVersionUID() {
			return serialVersionUID;
		}

		public List<Acct> getAccts() {
			return accts;
		}

		public void setAccts(List<Acct> accts) {
			this.accts = accts;
		}
		private List<Acct> accts;


	}


	public String getSwitch() {
		return switch1;
	}

	public void setSwitch(String switch1) {
		this.switch1 = switch1;
	}

	public String switch1;


}
