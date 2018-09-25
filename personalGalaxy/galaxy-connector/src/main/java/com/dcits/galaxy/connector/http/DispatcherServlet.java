package com.dcits.galaxy.connector.http;

import com.dcits.galaxy.base.MessageDistributor;
import com.dcits.galaxy.base.util.ExceptionUtils;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.connector.ConnectorProperties;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Service dispatcher Servlet.
 *
 * @author xuecy
 */
public class DispatcherServlet extends HttpServlet {

    private static final long serialVersionUID = 5766349180380479888L;

    private static final Logger logger = LoggerFactory
            .getLogger(DispatcherServlet.class);

    private static DispatcherServlet INSTANCE;

    // 修改为IService的RPC级服务处理
//    private String handlerId;

    /**
     * 应用组
     */
    private String appGroup;

    /**
     * 应用名
     */
    private String appName;

    /**
     * 报文格式
     */
    private String msgFormat;

    /**
     * 报文处理解析器
     */
    private String msgParser;

    private Map ipFilterMap;


    /**
     * 传入的字符集，默认utf-8
     */
    private String charset = ConnectorProperties.getProperty(
            "connector.msg.charset", "utf-8");

    public static DispatcherServlet getInstance() {
        return INSTANCE;
    }

    public DispatcherServlet() {
        DispatcherServlet.INSTANCE = this;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
//        handlerId = config.getInitParameter("handlerId");
        appGroup = config.getInitParameter("appGroup");
        appName = config.getInitParameter("appName");
        msgFormat = config.getInitParameter("msgFormat");
        msgParser = config.getInitParameter("msgParser");
        if (StringUtils.isBlank(msgParser))
            msgParser = null;
        String ipFilter = config.getInitParameter("ipFilter");
        if (!StringUtils.isBlank(ipFilter))
        {
            String[] ips = ipFilter.split(",");
            if (ips != null && ips.length > 0)
            {
                ipFilterMap = new HashMap();
                for (String ip : ips)
                {
                    ipFilterMap.put(ip,ip);
                }
            }
        }
    }

    /**
     * main
     */
    protected void service(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {
        long start = System.currentTimeMillis();
        // 获取报文
        String inMsg = null;
        String outMsg = null;
        BufferedInputStream bin = null;
        ByteArrayOutputStream baos = null;
        OutputStream out = null;
        try {

            if (ipFilterMap != null)
            {
                String ip = request.getRemoteAddr();
                if (!ipFilterMap.containsKey(ip))
                {
                    outMsg = "IP 地址不合法，请求拒绝！";
                    out = response.getOutputStream();
                    out.write(outMsg.getBytes(this.charset));
                    return;
                }
            }

            // 设置PlatFormId
            SpringApplicationContext.getContext().getBean(MessageDistributor.class).setPlatFormId();
            InputStream in = request.getInputStream();
            byte[] buffer = new byte[8192];
            int bytes = 0;
            bin = new BufferedInputStream(in);
            baos = new ByteArrayOutputStream();
            while ((bytes = bin.read(buffer, 0, 8192)) != -1) {
                baos.write(buffer, 0, bytes);
            }
            byte[] b = baos.toByteArray();
            inMsg = new String(b, this.charset);
            if (logger.isDebugEnabled()) {
                logger.debug("Request message is...\n" + inMsg);
            }
            outMsg = SpringApplicationContext.getContext().getBean(MessageDistributor.class).execute(inMsg, appGroup, msgFormat,msgParser);
            if (outMsg == null) {
                String errMsg = "Response message is null.";
                if (logger.isErrorEnabled()) {
                    logger.error(errMsg);
                }
                throw new ServletException(errMsg);
            }
            if (logger.isDebugEnabled()) {
                logger.debug("Response message is...\n" + outMsg);
            }

            // 返回结果
            out = response.getOutputStream();
            out.write(outMsg.getBytes(this.charset));
        } catch (Throwable t) {
            if (logger.isErrorEnabled()) {
                logger.error("execution exception.\n"
                        + ExceptionUtils.getStackTrace(t));
            }
            throw new ServletException(t);
        } finally {
            if (logger.isInfoEnabled()) {
                logger.info("Service execute time is : "
                        + (System.currentTimeMillis() - start));
            }
            SpringApplicationContext.getContext().getBean(MessageDistributor.class).removePlatFormId();
            try {
                if (bin != null) {
                    bin.close();
                }
            } catch (IOException ioe1) {
                if (logger.isErrorEnabled()) {
                    logger.error(ExceptionUtils.getStackTrace(ioe1));
                }
            } finally {
                bin = null;
            }

            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException ioe2) {
                if (logger.isErrorEnabled()) {
                    logger.error(ExceptionUtils.getStackTrace(ioe2));
                }
            } finally {
                baos = null;
            }
            if (out != null) {
                out.close();
            }
        }
    }
}