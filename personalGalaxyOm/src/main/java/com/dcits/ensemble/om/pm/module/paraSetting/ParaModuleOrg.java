package com.dcits.ensemble.om.pm.module.paraSetting;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by maruie on 2016/04/07 14:25:25.
 */
public class ParaModuleOrg extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is PARA_MODULE_ORG.MODULE_ID 模块代码
     */
    @TablePk(index = 1)
    private String moduleId;

    /**
     * This field is PARA_MODULE_ORG.SYSTEM_ID 目标系统ID
     */
    @TablePk(index = 2)
    private String systemId;

    /**
     * This field is PARA_MODULE_ORG.MODULE_NAME 目标模块名称
     */
    private String moduleName;

    /**
     * This field is PARA_MODULE_ORG.MODULE_DESC 目标模块描述
     */
    private String moduleDesc;

    /**
     * @return the value of  PARA_MODULE_ORG.MODULE_ID 模块代码
     */
    public String getModuleId() {
        return moduleId;
    }

    /**
     * @param moduleId the value for PARA_MODULE_ORG.MODULE_ID 模块代码
     */
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId == null ? null : moduleId.trim();
    }

    /**
     * @return the value of  PARA_MODULE_ORG.SYSTEM_ID 目标系统ID
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * @param systemId the value for PARA_MODULE_ORG.SYSTEM_ID 目标系统ID
     */
    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }

    /**
     * @return the value of  PARA_MODULE_ORG.MODULE_NAME 目标模块名称
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * @param moduleName the value for PARA_MODULE_ORG.MODULE_NAME 目标模块名称
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    /**
     * @return the value of  PARA_MODULE_ORG.MODULE_DESC 目标模块描述
     */
    public String getModuleDesc() {
        return moduleDesc;
    }

    /**
     * @param moduleDesc the value for PARA_MODULE_ORG.MODULE_DESC 目标模块描述
     */
    public void setModuleDesc(String moduleDesc) {
        this.moduleDesc = moduleDesc == null ? null : moduleDesc.trim();
    }
}