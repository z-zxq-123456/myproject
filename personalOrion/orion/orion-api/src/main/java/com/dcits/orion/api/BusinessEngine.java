package com.dcits.orion.api;

import com.dcits.orion.api.model.BusinessType;
import com.dcits.galaxy.base.data.BaseRequest;
import com.dcits.galaxy.base.data.BeanResult;
import com.dcits.galaxy.base.tuple.TwoTuple;

/**
 * @author Tim
 * @version V1.0
 * @description 流程引擎业务处理接口
 * @update 2014年12月19日 下午1:42:33
 */

public interface BusinessEngine {

    /**
     * 执行服务
     *
     * @param request
     * @return 通过流程引擎执行服务
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年12月19日 下午2:12:51
     */
    void init(BaseRequest request, BusinessType businessType);

    /**
     * 执行子流程
     * 返回子流程的执行结果和submit节点个数
     *
     * @param request
     * @param businessType
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年12月19日 上午11:31:01
     */
    TwoTuple<BeanResult, Integer> execFlow(BaseRequest request, BusinessType businessType);
}
