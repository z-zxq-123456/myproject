package com.dcits.galaxy.adapter;

import com.dcits.galaxy.junit.TestBase;

/**
 * Created by Tim on 2015/10/22.
 */
public class TestAdapter extends TestBase {

    public void testHttp() {
        Adapter<String> adapter = context.getBean(HttpAdapter.class);
        String response = adapter.execute("");
        System.out.println(response);
    }
}
