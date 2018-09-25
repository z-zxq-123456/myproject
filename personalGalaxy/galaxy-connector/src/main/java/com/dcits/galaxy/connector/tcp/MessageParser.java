package com.dcits.galaxy.connector.tcp;

import com.alibaba.dubbo.common.extension.SPI;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Socket消息解析器
 *
 * @author xuecy
 */
@SPI
public interface MessageParser {

    /**
     * 读取消息到字符串中
     *
     * @param in
     * @return
     * @exception IOException
     */
    String read(InputStream in) throws IOException;

    /**
     * 将消息写入到输出流中
     *
     * @param out
     * @param msg
     * @exception IOException
     */
    void write(OutputStream out, String msg) throws IOException;
}