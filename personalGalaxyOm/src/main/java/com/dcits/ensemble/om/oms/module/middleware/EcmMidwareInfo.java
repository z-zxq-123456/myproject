package com.dcits.ensemble.om.oms.module.middleware;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by oms on 2016/02/16 10:54:34.
 */
public class EcmMidwareInfo extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_MIDWARE_INFO.MIDWARE_ID 
     */
    @TablePk(index = 1)
    private Integer midwareId;

    /**
     * This field is ECM_MIDWARE_INFO.MIDWARE_VER_ID 
     */
    private Integer midwareVerId;

    /**
     * This field is ECM_MIDWARE_INFO.MIDWARE_NAME 
     */
    private String midwareName;
    
    /**
     * This field is ECM_MIDWARE_INFO.MIDWARE_TYPE 
     */
    private String midwareType;
    
    private String midwareTypeName;
    
    private String midwareVerNo;

    /**
     * This field is ECM_MIDWARE_INFO.MIDWARE_PATH 
     */
    private String midwarePath;

    /**
     * This field is ECM_MIDWARE_INFO.MIDWARE_WORK 
     */
    private String isDefault;

    /**
     * This field is ECM_MIDWARE_INFO.KFK_ZKS_ID
     */
    private String kfkZksId;

    private String kfkZksIdName;
    
    private String isDefaultParaName;



    private boolean selected = false;
    /**
     * @return the value of  ECM_MIDWARE_INFO.MIDWARE_ID 
     */
    public Integer getMidwareId() {
        return midwareId;
    }

    /**
     * @param midwareId the value for ECM_MIDWARE_INFO.MIDWARE_ID 
     */
    public void setMidwareId(Integer midwareId) {
        this.midwareId = midwareId;
    }

    /**
     * @return the value of  ECM_MIDWARE_INFO.MIDWARE_VER_ID 
     */
    public Integer getMidwareVerId() {
        return midwareVerId;
    }

    /**
     * @param midwareVerId the value for ECM_MIDWARE_INFO.MIDWARE_VER_ID 
     */
    public void setMidwareVerId(Integer midwareVerId) {
        this.midwareVerId = midwareVerId;
    }

    /**
     * @return the value of  ECM_MIDWARE_INFO.MIDWARE_NAME 
     */
    public String getMidwareName() {
        return midwareName;
    }

    /**
     * @param midwareName the value for ECM_MIDWARE_INFO.MIDWARE_NAME 
     */
    public void setMidwareName(String midwareName) {
        this.midwareName = midwareName == null ? null : midwareName.trim();
    }

    /**
     * @return the value of  ECM_MIDWARE_INFO.MIDWARE_TYPE 
     */
    public String getMidwareType() {
        return midwareType;
    }

    /**
     * @param midwareType the value for ECM_MIDWARE_INFO.MIDWARE_TYPE 
     */
    public void setMidwareType(String midwareType) {
        this.midwareType = midwareType == null ? null : midwareType.trim();
    }

    /**
     * @return the value of  ECM_MIDWARE_INFO.MIDWARE_PATH 
     */
    public String getMidwarePath() {
        return midwarePath;
    }

    /**
     * @param midwarePath the value for ECM_MIDWARE_INFO.MIDWARE_PATH 
     */
    public void setMidwarePath(String midwarePath) {
        this.midwarePath = midwarePath == null ? null : midwarePath.trim();
    }

   

	public String getMidwareTypeName() {
		return midwareTypeName;
	}

	public void setMidwareTypeName(String midwareTypeName) {
		this.midwareTypeName = midwareTypeName;
	}

	public String getMidwareVerNo() {
		return midwareVerNo;
	}

	public void setMidwareVerNo(String midwareVerNo) {
		this.midwareVerNo = midwareVerNo;
	}

	/**
	 * @return the isDefault
	 */
	public String getIsDefault() {
		return isDefault;
	}

	/**
	 * @param isDefault the isDefault to set
	 */
	public void setIsDefault(String isDefault) {
		 this.isDefault = isDefault == null ? null : isDefault.trim();
	}

	/**
	 * @return the isDefaultParaName
	 */
	public String getIsDefaultParaName() {
		return isDefaultParaName;
	}

	/**
	 * @param isDefaultParaName the isDefaultParaName to set
	 */
	public void setIsDefaultParaName(String isDefaultParaName) {
		this.isDefaultParaName = isDefaultParaName;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

    public String getKfkZksId() {
        return kfkZksId;
    }

    public void setKfkZksId(String kfkZksId) {
        this.kfkZksId = kfkZksId;
    }

    public String getKfkZksIdName() {
        return kfkZksIdName;
    }

    public void setKfkZksIdName(String kfkZksIdName) {
        this.kfkZksIdName = kfkZksIdName;
    }
}