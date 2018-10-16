package com.dcits.ensemble.om.pf.bean;

import java.math.BigDecimal;

public class BAContribute {
	    private String acctNo;
	    private String prodType;
	    private String ccy;
	    private String branch;
	    private String value;
	    private BigDecimal financingAmt;
	    private BigDecimal financingRate;
	    private BigDecimal periodDays;
	    private BigDecimal taxPay;
	    private BigDecimal capitalPrice;
	    private BigDecimal expectLose;
	    private BigDecimal manageCosting;
		public String getAcctNo() {
			return acctNo;
		}
		public void setAcctNo(String acctNo) {
			this.acctNo = acctNo;
		}
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
		public String getBranch() {
			return branch;
		}
		public void setBranch(String branch) {
			this.branch = branch;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
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
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("BAContribute [acctNo=");
			builder.append(acctNo);
			builder.append(", prodType=");
			builder.append(prodType);
			builder.append(", ccy=");
			builder.append(ccy);
			builder.append(", branch=");
			builder.append(branch);
			builder.append(", value=");
			builder.append(value);
			builder.append(", financingAmt=");
			builder.append(financingAmt);
			builder.append(", financingRate=");
			builder.append(financingRate);
			builder.append(", periodDays=");
			builder.append(periodDays);
			builder.append(", taxPay=");
			builder.append(taxPay);
			builder.append(", capitalPrice=");
			builder.append(capitalPrice);
			builder.append(", expectLose=");
			builder.append(expectLose);
			builder.append(", manageCosting=");
			builder.append(manageCosting);
			builder.append("]");
			return builder.toString();
		}
		
}
