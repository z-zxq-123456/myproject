package com.exampl.zxq.dubbo.adaptiveMethod;

import com.exampl.zxq.dubbo.utils.URL;

public class SimpleEx_impl2 implements SimpleEx {

    @Override
    public void echo(URL url, String s) {
        System.out.println("SimpleEx_impl2");
    }

    @Override
    public void bang(URL url, int i) {

    }
}
