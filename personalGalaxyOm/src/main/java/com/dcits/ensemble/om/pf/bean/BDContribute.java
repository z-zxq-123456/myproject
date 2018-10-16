package com.dcits.ensemble.om.pf.bean;

import java.math.BigDecimal;

public class BDContribute {
		private String acctNo;
	    private String prodType;
	    private String ccy;
	    private String branch;
	    private String value;
	    private BigDecimal averageBalance;
	    private BigDecimal cashReserve;
	    private BigDecimal transferPrice;
	    private BigDecimal period;
	    private BigDecimal cashReserveRatio;
	    private BigDecimal dayRate;
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
		public BigDecimal getAverageBalance() {
			return averageBalance;
		}
		public void setAverageBalance(BigDecimal averageBalance) {
			this.averageBalance = averageBalance;
		}
		public BigDecimal getCashReserve() {
			return cashReserve;
		}
		public void setCashReserve(BigDecimal cashReserve) {
			this.cashReserve = cashReserve;
		}
		public BigDecimal getTransferPrice() {
			return transferPrice;
		}
		public void setTransferPrice(BigDecimal transferPrice) {
			this.transferPrice = transferPrice;
		}
		public BigDecimal getPeriod() {
			return period;
		}
		public void setPeriod(BigDecimal period) {
			this.period = period;
		}
		public BigDecimal getCashReserveRatio() {
			return cashReserveRatio;
		}
		public void setCashReserveRatio(BigDecimal cashReserveRatio) {
			this.cashReserveRatio = cashReserveRatio;
		}
		public BigDecimal getDayRate() {
			return dayRate;
		}
		public void setDayRate(BigDecimal dayRate) {
			this.dayRate = dayRate;
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
			builder.append("BDContribute [acctNo=");
			builder.append(acctNo);
			builder.append(", prodType=");
			builder.append(prodType);
			builder.append(", ccy=");
			builder.append(ccy);
			builder.append(", branch=");
			builder.append(branch);
			builder.append(", value=");
			builder.append(value);
			builder.append(", averageBalance=");
			builder.append(averageBalance);
			builder.append(", cashReserve=");
			builder.append(cashReserve);
			builder.append(", transferPrice=");
			builder.append(transferPrice);
			builder.append(", period=");
			builder.append(period);
			builder.append(", cashReserveRatio=");
			builder.append(cashReserveRatio);
			builder.append(", dayRate=");
			builder.append(dayRate);
			builder.append(", manageCosting=");
			builder.append(manageCosting);
			builder.append("]");
			return builder.toString();
		}
		
}
