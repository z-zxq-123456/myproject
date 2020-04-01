package com.exampl.zxq.dubbo.logUtil;


import com.exampl.zxq.dubbo.extension.annotation.Adaptive;

@Adaptive
public class Slf4JLog implements LogAdapter {
    public void printLog() {
        System.out.println("Slf4JLog start!");
    }
}
