package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by admin on 2016/03/18 18:03:02.
 */
public class FmDepartment extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is FM_DEPARTMENT.DEPARTMENT 閮ㄩ棬浠ｇ爜
     */
    @TablePk(index = 1)
    private String department;

    /**
     * This field is FM_DEPARTMENT.DEPARTMENT_DESC 閮ㄩ棬鍚嶇О
     */
    private String departmentDesc;

    /**
     * This field is FM_DEPARTMENT.PROFIT_SEGMENT 鍒╂鼎涓績
     */
    private String profitSegment;

    /**
     * This field is FM_DEPARTMENT.COMPANY 
     */
    private String company;

    /**
     * @return the value of  FM_DEPARTMENT.DEPARTMENT 閮ㄩ棬浠ｇ爜
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the value for FM_DEPARTMENT.DEPARTMENT 閮ㄩ棬浠ｇ爜
     */
    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    /**
     * @return the value of  FM_DEPARTMENT.DEPARTMENT_DESC 閮ㄩ棬鍚嶇О
     */
    public String getDepartmentDesc() {
        return departmentDesc;
    }

    /**
     * @param departmentDesc the value for FM_DEPARTMENT.DEPARTMENT_DESC 閮ㄩ棬鍚嶇О
     */
    public void setDepartmentDesc(String departmentDesc) {
        this.departmentDesc = departmentDesc == null ? null : departmentDesc.trim();
    }

    /**
     * @return the value of  FM_DEPARTMENT.PROFIT_SEGMENT 鍒╂鼎涓績
     */
    public String getProfitSegment() {
        return profitSegment;
    }

    /**
     * @param profitSegment the value for FM_DEPARTMENT.PROFIT_SEGMENT 鍒╂鼎涓績
     */
    public void setProfitSegment(String profitSegment) {
        this.profitSegment = profitSegment == null ? null : profitSegment.trim();
    }

    /**
     * @return the value of  FM_DEPARTMENT.COMPANY 
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for FM_DEPARTMENT.COMPANY 
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}