package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by para on 2016/03/22 10:32:10.
 */
public class CifEconDist extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is CIF_ECON_DIST.ECON_DIST 缁忔祹鐗瑰尯
     */
    @TablePk(index = 1)
    private String econDist;

    /**
     * This field is CIF_ECON_DIST.ECON_DIST_DESC 鎻忚堪
     */
    private String econDistDesc;

    /**
     * This field is CIF_ECON_DIST.COMPANY 娉曚汉浠ｇ爜
     */
    private String company;

    /**
     * @return the value of  CIF_ECON_DIST.ECON_DIST 缁忔祹鐗瑰尯
     */
    public String getEconDist() {
        return econDist;
    }

    /**
     * @param econDist the value for CIF_ECON_DIST.ECON_DIST 缁忔祹鐗瑰尯
     */
    public void setEconDist(String econDist) {
        this.econDist = econDist == null ? null : econDist.trim();
    }

    /**
     * @return the value of  CIF_ECON_DIST.ECON_DIST_DESC 鎻忚堪
     */
    public String getEconDistDesc() {
        return econDistDesc;
    }

    /**
     * @param econDistDesc the value for CIF_ECON_DIST.ECON_DIST_DESC 鎻忚堪
     */
    public void setEconDistDesc(String econDistDesc) {
        this.econDistDesc = econDistDesc == null ? null : econDistDesc.trim();
    }

    /**
     * @return the value of  CIF_ECON_DIST.COMPANY 娉曚汉浠ｇ爜
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for CIF_ECON_DIST.COMPANY 娉曚汉浠ｇ爜
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}