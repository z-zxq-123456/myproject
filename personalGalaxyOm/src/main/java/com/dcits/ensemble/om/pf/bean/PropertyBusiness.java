package com.dcits.ensemble.om.pf.bean;

import java.math.BigDecimal;

public class PropertyBusiness {

	private BigDecimal financingAmt;//融资金额
	private BigDecimal financingRate;//融资利率
	private BigDecimal periodDays;//期限
	private BigDecimal taxPay;//税费支出
	private BigDecimal capitalPrice;//转移价格
	private BigDecimal expectLose;//预期损失
	private BigDecimal manageCosting;//经营成本
	public BigDecimal getFinancingAmt() {
		return financingAmt;
	}
	public void setFinancingAmt(BigDecimal financingAmt) {
		this.financingAmt = financingAmt;
	}
	public BigDecimal getFinancingRate() {
		return financingRate;
	}
	public void setFinancingRate(BigDecimal financingRate) {
		this.financingRate = financingRate;
	}

	public BigDecimal getPeriodDays() {
		return periodDays;
	}
	public void setPeriodDays(BigDecimal periodDays) {
		this.periodDays = periodDays;
	}
	public BigDecimal getTaxPay() {
		return taxPay;
	}
	public void setTaxPay(BigDecimal taxPay) {
		this.taxPay = taxPay;
	}
	public BigDecimal getCapitalPrice() {
		return capitalPrice;
	}
	public void setCapitalPrice(BigDecimal capitalPrice) {
		this.capitalPrice = capitalPrice;
	}
	public BigDecimal getExpectLose() {
		return expectLose;
	}
	public void setExpectLose(BigDecimal expectLose) {
		this.expectLose = expectLose;
	}
	public BigDecimal getManageCosting() {
		return manageCosting;
	}
	public void setManageCosting(BigDecimal manageCosting) {
		this.manageCosting = manageCosting;
	}
	
	
}
