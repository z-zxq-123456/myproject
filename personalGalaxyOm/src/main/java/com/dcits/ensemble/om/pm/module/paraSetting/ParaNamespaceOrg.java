package com.dcits.ensemble.om.pm.module.paraSetting;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by maruie on 2016/05/18 17:59:55.
 */
public class ParaNamespaceOrg extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is PARA_NAMESPACE_ORG.SYSTEM_ID 目标系统ID
     */
    @TablePk(index = 1)
    private String transactionId;

    /**
     * This field is PARA_NAMESPACE_ORG.TRANSACTION_ID 表名/交易ID
     */
    @TablePk(index = 2)
    private String systemId;


    /**
     * This field is PARA_NAMESPACE_ORG.IS_TBNAME 是否表名：Y;N
     */
    private String isTbname;

    /**
     * This field is PARA_NAMESPACE_ORG.TRANSACTION_DESC 交易中文描述
     */
    private String transactionDesc;

    /**
     * This field is PARA_NAMESPACE_ORG.MODULE_ID 模块代码
     */
    private String moduleId;

    /**
     * This field is PARA_NAMESPACE_ORG.JSP_URL 比较差异数据,编辑数据的jsp链接
     */
    private String jspUrl;

    /**
     * This field is PARA_NAMESPACE_ORG.BAND_ENTERINGCHECK 录入和复核是否可以是同一个人:Y;N
     */
    private String bandEnteringcheck;

    /**
     * This field is PARA_NAMESPACE_ORG.DELETE_AUTH 能否删除数据:Y;N
     */
    private String deleteAuth;

    /**
     * This field is PARA_NAMESPACE_ORG.NAMESPACE_NAME 命名空间名称
     */
    private String namespaceName;

    /**
     * This field is PARA_NAMESPACE_ORG.NAMESPACE_DESC 命名空间描述
     */
    private String namespaceDesc;

    /**
     * This field is PARA_NAMESPACE_ORG.JSP_VIEWURL
     */
    private String jspViewurl;

    /**
     * @return the value of  PARA_NAMESPACE_ORG.TRANSACTION_ID 表名/交易ID
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * @param transactionId the value for PARA_NAMESPACE_ORG.TRANSACTION_ID 表名/交易ID
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId == null ? null : transactionId.trim();
    }

    /**
     * @return the value of  PARA_NAMESPACE_ORG.IS_TBNAME 是否表名：Y;N
     */
    public String getIsTbname() {
        return isTbname;
    }

    /**
     * @param isTbname the value for PARA_NAMESPACE_ORG.IS_TBNAME 是否表名：Y;N
     */
    public void setIsTbname(String isTbname) {
        this.isTbname = isTbname == null ? null : isTbname.trim();
    }

    /**
     * @return the value of  PARA_NAMESPACE_ORG.TRANSACTION_DESC 交易中文描述
     */
    public String getTransactionDesc() {
        return transactionDesc;
    }

    /**
     * @param transactionDesc the value for PARA_NAMESPACE_ORG.TRANSACTION_DESC 交易中文描述
     */
    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc == null ? null : transactionDesc.trim();
    }

    /**
     * @return the value of  PARA_NAMESPACE_ORG.SYSTEM_ID 目标系统ID
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * @param systemId the value for PARA_NAMESPACE_ORG.SYSTEM_ID 目标系统ID
     */
    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }

    /**
     * @return the value of  PARA_NAMESPACE_ORG.MODULE_ID 模块代码
     */
    public String getModuleId() {
        return moduleId;
    }

    /**
     * @param moduleId the value for PARA_NAMESPACE_ORG.MODULE_ID 模块代码
     */
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId == null ? null : moduleId.trim();
    }

    /**
     * @return the value of  PARA_NAMESPACE_ORG.JSP_URL 比较差异数据,编辑数据的jsp链接
     */
    public String getJspUrl() {
        return jspUrl;
    }

    /**
     * @param jspUrl the value for PARA_NAMESPACE_ORG.JSP_URL 比较差异数据,编辑数据的jsp链接
     */
    public void setJspUrl(String jspUrl) {
        this.jspUrl = jspUrl == null ? null : jspUrl.trim();
    }

    /**
     * @return the value of  PARA_NAMESPACE_ORG.BAND_ENTERINGCHECK 录入和复核是否可以是同一个人:Y;N
     */
    public String getBandEnteringcheck() {
        return bandEnteringcheck;
    }

    /**
     * @param bandEnteringcheck the value for PARA_NAMESPACE_ORG.BAND_ENTERINGCHECK 录入和复核是否可以是同一个人:Y;N
     */
    public void setBandEnteringcheck(String bandEnteringcheck) {
        this.bandEnteringcheck = bandEnteringcheck == null ? null : bandEnteringcheck.trim();
    }

    /**
     * @return the value of  PARA_NAMESPACE_ORG.DELETE_AUTH 能否删除数据:Y;N
     */
    public String getDeleteAuth() {
        return deleteAuth;
    }

    /**
     * @param deleteAuth the value for PARA_NAMESPACE_ORG.DELETE_AUTH 能否删除数据:Y;N
     */
    public void setDeleteAuth(String deleteAuth) {
        this.deleteAuth = deleteAuth == null ? null : deleteAuth.trim();
    }

    /**
     * @return the value of  PARA_NAMESPACE_ORG.NAMESPACE_NAME 命名空间名称
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * @param namespaceName the value for PARA_NAMESPACE_ORG.NAMESPACE_NAME 命名空间名称
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName == null ? null : namespaceName.trim();
    }

    /**
     * @return the value of  PARA_NAMESPACE_ORG.NAMESPACE_DESC 命名空间描述
     */
    public String getNamespaceDesc() {
        return namespaceDesc;
    }

    /**
     * @param namespaceDesc the value for PARA_NAMESPACE_ORG.NAMESPACE_DESC 命名空间描述
     */
    public void setNamespaceDesc(String namespaceDesc) {
        this.namespaceDesc = namespaceDesc == null ? null : namespaceDesc.trim();
    }

    /**
     * @return the value of  PARA_NAMESPACE_ORG.JSP_VIEWURL
     */
    public String getJspViewurl() {
        return jspViewurl;
    }

    /**
     * @param jspViewurl the value for PARA_NAMESPACE_ORG.JSP_VIEWURL
     */
    public void setJspViewurl(String jspViewurl) {
        this.jspViewurl = jspViewurl == null ? null : jspViewurl.trim();
    }
}