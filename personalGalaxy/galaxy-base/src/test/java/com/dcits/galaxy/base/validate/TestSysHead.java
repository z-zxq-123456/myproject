package com.dcits.galaxy.base.validate;

import com.dcits.galaxy.base.data.SysHead;

/**
 * 系统头扩展字段
 * <p/>
 * Created by Tim on 2016/6/7.
 */
public class TestSysHead extends SysHead {

    /**
     * 业务扩展字段1
     */
    @V(desc = "扩展字段1", notNull = true)
    private String define1;

    /**
     * 业务扩展字段2
     */
    @V(desc = "扩展字段2", notNull = true, restraint = "define1=dd")
    private String define2;

    /**
     * 复写父类属性，将tranDate属性设置为不必输
     */
    @V(desc = "交易日期", notNull = false, maxSize = 8)
    private String tranDate;

    public String getDefine1() {
        return define1;
    }

    public void setDefine1(String define1) {
        this.define1 = define1;
    }

    public String getDefine2() {
        return define2;
    }

    public void setDefine2(String define2) {
        this.define2 = define2;
    }

    @Override
    public String getTranDate() {
        return tranDate;
    }

    @Override
    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }
}
