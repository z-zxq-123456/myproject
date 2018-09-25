package com.dcits.galaxy.orion.encrypt;

import com.dcits.galaxy.base.data.SysHead;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.junit.TestBase;
import com.dcits.orion.core.antirepeate.AntiRepeatUtil;
import com.dcits.orion.core.encrypt.EncryptUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lixbb on 2016/1/8.
 */
public class TestUnit extends TestBase {

    public Fin99990101In init() {
        Fin99990101In in = new Fin99990101In();
        in.setBody(new Fin99990101In.Body());
        in.setSysHead((SysHead) in.fieldTypeNewInstance("sysHead"));
        return in;
    }

    public void testGetwriteValue() throws Exception {
        Fin99990101In in = new Fin99990101In();
        in.setBody(new Fin99990101In.Body());
        in.setSysHead((SysHead) in.fieldTypeNewInstance("sysHead"));
        in.writeValue("body.acctNo", "1234");
        in.writeValue("sysHead.authUserId", "lixbb");
        System.out.println(in);
        System.out.println(in.readValue("body.acctNo"));
        System.out.println(in.readValue("sysHead"));
    }

    public void xxtestSplit() {
        System.out.println("hello.lxb.mon".split("\\.", 2).length);
    }

    //测试request 加解密
    public void testRequestEncrypt() throws Exception {
        Fin99990101In in = init();
        in.writeValue("sysHead.serviceCode", "SVR_FINANCIAL");
        in.writeValue("sysHead.messageType", "1200");
        in.writeValue("sysHead.messageCode", "6049");
        in.writeValue("body.acctNo", "1234");
        EncryptUtil encryptUtil = SpringApplicationContext.getContext().getBean(EncryptUtil.class);
        encryptUtil.requestEncryptDecrypt(in);
        System.out.println(in);
    }


    //测试request array对象 加解密
    public void testRequestArrayEncrypt() throws Exception {
        Fin99990101In in = init();
        in.writeValue("sysHead.serviceCode", "SVR_FINANCIAL");
        in.writeValue("sysHead.messageType", "1200");
        in.writeValue("sysHead.messageCode", "6048");
        in.writeValue("body.acctNo", "1234");
        List<Fin99990101In.Item> itemList = new ArrayList<Fin99990101In.Item>();
        for (int i = 0; i < 20; i++) {
            Fin99990101In.Item item = new Fin99990101In.Item();
            item.setPassword("password" + i);
            itemList.add(item);
        }
        in.writeValue("body.items", itemList);
        EncryptUtil encryptUtil = SpringApplicationContext.getContext().getBean(EncryptUtil.class);
        encryptUtil.requestEncryptDecrypt(in);
        System.out.println(in);
    }

    //测试request array 嵌套 array 加解密
    public void testRequestArraysEncrypt() throws Exception {
        Fin99990101In in = init();
        in.writeValue("sysHead.serviceCode", "SVR_FINANCIAL");
        in.writeValue("sysHead.messageType", "1200");
        in.writeValue("sysHead.messageCode", "6048");
        in.writeValue("body.acctNo", "1234");
        List<Fin99990101In.Item> itemList = new ArrayList<Fin99990101In.Item>();
        for (int i = 0; i < 20; i++) {
            Fin99990101In.Item item = new Fin99990101In.Item();
            List<Fin99990101In.Acct> accts = new ArrayList<Fin99990101In.Acct>();
            for (int j = 0; j < 10; j++) {
                Fin99990101In.Acct acct = new Fin99990101In.Acct();
                acct.setAcctNo("acct" + j);
                accts.add(acct);
            }
            item.setAccts(accts);
            item.setPassword("password" + i);
            itemList.add(item);
        }
        in.writeValue("body.items", itemList);
        EncryptUtil encryptUtil = SpringApplicationContext.getContext().getBean(EncryptUtil.class);
        encryptUtil.requestEncryptDecrypt(in);
        System.out.println(in);
    }

    //测试request array 嵌套 struts 加解密
    public void testRequestArrayStrutsEncrypt() throws Exception {
        Fin99990101In in = init();
        in.writeValue("sysHead.serviceCode", "SVR_FINANCIAL");
        in.writeValue("sysHead.messageType", "1200");
        in.writeValue("sysHead.messageCode", "6051");
        in.writeValue("body.acctNo", "1234");
        List<Fin99990101In.Item> itemList = new ArrayList<Fin99990101In.Item>();
        for (int i = 0; i < 20; i++) {
            Fin99990101In.Item item = new Fin99990101In.Item();
            Fin99990101In.Acct acct = new Fin99990101In.Acct();
            acct.setAcctNo("itemAcctno" + i);
            item.setAcct(acct);
            item.setPassword("password" + i);
            itemList.add(item);
        }
        in.writeValue("body.items", itemList);
        EncryptUtil encryptUtil = SpringApplicationContext.getContext().getBean(EncryptUtil.class);
        encryptUtil.requestEncryptDecrypt(in);
        System.out.println(in);
    }

    //测试request struct 嵌套 struct 加解密
    public void testRequestStructAndstruct() throws Exception {
        Fin99990101In in = init();
        in.writeValue("sysHead.serviceCode", "SVR_FINANCIAL");
        in.writeValue("sysHead.messageType", "1200");
        in.writeValue("sysHead.messageCode", "6052");
        in.writeValue("body.acctNo", "1234");

        Fin99990101In.Item item = new Fin99990101In.Item();
        Fin99990101In.Acct acct = new Fin99990101In.Acct();
        in.writeValue("body.item", item);
        in.writeValue("body.item.acct", acct);
        in.writeValue("body.item.acct.acctNo", "62257674110234");
        EncryptUtil encryptUtil = SpringApplicationContext.getContext().getBean(EncryptUtil.class);
        encryptUtil.requestEncryptDecrypt(in);
        System.out.println(in);
    }

    public void testResponse() throws Exception {
        Fin99990101In in = init();
        in.writeValue("sysHead.serviceCode", "SVR_FINANCIAL");
        in.writeValue("sysHead.messageType", "1200");
        in.writeValue("sysHead.messageCode", "6001");
        in.writeValue("body.acctNo", "1234");

        Fin99990101Out out = new Fin99990101Out();
        out.setPassword("toEncrypt");
        out.setAcctName("acctName:lixb");
        EncryptUtil encryptUtil = SpringApplicationContext.getContext().getBean(EncryptUtil.class);
        encryptUtil.responseEncryptDecrypt(in, out);
        System.out.println(out);
    }

    public void testResponseArray() throws Exception {
        Fin99990101In in = init();
        in.writeValue("sysHead.serviceCode", "SVR_FINANCIAL");
        in.writeValue("sysHead.messageType", "1200");
        in.writeValue("sysHead.messageCode", "6002");
        in.writeValue("body.acctNo", "1234");

        Fin99990101Out out = new Fin99990101Out();
        out.setPassword("toEncrypt");
        out.setAcctName("acctName:lixb");
        List<Fin99990101Out.OutItem> items = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Fin99990101Out.OutItem item = new Fin99990101Out.OutItem();
            item.setPassword("response password in items" + i);
            items.add(item);
        }
        out.setItems(items);
        EncryptUtil encryptUtil = SpringApplicationContext.getContext().getBean(EncryptUtil.class);
        encryptUtil.responseEncryptDecrypt(in, out);
        System.out.println(out);
    }


    public void testResponseArrays() throws Exception {
        Fin99990101In in = init();
        in.writeValue("sysHead.serviceCode", "SVR_FINANCIAL");
        in.writeValue("sysHead.messageType", "1200");
        in.writeValue("sysHead.messageCode", "6002");
        in.writeValue("body.acctNo", "1234");

        Fin99990101Out out = new Fin99990101Out();
        out.setPassword("toEncrypt");
        out.setAcctName("acctName:lixb");
        List<Fin99990101Out.OutItem> items = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Fin99990101Out.OutItem item = new Fin99990101Out.OutItem();
            item.setPassword("response password in items" + i);
            items.add(item);
            List<Fin99990101Out.OutAcct> accts = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                Fin99990101Out.OutAcct acct = new Fin99990101Out.OutAcct();
                acct.setAcctNo("acct no in Array and array");
                accts.add(acct);
            }
            item.setAccts(accts);
        }
        out.setItems(items);
        EncryptUtil encryptUtil = SpringApplicationContext.getContext().getBean(EncryptUtil.class);
        encryptUtil.responseEncryptDecrypt(in, out);
        System.out.println(out);
    }


    public void testResponseStruct() throws Exception {


        Fin99990101In in = init();
        in.writeValue("sysHead.serviceCode", "SVR_FINANCIAL");
        in.writeValue("sysHead.messageType", "1200");
        in.writeValue("sysHead.messageCode", "6003");
        in.writeValue("body.acctNo", "1234");

        Fin99990101Out out = new Fin99990101Out();
        out.setPassword("toEncrypt");
        out.setAcctName("acctName:lixb");

        Fin99990101Out.OutItem item = new Fin99990101Out.OutItem();
        Fin99990101Out.OutAcct acct = new Fin99990101Out.OutAcct();

        out.writeValue("item", item);
        out.writeValue("item.acct", acct);
        out.writeValue("item.acct.acctNo", "acctNo in item.acct");
        EncryptUtil encryptUtil = SpringApplicationContext.getContext().getBean(EncryptUtil.class);
        encryptUtil.responseEncryptDecrypt(in, out);
        System.out.println(out);
    }

    int times = 2000000000;
    String value = "value";

    public void testSetValue() throws Exception {

        long begin = new Date().getTime();
        for (int i = 0; i < times; i++) {
            Fin99990101In in = init();
            in.writeValue("body.acctNo", value);
        }
        long end = new Date().getTime();
        System.out.println(end - begin);
    }

    public void testSelfSet() throws Exception {

        long begin = new Date().getTime();
        for (int i = 0; i < times; i++) {
            Fin99990101In in = init();
            in.getBody().setAcctNo(value);
        }
        long end = new Date().getTime();
        System.out.println(end - begin);
    }

    public void testGetValue() throws Exception {
        Fin99990101In in = init();
        long begin = new Date().getTime();
        for (int i = 0; i < times; i++) {
            String str = (String) in.readValue("body.acctNo");
        }
        long end = new Date().getTime();
        System.out.println(end - begin);
    }

    public void testSelfGet() throws Exception {
        Fin99990101In in = init();
        long begin = new Date().getTime();
        for (int i = 0; i < times; i++) {
            String str = in.getBody().getAcctNo();
        }
        long end = new Date().getTime();
        System.out.println(end - begin);
    }

    public void testSel() throws Exception {
        long begin = new Date().getTime();
        for (int i = 0; i < times; i++) {
            Fin99990101In in = init();
            Field f = in.getClass().getDeclaredField("switch1");
            //f.setAccessible(true);
            f.set(in, "11111");
        }
        long end = new Date().getTime();
        System.out.println(end - begin);
    }

    public void testGetSet() throws Exception {
        long begin = new Date().getTime();
        for (int i = 0; i < times; i++) {
            Fin99990101In in = init();
            in.writeValue("switch", "ssss");
        }
        long end = new Date().getTime();
        System.out.println(end - begin);
    }


    //测试
    public void testAa() throws Exception {
        AntiRepeatUtil antiRepeatUtil = SpringApplicationContext.getContext().getBean(AntiRepeatUtil.class);
        System.out.println(antiRepeatUtil.switchOn());
    }

}
