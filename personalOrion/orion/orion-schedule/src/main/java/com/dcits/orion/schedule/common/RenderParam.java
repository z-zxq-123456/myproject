/**
 * <p>Title: RenderParam.java</p>
 * <p>Description: 对配置文件的参数进行渲染，使用Velocity实现动态参数配置。 <br>
 * 如${sysdate}，使用当前服务器日期替换</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0
 */

package com.dcits.orion.schedule.common;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.dcits.galaxy.base.util.DateUtils;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Tim
 * @version V1.0
 * @description 对配置文件的参数进行渲染，使用Velocity实现动态参数配置。 <br>
 * 如${sysdate}，使用当前服务器日期替换
 * @update 2014年9月15日 下午2:26:32
 */

public class RenderParam {

    private static Logger logger = LoggerFactory.getLogger(RenderParam.class);

    private static Map<String, Object> root = new HashMap<String, Object>();

    static {
        root.put("datetool", DateUtils.class);
        root.put("sysdate", DateUtils.getDate());
    }

    public static String render(String template, Map<String, Object> args) {
        if (logger.isDebugEnabled())
            logger.debug("render args is " + args.toString());
        String renderStr = template;
        Configuration cfg = new Configuration();
        StringTemplateLoader stringLoader = new StringTemplateLoader();
        stringLoader.putTemplate("template", renderStr);
        cfg.setTemplateLoader(stringLoader);
        StringWriter writer = new StringWriter();
        try {
            Template t = cfg.getTemplate("template", "UTF-8");
            t.process(args, writer);
            renderStr = writer.toString();
        } catch (TemplateException | IOException e) {
        }
        return renderStr;
    }

    public static String render(String template) {
        return render(template, root);
    }
}
