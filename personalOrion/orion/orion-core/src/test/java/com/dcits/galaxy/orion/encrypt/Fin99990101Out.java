/**   
 * <p>Title: Fin99990101Out.java</p>
 * <p>Description: 存款账户查询（demo）</p>
 * <p>Copyright: Copyright (c) 2014-2015</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 20150926
 * @version V1.0
 */
package com.dcits.galaxy.orion.encrypt;

import com.dcits.galaxy.base.data.Response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/***
 * @description 存款账户查询（demo）
 * @version V1.0
 * @author Tim
 * @update 20150926
 */
public class Fin99990101Out extends Response {

	/***
	 * @fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 账户名称<br>
	 * ACCT_NAME<br>
	 * seqNo:1<br>
	 * dataType:String<br>
	 * length:120<br>
     
	 * cons:
	 */
	private String acctName;

	/**
	 * 创建时间<br>
	 * CREATE_TIME<br>
	 * seqNo:2<br>
	 * dataType:String<br>
	 * length:50<br>
     
	 * cons:
	 */
	private String createTime;

	/**
	 * 创建日期<br>
	 * CREATE_DATE<br>
	 * seqNo:3<br>
	 * dataType:String<br>
	 * length:8<br>
     
	 * cons:
	 */
	private String createDate;

	/**
	 * 余额<br>
	 * BAL<br>
	 * seqNo:4<br>
	 * dataType:Double<br>
	 
     
	 * cons:
	 */
	private BigDecimal bal;

	/**
	 * 账户状态<br>
	 * STATUS<br>
	 * seqNo:5<br>
	 * dataType:String<br>
	 * length:1<br>
     
	 * cons:
	 */
	private String status;


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String password;


	/**
	 * 账户名称<br>
	 * ACCT_NAME
	 */
	public String getAcctName() {
		return acctName;
	}

	/**
	 * 账户名称<br>
	 * ACCT_NAME
	 */
	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	/**
	 * 创建时间<br>
	 * CREATE_TIME
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间<br>
	 * CREATE_TIME
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 创建日期<br>
	 * CREATE_DATE
	 */
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * 创建日期<br>
	 * CREATE_DATE
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	/**
	 * 余额<br>
	 * BAL
	 */
	public BigDecimal getBal() {
		return bal;
	}

	/**
	 * 余额<br>
	 * BAL
	 */
	public void setBal(BigDecimal bal) {
		this.bal = bal;
	}

	/**
	 * 账户状态<br>
	 * STATUS
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 账户状态<br>
	 * STATUS
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	public static class OutAcct implements Serializable {
		private static final long serialVersionUID = 1L;

		public String getAcctNo() {
			return acctNo;
		}

		public void setAcctNo(String acctNo) {
			this.acctNo = acctNo;
		}

		private String acctNo;

		public double getAmount() {
			return amount;
		}

		public void setAmount(double amount) {
			this.amount = amount;
		}

		private double amount;

	}
	public static class OutItem implements Serializable {
		private static final long serialVersionUID = 1L;

		public OutAcct getAcct() {
			return acct;
		}

		public void setAcct(OutAcct acct) {
			this.acct = acct;
		}

		public List<OutAcct> getAccts() {
			return accts;
		}

		public void setAccts(List<OutAcct> accts) {
			this.accts = accts;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		private OutAcct acct;
		List<OutAcct> accts;
		private String password;
	}

	public OutItem getItem() {
		return item;
	}

	public void setItem(OutItem item) {
		this.item = item;
	}

	public List<OutItem> getItems() {
		return items;
	}

	public void setItems(List<OutItem> items) {
		this.items = items;
	}

	private OutItem item;

	private List<OutItem> items;




}
