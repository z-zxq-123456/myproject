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

import com.dcits.galaxy.base.bean.IBean;

import java.util.List;

/**
 * @author Tim
 * @version V1.0
 * @description MBSD本地应用头
 * @update 20150127 15:38:10
 */
public interface ILocalHead extends IBean {

    /**
     * 本地授权结果<br>
     * RET
     */
    List<Result> getRet();

    /**
     * 本地授权结果<br>
     * RET
     */
    void setRet(List<Result> ret);
}

