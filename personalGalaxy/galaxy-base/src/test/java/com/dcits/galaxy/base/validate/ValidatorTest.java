package com.dcits.galaxy.base.validate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.galaxy.base.data.BaseRequest;
import com.dcits.galaxy.base.util.ClassLoaderUtils;
import com.dcits.galaxy.base.util.FileUtils;

import junit.framework.TestCase;

/**
 * Created by lixbb on 2016/5/16.
 */
public class ValidatorTest extends TestCase {
    private Non12009100In acct;

    @Override
    protected void setUp() {
        String filePath = ClassLoaderUtils.getResource("test.json").getFile();
        String jsonStr = FileUtils.readFile(filePath);
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        BaseRequest request = JSON.toJavaObject(jsonObject, Non12009100In.class);
        acct = JSONObject.parseObject(jsonStr, Non12009100In.class);
        System.out.println(acct.getSysHead());
    }

    public void testValidate() throws Exception {
        System.out.println(acct);
        long start = System.currentTimeMillis();
        Validator v = Validator.getInstance();
        v.validate(acct);
        System.out.println(System.currentTimeMillis() - start);
    }

    public void testValidate2() throws Exception {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            Validator v = Validator.getInstance();
            v.validate(acct);
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}