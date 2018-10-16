package com.dcits.ensemble.om.pf.common;


import com.dcits.galaxy.base.util.ClassLoaderUtils;

/**
 * Created by Tim on 2015/6/15.
 */
public class ToolsConstants {

    public static final String MYSQL_JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public static final String ORACLE_JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";

    public static final String CODE_DIR = "createcode";

    public static final String MBSD_DIR = "mbsdinterface";

    public static final String TEMPLATE_DIR = "template";

    public static final String FILE_SEPARATOR = System.getProperty("file.separator");

    public static final String CODE_CREATE_DIR = "tools.code.create.dir";

    public static final String CLAZZ_ROOT_PATH = ClassLoaderUtils.getResource("").getFile();

    public static final String TRANS_DIRECTION_IN = "In";

    public static final String TRANS_DIRECTION_OUT = "Out";

    public static final String downFolderName = "downloadFiles";

    public static final String uploadFolderName = "uploadFiles";

    public static final String headFileName = "/uploadFiles/MBSD_SYS.xls";
}
