package com.dcits.orion.base.file;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.List;

import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.orion.api.IFileParse;
import com.dcits.orion.api.model.FileData;
import com.dcits.orion.base.model.FileConvert;

/**
 * Created by lixbb on 2016/3/10.
 */
public class FileParseUtils {
    /**
     * 获取整个文件数据
     *
     * @param path
     * @param fileName
     * @param convertName
     * @return
     */

    public static FileData getFileData(String path, String fileName, String convertName) {
        return getFileData(path, fileName, convertName, 0, Integer.MAX_VALUE);
    }


    public static FileData getFileData(String path, String fileName, String convertName, long pos, int num) {
        FileConvert fileConvert = FileConvertUtil.getFileConvert(convertName);
        IFileParse fileParse = getFileParse(fileConvert.getType());
        return fileParse.getFileData(path, fileName, convertName, pos, num);

    }

    /**
     * 获取整个文件对象的body集合
     *
     * @param path
     * @param fileName
     * @param convertName
     */

    public static <T> List<T> getBody(String path, String fileName, String convertName) {
        return getFileData(path, fileName, convertName).getBodyData();
    }

    /**
     * 获取body集合指定偏移量、行数的数据
     *
     * @param path        文件路径
     * @param fileName    文件名称
     * @param convertName 转换名称
     * @param pos         文件数据偏移量
     * @param num         文件行数
     * @return
     */

    public static <T> List<T> getBody(String path, String fileName, String convertName, long pos, int num) {
        return getFileData(path, fileName, convertName, pos, num).getBodyData();
    }

    public static <T> T getHead(String path, String fileName, String convertName) {
        FileConvert fileConvert = FileConvertUtil.getFileConvert(convertName);
        IFileParse fileParse = getFileParse(fileConvert.getType());
        return fileParse.getHead(path, fileName, convertName);
    }

    /**
     * 获取body集合指定偏移量、行数的数据
     *
     * @param path        文件路径
     * @param fileName    文件名称
     * @param convertName 转换名称
     * @return
     */
    public static <T> T getTail(String path, String fileName, String convertName) {
        FileConvert fileConvert = FileConvertUtil.getFileConvert(convertName);
        IFileParse fileParse = getFileParse(fileConvert.getType());
        return fileParse.getTail(path, fileName, convertName);
    }

    /**
     * 写指定格式文件
     *
     * @param path
     * @param fileName
     * @param convertName
     * @param fileData
     */
    public static void writeFile(String path, String fileName, String convertName, FileData fileData) {
        FileConvert fileConvert = FileConvertUtil.getFileConvert(convertName);
        IFileParse fileParse = getFileParse(fileConvert.getType());
        fileParse.writeFile(path, fileName, convertName, fileData);
    }

    /**
     * 写指定格式文件,只写body
     *
     * @param path
     * @param fileName
     * @param convertName
     * @param body
     */

    public static void writeBody(String path, String fileName, String convertName, List body) {
        writeBody(path, fileName, convertName, body, false);
    }

    public static void writeBody(String path, String fileName, String convertName, List body, boolean append) {
        FileConvert fileConvert = FileConvertUtil.getFileConvert(convertName);
        IFileParse fileParse = getFileParse(fileConvert.getType());
        fileParse.writeBody(path, fileName, convertName, body, append);
    }

    /**
     * 写文件头
     *
     * @param path
     * @param fileName
     * @param convertName
     * @param head
     */
    public static void writeHead(String path, String fileName, String convertName, Object head) {
        FileConvert fileConvert = FileConvertUtil.getFileConvert(convertName);
        IFileParse fileParse = getFileParse(fileConvert.getType());
        fileParse.writeHead(path, fileName, convertName, head);
    }

    /**
     * 写文件尾
     *
     * @param path
     * @param fileName
     * @param convertName
     * @param tail
     */
    public static void writeTail(String path, String fileName, String convertName, Object tail) {
        FileConvert fileConvert = FileConvertUtil.getFileConvert(convertName);
        IFileParse fileParse = getFileParse(fileConvert.getType());
        fileParse.writeTail(path, fileName, convertName, tail);

    }

    private static class MyFileFilter implements FileFilter {
        private String fileName;

        public MyFileFilter(String fileName) {
            this.fileName = fileName;
        }

        /**
         * Tests whether or not the specified abstract pathname should be
         * included in a pathname list.
         *
         * @param pathname The abstract pathname to be tested
         * @return <code>true</code> if and only if <code>pathname</code>
         * should be included
         */
        @Override
        public boolean accept(File pathname) {
            if (pathname.isDirectory()) {
                return false;
            } else {
                String curFileName = pathname.getName();
                if (StringUtils.isEquals(curFileName, fileName))
                    return false;
                else {
                    String[] fileNames = StringUtils.split(fileName, '.');
                    String[] curFileNames = StringUtils.split(curFileName, '.');
                    if (fileNames.length == curFileNames.length) {
                        for (int i = 0; i < fileNames.length; i++) {
                            if (fileNames.length == 1) {
                                if (!curFileNames[i].startsWith(fileNames[i]))
                                    return false;

                            } else {
                                if (i == fileNames.length - 2||i == fileNames.length - 1) {
                                    if (!curFileNames[i].startsWith(fileNames[i]))
                                        return false;

                                } else {
                                    if (!StringUtils.isEquals(curFileNames[i], fileNames[i]))
                                        return false;

                                }
                            }
                        }
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
    }

    /**
     * 文件合并
     *
     * @param path
     * @param fileName 合并规则示例：  文件名：acct_2012-22-23.txt
     *                 则将文件名是acct_2012-22-23XXXXX.txt的所有文件按字典顺序添加到acct_2012-22-23.txt
     */
    public static void fileCombine(String path, String fileName, String convertName) {
        FileConvert fileConvert = FileConvertUtil.getFileConvert(convertName);
        char[] br = StringUtils.getBr(fileConvert.getBr());
        File filePath = new File(path);
        File[] files = filePath.listFiles(new MyFileFilter(fileName));
        String[] fileNames = new String[files.length];
        int i = 0;
        for (File file : files) {
            fileNames[i++] = file.getName();
        }
        Arrays.sort(fileNames);
        int bufSize = 1024 * 8;
        boolean writeBr = false;
        try (FileOutputStream out = new FileOutputStream(path + "/" + fileName);
             FileChannel outChannel = out.getChannel()) {
            for (String subFileName : fileNames) {
                // 首行不添加换行符 modify for sonar
                if (writeBr) {
                    ByteBuffer brBb = StringUtils.getBytesByChars(br, fileConvert.getEncoding());
                    outChannel.write(brBb);
                }
                writeBr = true;
                try (FileInputStream in = new FileInputStream(path + "/" + subFileName);
                     FileChannel subFc = in.getChannel()) {
                    ByteBuffer bb = ByteBuffer.allocate(bufSize);
                    while (subFc.read(bb) != -1) {
                        bb.flip();
                        outChannel.write(bb);
                        bb.clear();
                    }
                }
            }

        } catch (Exception e) {
            throw new GalaxyException(e);
        }
    }

    /**
     * 文件删除
     *
     * @param path
     * @param fileName 删除规则示例：  文件名：acct_2012-22-23.txt
     *                 则将文件名是acct_2012-22-23XXXXX.txt的所有文件删除，而acct_2012-22-23.txt并不删除
     */
    public static void fileDeleteExceptSelf(String path, String fileName) {
        File filePath = new File(path);
        File[] files = filePath.listFiles(new MyFileFilter(fileName));
        for (File file : files) {
            file.delete();
        }
    }

    private static IFileParse getFileParse(String type) {
        if ("txt".equals(type)) {
            return SpringApplicationContext.getContext().getBean(TxtFileParse.class);
        } else if ("excel".equals(type)) {
            return SpringApplicationContext.getContext().getBean(ExcelFileParse.class);

        } else throw new GalaxyException("不支持的文件格式");

    }
}
