package com.dcits.ensemble.om.pf.bean;

import java.math.BigDecimal;

public class BContribute {
	private String acctNo;
	private String ccy;
	private String branch;
    private BigDecimal b;
    private BigDecimal ec;
    private BigDecimal i;
	public String getAcctNo() {
		return acctNo;
	}
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	public String getCcy() {
		return ccy;
	}
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public BigDecimal getB() {
		return b;
	}
	public void setB(BigDecimal b) {
		this.b = b;
	}
	public BigDecimal getEc() {
		return ec;
	}
	public void setEc(BigDecimal ec) {
		this.ec = ec;
	}
	public BigDecimal getI() {
		return i;
	}
	public void setI(BigDecimal i) {
		this.i = i;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BContribute [acctNo=");
		builder.append(acctNo);
		builder.append(", ccy=");
		builder.append(ccy);
		builder.append(", branch=");
		builder.append(branch);
		builder.append(", b=");
		builder.append(b);
		builder.append(", ec=");
		builder.append(ec);
		builder.append(", i=");
		builder.append(i);
		builder.append("]");
		return builder.toString();
	}
	
	
}
