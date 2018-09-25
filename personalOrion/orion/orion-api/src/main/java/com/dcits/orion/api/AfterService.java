/**
 * <p>Title: AfterService.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 2015年1月19日 下午3:34:02
 * @version V1.0
 */
package com.dcits.orion.api;

import com.dcits.galaxy.base.data.BeanResult;
import com.dcits.galaxy.base.data.BaseRequest;

/**
 * @author Tim
 * @version V1.0
 * @description 处理服务后的通用处理
 * @update 2015年1月19日 下午3:34:02
 */

public interface AfterService<T extends BaseRequest> {

    /**
     * 服务后通用处理
     *
     * @param request
     * @param br
     * @description
     * @version 1.0
     * @author Tim
     * @update 2015年1月19日 下午3:35:49
     */
    void afterProcess(T request, BeanResult br);
}
