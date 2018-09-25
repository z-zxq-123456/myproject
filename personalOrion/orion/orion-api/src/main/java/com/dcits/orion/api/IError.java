/**
 * Title: Galaxy(Distributed service platform)
 * File: IError.java
 * Copyright: Copyright (c) 2014-2016
 * Company: 神州数码融信软件有限公司
 */
package com.dcits.orion.api;

import com.dcits.galaxy.base.extension.GalaxySPI;

/**
 * 对于平台层面的异常接口，将异常转换为对应的错误格式报文。
 * 此接口扩展实现的key定义与MapParser保持一致。
 * <p>Created on 2016/11/9.</p>
 *
 * @author Tim <guotao@dcits.com>
 * @version 1.0.0
 * @since 1.7
 */
@GalaxySPI("json2map")
public interface IError {

    String pack(Throwable t);

}
