package com.dcits.orion.api;

import com.dcits.galaxy.base.data.*;

/**
 * Created by Tim on 2015/7/8.
 */
public interface Convert<T> {

    /**
     * 组包，将公共数据结构中的数据组织为特定的报文结构
     *
     * @param sysHead
     * @param br
     * @return
     */
    T pack(ISysHead sysHead, BeanResult br);

    /**
     * 报文拆包
     *
     * @param msg 请求报文
     * @return
     */
    BaseRequest unpack(T msg);

}
