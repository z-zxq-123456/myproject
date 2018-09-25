/**
 * Title: Galaxy(Distributed service platform)
 * File: Adapter.java
 * Copyright: Copyright (c) 2014-2016
 * Company: 神州数码融信软件有限公司
 */
package com.dcits.galaxy.adapter;

/**
 * 通讯适配器接口
 * <p>Created on 2015/10/21.</p>
 *
 * @author Tim <guotao@dcits.com>
 * @since 1.7
 */
public interface Adapter<T> {

    /**
     * 适配接出处理
     *
     * @param req
     *         请求对象
     * @return 响应对象
     */
    T execute(T req);

}
