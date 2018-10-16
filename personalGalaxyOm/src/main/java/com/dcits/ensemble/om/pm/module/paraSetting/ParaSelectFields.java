package com.dcits.ensemble.om.pm.module.paraSetting;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by maruie on 2016/04/06 17:47:52.
 */
public class ParaSelectFields extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is PARA_SELECT_FIELDS.TABLE_NAME 表名字
     */
    @TablePk(index = 1)
    private String tableName;

    /**
     * This field is PARA_SELECT_FIELDS.SELECT1 条件1
     */
    private String select1;

    /**
     * This field is PARA_SELECT_FIELDS.SELECT2 条件2
     */
    private String select2;

    /**
     * This field is PARA_SELECT_FIELDS.SELECT3 条件3
     */
    private String select3;

    /**
     * @return the value of  PARA_SELECT_FIELDS.TABLE_NAME 表名字
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName the value for PARA_SELECT_FIELDS.TABLE_NAME 表名字
     */
    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    /**
     * @return the value of  PARA_SELECT_FIELDS.SELECT1 条件1
     */
    public String getSelect1() {
        return select1;
    }

    /**
     * @param select1 the value for PARA_SELECT_FIELDS.SELECT1 条件1
     */
    public void setSelect1(String select1) {
        this.select1 = select1 == null ? null : select1.trim();
    }

    /**
     * @return the value of  PARA_SELECT_FIELDS.SELECT2 条件2
     */
    public String getSelect2() {
        return select2;
    }

    /**
     * @param select2 the value for PARA_SELECT_FIELDS.SELECT2 条件2
     */
    public void setSelect2(String select2) {
        this.select2 = select2 == null ? null : select2.trim();
    }

    /**
     * @return the value of  PARA_SELECT_FIELDS.SELECT3 条件3
     */
    public String getSelect3() {
        return select3;
    }

    /**
     * @param select3 the value for PARA_SELECT_FIELDS.SELECT3 条件3
     */
    public void setSelect3(String select3) {
        this.select3 = select3 == null ? null : select3.trim();
    }
}