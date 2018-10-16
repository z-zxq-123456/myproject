package com.dcits.ensemble.om.pf.bean;

import java.math.BigDecimal;

public class BMContribute {
	private String acctNo;
	private String ccy;
    private String branch;
    private BigDecimal businessIncome;
    private BigDecimal shareManageCosting;
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
	public BigDecimal getBusinessIncome() {
		return businessIncome;
	}
	public void setBusinessIncome(BigDecimal businessIncome) {
		this.businessIncome = businessIncome;
	}
	public BigDecimal getShareManageCosting() {
		return shareManageCosting;
	}
	public void setShareManageCosting(BigDecimal shareManageCosting) {
		this.shareManageCosting = shareManageCosting;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BMContribute [acctNo=");
		builder.append(acctNo);
		builder.append(", ccy=");
		builder.append(ccy);
		builder.append(", branch=");
		builder.append(branch);
		builder.append(", businessIncome=");
		builder.append(businessIncome);
		builder.append(", shareManageCosting=");
		builder.append(shareManageCosting);
		builder.append("]");
		return builder.toString();
	}
	
	
}
