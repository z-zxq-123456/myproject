package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/03/18 18:03:02.
 */
public class FmAcctExec extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is FM_ACCT_EXEC.ACCT_EXEC 瀹㈡埛缁忕悊
     */
    @TablePk(index = 1)
    private String acctExec;

    /**
     * This field is FM_ACCT_EXEC.ACCT_EXEC_NAME 瀹㈡埛缁忕悊濮撳悕
     */
    private String acctExecName;

    /**
     * This field is FM_ACCT_EXEC.MANAGER 涓荤缁忕悊
     */
    private String manager;

    /**
     * This field is FM_ACCT_EXEC.PROFIT_SEGMENT 鍒╂鼎涓績
     */
    private String profitSegment;

    /**
     * This field is FM_ACCT_EXEC.COLLAT_MGR_IND 
     */
    private String collatMgrInd;

    /**
     * This field is FM_ACCT_EXEC.BRANCH 褰掑睘鏈烘瀯
     */
    private String branch;

    /**
     * This field is FM_ACCT_EXEC.COMPANY 
     */
    private String company;

    /**
     * @return the value of  FM_ACCT_EXEC.ACCT_EXEC 瀹㈡埛缁忕悊
     */
    public String getAcctExec() {
        return acctExec;
    }

    /**
     * @param acctExec the value for FM_ACCT_EXEC.ACCT_EXEC 瀹㈡埛缁忕悊
     */
    public void setAcctExec(String acctExec) {
        this.acctExec = acctExec == null ? null : acctExec.trim();
    }

    /**
     * @return the value of  FM_ACCT_EXEC.ACCT_EXEC_NAME 瀹㈡埛缁忕悊濮撳悕
     */
    public String getAcctExecName() {
        return acctExecName;
    }

    /**
     * @param acctExecName the value for FM_ACCT_EXEC.ACCT_EXEC_NAME 瀹㈡埛缁忕悊濮撳悕
     */
    public void setAcctExecName(String acctExecName) {
        this.acctExecName = acctExecName == null ? null : acctExecName.trim();
    }

    /**
     * @return the value of  FM_ACCT_EXEC.MANAGER 涓荤缁忕悊
     */
    public String getManager() {
        return manager;
    }

    /**
     * @param manager the value for FM_ACCT_EXEC.MANAGER 涓荤缁忕悊
     */
    public void setManager(String manager) {
        this.manager = manager == null ? null : manager.trim();
    }

    /**
     * @return the value of  FM_ACCT_EXEC.PROFIT_SEGMENT 鍒╂鼎涓績
     */
    public String getProfitSegment() {
        return profitSegment;
    }

    /**
     * @param profitSegment the value for FM_ACCT_EXEC.PROFIT_SEGMENT 鍒╂鼎涓績
     */
    public void setProfitSegment(String profitSegment) {
        this.profitSegment = profitSegment == null ? null : profitSegment.trim();
    }

    /**
     * @return the value of  FM_ACCT_EXEC.COLLAT_MGR_IND 
     */
    public String getCollatMgrInd() {
        return collatMgrInd;
    }

    /**
     * @param collatMgrInd the value for FM_ACCT_EXEC.COLLAT_MGR_IND 
     */
    public void setCollatMgrInd(String collatMgrInd) {
        this.collatMgrInd = collatMgrInd == null ? null : collatMgrInd.trim();
    }

    /**
     * @return the value of  FM_ACCT_EXEC.BRANCH 褰掑睘鏈烘瀯
     */
    public String getBranch() {
        return branch;
    }

    /**
     * @param branch the value for FM_ACCT_EXEC.BRANCH 褰掑睘鏈烘瀯
     */
    public void setBranch(String branch) {
        this.branch = branch == null ? null : branch.trim();
    }

    /**
     * @return the value of  FM_ACCT_EXEC.COMPANY 
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for FM_ACCT_EXEC.COMPANY 
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}