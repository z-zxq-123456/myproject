package com.zxq.learn.fileParser;

import java.io.FileNotFoundException;
import java.util.Map;

/**
 * 读取xls/xlsx文件
 * Created{ by zhouxqh} on 2018/1/5.
 */
public class TestReadXls {

    public static void main(String[]args){

        String path = "/Open2";

        // 对读取Excel表格标题测试
        try {
            ReadFileUtils readFileUtils = new ReadFileUtils(path);
            String[] title = readFileUtils.readExcelFile();
            System.out.println("获得Excel表格的标题:");
            for (String s : title) {
                System.out.print(s + " ");
            }
            System.out.println();
        // 对读取Excel表格内容测试
            Map<Integer, Map<Integer,Object>> map = readFileUtils.readExcelContent();
            System.out.println("获得Excel表格的内容:");
            for (int i = 1; i <= map.size(); i++) {
                System.out.println(map.get(i));
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("未找到指定路径的文件!");
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
