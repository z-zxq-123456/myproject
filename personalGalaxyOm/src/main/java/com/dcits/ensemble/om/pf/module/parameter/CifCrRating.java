package com.dcits.ensemble.om.pf.module.parameter;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by para on 2016/03/22 10:32:09.
 */
public class CifCrRating extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is CIF_CR_RATING.CR_RATING 淇＄敤绛夌骇
     */
    @TablePk(index = 1)
    private String crRating;

    /**
     * This field is CIF_CR_RATING.CR_RATING_DESC 淇＄敤绛夌骇鎻忚堪
     */
    private String crRatingDesc;

    /**
     * This field is CIF_CR_RATING.COMPANY 娉曚汉浠ｇ爜
     */
    private String company;

    /**
     * @return the value of  CIF_CR_RATING.CR_RATING 淇＄敤绛夌骇
     */
    public String getCrRating() {
        return crRating;
    }

    /**
     * @param crRating the value for CIF_CR_RATING.CR_RATING 淇＄敤绛夌骇
     */
    public void setCrRating(String crRating) {
        this.crRating = crRating == null ? null : crRating.trim();
    }

    /**
     * @return the value of  CIF_CR_RATING.CR_RATING_DESC 淇＄敤绛夌骇鎻忚堪
     */
    public String getCrRatingDesc() {
        return crRatingDesc;
    }

    /**
     * @param crRatingDesc the value for CIF_CR_RATING.CR_RATING_DESC 淇＄敤绛夌骇鎻忚堪
     */
    public void setCrRatingDesc(String crRatingDesc) {
        this.crRatingDesc = crRatingDesc == null ? null : crRatingDesc.trim();
    }

    /**
     * @return the value of  CIF_CR_RATING.COMPANY 娉曚汉浠ｇ爜
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for CIF_CR_RATING.COMPANY 娉曚汉浠ｇ爜
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}