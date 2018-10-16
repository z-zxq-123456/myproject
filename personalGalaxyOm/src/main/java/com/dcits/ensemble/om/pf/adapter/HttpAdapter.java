package com.dcits.ensemble.om.pf.adapter;

import com.dcits.galaxy.base.exception.GalaxyException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

/**
 * Created by Tim on 2015/9/21.
 */
public class HttpAdapter {

    private static final Logger log = LoggerFactory
            .getLogger(HttpAdapter.class);

    /**
     * http通讯接出
     *
     * @param str json格式字符串
     * @return
     */
    public static String doSubmit(String url, String str) {
        PostMethod pm = null;
        String out = null;
        ByteArrayInputStream bais = null;
        if (log.isDebugEnabled())
            log.debug("通讯接出：" + url);
        try {
            HttpClient hc = new HttpClient();
            pm = new PostMethod(url);
            // hc.getHttpConnectionManager().getParams().setConnectionTimeout(5 * 1000);
            pm.getParams().setSoTimeout(60 * 1000);
            bais = new ByteArrayInputStream(str.getBytes("UTF-8"));
            pm.setRequestEntity(new InputStreamRequestEntity(bais));
            int status = hc.executeMethod(pm);
            if (status != 200) {
                throw new GalaxyException("后台服务端执行出错：" + status);
            } else {
                byte[] b_out = pm.getResponseBody();
                out = new String(b_out, "UTF-8");
            }
        } catch (ConnectException e) {
            throw new GalaxyException("[" + url + "]后台服务未启动");
        } catch (SocketTimeoutException e) {
            throw new GalaxyException("获取响应超时");
        } catch (GalaxyException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (null != bais)
                    bais.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (null != pm) {
                pm.releaseConnection();
            }
        }
        return out;
    }


    /**
     * http通讯接出
     *
     * @param str json格式字符串
     * @return
     */
    public static String doPostMsg(String url, String str) {
        PostMethod pm = null;
        String out = null;
        ByteArrayInputStream bais = null;
        if (log.isDebugEnabled())
            log.debug("通讯接出：" + url);
        try {
            HttpClient hc = new HttpClient();
            pm = new PostMethod(url);
            // hc.getHttpConnectionManager().getParams().setConnectionTimeout(5 * 1000);
            pm.getParams().setSoTimeout(60 * 1000);
            bais = new ByteArrayInputStream(str.getBytes("UTF-8"));
            pm.setRequestEntity(new InputStreamRequestEntity(bais));
            int status = hc.executeMethod(pm);
            if (status != 200) {
                return ("ERROR:后台服务端执行出错：" + status);
            } else {
                byte[] b_out = pm.getResponseBody();
                out = new String(b_out, "UTF-8");
            }
        } catch (ConnectException e) {
            return ("ERROR:[" + url + "]后台服务未启动");
        } catch (SocketTimeoutException e) {
            return ("ERROR:获取响应超时");
        } catch (Exception e) {
            return ("ERROR:" + e.getMessage());
        } finally {
            try {
                if (null != bais)
                    bais.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (null != pm) {
                pm.releaseConnection();
            }
        }
        return out;
    }
}
