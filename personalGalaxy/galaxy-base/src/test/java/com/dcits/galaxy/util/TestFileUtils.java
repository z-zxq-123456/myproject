package com.dcits.galaxy.util;

import com.dcits.galaxy.base.file.FilePositions;
import com.dcits.galaxy.base.tuple.TwoTuple;
import com.dcits.galaxy.base.util.FileUtils;
import com.dcits.galaxy.base.util.SeqUtils;
import com.dcits.galaxy.base.util.StringUtils;

import junit.framework.TestCase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Tim on 2016/1/20.
 */
public class TestFileUtils extends TestCase {

    private String filePatch = "e:/test2.txt";

    private File f = new File(filePatch);

    public void testWriteFile() throws IOException {
        long start = System.currentTimeMillis();
        // 使用BufferWriter写入1亿条记录，每行20位随机数，耗时43秒。文件大小1.95 GB
        BufferedWriter b = new BufferedWriter(new FileWriter(f));
        for (int i = 0; i < 100000000; i++) {
            if (i == 100000000 - 1)
                b.write(SeqUtils.getRandomNumber(20));
            else
                b.write(SeqUtils.getRandomNumber(20) + "\n");
            if (i % 10000 == 0) b.flush();
        }
        b.close();
        // 测试100000000行数据，获取行号需要7秒
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public void testWriteFile2() throws IOException {
        long start = System.currentTimeMillis();
        // 使用BufferWriter写入1亿条记录，每行20位随机数，耗时43秒。文件大小1.95 GB
        BufferedWriter b = new BufferedWriter(new FileWriter(f));
        for (int i = 0; i < 100000000; i++) {
            b.write(SeqUtils.getRandomNumber(20) + "\n");
            if (i % 100000 == 0) b.flush();
        }
        b.close();
        // 测试100000000行数据，获取行号需要7秒
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public void testWriteFile3() throws IOException {
        long start = System.currentTimeMillis();
        // 使用BufferWriter写入1亿条记录，每行20位随机数，耗时43秒。文件大小1.95 GB
        BufferedWriter b = new BufferedWriter(new FileWriter(f));
        int count = 2000000;
        String other = "|0.01|CNY||||||||||";
        for (int i = 0; i <= count; i++) {
            StringBuilder sb = new StringBuilder();
            String baseAcctNo = "740" + StringUtils.lfillStr(String.valueOf(i), 8, "0");
            if (i == count)
                b.write(sb.append(String.valueOf(i)).append("|").append("测试账户").append(baseAcctNo).append("|").append(baseAcctNo).append(other).toString());
            else {
                if (i == 0) {
                    b.write(sb.append(String.valueOf(count)).append("|").append(String.valueOf(count * 0.01)).append("\n").toString());
                } else if (i < 4) {
                    b.write(sb.append(String.valueOf(i)).append("|").append("UnKnow").append(baseAcctNo).append("|").append(baseAcctNo).append(other).append("\n").toString());
                } else {
                    b.write(sb.append(String.valueOf(i)).append("|").append("测试账户").append(baseAcctNo).append("|").append(baseAcctNo).append(other).append("\n").toString());
                }
            }
            if (i % 10000 == 0) b.flush();
        }
        b.close();
        // 测试100000000行数据，获取行号需要7秒
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }


    public void testGetRowCount() throws IOException {
        long start = System.currentTimeMillis();
        //1.95G文件，1亿条记录，7秒
        FileUtils.getRowCount(filePatch);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }


    public void testGetRowCount2() throws IOException {
        long start = System.currentTimeMillis();
        //1.95G文件，1亿条记录，默认64K缓冲区，14秒
        FileUtils.getRowCount(filePatch, 5000);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public void testGetRowCount3() throws IOException {
        long start = System.currentTimeMillis();
        //1.95G文件，1亿条记录， 1M缓冲区 24秒
        FileUtils.getRowCount(filePatch, 5000, 1024 * 1024 * 1);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public void testGetRowCount4() throws IOException {
        long start = System.currentTimeMillis();
        //1.95G文件，1亿条记录， 10、32、64、128K缓冲区 14秒左右
        FileUtils.getRowCount(filePatch, 5000, 1024 * 10);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public void testBufferedRandomAccessFileReadRows() throws IOException {
        TwoTuple<Long, List<FilePositions>> twoTuple = FileUtils.getRowCount(filePatch, 5000);
        System.out.println("count:" + twoTuple.first);
        for (FilePositions fPos : twoTuple.second) {
            long start = System.currentTimeMillis();
            List<String> row = FileUtils.bufferedRandomAccessFileReadRows(f, "UTF-8", fPos.getPos(), fPos.getNum());
            long end = System.currentTimeMillis();
            System.out.println(end - start);
        }
    }
}
