package com.dcits.ensemble.om.cmc.dataTable;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @Description :
 * @Author :admin
 * @Date : Create on 2018/4/26
 */
public class CmcCardTableUsedInfoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String productRuleNo;
    private BigInteger usedNum;
    private String tableName;
    private BigInteger totalNum;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getProductRuleNo() {
        return productRuleNo;
    }

    public void setProductRuleNo(String productRuleNo) {
        this.productRuleNo = productRuleNo;
    }

    public BigInteger getUsedNum() {
        return usedNum;
    }

    public void setUsedNum(BigInteger usedNum) {
        this.usedNum = usedNum;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public BigInteger getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(BigInteger totalNum) {
        this.totalNum = totalNum;
    }
}
