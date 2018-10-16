package com.dcits.ensemble.om.pf.bean;

import java.math.BigDecimal;

public class TargetReturnPricing implements java.io.Serializable  {
	
	private static final long serialVersionUID = -3209338467200677244L;
	private String prodType;
	private String ccy;
	private String eventType;
	private String crDr;
	private BigDecimal balance;
	private BigDecimal days;
	private BigDecimal averagePrice;//平均价格
	private BigDecimal expectationProfit;//期望利润
	private BigDecimal manageCosting;//经营成本
	private BigDecimal computeRate;//计算利率
	public String getProdType() {
		return prodType;
	}
	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	public String getCcy() {
		return ccy;
	}
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getCrDr() {
		return crDr;
	}
	public void setCrDr(String crDr) {
		this.crDr = crDr;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public BigDecimal getDays() {
		return days;
	}
	public void setDays(BigDecimal days) {
		this.days = days;
	}
	public BigDecimal getAveragePrice() {
		return averagePrice;
	}
	public void setAveragePrice(BigDecimal averagePrice) {
		this.averagePrice = averagePrice;
	}
	public BigDecimal getExpectationProfit() {
		return expectationProfit;
	}
	public void setExpectationProfit(BigDecimal expectationProfit) {
		this.expectationProfit = expectationProfit;
	}
	public BigDecimal getManageCosting() {
		return manageCosting;
	}
	public void setManageCosting(BigDecimal manageCosting) {
		this.manageCosting = manageCosting;
	}
	public BigDecimal getComputeRate() {
		return computeRate;
	}
	public void setComputeRate(BigDecimal computeRate) {
		this.computeRate = computeRate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TargetReturnPricing [prodType=");
		builder.append(prodType);
		builder.append(", ccy=");
		builder.append(ccy);
		builder.append(", eventType=");
		builder.append(eventType);
		builder.append(", crDr=");
		builder.append(crDr);
		builder.append(", balance=");
		builder.append(balance);
		builder.append(", days=");
		builder.append(days);
		builder.append(", averagePrice=");
		builder.append(averagePrice);
		builder.append(", expectationProfit=");
		builder.append(expectationProfit);
		builder.append(", manageCosting=");
		builder.append(manageCosting);
		builder.append(", computeRate=");
		builder.append(computeRate);
		builder.append("]");
		return builder.toString();
	}

}
