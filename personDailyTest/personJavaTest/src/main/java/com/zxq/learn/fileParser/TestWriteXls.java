package com.zxq.learn.fileParser;

/**
 * Created{ by zhouxqh} on 2018/1/8.
 */
public class TestWriteXls {
    public static void main(String[]args){

        String path = "/testExcel.xls";

        ReadFileUtils readFileUtils  = new ReadFileUtils();
        String title[] = {"id","name","password"};
        try {
            readFileUtils.createExcel(path,"sheet1",title);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
