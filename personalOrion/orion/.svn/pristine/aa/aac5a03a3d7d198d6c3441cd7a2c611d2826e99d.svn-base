package com.dcits.orion.base.util;

import com.dcits.galaxy.base.data.SysHead;
import junit.framework.TestCase;

/**
 * <p>Created on 2017/2/21.</p>
 *
 * @author Tim <guotao@dcits.com>
 * @version 1.0.0
 * @since 1.7
 */
public class ConvertUtilsTest extends TestCase {

    public void testInServiceCode() throws Exception {
        String sc = ConvertUtils.inServiceCode("aaa");
        assertEquals("aaa", sc);
        sc = ConvertUtils.inServiceCode("ServiceB");
        assertEquals("MbsdCore", sc);
        sc = ConvertUtils.inServiceCode("ServiceA");
        assertEquals("MbsdCore", sc);
        sc = ConvertUtils.inServiceCode("MbsdCore");
        assertEquals("MbsdCore", sc);
    }

    public void testOutServiceCode() throws Exception {
        SysHead sh = new SysHead();
        sh.setServiceCode("ServiceA");
        ConvertUtils.inServiceCode(sh);
        assertEquals("MbsdCore", sh.getServiceCode());
        ConvertUtils.outServiceCode(sh);
        assertEquals("ServiceA", sh.getServiceCode());

        sh.setServiceCode("SVR_FINANCIAL");
        ConvertUtils.inServiceCode(sh);
        assertEquals("Financial", sh.getServiceCode());
        ConvertUtils.outServiceCode(sh);
        assertEquals("SVR_FINANCIAL", sh.getServiceCode());

        sh.setServiceCode("MbsdCore");
        ConvertUtils.inServiceCode(sh);
        assertEquals("MbsdCore", sh.getServiceCode());
        ConvertUtils.outServiceCode(sh);
        assertEquals("MbsdCore", sh.getServiceCode());

        sh.setServiceCode("aaa");
        ConvertUtils.inServiceCode(sh);
        assertEquals("aaa", sh.getServiceCode());
        ConvertUtils.outServiceCode(sh);
        assertEquals("aaa", sh.getServiceCode());
    }
}