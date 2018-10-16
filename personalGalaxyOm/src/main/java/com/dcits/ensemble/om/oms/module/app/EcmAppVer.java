package com.dcits.ensemble.om.oms.module.app;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by tangxl on 2015/08/11 10:00:01.
 */
public class EcmAppVer extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_APP_VER.APP_VER_ID 
     */
    @TablePk(index = 1)
    private Integer appVerId;

    /**
     * This field is ECM_APP_VER.APP_ID 
     */
    private Integer appId;
    /**
     * This field is ECM_APP_VER.APP_VER_NUM 
     */
    private Integer appVerNum;
    /**
     * This field is ECM_APP_VER.APP_VER_DESC 
     */
    private String appVerDesc;
    /**
     * This field is ECM_APP_VER.APP_VER_DATE 
     */
    private String appVerDate;
    /**
     * This field is ECM_APP_VER.APP_VER_USERID 
     */
    private String appVerUserid;
    /**
     * This field is ECM_APP_VER.APP_VER_PATH 
     */
    private String appVerPath;    
    /**
     * This field is ECM_APP_VER.APP_VER_TYPE 
     */
    private String appVerType;
    
    private String userName;  
    
    private String  appSimpenNm;	
    
    private String  appName;	
    
    private String  appVerTypeName;

  

	private MultipartFile sourceFile;

	public MultipartFile getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(MultipartFile sourceFile) {
		this.sourceFile = sourceFile;
	}
	 
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	public String getAppSimpenNm() {
		return appSimpenNm;
	}

	public void setAppSimpenNm(String appSimpenNm) {
		this.appSimpenNm = appSimpenNm;
	}
	
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
     * @return the value of  ECM_APP_VER.APP_VER_ID 
     */
    public Integer getAppVerId() {
        return appVerId;
    }

    /**
     * @param appVerId the value for ECM_APP_VER.APP_VER_ID 
     */
    public void setAppVerId(Integer appVerId) {
        this.appVerId = appVerId;
    }
    /**
     * @return the value of  ECM_APP_VER.APP_ID 
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * @param appId the value for ECM_APP_VER.APP_ID 
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }
    /**
     * @return the value of  ECM_APP_VER.APP_VER_NUM 
     */
    public Integer getAppVerNum() {
        return appVerNum;
    }

    /**
     * @param appVerNum the value for ECM_APP_VER.APP_VER_NUM 
     */
    public void setAppVerNum(Integer appVerNum) {
        this.appVerNum = appVerNum;
    }
    /**
     * @return the value of  ECM_APP_VER.APP_VER_DESC 
     */
    public String getAppVerDesc() {
        return appVerDesc;
    }

    /**
     * @param appVerDesc the value for ECM_APP_VER.APP_VER_DESC 
     */
    public void setAppVerDesc(String appVerDesc) {
        this.appVerDesc = appVerDesc == null ? null : appVerDesc.trim();
    }
    /**
     * @return the value of  ECM_APP_VER.APP_VER_DATE 
     */
    public String getAppVerDate() {
        return appVerDate;
    }

    /**
     * @param appVerDate the value for ECM_APP_VER.APP_VER_DATE 
     */
    public void setAppVerDate(String appVerDate) {
        this.appVerDate = appVerDate == null ? null : appVerDate.trim();
    }
    /**
     * @return the value of  ECM_APP_VER.APP_VER_USERID 
     */
    public String getAppVerUserid() {
        return appVerUserid;
    }

    /**
     * @param appVerUserid the value for ECM_APP_VER.APP_VER_USERID 
     */
    public void setAppVerUserid(String appVerUserid) {
        this.appVerUserid = appVerUserid;
    }
    /**
     * @return the value of  ECM_APP_VER.APP_VER_PATH 
     */
    public String getAppVerPath() {
        return appVerPath;
    }

    /**
     * @param appVerPath the value for ECM_APP_VER.APP_VER_PATH 
     */
    public void setAppVerPath(String appVerPath) {
        this.appVerPath = appVerPath == null ? null : appVerPath.trim();
    }

	public String getAppVerType() {
		return appVerType;
	}

	public void setAppVerType(String appVerType) {
		this.appVerType = appVerType;
	}
	
	
	public String getAppVerTypeName() {
		return appVerTypeName;
	}

	public void setAppVerTypeName(String appVerTypeName) {
		this.appVerTypeName = appVerTypeName;
	}
    
    
}