package com.dcits.galaxy.orion.common;

import com.dcits.orion.core.Context;
import com.dcits.galaxy.base.data.SysHead;

import junit.framework.TestCase;

/**
 * Created by Tim on 2016/9/20.
 */
public class ContextTest extends TestCase {

    public void testSerializeContext() throws Exception {
        Context context = new Context();
        SysHead sysHead = new SysHead();
        sysHead.setBranchId("0901");
        context.setSysHead(sysHead);
        context.setDtpFlag("Y");
        context.setIsBatch(true);
        System.out.println(Context.serializeContext(context));
    }

    public void testDeserialize() throws Exception {
        String contextJson = "{\"dtpFlag\":\"Y\",\"isBatch\":true,\"sysHead\":{\"branchId\":\"0901\"}}";
        Context context = Context.deserialize(contextJson);
        System.out.println(context);
    }
}