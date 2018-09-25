package com.dcits.galaxy.connector.tcp;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.dcits.galaxy.base.MessageDistributor;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.connector.ConnectorProperties;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Service dispatcher thread.
 *
 * @author xuecy
 */
public class DispatcherThread implements Runnable {
    private static final Logger logger = LoggerFactory
            .getLogger(DispatcherThread.class);
    private Socket client;
    private SocketConnector server;

    /**
     * 解析器名称
     */
    private String parserName = ConnectorProperties.getProperty(
            "connector.socket.parser.name", "default");

    public DispatcherThread(Socket client, SocketConnector server) {
        this.client = client;
        this.server = server;
    }

    public void run() {
        long start = System.currentTimeMillis();
        InputStream in = null;
        OutputStream out = null;
        String outMsg;
        try {
            // 设置PlatFormId
            SpringApplicationContext.getContext().getBean(MessageDistributor.class).setPlatFormId();
            in = this.client.getInputStream();
            if (logger.isDebugEnabled()) {
                logger.debug("waiting for client[" + this.client.toString()
                        + "] to send a message...");
            }

            // 动态获取解析器
            MessageParser parser = ExtensionLoader.getExtensionLoader(
                    MessageParser.class).getExtension(parserName);

            String inMsg = parser.read(in);
            if (logger.isDebugEnabled()) {
                logger.debug("received the message sent by the client["
                        + this.client.toString() + "] is \n" + inMsg);
            }

            // 处理
            if (this.server.getTestMessage().equals(inMsg)) {
                // 针对测试报文的处理
                if (logger.isInfoEnabled()) {
                    logger.info("message is test message.");
                }
                outMsg = this.server.getTestReturnMessage();
            } else {
                outMsg = SpringApplicationContext.getContext().getBean(MessageDistributor.class).execute(inMsg, this.server.getAppGroup(), this.server.getMsgFormat(), this.server.getMsgParser());

                if (outMsg == null) {
                    String errMsg = "Response message is null.";
                    if (logger.isErrorEnabled()) {
                        logger.error(errMsg);
                    }
                }
                if (logger.isDebugEnabled()) {
                    logger.debug("Response message is...\n" + outMsg);
                }
            }

            // 返回
            out = this.client.getOutputStream();
            parser.write(out, outMsg);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("an error occurred processing the client["
                        + this.client.toString() + "] request.\n"
                        + ExceptionUtils.getStackTrace(e));
            }
        } finally {
            if (logger.isInfoEnabled()) {
                logger.info("Service execute time is : "
                        + (System.currentTimeMillis() - start));
            }
            // 清除平台ID
            SpringApplicationContext.getContext().getBean(MessageDistributor.class).removePlatFormId();
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    if (logger.isErrorEnabled()) {
                        logger.error("close InputStream error:"
                                + ExceptionUtils.getStackTrace(e));
                    }
                } finally {
                    in = null;
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    if (logger.isErrorEnabled()) {
                        logger.error("close ByteArrayOutputStream error:"
                                + ExceptionUtils.getStackTrace(e));
                    }
                } finally {
                    out = null;
                }
            }
        }
    }

}
