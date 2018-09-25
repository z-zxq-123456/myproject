package com.dcits.orion.base.model;

import com.dcits.galaxy.base.bean.AbstractBean;

/**
 * 文件转换格式定义
 * <p/>
 * Created by Tim on 2016/3/8.
 */
public class FileConvert extends AbstractBean {

    private static final long serialVersionUID = -2946565311309617753L;
    /**
     * 文件格式名称（不可重复）
     */
    private String name;

    /**
     * 文件类型
     * txt ,excel
     */
    private String type;

    /**
     * 文件编码
     * 默认UTF-8
     * UTF-8，GBK
     */
    private String encoding = "UTF-8";

    /**
     * 换行符
     */
    private String br = "\r\n";

    /**
     * 文件头格式
     */
    private FileHead fileHead;

    /**
     * 文件体格式
     */
    private FileBody fileBody;

    /**
     * 文件尾格式
     */
    private FileTail fileTail;



    /**
     * Excel Sheet Name
     */
    private String sheetName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getBr() {
        return br;
    }

    public void setBr(String br) {
        this.br = br;
    }

    public FileHead getFileHead() {
        return fileHead;
    }

    public void setFileHead(FileHead fileHead) {
        this.fileHead = fileHead;
    }

    public FileBody getFileBody() {
        return fileBody;
    }

    public void setFileBody(FileBody fileBody) {
        this.fileBody = fileBody;
    }

    public FileTail getFileTail() {
        return fileTail;
    }

    public void setFileTail(FileTail fileTail) {
        this.fileTail = fileTail;
    }
    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }
}
