package com.dcits.orion.core.dao;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;
import junit.framework.TestCase;

/**
 * <p>Created on 2017/4/6.</p>
 *
 * @author Tim <guotao@dcits.com>
 * @version 1.0.0
 * @since 1.7
 */
public class DataAccessorTest extends TestCase {

    public static class Test1 extends AbstractBean {
        @TablePk(index = 1)
        private String pk1;

        @TablePk(index = 2)
        private String pk2;

        @TablePk(index = 3)
        private String pk3;

        public String getPk1() {
            return pk1;
        }

        public void setPk1(String pk1) {
            this.pk1 = pk1;
        }

        public String getPk3() {
            return pk3;
        }

        public void setPk3(String pk3) {
            this.pk3 = pk3;
        }

        public String getPk2() {
            return pk2;
        }

        public void setPk2(String pk2) {
            this.pk2 = pk2;
        }
    }

    public void testParsePks() throws Exception {
        DataAccessor da = new DataAccessor();
        System.out.println(da.parsePks(new Test1(), true, new Object[]{
                "111", null, null, ""
        }));
    }

}