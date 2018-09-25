/**   
 * <p>Title: Fin99990101In.java</p>
 * <p>Description: 存款账户查询（demo）</p>
 * <p>Copyright: Copyright (c) 2014-2015</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 20150926
 * @version V1.0
 */
package com.dcits.galaxy.orion.xml;

import com.dcits.galaxy.base.data.Request;
import com.dcits.galaxy.base.validate.V;

import java.io.Serializable;
import java.math.BigDecimal;
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

		String loanNo;
		String ddNo;
		List<Repay> repayArray;

		public List<Repay> getRepayArray() {
			return repayArray;
		}

		public void setRepayArray(List<Repay> repayArray) {
			this.repayArray = repayArray;
		}



		public String getLoanNo() {
			return loanNo;
		}

		public void setLoanNo(String loanNo) {
			this.loanNo = loanNo;
		}

		public String getDdNo() {
			return ddNo;
		}

		public void setDdNo(String ddNo) {
			this.ddNo = ddNo;
		}






	}

	public static class Repay implements Serializable {
		private static final long serialVersionUID = 1L;

		String option;
		String schNo;
		String priRepayFreq;//PRI_REPAY_FREQ
		String repayDate;//REPAY_DATE
		String priRepayDay;//PRI_REPAY_DAY
		String endDate;//END_DATE;
		double ddAllocation;//DD_ALLOCATION
		String repayCny;
		String ddRepayXrate;//DD_REPAY_XRATE;
		String xrateId;//XRATE_ID

		public BigDecimal getRepayAmt() {
			return repayAmt;
		}

		public void setRepayAmt(BigDecimal repayAmt) {
			this.repayAmt = repayAmt;
		}

		BigDecimal repayAmt;//REPAY_AMT;

		public String getOption() {
			return option;
		}

		public void setOption(String option) {
			this.option = option;
		}

		public String getSchNo() {
			return schNo;
		}

		public void setSchNo(String schNo) {
			this.schNo = schNo;
		}

		public String getPriRepayFreq() {
			return priRepayFreq;
		}

		public void setPriRepayFreq(String priRepayFreq) {
			this.priRepayFreq = priRepayFreq;
		}

		public String getRepayDate() {
			return repayDate;
		}

		public void setRepayDate(String repayDate) {
			this.repayDate = repayDate;
		}

		public String getPriRepayDay() {
			return priRepayDay;
		}

		public void setPriRepayDay(String priRepayDay) {
			this.priRepayDay = priRepayDay;
		}

		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}

		public double getDdAllocation() {
			return ddAllocation;
		}

		public void setDdAllocation(double ddAllocation) {
			this.ddAllocation = ddAllocation;
		}

		public String getRepayCny() {
			return repayCny;
		}

		public void setRepayCny(String repayCny) {
			this.repayCny = repayCny;
		}

		public String getDdRepayXrate() {
			return ddRepayXrate;
		}

		public void setDdRepayXrate(String ddRepayXrate) {
			this.ddRepayXrate = ddRepayXrate;
		}

		public String getXrateId() {
			return xrateId;
		}

		public void setXrateId(String xrateId) {
			this.xrateId = xrateId;
		}


	}







}
