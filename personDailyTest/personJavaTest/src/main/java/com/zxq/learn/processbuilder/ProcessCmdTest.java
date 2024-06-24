package com.zxq.learn.processbuilder;

import java.io.IOException;

/**
 * @desc: ProcessCmdTest  java执行命令行程序
 * @author: zhouxqh
 * @create: 2020-07-07 11:26
 **/
public class ProcessCmdTest {

    public static void main(String[] args) {
//            String[] cmds = {"ipconfig"};
            String[] cmds = {"curl","-XGET", "http://localhost:9200/_analyze"};
            ProcessBuilder ps = new ProcessBuilder(cmds);
        try {

            Process p =  ps.start();
            byte[] bytes = new byte[8000];
            while (p.getInputStream().read(bytes) != -1){
                System.out.println(new String(bytes,"ISO-8859-1"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
