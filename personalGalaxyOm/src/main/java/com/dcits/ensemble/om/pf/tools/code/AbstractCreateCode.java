package com.dcits.ensemble.om.pf.tools.code;

import com.dcits.ensemble.om.pf.common.ToolsConstants;
import com.dcits.galaxy.base.util.DateUtils;
import com.dcits.galaxy.base.util.FileUtils;

import java.io.File;

/**
 * Created by Tim on 2015/6/17.
 */
public abstract class AbstractCreateCode implements CreateCode {
    /**
     * 文件根目录
     */
    protected String rootPath;

    /**
     * 工程目录
     */
    protected String projectPath;

    /**
     * 输出文件名
     */
    protected String fileName;

    /**
     * 模块
     */
    protected String module;

    public AbstractCreateCode(String module, String rootPath) {
        this.module = module;
        this.rootPath = rootPath;
    }

    /**
     * 文件生成目录
     *
     * @param tempPath
     * @param creater
     * @return
     */
    @Override
    public String createRootPath(String tempPath, String creater) {
        this.rootPath = new StringBuffer().append(tempPath).append(ToolsConstants.FILE_SEPARATOR)
                .append(ToolsConstants.downFolderName).append(ToolsConstants.FILE_SEPARATOR)
                .append(ToolsConstants.CODE_DIR).append(ToolsConstants.FILE_SEPARATOR)
                .append(DateUtils.getDate()).append(ToolsConstants.FILE_SEPARATOR)
                .append(creater).append(ToolsConstants.FILE_SEPARATOR)
                .append(String.valueOf(System.currentTimeMillis())).append(ToolsConstants.FILE_SEPARATOR).toString();
        //System.out.println("source code generate rootPath:" + rootPath);
        return this.rootPath;
    }

    /**
     * 写文件
     *
     * @param filePath
     * @param context
     * @return
     */
    @Override
    public void writeFile(String filePath, String fileName, String context) {
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileUtils.writeFile(filePath + fileName, context, FILE_ENCODING);
    }

    /**
     * 文件存放工程Path
     *
     * @return
     */
    public abstract String createProjectPath(String user);
}
