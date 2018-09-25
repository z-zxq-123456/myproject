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

import com.dcits.orion.api.model.BusinessType;
import com.dcits.galaxy.base.data.BaseRequest;
import com.dcits.galaxy.base.data.BaseResponse;
import com.dcits.galaxy.base.data.BeanResult;
import com.dcits.galaxy.base.tuple.TwoTuple;

/**
 * @author Tim
 * @version V1.0
 * @description 抽象的业务处理接口，业务接口继承此接口，通过流程引擎执行服务
 * @update 2014年12月19日 上午11:03:13
 */

public interface IProcess<T extends BaseRequest, R extends BaseResponse> {

    /**
     * 执行服务过程
     *
     * @param request
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年12月19日 上午11:31:01
     */
    BeanResult process(T request);

    /**
     * 内部服务执行服务过程
     *
     * @param request
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年12月19日 上午11:31:01
     */
    BeanResult innerProcess(T request);

    /**
     * 内部服务执行服务过程
     *
     * @param req
     * @param businessType
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年12月19日 上午11:31:01
     */
    BeanResult innerProcess(T req, BusinessType businessType);

    /**
     * 异步内部服务执行服务过程
     *
     * @param req
     * @return
     */
    void asyncInnerProcess(T req);

    /**
     * 执行子服务过程
     * 执行子流程的服务，并返回子流程的submit节点个数
     *
     * @param request
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年12月19日 上午11:31:01
     */
    TwoTuple<BeanResult, Integer> subProcess(T request);

    /**
     * 获取实体请求对象模型
     *
     * @return
     */
    Class<? extends BaseRequest> getRequestModel();

    /**
     * 服务前处理
     *
     * @param request
     */
    void beforeProcess(T request);

    /**
     * 服务后处理
     *
     * @param request
     * @param response
     */
    void afterProcess(T request, R response);
}
