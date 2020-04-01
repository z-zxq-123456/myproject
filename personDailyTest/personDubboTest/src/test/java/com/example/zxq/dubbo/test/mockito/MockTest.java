package com.example.zxq.dubbo.test.mockito;

import org.mockito.Mockito;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

public class MockTest {

    public static void main(String[] args) throws IOException {
        List list = Mockito.mock(List.class);
        list.add(1);
        verify(list).add(1);//验证add(1)

        //2.2 模拟我们所期望的结果
        OutputStream outputStream =  Mockito.mock(OutputStream.class);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        //预设当流关闭时抛出异常
        doThrow(new IOException()).when(outputStream).close();
        outputStream.close();
    }
}
