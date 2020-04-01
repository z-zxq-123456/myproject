package com.exampl.zxq.dubbo.logUtil;

import com.exampl.zxq.dubbo.extension.annotation.SPI;

/**
 * LogAdapter
 */
@SPI("log4j")
public interface LogAdapter {

    public void printLog();
}
