package com.dcits.orion.api;

import com.dcits.orion.api.model.EventNode;

/**
 * 事件组件获取器
 * <p/>
 * Created by Tim on 2015/11/17.
 */
public interface EventWrapper {

    /**
     * 获取事件检查方法
     *
     * @param eventType 事件类型
     * @param prodType 产品类型
     * @param eventNodeId 事件节点ID
     * @return
     */
    EventNode getEventCalc(String eventType, String prodType, String eventNodeId);

    /**
     * 获取事件提交方法
     *
     * @param eventType 事件类型
     * @param prodType 产品类型
     * @param eventNodeId 事件节点ID
     * @return
     */
    EventNode getEventSubmit(String eventType, String prodType, String eventNodeId);

    /**
     * 获取产品类型的基础类型
     *
     * @param prodType
     * @return
     */
    String getBaseProdType(String prodType);
}
