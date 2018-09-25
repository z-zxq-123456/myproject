/**
 * <p>Title: XmlUtil.java</p>
 * <p>Description: Xml序列化与反序列化工具类，XStream解析器的封装</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0
 */
package com.dcits.galaxy.base.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;

/**
 * @author Tim
 * @version V1.0
 * @description Xml序列化与反序列化工具类，XStream解析器的封装
 * @update 2014年9月15日 下午2:11:17
 */

public class XmlUtils {
    private static Logger logger = LoggerFactory
            .getLogger(XmlUtils.class);

    /**
     * @param obj
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年9月15日 下午2:13:12
     */
    public static String toXml(Object obj) {
        XStream xstream = new XStream();
        xstream.processAnnotations(obj.getClass());
        return xstream.toXML(obj);
    }

    /**
     * @param xmlStr
     * @param cls
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年9月15日 下午2:13:10
     */
    @SuppressWarnings("unchecked")
    public static <T> T toBean(String xmlStr, Class<T> cls) {
        XStream xstream = new XStream(new DomDriver());
        xstream.processAnnotations(cls);
        T obj = (T) xstream.fromXML(xmlStr);
        return obj;
    }

    /**
     * @param obj
     * @param absPath
     * @param fileName
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年9月15日 下午2:13:07
     */
    public static boolean toXMLFile(Object obj, String absPath, String fileName) {
        String strXml = toXml(obj);
        String filePath = absPath + fileName;
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                if (logger.isErrorEnabled())
                    logger.error("Create file {" + filePath + "} fail!"
                            + ExceptionUtils.getStackTrace(e));
                return false;
            }
        }
        OutputStream ous = null;
        try {
            ous = new FileOutputStream(file);
            ous.write(strXml.getBytes());
            ous.flush();
        } catch (Exception e1) {
            if (logger.isErrorEnabled())
                logger.error("Write file {" + filePath + "} fail!"
                        + ExceptionUtils.getStackTrace(e1));
            return false;
        } finally {
            if (ous != null)
                try {
                    ous.close();
                } catch (IOException e) {
                    logger.error("Write file {" + filePath + "} io excepiton!"
                            + ExceptionUtils.getStackTrace(e));
                }
        }
        return true;
    }

    /**
     * @param fileName
     * @param cls
     * @return
     * @throws Exception
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年9月15日 下午2:13:01
     */
    public static <T> T toBeanFromFile(String fileName, Class<T> cls)
            throws Exception {
        return toBeanFromFile("", fileName, cls);
    }

    /**
     * @param absPath
     * @param fileName
     * @param cls
     * @return
     * @throws Exception
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年9月15日 下午2:11:48
     */
    @SuppressWarnings("unchecked")
    public static <T> T toBeanFromFile(String absPath, String fileName,
                                       Class<T> cls) throws Exception {
        String filePath = absPath + fileName;
        InputStream in = null;
        FileInputStream fIns = null;
        BufferedInputStream ins = null;
        T obj = null;
        if (logger.isDebugEnabled())
            logger.debug("Parse file " + filePath);
        try {
            URL url = ClassLoaderUtils.getExtendResource(filePath);
            if (null != url) {
                fIns = new FileInputStream(new File(url.getFile()));
                in = fIns;
            } else {
                fIns = new FileInputStream(filePath);
                ins = new BufferedInputStream(fIns);
                in = ins;
            }
            XStream xstream = new XStream(new DomDriver("UTF-8"));
            xstream.processAnnotations(cls);
            obj = (T) xstream.fromXML(in);
        } catch (Exception e) {
            throw new Exception("Parse file {" + filePath + "} fail!", e);
        } finally {
            if (fIns != null)
                fIns.close();
            if (ins != null)
                ins.close();
        }
        return obj;
    }
}
