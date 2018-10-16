package com.dcits.ensemble.om.cmc.model.scpResultModel;

import java.util.List;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/4/26
 */
public class BeanResult {

    String retStatus;

    List<Ret> ret;

    public String getRetStatus() {
        return retStatus;
    }

    public void setRetStatus(String retStatus) {
        this.retStatus = retStatus;
    }

    public List<Ret> getRet() {
        return ret;
    }

    public void setRet(List<Ret> ret) {
        this.ret = ret;
    }
}
