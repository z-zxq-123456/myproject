package com.dcits.ensemble.om.oms.module.middleware;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by oms on 2016/02/16 10:48:53.
 */
public class EcmMidwareVer extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;
    
    /**
     * This field is ECM_MIDWARE_VER.MIDWARE_ID 
     */
    private MultipartFile sourceFile;
    
    private String midwareTypeName;
    
    private String userName;
    

    /**
     * This field is ECM_MIDWARE_VER.MIDWARE_VER_ID 
     */
    @TablePk(index = 1)
    private Integer midwareVerId;

    /**
     * This field is ECM_MIDWARE_VER.MIDWARE_TYPE 
     */
    private String midwareType;
    private String midwareName;

    /**
     * This field is ECM_MIDWARE_VER.MIDWARE_VER_NO 
     */
    private String midwareVerNo;

    /**
     * This field is ECM_MIDWARE_VER.MIDWARE_VER_PATH 
     */
    private String midwareVerPath;

    /**
     * This field is ECM_MIDWARE_VER.MIDWARE_VER_DATE 
     */
    private String midwareVerDate;

    /**
     * This field is ECM_MIDWARE_VER.MIDWARE_VER_USERID 
     */
    private String midwareVerUserid;

    /**
     * This field is ECM_MIDWARE_VER.MIDWARE_VER_DESC 
     */
    private String midwareVerDesc;

    /**
     * @return the value of  ECM_MIDWARE_VER.MIDWARE_VER_ID 
     */
    public Integer getMidwareVerId() {
        return midwareVerId;
    }

    /**
     * @param midwareVerId the value for ECM_MIDWARE_VER.MIDWARE_VER_ID 
     */
    public void setMidwareVerId(Integer midwareVerId) {
        this.midwareVerId = midwareVerId;
    }

    /**
     * @return the value of  ECM_MIDWARE_VER.MIDWARE_TYPE 
     */
    public String getMidwareType() {
        return midwareType;
    }

    /**
     * @param midwareType the value for ECM_MIDWARE_VER.MIDWARE_TYPE 
     */
    public void setMidwareType(String midwareType) {
        this.midwareType = midwareType == null ? null : midwareType.trim();
    }

    
    /**
     * @return the value of  ECM_MIDWARE_VER.MIDWARE_VER_PATH 
     */
    public String getMidwareVerPath() {
        return midwareVerPath;
    }

    /**
     * @param midwareVerPath the value for ECM_MIDWARE_VER.MIDWARE_VER_PATH 
     */
    public void setMidwareVerPath(String midwareVerPath) {
        this.midwareVerPath = midwareVerPath == null ? null : midwareVerPath.trim();
    }

    /**
     * @return the value of  ECM_MIDWARE_VER.MIDWARE_VER_DATE 
     */
    public String getMidwareVerDate() {
        return midwareVerDate;
    }

    /**
     * @param midwareVerDate the value for ECM_MIDWARE_VER.MIDWARE_VER_DATE 
     */
    public void setMidwareVerDate(String midwareVerDate) {
        this.midwareVerDate = midwareVerDate == null ? null : midwareVerDate.trim();
    }

    /**
     * @return the value of  ECM_MIDWARE_VER.MIDWARE_VER_USERID 
     */
    public String getMidwareVerUserid() {
        return midwareVerUserid;
    }

    /**
     * @param midwareVerUserid the value for ECM_MIDWARE_VER.MIDWARE_VER_USERID 
     */
    public void setMidwareVerUserid(String midwareVerUserid) {
        this.midwareVerUserid = midwareVerUserid;
    }

    /**
     * @return the value of  ECM_MIDWARE_VER.MIDWARE_VER_DESC 
     */
    public String getMidwareVerDesc() {
        return midwareVerDesc;
    }

    /**
     * @param midwareVerDesc the value for ECM_MIDWARE_VER.MIDWARE_VER_DESC 
     */
    public void setMidwareVerDesc(String midwareVerDesc) {
        this.midwareVerDesc = midwareVerDesc == null ? null : midwareVerDesc.trim();
    }


	public String getMidwareName() {
		return midwareName;
	}

	public void setMidwareName(String midwareName) {
		this.midwareName = midwareName;
	}

	public MultipartFile getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(MultipartFile sourceFile) {
		this.sourceFile = sourceFile;
	}

	public String getMidwareTypeName() {
		return midwareTypeName;
	}

	public void setMidwareTypeName(String midwareTypeName) {
		this.midwareTypeName = midwareTypeName;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the midwareVerNo
	 */
	public String getMidwareVerNo() {
		return midwareVerNo;
	}

	/**
	 * @param midwareVerNo the midwareVerNo to set
	 */
	public void setMidwareVerNo(String midwareVerNo) {
		this.midwareVerNo = midwareVerNo;
	}

	

	
}