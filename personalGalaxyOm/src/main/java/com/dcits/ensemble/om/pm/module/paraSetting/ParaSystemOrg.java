package com.dcits.ensemble.om.pm.module.paraSetting;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by maruie on 2016/03/30 16:57:46.
 */
public class ParaSystemOrg extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is PARA_SYSTEM_ORG.SYSTEM_ID 目标系统ID
     */
    @TablePk(index = 1)
    private String systemId;

    /**
     * This field is PARA_SYSTEM_ORG.SYSTEM_NAME 目标系统名称
     */
    private String systemName;

    /**
     * This field is PARA_SYSTEM_ORG.SYSTEM_DESC 目标系统描述
     */
    private String systemDesc;

    /**
     * @return the value of  PARA_SYSTEM_ORG.SYSTEM_ID 目标系统ID
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * @param systemId the value for PARA_SYSTEM_ORG.SYSTEM_ID 目标系统ID
     */
    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }

    /**
     * @return the value of  PARA_SYSTEM_ORG.SYSTEM_NAME 目标系统名称
     */
    public String getSystemName() {
        return systemName;
    }

    /**
     * @param systemName the value for PARA_SYSTEM_ORG.SYSTEM_NAME 目标系统名称
     */
    public void setSystemName(String systemName) {
        this.systemName = systemName == null ? null : systemName.trim();
    }

    /**
     * @return the value of  PARA_SYSTEM_ORG.SYSTEM_DESC 目标系统描述
     */
    public String getSystemDesc() {
        return systemDesc;
    }

    /**
     * @param systemDesc the value for PARA_SYSTEM_ORG.SYSTEM_DESC 目标系统描述
     */
    public void setSystemDesc(String systemDesc) {
        this.systemDesc = systemDesc == null ? null : systemDesc.trim();
    }
}