package com.exampl.zxq.dubbo.adaptiveMethod;

import com.exampl.zxq.dubbo.extension.annotation.Adaptive;
import com.exampl.zxq.dubbo.extension.annotation.SPI;
import com.exampl.zxq.dubbo.utils.URL;

@SPI("impl1")
public interface SimpleEx {

    @Adaptive
    public void echo(URL url,String s);

    public void bang(URL url ,int i);
}
