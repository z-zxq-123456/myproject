package com.dcits.ensemble.om.pf.tools.code;

/**
 * 根据模板生成代码
 * <p/>
 * Created by Tim on 2015/6/17.
 */
public interface CreateCode {

    public static final String FILE_ENCODING = "UTF-8";

    /**
     * 文件生成目录
     *
     * @param tempPath
     * @param creater
     * @return
     */
    String createRootPath(String tempPath, String creater);

    /**
     * 写文件
     *
     * @param filePath
     * @param fileName
     * @param context
     * @return
     */
    void writeFile(String filePath, String fileName, String context);

}
