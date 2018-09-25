package com.dcits.orion.api;

import java.util.Map;

/**
 * invocation handler.
 *
 * @author xuecy
 */

public interface Handler {

    /**
     * 业务系统对外暴露的处理接口
     *
     * @param msg
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2015年1月19日 下午1:42:45
     */
    @Deprecated
    String handle(String msg);

    /**
     * 业务系统对外暴露的处理接口
     *
     * @param msg
     * @return
     */
    Map handle(Map msg);
}