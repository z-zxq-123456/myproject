package com.dcits.galaxy.base.util;

import com.dcits.galaxy.base.tuple.TwoTuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 错误资源文件加载，实现国际化定义
 *
 * @author Tim
 */
public class ErrorUtils {

    public enum PlaceHolder {
        // 忽略错误
        IGNORE,
        // 检查错误
        A,
        // 确认错误
        D,
        // 授权错误
        O;

        private static final String SEPARATOR = "%";
    }

    private static final Logger logger = LoggerFactory
            .getLogger(ErrorUtils.class);

    private static ResourceBundleMessageSource resourceBundle = new ResourceBundleMessageSource();

    /**
     * 中文
     */
    private static final String ZH_LANGUAGE = "CHINESE";

    /**
     * 英文
     */
    private static final String EN_LANGUAGE = "AMERICAN/ENGLISH";

    /*
    * 资源文件
    */
    private static final String FILE_KEYWORKS = "error";

    /*
     * JAR包中资源文件
     * modify by Tim 2015/12/07
     * PathMatchingResourcePatternResolver 读取jar包中的文件不只能直接读取根目录，增加error文件夹。
     * 所有模块的错误码配置必须放置此文件夹中
     *
     */
    private static final String JAR_RESOURCES = "classpath*:" + FILE_KEYWORKS + "/*" + FILE_KEYWORKS + "*.properties";

    /**
     * 资源文件
     * classPath根目录
     */
    private static final String RESOURCES = "classpath*:*" + FILE_KEYWORKS + "*.properties";

    static {
        try {
            // 获得资源文件
            PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
            List<String> nameListCn = new ArrayList<String>();

            Resource[] jarResources = patternResolver.getResources(JAR_RESOURCES);
            if (logger.isDebugEnabled())
                logger.debug("加载CLASSPATH下[error]文件夹错误码配置文件[" + jarResources.length + "]");
            for (Resource resource : jarResources) {
                String fileName = resource.getFilename();
                fileName = fileName.substring(0, fileName.indexOf(FILE_KEYWORKS) + 5);
                if (logger.isDebugEnabled())
                    logger.debug("加载[error]下错误码配置文件[" + resource.getFilename() + "][" + fileName + "]");
                nameListCn.add(FILE_KEYWORKS + "/" + fileName);
            }

            Resource[] resources = patternResolver.getResources(RESOURCES);
            if (logger.isDebugEnabled())
                logger.debug("加载CLASSPATH根目录错误码配置文件[" + resources.length + "]");
            for (Resource resource : resources) {
                String fileName = resource.getFilename();
                fileName = fileName.substring(0, fileName.indexOf(FILE_KEYWORKS) + 5);
                if (logger.isDebugEnabled())
                    logger.debug("加载错误码配置文件[" + resource.getFilename() + "][" + fileName + "]");
                nameListCn.add(fileName);
            }
            resourceBundle.setBasenames(nameListCn.toArray(new String[]{}));
            // 刷新缓存
            resourceBundle.setCacheSeconds(5);
        } catch (Throwable t) {
        }
    }

    /**
     * 获取错误码描述信息
     *
     * @param errCode
     *         错误码
     * @return
     */
    public static String getErrorDesc(String errCode) {
        return getErrorDesc(errCode, ZH_LANGUAGE);
    }

    /**
     * 获取错误码描述信息
     *
     * @param errCode
     *         错误码
     * @param userLang
     *         操作员语言<br>
     *         CHINESE－中文；<br>
     *         AMERICAN/ENGLISH－英文；
     * @return
     */
    public static String getErrorDesc(String errCode, String userLang) {
        String errDesc = "";
        try {
            if (null == userLang || ZH_LANGUAGE.equals(userLang))
                errDesc = resourceBundle.getMessage(errCode, null, Locale.SIMPLIFIED_CHINESE);
            else if (EN_LANGUAGE.equals(userLang))
                errDesc = resourceBundle.getMessage(errCode, null, Locale.US);
        } catch (NoSuchMessageException e) {
        }
        return errDesc;
    }

    /**
     * 获取错误码描述信息，默认中文错误码
     *
     * @param errCode
     *         错误码
     * @param args
     *         参数
     * @return
     */
    public static String getParseErrorDesc(String errCode, String... args) {
        return getParseErrorDesc(errCode, ZH_LANGUAGE, args);
    }

    /**
     * 获取错误码描述信息
     *
     * @param errCode
     *         错误码
     * @param userLang
     *         操作员语言<br>
     *         CHINESE－中文；<br>
     *         AMERICAN/ENGLISH－英文；
     * @param args
     *         参数
     * @return
     */
    public static String getParseErrorDesc(String errCode, String userLang, String... args) {
        String errDesc = "";
        try {
            if (null == userLang || ZH_LANGUAGE.equals(userLang))
                errDesc = resourceBundle.getMessage(errCode, args, Locale.SIMPLIFIED_CHINESE);
            else if (EN_LANGUAGE.equals(userLang))
                errDesc = resourceBundle.getMessage(errCode, args, Locale.US);
        } catch (NoSuchMessageException e) {
        }
        return errDesc;
    }

    /**
     * 获取占位符与实际的错误码信息
     *
     * @param errMsg
     * @return
     */
    public static TwoTuple<PlaceHolder, String> getPlaceHolder(String errMsg) {
        if (StringUtils.isEmpty(errMsg))
            return new TwoTuple<>(null, null);
        if (errMsg.startsWith(PlaceHolder.SEPARATOR)) {
            String[] errs = errMsg.split(PlaceHolder.SEPARATOR);
            if (errs.length == 3) {
                return new TwoTuple<>(PlaceHolder.valueOf(errs[1]), errs[2]);
            }
        }
        return new TwoTuple<>(null, errMsg);
    }
}
