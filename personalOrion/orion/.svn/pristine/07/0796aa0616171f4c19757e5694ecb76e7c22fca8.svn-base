package com.dcits.orion.base.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.util.FileUtils;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.orion.api.model.FileData;
import com.dcits.orion.base.model.Field;
import com.dcits.orion.base.model.FileConvert;
import com.dcits.orion.base.model.FileRow;

/**
 * Created by lixbb on 2016/3/9.
 */
@Repository
public class TxtFileParse extends AbstractFileParse  {


    private static final Logger logger = LoggerFactory
            .getLogger(TxtFileParse.class);
    /**
     * 获取整个文件数据
     *
     * @param path
     * @param fileName
     * @param convertName
     * @return
     */
    public FileData getFileData(String path, String fileName, String convertName,long pos, int num) {
        FileConvert fileConvert = FileConvertUtil.getFileConvert(convertName);
        FileData fileData = new FileData();
        FileRow headRow = fileConvert.getFileHead();
        FileRow bodyRow = fileConvert.getFileBody();
        FileRow tailRow = fileConvert.getFileTail();
        File file = new File(path + "/" + fileName);

        boolean lastPage = false;//是否是最后一页数据

        List<String> rows = FileUtils.bufferedRandomAccessFileReadRows(file, fileConvert.getEncoding(), pos, num);
        if (rows == null)
            return null;
        int rowCount = rows.size();

        if (num > rowCount)
            lastPage = true;
        else {
            if (tailRow != null)
            {
                List<String> newRows = FileUtils.bufferedRandomAccessFileReadRows(file, fileConvert.getEncoding(), pos, num+1);
                if (rows.size() == newRows.size())
                {
                    lastPage = true;
                }
            }
        }


        List body = new ArrayList();
        fileData.setBodyData(body);
        for (int index = 0;index < rowCount; index++)
        {
            String rowTxt = rows.get(index);
            try {
                if (pos == 0 && index == 0 && headRow != null && rowAccept(headRow, rowTxt)) {
                    Object head = getData(rowTxt, headRow);
                    fileData.setHeadData(head);
                } else if (index == rowCount - 1 && tailRow != null && rowAccept(tailRow, rowTxt) && lastPage) {
                        Object tail = getData(rowTxt, tailRow);
                        fileData.setTailData(tail);
                } else if (rowAccept(bodyRow, rowTxt)) {
                    Object bodyObj = getData(rowTxt, bodyRow);
                    body.add(bodyObj);
                }
            }
            catch (Exception e)
            {
                throw new GalaxyException("文件解析失败，行内容：<" + rowTxt + ">偏移" + pos + "后第" + index + "行，错误原因：" + e.getMessage());
            }
        }
        return fileData;
    }
    @Override
    public <T> T getHead(String path, String fileName, String convertName) {
        FileData fileData = getFileData(path, fileName, convertName, 0, 1);
        return (T)fileData.getHeadData();
    }

    /**
     * 获取文件尾
     *
     * @param path        文件路径
     * @param fileName    文件名称
     * @param convertName 转换名称
     * @return
     */
    @Override
    public  <T> T getTail(String path, String fileName, String convertName) {
        String strTail = FileUtils.getLastRow(path + "/" + fileName);
        if (StringUtils.isBlank(strTail))
            return null;
        else
        {
            FileConvert fileConvert = FileConvertUtil.getFileConvert(convertName);
            return (T)getData(strTail,fileConvert.getFileTail());
        }
    }

    /**
     * 写指定格式文件
     *
     * @param path
     * @param fileName
     * @param convertName
     * @param fileData
     */
    @Override
    public <H, B, T> void writeFile(String path, String fileName, String convertName, FileData<H, B, T> fileData,boolean append) {
        long begin = System.currentTimeMillis();
        FileConvert fileConvert = FileConvertUtil.getFileConvert(convertName);
        PrintWriter out = null;
        mkdir(path);
        boolean writeBr = fileExist(path,fileName) && append;
        try {
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path+"/"+fileName,append),fileConvert.getEncoding())));
            if (fileConvert.getFileHead() != null && fileData.getHeadData() != null)
            {
                String txt = getString(fileData.getHeadData(),fileConvert.getFileHead());
                write(out, txt, fileConvert.getBr(),writeBr);
                writeBr = true;
            }
            if (fileConvert.getFileBody() != null && fileData.getBodyData() != null)
            {
                List body = fileData.getBodyData();
                StringBuffer sb = new StringBuffer();
                for (Object object : body)
                {
                    String txt = getString(object,fileConvert.getFileBody());
                    //write(out,txt,fileConvert.getBr(),writeBr);
                    if (writeBr)
                        sb.append(StringUtils.getBr(fileConvert.getBr()));
                    sb.append(txt);
                    writeBr = true;
                }
                write(out,sb.toString(),fileConvert.getBr(),false);
            }
            if (fileConvert.getFileTail() != null && fileData.getTailData() != null)
            {
                String txt = getString(fileData.getTailData(),fileConvert.getFileTail());
                write(out,txt,fileConvert.getBr(),writeBr);
            }
        } catch (UnsupportedEncodingException e) {
            throw new GalaxyException("文件编码格式不支持");
        } catch (FileNotFoundException e) {
            throw new GalaxyException("文件不存在");
        }
        finally {
            if (out != null)
                out.close();
        }
        if (logger.isInfoEnabled())
            logger.info("写文件执行时间:" + (System.currentTimeMillis() - begin));
    }

    private boolean fileExist(String path, String fileName)
    {
        File file = new File(path+"/"+fileName);
        if (!file.exists())
            return false;
        if (file.length() == 0)
            return false;
        else return true;
    }

    @Override
    public <H, B, T> void writeFile(String path, String fileName, String convertName, FileData<H, B, T> fileData) {
        writeFile(path,fileName,convertName,fileData,false);
    }


    private void write(PrintWriter out,String txt, String br,boolean writeBr)
    {
        if (writeBr)
        {
          out.write(StringUtils.getBr(br));
        }
        out.print(txt);


    }


    /**
     * 根据行对象，生成行字符串
     *
     * @param object
     * @param fileRow
     */
    private String getString(Object object,FileRow fileRow)
    {
        StringBuffer sb = new StringBuffer();
        if (!StringUtils.isBlank(fileRow.getIdent()))
        {
            sb.append(fileRow.getIdent());
        }
        for (Field field : fileRow.getFields())
        {
            String fieldValue = getValue(object,field);
            if ("F".equals(fileRow.getSepType()))
            {
                fieldValue = StringUtils.pd(fieldValue, field.getLr(), field.getPd(), field.getLength());
            }
            else if ("S".equals(fileRow.getSepType()))
            {
                if (sb.length() > 0)
                    sb.append(fileRow.getSeparator());
            }
            else
            {
                throw new GalaxyException("字段分隔类型不正确");
            }
            sb.append(fieldValue);
        }
        return sb.toString();
    }



    /**
     * 获得一行记录对应的对象
     *
     * @param txt 字符串
     * @param fileRow 行定义
     */
    private Object getData(String txt, FileRow fileRow)
    {
        Object o = newBean(fileRow.getClazz());
        if ("F".equals(fileRow.getSepType()))
        {
            int offset = 0;
            if (fileRow.getIdent() != null && fileRow.getIdent().length() > 0)
                offset = fileRow.getIdent().length();
            for (Field field : fileRow.getFields())
            {
                try {

                    String strFieldValue = txt.substring(offset,offset+field.getLength());
                    setValue(o,field,strFieldValue);
                    offset =  offset + field.getLength();
                }
                catch (Exception e)
                {
                    throw new GalaxyException(e);
                }
            }
        }
        else if ("S".equals(fileRow.getSepType()))
        {
            String separator = fileRow.getSeparator();
            if (StringUtils.isBlank(separator))
                throw new GalaxyException("分隔符未定义");
            separator = separator.replace("\\","\\\\")
                        .replace("|", "\\|")
                        .replace("+","\\+")
                        .replace("*","\\*")
                        .replace("^","\\^")
                        .replace("$","\\$")
                        .replace("/", "\\/")
                        .replace("[","\\[")
                        .replace("]","\\]")
                        .replace("(","\\(")
                        .replace(")","\\)")
                        .replace("-","\\-")
                        .replace(".","\\.");
            String[] fieldValues = txt.split(separator);
            int i = 0;
            if (fileRow.getIdent() != null && fileRow.getIdent().length() > 0)
                i = 1;
            for (Field field : fileRow.getFields())
            {
                if (i < fieldValues.length)
                {
                    String strFieldValue = fieldValues[i];
                    setValue(o,field,strFieldValue);
                }
                else {
                    setValue(o,field,"");
                }
                i++;
            }
        }
        else
        {
            throw new GalaxyException("字段分隔类型不正确");
        }
        return o;
    }

    private boolean rowAccept(FileRow fileRowRow,String txt)
    {
        if (StringUtils.isBlank(fileRowRow.getIdent()))
            return true;
        else if (txt.startsWith(fileRowRow.getIdent()))
            return true;
        else return false;
    }
}
