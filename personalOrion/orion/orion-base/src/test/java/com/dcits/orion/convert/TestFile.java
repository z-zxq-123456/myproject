package com.dcits.orion.convert;

import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.orion.base.file.FileConvertUtil;

import java.io.UnsupportedEncodingException;

/**
 * Created by lixbb on 2016/3/11.
 */
public class TestFile {
    public static void main(String[] arsg)
    {
        //FileData fileData = FileParseUtils.getFileData("E:/txt","TestExcel.xls","excelFormatExample");
        //System.out.println(fileData);
        System.out.println(StringUtils.parseInteger("1.0"));

        try {
            FileConvertUtil.getFileConvert("xxxx").getFileBody().setSeparator( new String(new byte[]{0x1b},"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}