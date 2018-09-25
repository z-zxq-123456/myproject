/**
 * Title: Galaxy(Distributed service platform)
 * File: MessageDistributor.java
 * Copyright: Copyright (c) 2014-2017
 * Company: 神州数码融信软件有限公司
 */
package com.dcits.galaxy.base;

/**
 * 消息分发器，负责消费的转发
 * <p>Created on 2017/2/23.</p>
 *
 * @author Tim <guotao@dcits.com>
 * @version 1.0.0
 * @since 1.7
 */
public interface MessageDistributor {

    /**
     * 消息执行
     *
     * @param inMsg
     * @param appGroup
     * @param msgFormat
     * @param msgParser
     * @return
     */
    String execute(String inMsg, String appGroup, String msgFormat, String msgParser);

    /**
     * 设置平台ID
     */
    void setPlatFormId();

    /**
     * 清除平台ID
     */
    void removePlatFormId();
}
