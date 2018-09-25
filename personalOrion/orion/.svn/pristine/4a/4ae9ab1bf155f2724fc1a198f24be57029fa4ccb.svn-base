package com.dcits.orion.api;

import com.dcits.orion.api.model.FileData;

import java.util.List;

/**
 * 文件解析接口
 * <p/>
 * Created by Tim on 2016/3/8.
 */
public interface IFileParse {

    /**
     * 获取整个文件数据，
     *
     * @param path
     * @param fileName
     * @param convertName
     * @param pos         文件数据偏移量,文本文件偏移字节位，excel偏移行号
     * @param num         文件行数
     * @return
     */
    FileData getFileData(String path, String fileName, String convertName,long pos, int num);

    <T> T getHead(String path, String fileName, String convertName);

    /**
     * 获取body集合指定偏移量、行数的数据
     *
     * @param path        文件路径
     * @param fileName    文件名称
     * @param convertName 转换名称
     * @return
     */
    <T> T getTail(String path, String fileName, String convertName);
    /**
     * 写指定格式文件
     *
     * @param path
     * @param fileName
     * @param convertName
     * @param <T>
     */
    <H, B , T > void writeFile(String path, String fileName, String convertName, FileData<H, B, T> fileData,boolean append);

    <H, B , T > void writeFile(String path, String fileName, String convertName, FileData<H, B, T> fileData);
    /**
     * 写指定格式文件,只写body
     *
     * @param path
     * @param fileName
     * @param convertName
     */
    void writeBody(String path, String fileName, String convertName, List body);

    void writeBody(String path, String fileName, String convertName, List body,boolean append);
    /**
     * 写文件头
     *
     * @param path
     * @param fileName
     * @param convertName
     */
    void writeHead(String path, String fileName, String convertName, Object head);
    /**
     * 写文件尾
     *
     * @param path
     * @param fileName
     * @param convertName
     */
    void  writeTail(String path, String fileName, String convertName, Object tail);




}
