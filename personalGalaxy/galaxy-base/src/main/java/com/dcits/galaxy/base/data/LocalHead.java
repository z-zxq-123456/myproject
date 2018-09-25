/**
 * <p>Title: LocalHead.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 20150127 15:38:10
 * @version V1.0
 */
package com.dcits.galaxy.base.data;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.base.validate.V;

import java.util.List;

/**
 * @author Tim
 * @version V1.0
 * @description MBSD本地应用头
 * @update 20150127 15:38:10
 */
public class LocalHead extends AbstractBean implements ILocalHead {

    /**
     * @fields serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 本地授权结果<br>
     * RET<br>
     * seqNo:1<br>
     * dataType:ARRAY<br>
     * cons:结构数组
     */
    @V(desc = "本地授权结果")
    private List<Result> ret;


    /**
     * 本地授权结果<br>
     * RET
     */
    public List<Result> getRet() {
        return ret;
    }

    /**
     * 本地授权结果<br>
     * RET
     */
    public void setRet(List<Result> ret) {
        this.ret = ret;
    }
}

