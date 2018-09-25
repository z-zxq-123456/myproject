package com.dcits.orion.convert;

import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.junit.TestBase;
import com.dcits.orion.api.model.FileData;
import com.dcits.orion.base.file.FileConvertUtil;
import com.dcits.orion.base.file.FileParseUtils;
import com.dcits.orion.base.map.mapping.ParserMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tim on 2016/3/8.
 */
public class TestFileConvert extends TestBase {

    public void testConvertLoad() {
        System.out.println(FileConvertUtil.getFileConvert("txtFormatExample"));
    }

    public void testSepParse1()
    {

        ParserMapping parserMapping = SpringApplicationContext.getContext().getBean(ParserMapping.class);
        System.out.println(parserMapping);

    }

    public void testSepParseJavaBean()
    {

        List body = FileParseUtils.getBody("E:/txt","CORE_CAPT_20150121.txt","exampleJavaBean",0,3);
        System.out.println(body.size());

    }

    public void testFixParse1()
    {

        List body = FileParseUtils.getBody("E:/txt","TEST_FIX_NO_HEAD.txt","fixNoHead",0,1);
        System.out.println(body.size());

    }

    public void testFixParseJavaBean()
    {

        List body = FileParseUtils.getBody("E:/txt","TEST_FIX_NO_HEAD.txt","exampleJavaBean",0,3);
        System.out.println(body.size());

    }

    public void testWrite()
    {
        Map head = new HashMap();
        head.put("HEAD","H");
        head.put("SYSTEM_ID","TEST");
        head.put("ROWCOUNT",10);
        head.put("RUNDATE","20150316");
        List body = new ArrayList();
        int size = 100;
        for (int i = 0 ; i < size; i++)
        {
            Map bodyMap = new HashMap();
            body.add(bodyMap);
            bodyMap.put("BODY", "B");
            bodyMap.put("BASE_ACCT_NO","账号");
            bodyMap.put("ACCT_TYPE","AIO");
            bodyMap.put("CCY","CNY");
            bodyMap.put("AMOUNT",1000000);
        }
        Map tail = new HashMap();
        tail.put("TAIL","T");
        tail.put("TOTAL_AMOUNT", body.size());
        FileData fileData = new FileData();
        fileData.setHeadData(head);
        fileData.setBodyData(body);
        fileData.setTailData(tail);
        Long begin = System.currentTimeMillis();
        FileParseUtils.writeFile("E:/txt", "testWrite.txt", "txtFormatExample", fileData);
        long end  = System.currentTimeMillis();
        System.out.println("写" + size + "条记录耗时" + (end - begin));
        begin = System.currentTimeMillis();
        FileData fd = FileParseUtils.getFileData("E:/txt", "testWrite.txt", "txtFormatExample",0,50);
        end  = System.currentTimeMillis();
        System.out.println("读10000000条记录的文件开始10000条记录耗时" + (end - begin));
        System.out.print("hello");
    }
    public void testCombin()
    {
        FileParseUtils.fileCombine("E:/txt/combin","aaa.txt","txtFormatExample");
    }

    public void testExcelRead()
    {
        FileData fileData = FileParseUtils.getFileData("E:/txt","TestExcel.xls","excelFormatExample");
        System.out.println(fileData);
    }

    public void testExcelWrite()
    {
        FileData fileData = FileParseUtils.getFileData("E:/txt","TestExcel.xls","excelFormatExample");
        for (int i = 0; i < 100000; i++)
        {
            Object o = fileData.getBodyData().get(0);
            fileData.getBodyData().add(o);
        }
        FileParseUtils.writeFile("E:/txt","TestExcel.xlsx","excelFormatExample",fileData);
    }

    public void testExcelWriteBody()
    {
        FileData fileData = FileParseUtils.getFileData("E:/txt","TestExcel.xls","excelFormatExample");

        FileParseUtils.writeBody("E:/txt","TestExcelBody.xlsx","excelFormatExample",fileData.getBodyData());
    }

    public void testExcelWriteHead()
    {
        FileData fileData = FileParseUtils.getFileData("E:/txt","TestExcel.xls","excelFormatExample");

        FileParseUtils.writeHead("E:/txt","TestExcelHead.xlsx","excelFormatExample",fileData.getHeadData());
    }

    public void testExcelWriteTail()
    {
        FileData fileData = FileParseUtils.getFileData("E:/txt","TestExcel.xls","excelFormatExample");

        FileParseUtils.writeTail("E:/txt","TestExcelTail.xlsx","excelFormatExample",fileData.getTailData());
    }


}
