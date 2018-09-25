/**
 * <p>Title: AbstractIService.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 2014年12月19日 上午11:03:13
 * @version V1.0
 */
package com.dcits.orion.api;

import com.dcits.galaxy.base.data.BaseRequest;
import com.dcits.galaxy.base.data.BaseResponse;

/**
 * @author Tim
 * @version V1.0
 * @description 抽象的业务处理接口，业务接口继承此接口，通过流程引擎执行服务
 * @update 2014年12月19日 上午11:03:13
 */

public interface BusinessProcess extends IProcess<BaseRequest, BaseResponse> {
}
