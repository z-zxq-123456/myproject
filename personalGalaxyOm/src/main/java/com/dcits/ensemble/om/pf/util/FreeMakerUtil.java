package com.dcits.ensemble.om.pf.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 * Created by Tim on 2015/9/17.
 */
public class FreeMakerUtil {

    /**
     * 根据模板生成文件内容
     *
     * @param templatePath
     * @param templateFile
     * @param inT
     * @return
     */
    public static String createTemplate(String templatePath, String templateFile, Map<String, Object> inT)
            throws IOException, TemplateException {
        Writer out = null;
        Configuration cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading(new File(templatePath));
        Template temp = cfg.getTemplate(templateFile);
        try {
            out = new StringWriter();
            temp.process(inT, out);
            out.flush();
            return out.toString();
        } finally {
            if (null != out)
                out.close();
        }
    }
}
