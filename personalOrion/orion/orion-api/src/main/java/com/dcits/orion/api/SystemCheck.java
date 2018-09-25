package com.dcits.orion.api;

import com.dcits.galaxy.base.data.BeanResult;
import com.dcits.galaxy.base.data.BaseRequest;

/**
 * @author Tim
 * @version V1.0
 * @description 系统公共检查处理
 * @update 2014年10月27日 下午2:51:35
 */

public interface SystemCheck<T extends BaseRequest> {

    /**
     * 系统公共检查
     *
     * @param request
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2015年1月16日 下午3:36:05
     */
    BeanResult systemCheck(T request);
}
